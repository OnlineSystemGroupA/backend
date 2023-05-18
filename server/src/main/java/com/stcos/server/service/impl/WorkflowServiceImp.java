package com.stcos.server.service.impl;

import com.stcos.server.exception.ServiceException;
import com.stcos.server.service.WorkflowService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkflowServiceImp implements WorkflowService {


    private RuntimeService runtimeService;

    @Autowired
    public void setRuntimeService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    private TaskService taskService;

    @Autowired
    public void setTaskService(TaskService taskService){this.taskService = taskService;}


    @Override
    public void completeTask(String taskId) throws ServiceException {
        Task task = getTaskById(taskId);
        taskService.complete(task.getId());
    }

    @Override
    public Task getTaskById(String taskId) throws ServiceException {
        List<Task> tasks = taskService.createTaskQuery().taskId(taskId).list();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(tasks == null) //没有对应Id的task
            throw new ServiceException(1);
        Task task = tasks.get(0);
        if(!task.getAssignee().equals(userDetails.getUsername())){ //当前用户不是被分配到的用户（即不可见）
            throw new ServiceException(0);
        }
        return task;
    }

    @Override
    public List<Task> getTasks() throws ServiceException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return taskService.createTaskQuery().taskAssignee(userDetails.getUsername()).list();
    }

    @Override
    public Object getTaskItem(String processId, String itemName) throws ServiceException {
        Task task = getTaskById(processId);
        if(!taskService.hasVariable(task.getId(),itemName)) //没有对应资源
            throw new ServiceException(1);
        return taskService.getVariable(task.getId(), itemName);

    }

    @Override
    public void updateTaskItem(String processId, String itemName) throws ServiceException {

    }

    @Override
    public String startProcess() throws ServiceException {
        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey("workflow");
        return processInstance.getProcessInstanceId();
    }

}
