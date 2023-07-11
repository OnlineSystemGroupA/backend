package com.stcos.server.service.impl;

import com.stcos.server.entity.file.FileMetadata;
import com.stcos.server.entity.form.ContractForm;
import com.stcos.server.entity.process.ProcessDetails;
import com.stcos.server.entity.user.Operator;
import com.stcos.server.entity.user.User;
import com.stcos.server.entity.form.Form;
import com.stcos.server.entity.form.FormMetadata;
import com.stcos.server.util.ProcessVariablesBuilder;
import com.stcos.server.exception.ServerErrorException;
import com.stcos.server.exception.ServiceException;
import com.stcos.server.service.*;
import com.stcos.server.util.FormUtil;
import com.stcos.server.util.TaskUtil;
import com.stcos.server.util.WordAndPdfUtil;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
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
    public void completeTask(String processId, Boolean passable) throws ServiceException {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!user.hasProcessInstance(processId)) throw new ServiceException(0); // 指定流程实例对当前用户不可见
        Task task = taskService.createTaskQuery().processInstanceId(processId).active().includeProcessVariables().singleResult();
        if (task == null) throw new ServiceException(1);                        // 找不到活跃的任务
        if (!task.getAssignee().equals(user.getUid())) throw new ServiceException(0); //当前用户不是被分配到的用户（即不可见）

        if (TaskUtil.isCompletable(task, formService)) {
            runtimeService.setVariable(processId, "passable", passable);
            taskService.complete(task.getId());
        }

    }

    @Override
    public Task getTaskById(String taskId) throws ServiceException {
//        List<Task> tasks = taskService.createTaskQuery().taskId(taskId).list();
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (tasks == null) //没有对应Id的task
//            throw new ServiceException(1);
//        Task task = tasks.get(0);
//        if (!task.getAssignee().equals(user.getUid())) { //当前用户不是被分配到的用户（即不可见）
//            throw new ServiceException(0);
//        }
        return taskService.createTaskQuery().processInstanceId(taskId).active().singleResult();
    }

    private final Map<String, Comparator<ProcessInstance>> comparatorMap = new HashMap<>() {{
        put("projectId", Comparator.comparing(a -> ((Long) a.getProcessVariables().get("projectId"))));

        put("title", Comparator.comparing(a -> ((String) a.getProcessVariables().get("title"))));
        put("startDate", Comparator.comparing(a -> ((LocalDateTime) a.getProcessVariables().get("startDate"))));
        put("assignee", Comparator.comparing(a -> ((LocalDateTime) a.getProcessVariables().get("assignee"))));
        put("currentTask", Comparator.comparing(a -> ((String) a.getProcessVariables().get("currentTask"))));
    }};

    @Override
    public List<Task> getTasks(int pageIndex, int numPerPage, String orderBy, Boolean assigned) throws ServiceException {

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
            if (assigned != null && assigned && !Objects.equals(task.getAssignee(), user.getUid())) continue;
            taskList.add(task);
        }

        return taskList;
    }

    @Override
    public Form getForm(String processId, String formName) throws ServiceException {
        // 判断 processId 对应的流程是否存在，并获取表单元数据 ID
        Long formMetadataId = getFormMetadataId(processId, formName);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 调用 FormService 接口，返回表单对象
        return formService.getForm(formMetadataId, user.getUid());
    }

    @Override
    public void updateForm(String processId, String formType, Form form) throws ServiceException {
        // 判断 processId 对应的流程是否存在，并获取表单元数据 ID
        Long formMetadataId = getFormMetadataId(processId, formType);

        // 调用 FormService 接口，完成表单更新
        formService.saveOrUpdateForm(formMetadataId, form);
    }

    @Override
    public FileMetadata uploadSample(String processId, MultipartFile file) throws ServiceException {
        // 判断 processId 对应的流程是否存在，并获取样品元数据 ID
        Long sampleMetadataId = getSampleMetadataId(processId);

        // 调用 FileService 接口实现样品上传，返回样品文件摘要
        return fileService.uploadSample(processId, sampleMetadataId, file);
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
        return (Long) runtimeService.getVariable(processId, "sampleMetadata");
    }

    private ProcessDetailsService processDetailsService;

    @Autowired
    public void setProcessDetailsService(ProcessDetailsService processDetailsService) {
        this.processDetailsService = processDetailsService;
    }


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
        Map<String, Object> processVariables = ProcessVariablesBuilder.build(client.getUid(), client.getRealName(),
                marketingManagerId, testingManagerId, qualityManagerId, signatoryId,
                processDetailsService, formService, fileService);

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

    private OperatorService operatorService;

    @Autowired
    public void setOperatorService(OperatorService operatorService) {
        this.operatorService = operatorService;
    }

    @Override
    public void setParticipants(String processId, String userId) throws ServiceException {
        // 判断指定流程实例当前登录用户是否可见
        Operator user = (Operator) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.hasProcessInstance(processId)) throw new ServiceException(0); // 目标流程实例当前登录用户不可见
        // 检查目标用户是否存在
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();
        Operator operator = operatorService.getById(userId);
        if (processInstance == null || operator == null) throw new ServiceException(1); // 目标用户不存在
        String role;
        switch (user.getDepartment()) {
            case "测试部" -> role = "testingOperator";
            case "市场部" -> role = "marketingOperator";
            default -> throw new ServerErrorException(new RuntimeException());
        }
        // 设置指定的流程参与者
        runtimeService.setVariable(processId, role, userId);
    }

    @Override
    public ProcessDetails getProcessDetails(String processId) {

        // 首先判断是否可见


        Long projectId = (Long) runtimeService.getVariable(processId, "projectId");
        ProcessDetails processDetails = processDetailsService.getById(projectId);
        Task task = taskService.createTaskQuery().processInstanceId(processId).active().singleResult();
        return processDetails;
    }

    /* 获取 or 保存表单 PDF 文件 */
    @Override
    public Resource getFormFile(String processId, String formName) throws ServiceException {
        Long formMetadataId = getFormMetadataId(processId, formName);           // 判断 processId 对应的流程是否存在，并获取表单元数据 ID
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.hasProcessInstance(processId)) throw new ServiceException(0); // 指定流程实例对当前登录用户不可见
        Form form = formService.getForm(formMetadataId, user.getUid());         // 获取表单内容
        return fileService.generateFormPdf(processId, form, formName);          // 生成 PDF 文件并返回给前端

    }

    @Override
    public void saveFileForm(String processId, String formName, MultipartFile file) throws ServiceException {
        Long formMetadataId = getFormMetadataId(processId, formName);           // 判断 processId 对应的流程是否存在，并获取表单元数据 ID
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.hasProcessInstance(processId)) throw new ServiceException(0); //指定流程实例对当前登录用户不可见
        if (!formService.hasWritePermission(formMetadataId, user.getUid())) throw new ServiceException(1);
        fileService.saveFormPdf(processId, file, formName);
    }

    @Override
    public void deleteProcess(String processId) throws ServiceException {

    }
}
