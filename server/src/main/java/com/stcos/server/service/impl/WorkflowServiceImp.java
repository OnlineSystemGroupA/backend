package com.stcos.server.service.impl;

import com.stcos.server.config.security.UserDetailsImp;
import com.stcos.server.entity.dto.FileMetadataDto;
import com.stcos.server.entity.file.FileMetadata;
import com.stcos.server.entity.file.Sample;
import com.stcos.server.entity.form.Form;
import com.stcos.server.entity.form.FormIndex;
import com.stcos.server.exception.ServiceException;
import com.stcos.server.service.FileService;
import com.stcos.server.service.WorkflowService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class WorkflowServiceImp implements WorkflowService {
    private RuntimeService runtimeService;

    @Autowired
    public void setRuntimeService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    private TaskService taskService;

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    private FileService fileService;

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    private FormMapper formMapper;

    @Autowired
    public void setFormMapper(FormMapper formMapper) {
        this.formMapper = formMapper;
    }

    private FileMapper fileMapper;

    @Autowired
    public void setFileMapper(FileMapper fileMapper) { this.fileMapper = fileMapper; }

    @Override
    public void completeTask(String taskId) throws ServiceException {
        Task task = getTaskById(taskId);

        // 验证是否满足完成条件

        // 若满足完成条件则完成该任务
        taskService.complete(task.getId());
    }

    @Override
    public Task getTaskById(String taskId) throws ServiceException {
        List<Task> tasks = taskService.createTaskQuery().taskId(taskId).list();
        UserDetailsImp userDetails = (UserDetailsImp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (tasks == null) //没有对应Id的task
            throw new ServiceException(1);
        Task task = tasks.get(0);
        if (!task.getAssignee().equals(userDetails.getUid())) { //当前用户不是被分配到的用户（即不可见）
            throw new ServiceException(0);
        }
        return task;
    }

    @Override
    public List<Task> getTasks() throws ServiceException {
        UserDetailsImp userDetails = (UserDetailsImp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return taskService.createTaskQuery().taskAssignee(userDetails.getUid()).list();
    }

    @Override
    public Form getForm(String processInstanceId, String formType) throws ServiceException {
        // 判断 processId 对应的流程是否存在，并获取表单索引
        FormIndex formIndex = getFormIndex(processInstanceId, formType);

        // 获取当前登录用户，和当前文件的可读用户列表
        String userId = ((UserDetailsImp) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUid();
        List<String> readableUsers = formIndex.getReadableUsers();

        Form form;
        // 判断当前登录用户是否具有读取权限
        if (readableUsers != null && readableUsers.contains(userId)) {
            // 获取表单数据
            form = formIndex.getForm();
        } else {
            throw new ServiceException(1); // 无读取权限的异常
        }

        return form;
    }

    @Override
    public void updateForm(String processInstanceId, String formType, String formData) throws ServiceException {
        // 判断 processId 对应的流程是否存在，并获取表单索引
        FormIndex formIndex = getFormIndex(processInstanceId, formType);

        // 获取当前登录用户，和当前表单的可写用户列表
        String userId = ((UserDetailsImp) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUid();
        List<String> writableUsers = formIndex.getWritableUsers();

        // 根据表单类型把 JSON 格式转为相应的表单对象
        Form form = Form.buildForm(formType, formData);
        assert form != null;

        // 判断当前登录用户是否具有修改权限
        if (writableUsers != null && writableUsers.contains(userId)) {
            if(formIndex.getFormIndexId() == null) {
                // 保存表单  Todo: FormMapper
                formMapper.saveForm(form);

                // 初始化表单索引
                formIndex = new FormIndex(form.getFormId(), formType, userId, LocalDateTime.now(), userId, LocalDateTime.now(), form);
            } else {
                // 更新表单索引
                formIndex.setLastModifiedBy(userId); // 设置最后修改人为当前用户
                formIndex.setLastModifiedDate(LocalDateTime.now()); // 设置最后修改时间为当前时间
                formIndex.setForm(form); // 设置更新后的文件
            }
        } else {
            throw new ServiceException(1); // 无修改权限的异常
        }

        // 保存表单索引  Todo: FormMapper
        formMapper.saveFormIndex(formIndex);
    }

    @Override
    public List<FileMetadataDto> uploadSample(String processInstanceId, List<MultipartFile> files) throws ServiceException {
        // 判断 processId 对应的流程是否存在，并获取样品对象
        Sample sample = getSample(processInstanceId);

        // 获取当前登录用户，和当前样品的可写用户列表
        String userId = ((UserDetailsImp) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUid();
        List<String> writableUsers = sample.getWritableUsers();

        List<FileMetadata> fileMetadataList = new ArrayList<>();

        // 判断当前登录用户是否具有上传权限
        if (writableUsers != null && writableUsers.contains(userId)) {
            try {
                // 上传样品文件
                fileMetadataList = fileService.uploadSample(processInstanceId, userId, files);
            } catch (ServiceException e) {
                switch (e.getCode()) {
                    case 0 -> throw new ServiceException(2); // 没有上传文件的异常
                    case 1 -> throw new ServiceException(3); // 存储空间不足的异常
                    case 2 -> throw new ServiceException(4); // 文件上传失败的异常
                }
            }

            // 添加新文件元数据
            sample.mergeFileMetadataList(fileMetadataList);

            // 保存样品对象
            fileMapper.saveSample(sample);
        } else {
            throw new ServiceException(1); // 无上传权限的异常
        }

        // 返回样品文件摘要
        return fileMetadataList.stream()
                .map(FileMetadataDto::new)
                .toList();
    }

    @Override
    public List<File> downloadSample(String processInstanceId) throws ServiceException {
        // 判断 processId 对应的流程是否存在，并获取样品对象
        Sample sample = getSample(processInstanceId);

        // 获取当前登录用户，和当前样品的可读用户列表
        String userId = ((UserDetailsImp) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUid();
        List<String> readableUsers = sample.getReadableUsers();

        List<File> files = new ArrayList<>();

        // 判断当前登录用户是否具有上传权限
        if (readableUsers != null && readableUsers.contains(userId)) {
            // 下载样品文件
            try {
                files = fileService.downloadSample(sample.getFileMetadataList());
            } catch (ServiceException e) {
                switch (e.getCode()) {
                    case 0 -> throw new ServiceException(2); // 文件不存在的异常
                    case 1 -> throw new ServiceException(3); // 文件下载失败的异常
                }
            }
        } else {
            throw new ServiceException(1); // 下载权限的异常
        }

        return files;
    }

    @Override
    public void deleteFiles(String processInstanceId) throws ServiceException {
        // 判断 processId 对应的流程是否存在，并获取样品对象
        Sample sample = getSample(processInstanceId);

        // 获取当前登录用户，和当前样品的可写用户列表
        String userId = ((UserDetailsImp) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUid();
        List<String> writableUsers = sample.getWritableUsers();

        // 判断当前登录用户是否具有删除权限
        if (writableUsers != null && writableUsers.contains(userId)) {
            try {
                fileService.deleteFiles(sample.getFileMetadataList());
            } catch (ServiceException e) {
                switch (e.getCode()) {
                    case 0 -> throw new ServiceException(2); // 文件不存在的异常
                    case 1 -> throw new ServiceException(3); // 文件删除失败的异常
                }
            }

            // 删除样品对象
            fileMapper.deleteBySampleId(sample.getSampleId());
        } else {
            throw new ServiceException(1); // 无删除权限的异常
        }
    }

    private FormIndex getFormIndex(String processInstanceId, String formType) throws ServiceException {
        // 判断 processId 对应的流程是否存在
        ProcessInstance processInstance = runtimeService.
                createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if (processInstance == null) {
            throw new ServiceException(0); // 流程不存在的异常
        }

        // 获取表单索引 ID
        Long formIndexId = (Long) runtimeService.getVariable(processInstanceId, formType);

        // 根据表单索引 ID 查询数据库
        return formMapper.selectByFormIndexId(formIndexId);
    }

    private Sample getSample(String processInstanceId) throws ServiceException {
        // 判断 processId 对应的流程是否存在
        ProcessInstance processInstance = runtimeService.
                createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if (processInstance == null) {
            throw new ServiceException(0); // 流程不存在的异常
        }

        // 获取样品 ID
        Long sampleId = (Long) runtimeService.getVariable(processInstanceId, "sample");

        // 获取样品对象
        return fileMapper.selectBySampleId(sampleId);
    }

    @Override
    public String startProcess() throws ServiceException {
        // 获取当前登录用户，使用其 id 设置任务发起人
        String userId = ((UserDetailsImp) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUid();

        // 初始化流程变量，创建


        // TODO 将以下创建 map 的方法注释
        Map<String, Object> map = new HashMap<>() {{
            put("client", userId);
            put("admin", "op-1");
            put("passed", true);
        }};

        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey("workflow", map);

        return processInstance.getProcessInstanceId();
    }

}
