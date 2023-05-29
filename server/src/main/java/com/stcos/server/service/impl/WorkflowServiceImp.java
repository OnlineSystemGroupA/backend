package com.stcos.server.service.impl;

import com.stcos.server.config.security.User;
import com.stcos.server.entity.dto.FileMetadataDto;
import com.stcos.server.entity.file.Sample;
import com.stcos.server.entity.form.Form;
import com.stcos.server.entity.form.FormIndex;
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
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.io.File;
import java.util.Map;

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
        return taskService.createTaskQuery().taskAssignee(user.getUid()).list();
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
     * @return 所有表单以及是否对用户可见
     */
    private HashMap<String, Boolean> getAllForms(){
        return new HashMap<>(){{
           put("ApplicationForm", true);
            put("ApplicationVerifyForm",true); // 申请审核表
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

    private HashMap<String, Object> getProcessVariables(String startUserId){
        HashMap<String, Object> processVariables = new HashMap<>();
        processVariables.put("passable", true);              // 流程控制变量
        processVariables.put("description", null);           // 已完成的最后一个任务分配人的描述
        processVariables.put("client", startUserId);        // 流程发起人ID
        processVariables.put("marketingManager", null);
        processVariables.put("testingManager", null);
        processVariables.put("qualityManager", null);
        processVariables.put("signatory", null);
        processVariables.put("marketingOperator", null);
        processVariables.put("testingOperator", null);
        processVariables.put("startDate", new LocalDateTime()); // 流程启动时间
        processVariables.put("finishDate", null);            // 流程结束时间
        processVariables.put("state", "进行中");              // 流程状态
        processVariables.put("currentTask", "填写申请表");     // 当前正在进行的任务
        processVariables.put("Sample", null);                // 样品
        HashMap<String, Boolean> allForms = getAllForms();
        for (Map.Entry<String, Boolean> entry : allForms.entrySet()){
            String formName = entry.getKey();
            boolean userReadable = entry.getValue();
            FormIndex formIndex = new FormIndex(formName, userReadable, startUserId);
            processVariables.put(formName, formIndex);
            formService.saveFormIndex(formIndex);
        }
        return processVariables;
    }

    @Override
    public String startProcess() throws ServiceException {
        // 获取当前登录用户，使用其 id 设置任务发起人
        String userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUid();

        // 初始化流程变量，创建
        HashMap<String, Object> processVariables = getProcessVariables(userId);
        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey("workflow", processVariables);

        return processInstance.getProcessInstanceId();
    }

    @Override
    public List<FormMetadata> getFormMetadata(String processId) throws ServiceException {
        return null;
    }
}
