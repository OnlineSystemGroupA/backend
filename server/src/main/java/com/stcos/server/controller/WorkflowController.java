package com.stcos.server.controller;

import com.stcos.server.controller.api.WorkflowApi;
import com.stcos.server.pojo.dto.ProcessIdDto;
import com.stcos.server.pojo.dto.TaskDto;
import com.stcos.server.service.ServiceException;
import com.stcos.server.service.WorkflowService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/5 20:45
 */
@RestController
public class WorkflowController implements WorkflowApi {

    private WorkflowService service;
    @Autowired
    public void setWorkflowController(WorkflowService service) {
        this.service = service;
    }


    @Override
    public ResponseEntity<Void> completeTask(String taskId) {
        ResponseEntity<Void> response = null;
        try {
            service.completeTask(taskId);
        } catch (ServiceException e) {
            switch (e.getCode()) {
                case 0 -> response = ResponseEntity.status(403).build();
                case 1 -> response = ResponseEntity.status(404).build();
            }
        }
        if (response == null) { //未接收到下层传入的Exception
            response = ResponseEntity.ok().build();
        }
        return response;

    }

    @Override
    public ResponseEntity<Object> downloadSamples(String processId) {
        return WorkflowApi.super.downloadSamples(processId);
    }

    @Override
    public ResponseEntity<TaskDto> getTaskById(String taskId) {
        Task task = null;
        ResponseEntity<TaskDto> response = null;
        try{
            task = service.getTaskById(taskId);
        } catch (ServiceException e) {
            switch (e.getCode()) {
                case 0 -> response = ResponseEntity.status(403).build();
                case 1 -> response = ResponseEntity.status(404).build();
            }
        }
        if(response == null){ //未接收到下层传入的Exception
            if(task == null || !task.getId().equals(taskId)) throw new RuntimeException("Error At getTaskById");
            response = ResponseEntity.ok
                    (new TaskDto(task.getProcessInstanceId(), taskId, task.getName(), task.getDescription(), task.getOwner()));
        }

        return response;
    }

    @Override
    public ResponseEntity<Object> getTaskItem(String processId, String itemName) {
        Object item = null;
        ResponseEntity<Object> response = null;
        try{
            item = service.getTaskItem(processId, itemName);
        } catch (ServiceException e) {
            switch (e.getCode()) {
                case 0 -> response = ResponseEntity.status(403).build();
                case 1 -> response = ResponseEntity.status(404).build();
            }
        }
        if(response == null){ //未接收到下层传入的Exception
            if(item == null) throw new RuntimeException("Error At getTaskItem");
            response = ResponseEntity.ok(item);
        }

        return response;
    }

    @Override
    public ResponseEntity<List<TaskDto>> getTasks() {
        List<Task> tasks;
        try {
            tasks = service.getTasks();
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        List<TaskDto> taskDtos = new ArrayList<>();
        for (Task task: tasks) {
            taskDtos.add(new TaskDto(task.getProcessInstanceId(), task.getId(), task.getName(), task.getDescription(), task.getOwner()));
        }
        return ResponseEntity.ok(taskDtos);
    }

    @Override
    public ResponseEntity<Void> samplesUpload(String processId) {
        return WorkflowApi.super.samplesUpload(processId);
    }

    @Override
    public ResponseEntity<ProcessIdDto> startProcess() {
        String ProcessId;
        try {
            ProcessId = service.startProcess();
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(new ProcessIdDto(ProcessId));
    }

    @Override
    public ResponseEntity<Void> updateTaskItem(String processId, String itemName) {
        ResponseEntity<Void> response = null;
        try{
            service.updateTaskItem(processId, itemName);
        } catch (ServiceException e) {
            switch (e.getCode()) {
                case 0 -> response = ResponseEntity.status(201).build();
                case 1 -> response = ResponseEntity.status(403).build();
                case 2 -> response = ResponseEntity.status(404).build();
            }
        }
        if(response == null)
            response = ResponseEntity.ok().build();
        return response;
    }
}

/*
    问题：
    1.get/updateTaskItem中，为何传入参数是processId而非taskId？
    2.startUserId是否是Owner
    3.用户对资源是否有权限
 */