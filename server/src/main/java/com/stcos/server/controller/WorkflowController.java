package com.stcos.server.controller;

import com.stcos.server.controller.api.WorkflowApi;
import com.stcos.server.entity.dto.FileIndexDto;
import com.stcos.server.entity.dto.FormMetadataDto;
import com.stcos.server.entity.dto.ProcessDto;
import com.stcos.server.entity.dto.ProcessIdDto;
import com.stcos.server.entity.form.Form;
import com.stcos.server.entity.form.FormIndex;
import com.stcos.server.exception.ServerErrorException;
import com.stcos.server.exception.ServiceException;
import com.stcos.server.service.WorkflowService;
import com.stcos.server.util.JSONUtil;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/5 20:45
 */
@RestController
public class WorkflowController implements WorkflowApi {

    private WorkflowService workflowService;

    @Autowired
    public void setWorkflowService(WorkflowService service) {
        this.workflowService = service;
    }

    @Override
    public ResponseEntity<ProcessIdDto> startProcess() {
        String ProcessId;
        try {
            ProcessId = workflowService.startProcess(); // 调用服务层方法开启新的流程
        } catch (ServiceException e) {
            throw new ServerErrorException(e);  // 触发意外异常
        }
        return ResponseEntity.ok(new ProcessIdDto(ProcessId)); // 成功启动流程，返回流程实例 ID
    }

    @Override
    public ResponseEntity<Void> completeTask(String processId, String taskId, Boolean passable) {
        ResponseEntity<Void> response = null;
        try {
            workflowService.completeTask(processId, taskId, passable);
        } catch (ServiceException e) {
            switch (e.getCode()) {
                case 0 -> response = ResponseEntity.status(403).build(); // 指定任务对该用户不可见或当前用户无完成任务权限
                case 1 -> response = ResponseEntity.status(404).build(); // 指定任务或流程不存在
            }
        }
        if (response == null) { // 未接收到下层传入的Exception
            response = ResponseEntity.ok().build(); // 成功完成指定任务
        }
        return response;

    }

    @Override
    public ResponseEntity<Void> putForm(String processId, String formName, String formData) {
        ResponseEntity<Void> response = null;
        Form form = Form.buildForm(formName, formData);
        if (form == null) {
            return ResponseEntity.badRequest().build();     // 表单名称不合法
        }
        try {
            workflowService.updateForm(processId, formName, form);
            response = ResponseEntity.ok().build();
        } catch (ServiceException e) {
            switch (e.getCode()) {
                case 0 -> response = ResponseEntity.status(403).build(); // 该流程实例对当前用户不可见或当前用户无修改权限
                case 1 -> response = ResponseEntity.status(404).build(); // 指定流程实例不存在
                default -> ResponseEntity.internalServerError();
            }
        }
        return response;
    }

    @Override
    public ResponseEntity<String> getForm(String processId, String formName) {
        ResponseEntity<String> response = null;
        try {
            Form form = workflowService.getForm(processId, formName);
            String formData = JSONUtil.toJSONString(form);
            response = ResponseEntity.ok(formData);
        } catch (ServiceException e) {
            switch (e.getCode()) {
                case 0 -> response = ResponseEntity.status(403).build();   // 指定流程或表单对该用户不可见
                case 1 -> response = ResponseEntity.status(404).build();   // 指定流程或表单不存在
                default -> ResponseEntity.internalServerError();
            }
        }
        return response;
    }

    @Override
    public ResponseEntity<List<FormMetadataDto>> getFormMetadata(String processId) {
        ResponseEntity<List<FormMetadataDto>> response = null;
        try {
            List<FormIndex> formIndexList = workflowService.getFormMetadata(processId);
            List<FormMetadataDto> formMetadataDtoList = new ArrayList<>(formIndexList.size());
            for (FormIndex formIndex : formIndexList) {
                formMetadataDtoList.add(
                        new FormMetadataDto(formIndex.getFormIndexId(), formIndex.getFormName())
                );
            }
            response = ResponseEntity.ok(formMetadataDtoList);
        } catch (ServiceException e) {
            switch (e.getCode()) {
                case 0 -> response = ResponseEntity.status(403).build();   // 指定流程或表单对该用户不可见
                case 1 -> response = ResponseEntity.status(404).build();   // 指定流程或表单不存在
                default -> ResponseEntity.internalServerError();
            }
        }
        return response;
    }

    @Override
    public ResponseEntity<List<MultipartFile>> downloadSample(String processId) {
        ResponseEntity<List<MultipartFile>> response = null;
//        try {
//            List<FormIndex> formIndexList = workflowService.download;
//            List<FormMetadataDto> formMetadataDtoList = new ArrayList<>(formIndexList.size());
//            for (FormIndex formIndex : formIndexList) {
//                formMetadataDtoList.add(
//                        new FormMetadataDto(formIndex.getFormIndexId(), formIndex.getFormName())
//                );
//            }
//            response = ResponseEntity.ok(formMetadataDtoList);
//        } catch (ServiceException e) {
//            switch (e.getCode()) {
//                case 0 -> response = ResponseEntity.status(403).build();   // 指定流程或表单对该用户不可见
//                case 1 -> response = ResponseEntity.status(404).build();   // 指定流程或表单不存在
//                default -> ResponseEntity.internalServerError();
//            }
//        }
        return response;
    }


    @Override
    public ResponseEntity<Void> getProcessDetails(String processId) {
        Task task = null;
//        ResponseEntity<TaskDto> response = null;
//        try {
//            task = service.getTaskById(taskId);
//        } catch (ServiceException e) {
//            switch (e.getCode()) {
//                case 0 -> response = ResponseEntity.status(403).build();
//                case 1 -> response = ResponseEntity.status(404).build();
//            }
//        }
//        if (response == null) { //未接收到下层传入的Exception
//            if (task == null || !task.getId().equals(taskId)) throw new RuntimeException("Error At getTaskById");
//            response = ResponseEntity.ok
//                    (new TaskDto(task.getProcessInstanceId(), taskId, task.getName(), task.getDescription(), task.getOwner()));
//        }
//
        return null;
    }

    @Override
    public ResponseEntity<List<ProcessDto>> getProcesses() {
        return WorkflowApi.super.getProcesses();
    }


    @Override
    public ResponseEntity<List<FileIndexDto>> uploadSample(String processId, List<MultipartFile> files) {
        return WorkflowApi.super.uploadSample(processId, files);
    }

//    @Override
//    public ResponseEntity<Void> updateTaskItem(String processId, String itemName) {
//        ResponseEntity<Void> response = null;
//        try {
//            service.updateForm(processId, itemName, null);
//        } catch (ServiceException e) {
//            switch (e.getCode()) {
//                case 0 -> response = ResponseEntity.status(201).build();
//                case 1 -> response = ResponseEntity.status(403).build();
//                case 2 -> response = ResponseEntity.status(404).build();
//            }
//        }
//        if (response == null)
//            response = ResponseEntity.ok().build();
//        return response;
//    }
}

/*
    问题：
    1.get/updateTaskItem中，为何传入参数是processId而非taskId？
    2.startUserId是否是Owner
    3.用户对资源是否有权限
 */