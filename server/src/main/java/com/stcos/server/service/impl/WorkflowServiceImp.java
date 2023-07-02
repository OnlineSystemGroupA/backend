package com.stcos.server.service.impl;

import com.stcos.server.entity.user.User;
import com.stcos.server.entity.dto.FileMetadataDto;
import com.stcos.server.entity.form.Form;
import com.stcos.server.entity.form.FormMetadata;
import com.stcos.server.entity.process.ProcessVariables;
import com.stcos.server.exception.ServiceException;
import com.stcos.server.service.*;
import com.stcos.server.util.TaskUtil;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;
import java.io.File;

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

    private OperatorService operatorService;

    @Autowired
    public void setOperatorService(OperatorService operatorService) {
        this.operatorService = operatorService;
    }

    private SettingService settingService;

    @Autowired
    public void setSettingService(SettingService settingService) {
        this.settingService = settingService;
    }


    private ProcessRecordService processRecordService;

    @Autowired
    public void setProcessRecordService(ProcessRecordService processRecordService) {
        this.processRecordService = processRecordService;
    }

    @Override
    public void completeTask(String processId, String taskId, Boolean passable) throws ServiceException {
        Task task = getTaskById(taskId);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!task.getAssignee().equals(user.getUid())) { //当前用户不是被分配到的用户（即不可见）
            throw new ServiceException(0);
        }

        if (TaskUtil.isCompletable(task, formService)) {
            taskService.complete(task.getId());
            task.getProcessVariables().replace("passable", passable);
        }

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

    private final Map<String, Comparator<ProcessInstance>> comparatorMap = new HashMap<>() {{
        put("recordId", Comparator.comparing(a -> ((Long) a.getProcessVariables().get("recordId"))));

        put("title", Comparator.comparing(a -> ((String) a.getProcessVariables().get("title"))));
        put("startDate", Comparator.comparing(a -> ((LocalDateTime) a.getProcessVariables().get("startDate"))));
        put("assignee", Comparator.comparing(a -> ((LocalDateTime) a.getProcessVariables().get("assignee"))));
        put("currentTask", Comparator.comparing(a -> ((String) a.getProcessVariables().get("currentTask"))));
    }};

    @Override
    public List<Task> getTasks(int pageIndex, int numPerPage, String orderBy) throws ServiceException {

        List<Task> taskList = new ArrayList<>();

        // 判断待排序的键是否有效
        if (!comparatorMap.containsKey(orderBy)) throw new ServiceException(0);

        // 查找当前登录用户可见的流程实例
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<String> processInstanceSet = user.getProcessInstances();
        if (processInstanceSet.isEmpty()) return taskList;
        List<ProcessInstance> res = runtimeService.createProcessInstanceQuery()
                .processInstanceIds(processInstanceSet)
                .includeProcessVariables()
                .list();

        // 根据对应字段对流程实例进行排序，并截取部分内容
        res.sort(comparatorMap.get(orderBy));


        // 生成索引
        int beginIndex = numPerPage * (pageIndex - 1);
        int toIndex = beginIndex + numPerPage;
        if (beginIndex >= res.size()) return taskList;
        if (toIndex >= res.size()) toIndex = res.size();

        res = res.subList(beginIndex, toIndex);

        // 针对每个流程实例找到对应正在活跃的任务
        for (ProcessInstance processInstance : res) {
            Task task = taskService.createTaskQuery()
                    .processInstanceId(processInstance.getProcessInstanceId())
                    .active().includeProcessVariables()
                    .singleResult();
            taskList.add(task);
        }

        return taskList;
    }

    @Override
    public Form getForm(String processId, String formName) throws ServiceException {
        // 判断 processId 对应的流程是否存在，并获取表单元数据 ID
        Long formMetadataId = getFormMetadataId(processId, formName);

        // 调用 FormService 接口，返回表单对象
        return formService.getForm(formMetadataId);
    }

    @Override
    public void updateForm(String processId, String formType, Form form) throws ServiceException {
        // 判断 processId 对应的流程是否存在，并获取表单元数据 ID
        Long formMetadataId = getFormMetadataId(processId, formType);

        // 调用 FormService 接口，完成表单更新
        formService.saveOrUpdateForm(formMetadataId, form);
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
                createProcessInstanceQuery().processInstanceId(processId).includeProcessVariables().singleResult();
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


    @Secured("ROLE_CLIENT") // 限制只有客户可以发起流程
    @Override
    public String startProcess() throws ServiceException {
        // 获取当前登录用户，使用其 id 设置任务发起人
        User client = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 查找当前平台上的各主管以及授权签字人信息
        String marketingManagerId = settingService.getMarketingManager();
        String testingManagerId = settingService.getTestingManager();
        String qualityManagerId = settingService.getQualityManager();
        String signatoryId = settingService.getSignatory();

        // 创建流程记录对象，并获取其 ID
        Long recordId = processRecordService.create();

        // 初始化流程变量，创建 ProcessVariables 对象
        Map<String, Object> processVariables = new ProcessVariables(client.getUid(), client.getRealName(), recordId,
                marketingManagerId, testingManagerId, qualityManagerId, signatoryId);

        // 使用 ProcessVariables 对象创建新流程实例
        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey("workfloww", processVariables);
        return processInstance.getProcessInstanceId();
    }

    @Override
    public List<FormMetadata> getFormMetadata(String processId) throws ServiceException {
        return null;
    }

    @Override
    public int getProcessCount() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getProcessesCount();
    }


}
