package com.stcos.server.controller;

import com.stcos.server.controller.api.WorkflowApi;
import com.stcos.server.entity.dto.*;
import com.stcos.server.entity.file.FileMetadata;
import com.stcos.server.entity.form.Form;
import com.stcos.server.entity.form.FormMetadata;
import com.stcos.server.entity.process.ProcessDetails;
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
import org.springframework.security.access.annotation.Secured;
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

    /*   AUTO_WIRED BEGIN */
    private WorkflowService workflowService;

    @Autowired
    public void setWorkflowService(WorkflowService service) {
        this.workflowService = service;
    }
    /*   AUTO_WIRED END   */


    @Override
    @Secured("ROLE_CLIENT")                                     // 限制只有客户可以发起流程
    public ResponseEntity<ProcessIdDto> startProcess() {
        String ProcessId;
        try {
            ProcessId = workflowService.startProcess();         // 调用服务层方法开启新的流程
        } catch (ServiceException e) {
            throw new ServerErrorException(e);                  // 触发意外异常
        }
        return ResponseEntity.ok(new ProcessIdDto(ProcessId));  // 成功启动流程，返回流程实例 ID
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
    public ResponseEntity<Void> completeTask(String processId, Boolean passable) {
        ResponseEntity<Void> response = null;
        if (passable == null) passable = true;
        try {
            workflowService.completeTask(processId, passable);
        } catch (ServiceException e) {
            switch (e.getCode()) {
                case 0 -> response = ResponseEntity.status(403).build(); // 指定任务对该用户不可见或当前用户无完成任务权限
                case 1 -> response = ResponseEntity.status(404).build(); // 指定任务或流程不存在
                case 2 -> response = ResponseEntity.status(460).build(); // 指定任务未满足完成条件
            }
        }
        if (response == null) { // 未接收到下层传入的Exception
            response = ResponseEntity.ok().build(); // 成功完成指定任务
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
    public ResponseEntity<FileMetadataDto> uploadFileSample(String processId, MultipartFile file) {
        ResponseEntity<FileMetadataDto> response = null;
        try {
            FileMetadata fileMetadata = workflowService.uploadSample(processId, file);
            FileMetadataDto fileMetadataDto = new FileMetadataDto(fileMetadata.getFileMetadataId(),
                                fileMetadata.getFileName(),
                                fileMetadata.getFileType());
            response = ResponseEntity.ok(fileMetadataDto);
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
    public ResponseEntity<Resource> downloadFileSample(String processId) {
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
    public ResponseEntity<ProcessDetails> getProcessDetails(String processId) {
        Task task = null;
        ProcessDetails processDetails = workflowService.getProcessDetails(processId);


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
        return ResponseEntity.ok(processDetails);
    }

    @Override
    public ResponseEntity<List<ProcessDto>> getProcesses(Integer pageIndex, Integer numPerPage, String orderBy, Boolean assigned) {
        List<Task> taskList;
        try {
            taskList = workflowService.getTasks(pageIndex, numPerPage, orderBy, assigned);
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }
        List<ProcessDto> processDtoList = new ArrayList<>(taskList.size());

        //  ProcessDto
        for (Task task : taskList) {
            Map<String, Object> variables = task.getProcessVariables();
            processDtoList.add(
                    new ProcessDto(variables.get("projectId").toString(),
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

    @Override
    @Secured("ROLE_MANAGER") // 只有主管可以调用该 API
    public ResponseEntity<Void> setParticipant(String processId, UserIdDto userIdDto) {
        ResponseEntity<Void> result = null;
        try {
            workflowService.setParticipants(processId, userIdDto.getUserId());
        } catch (ServiceException e) {
            switch (e.getCode()) {
                case 0 -> result = ResponseEntity.status(403).build();  // 目标流程实例当前登录用户不可见
                case 1 -> result = ResponseEntity.status(404).build();  // 目标用户不存在
                case 2 -> result = ResponseEntity.status(409).build();  // 当前任务阶段不允许设置该角色的参与者
            }
        }
        if (result == null) {
            result = ResponseEntity.ok().build();
        }
        return result;
    }

    @Override
    public ResponseEntity<Resource> downloadFileForm(String processId, String formName) {

        return ResponseEntity.ok(workflowService.downloadForm(processId, formName));

//        return WorkflowApi.super.downloadFileForm(processId, formName);
    }

    @Override
    public ResponseEntity<Void> uploadFileForm(String processId, String formName, MultipartFile file) {

//        System.out.println(file.getOriginalFilename());
//
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream("./" + file.getOriginalFilename());
//            fileOutputStream.write(file.getInputStream().readAllBytes());
//            fileOutputStream.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        System.out.println("sjdjfhds");

        return ResponseEntity.ok(null);
    }
}
