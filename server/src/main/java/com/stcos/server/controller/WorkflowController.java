package com.stcos.server.controller;

import com.stcos.server.controller.api.WorkflowApi;
import com.stcos.server.entity.dto.*;
import com.stcos.server.entity.form.Form;
import com.stcos.server.entity.form.FormMetadata;
import com.stcos.server.exception.ServerErrorException;
import com.stcos.server.exception.ServiceException;
import com.stcos.server.service.WorkflowService;
import com.stcos.server.util.JSONUtil;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            List<FormMetadata> formMetadataList = workflowService.getFormMetadata(processId);
            List<FormMetadataDto> formMetadataDtoList = new ArrayList<>(formMetadataList.size());
            for (FormMetadata formMetadata : formMetadataList) {
                formMetadataDtoList.add(
                        new FormMetadataDto(formMetadata.getFormMetadataId(), formMetadata.getFormName())
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
    public ResponseEntity<List<FileIndexDto>> uploadSample(String processId, List<MultipartFile> files) {
        ResponseEntity<List<FileIndexDto>> response = null;
        try {
            List<FileMetadataDto> fileMetadataDtoList = workflowService.uploadSample(processId, files);
            List<FileIndexDto> fileIndexDtoList = new ArrayList<>(fileMetadataDtoList.size());
            for (FileMetadataDto fileMetadataDto : fileMetadataDtoList) {
                fileIndexDtoList.add(
                        new FileIndexDto(fileMetadataDto.getFileMetadataId(),
                                fileMetadataDto.getFileName(),
                                fileMetadataDto.getFileType())
                );
            }
            response = ResponseEntity.ok(fileIndexDtoList);
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
    public ResponseEntity<Resource> downloadSample(String processId) {
        ResponseEntity<Resource> response = null;
        try {
            File zipFile = workflowService.downloadSample(processId);

            String filename = zipFile.getName();
            String desiredFilename = filename.substring(0, filename.lastIndexOf('_')) + ".zip";

            FileSystemResource resource = new FileSystemResource(zipFile);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", desiredFilename);

            response = ResponseEntity.ok().headers(headers).body(resource);

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
    public ResponseEntity<List<ProcessDto>> getProcesses(Integer pageIndex, Integer numPerPage, String orderBy) {
        List<Task> taskList;
        try {
            taskList = workflowService.getTasks(pageIndex, numPerPage, orderBy);
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }
        List<ProcessDto> processDtoList = new ArrayList<>(taskList.size());

        //  ProcessDto
        for (Task task : taskList) {
            Map<String, Object> variables = task.getProcessVariables();
            processDtoList.add(
                    new ProcessDto(variables.get("recordId").toString(),
                            task.getProcessInstanceId(),
                            task.getId(),
                            (String) variables.get("title"),
                            task.getName(),
                            (String) variables.get("assignee"),
                            (String) variables.get("startUser"),
                            ((LocalDateTime) variables.get("startDate")).toString()
                    )
            );
        }
        return ResponseEntity.ok(processDtoList);
    }

    @Override
    public ResponseEntity<Integer> getProcessCount() {
        return ResponseEntity.ok(workflowService.getProcessCount());
    }
}
