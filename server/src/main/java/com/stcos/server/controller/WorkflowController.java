package com.stcos.server.controller;

import com.stcos.server.controller.api.WorkflowApi;
import com.stcos.server.pojo.dto.ProcessIdDto;
import com.stcos.server.pojo.dto.TaskDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/5 20:45
 */
public class WorkflowController implements WorkflowApi {


    @Override
    public ResponseEntity<Void> completeTask(String taskId) {
        return WorkflowApi.super.completeTask(taskId);
    }

    @Override
    public ResponseEntity<Object> downloadSamples(String processId) {
        return WorkflowApi.super.downloadSamples(processId);
    }

    @Override
    public ResponseEntity<TaskDto> getTaskById(String taskId) {
        return WorkflowApi.super.getTaskById(taskId);
    }

    @Override
    public ResponseEntity<Object> getTaskItem(String processId, String itemName) {
        return WorkflowApi.super.getTaskItem(processId, itemName);
    }

    @Override
    public ResponseEntity<List<TaskDto>> getTasks() {
        return WorkflowApi.super.getTasks();
    }

    @Override
    public ResponseEntity<Void> samplesUpload(String processId) {
        return WorkflowApi.super.samplesUpload(processId);
    }

    @Override
    public ResponseEntity<ProcessIdDto> startProcess() {
        return WorkflowApi.super.startProcess();
    }

    @Override
    public ResponseEntity<Void> updateTaskItem(String processId, String itemName) {
        return WorkflowApi.super.updateTaskItem(processId, itemName);
    }
}
