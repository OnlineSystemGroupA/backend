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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 所有表单
     *
     * @return 所有表单以及是否对用户可见
     */
    private HashMap<String, Boolean> getAllForms() {
        return new HashMap<>() {{
            put("ApplicationForm", true);
            put("ApplicationVerifyForm", true); // 申请审核表
            put("TestFunctionForm", true);      // 测试功能表
            put("QuotationForm", true);      // 报价表
            put("DocumentReviewForm", true);    // 文档审核表
            put("TestPlanForm", false);          // 测试计划表
            put("TestPlanVerifyForm", false);      // 测试计划审核表
            put("TestRecordsForm", true);          // 测试记录表
            put("TestProblemForm", true);        // 测试问题表
            put("TestReportForm", true);        // 测试报告表
            put("ReportVerifyForm", false);      // 测试报告检查表
            put("TestWorkCheckForm", false);     // 测试检查表
        }};
    }

    private HashMap<String, Object> getProcessVariables(User startUser) {
        HashMap<String, Object> processVariables = new HashMap<>();

        HashMap<String, Boolean> allForms = getAllForms();
        for (Map.Entry<String, Boolean> entry : allForms.entrySet()) {
            String formName = entry.getKey();
            FormMetadata formMetadata = new FormMetadata();
            formMetadata.setFormName(formName);
            if (entry.getValue()) {
                formMetadata.addReadPermission(startUser.getUid());
            }
            formService.saveFormMetadata(formMetadata);
            processVariables.put(formName, formMetadata.getFormMetadataId());
        }
        return processVariables;
    }

    @Override
    public String startProcess() throws ServiceException {
        // 获取当前登录用户，使用其 id 设置任务发起人
        User client = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // 查找当前平台上的各主管以及授权签字人信息

        // 初始化流程变量，创建 ProcessVariables 对象
        Map<String, Object> processVariables = new ProcessVariables(client, null,
                null, null, null);

        // 使用 ProcessVariables 对象创建新流程实例
        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey("workfloww", processVariables);
        return processInstance.getProcessInstanceId();
    }

    @Override
    public List<FormMetadata> getFormMetadata(String processId) throws ServiceException {
        return null;
    }

}
