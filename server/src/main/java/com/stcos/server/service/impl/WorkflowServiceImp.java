package com.stcos.server.service.impl;

import com.stcos.server.config.security.User;
import com.stcos.server.entity.dto.FileMetadataDto;
import com.stcos.server.entity.form.Form;
import com.stcos.server.entity.form.FormMetadata;
import com.stcos.server.entity.process.ProcessVariable;
import com.stcos.server.exception.ServiceException;
import com.stcos.server.service.FileService;
import com.stcos.server.service.FormService;
import com.stcos.server.service.WorkflowService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.io.File;
import java.util.Set;

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

    private FormService formService;

    @Autowired
    public void setFormService(FormService formService) {
        this.formService = formService;
    }

    private FileService fileService;

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public void completeTask(String processId, String taskId, Boolean passable) throws ServiceException {
        Task task = getTaskById(taskId);

        // TODO 验证是否满足完成条件
        // 若满足完成条件则完成该任务
        taskService.complete(task.getId());
    }

    @Override
    public Task getTaskById(String taskId) throws ServiceException {
        List<Task> tasks = taskService.createTaskQuery().taskId(taskId).list();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (tasks == null) //没有对应Id的task
            throw new ServiceException(1);
        Task task = tasks.get(0);
        if (!task.getAssignee().equals(user.getUid())) { //当前用户不是被分配到的用户（即不可见）
            throw new ServiceException(0);
        }
        return task;
    }

    @Override
    public List<Task> getTasks() throws ServiceException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // 获取流程变量的坑！
        // 参考：https://blog.csdn.net/weixin_43861630/article/details/129056964
        Set<Task> taskSet = new HashSet<>(taskService.createTaskQuery().
                processVariableValueEquals("startUser", user.getUid()).
                includeProcessVariables().list());
        taskSet.addAll(taskService.createTaskQuery().taskAssignee(user.getUid()).includeProcessVariables().list());
        return taskSet.stream().toList();
    }

    @Override
    public Form getForm(String processId, String formType) throws ServiceException {
        // 判断 processId 对应的流程是否存在，并获取表单元数据 ID
        Long formMetadataId = getFormMetadataId(processId, formType);

        // 调用 FormService 接口，返回表单对象
        return formService.getForm(formMetadataId);
    }

    @Override
    public void updateForm(String processId, String formType, Form form) throws ServiceException {
        // 判断 processId 对应的流程是否存在，并获取表单元数据 ID
        Long formMetadataId = getFormMetadataId(processId, formType);

        // 调用 FormService 接口，完成表单更新
        formService.updateForm(formMetadataId, formType, form);
    }

    @Override
    public List<FileMetadataDto> uploadSample(String processId, List<MultipartFile> files) throws ServiceException {
        // 判断 processId 对应的流程是否存在，并获取样品元数据 ID
        Long sampleMetadataId = getSampleMetadataId(processId);

        // 调用 FileService 接口实现样品上传，返回样品文件摘要
        return fileService.uploadSample(processId, sampleMetadataId, files);
    }

    @Override
    public File downloadSample(String processId) throws ServiceException {
        // 判断 processId 对应的流程是否存在，并获取样品元数据 ID
        Long sampleMetadataId = getSampleMetadataId(processId);

        // 调用 FileService 接口实现样品下载，返回样品文件列表的压缩文件
        return fileService.downloadSample(processId, sampleMetadataId);
    }

    @Override
    public void deleteSample(String processId) throws ServiceException {
        // 判断 processId 对应的流程是否存在，并获取样品元数据 ID
        Long sampleMetadataId = getSampleMetadataId(processId);

        // 调用 FileService 接口实现样品删除
        fileService.deleteSample(sampleMetadataId);
    }

    private Long getFormMetadataId(String processId, String formType) throws ServiceException {
        // 判断 processId 对应的流程是否存在
        ProcessInstance processInstance = runtimeService.
                createProcessInstanceQuery().processInstanceId(processId).singleResult();
        if (processInstance == null) {
            throw new ServiceException(0); // 流程不存在的异常
        }

        // 获取表单元数据 ID
        return (Long) runtimeService.getVariable(processId, formType);
    }

    private Long getSampleMetadataId(String processId) throws ServiceException {
        // 判断 processId 对应的流程是否存在
        ProcessInstance processInstance = runtimeService.
                createProcessInstanceQuery().processInstanceId(processId).singleResult();
        if (processInstance == null) {
            throw new ServiceException(0); // 流程不存在的异常
        }

        // 获取样品元数据 ID
        return (Long) runtimeService.getVariable(processId, "sample");
    }

    @Override
    public String startProcess() throws ServiceException {
        // 获取当前登录用户，使用其 id 设置任务发起人
        String userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUid();

        // 初始化流程变量，创建
        ProcessVariable processVariable = new ProcessVariable(userId);
        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey("workflow", processVariable);

        return processInstance.getProcessInstanceId();
    }

    @Override
    public List<FormMetadata> getFormMetadata(String processId) throws ServiceException {
        return null;
    }
}
