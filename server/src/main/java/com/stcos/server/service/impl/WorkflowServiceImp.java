package com.stcos.server.service.impl;

import com.stcos.server.config.security.UserDetailsImp;
import com.stcos.server.config.security.UserDetailsImp;
import com.stcos.server.entity.form.Form;
import com.stcos.server.entity.form.FormIndex;
import com.stcos.server.exception.ServiceException;
import com.stcos.server.service.WorkflowService;
import org.flowable.common.engine.impl.identity.Authentication;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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


    @Override
    public void completeTask(String taskId) throws ServiceException {
        Task task = getTaskById(taskId);

        // 验证是否满足完成条件

        // 若满足完成条件则完成该任务
        taskService.complete(task.getId());
    }

    @Override
    public Task getTaskById(String taskId) throws ServiceException {
        List<Task> tasks = taskService.createTaskQuery().taskId(taskId).list();
        UserDetailsImp userDetails = (UserDetailsImp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (tasks == null) //没有对应Id的task
            throw new ServiceException(1);
        Task task = tasks.get(0);
        if (!task.getAssignee().equals(userDetails.getUid())) { //当前用户不是被分配到的用户（即不可见）
            throw new ServiceException(0);
        }
        return task;
    }

    @Override
    public List<Task> getTasks() throws ServiceException {

//        System.out.println(taskService.createTaskQuery().executionId("5fa248e5-f56b-11ed-9d29-4c796ec27013").singleResult().getAssignee());

        UserDetailsImp userDetails = (UserDetailsImp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return taskService.createTaskQuery().taskAssignee(userDetails.getUid()).list();
    }

    @Override
    public Object getTaskItem(String processId, String itemName) throws ServiceException {
        Task task = getTaskById(processId);
        if (!taskService.hasVariable(task.getId(), itemName)) //没有对应资源
            throw new ServiceException(1);
        return taskService.getVariable(task.getId(), itemName);

    }

    @Override
    public void updateForm(String processInstanceId, String formName, Form form) throws ServiceException {
        // 判断 processId 对应的流程是否存在
        ProcessInstance processInstance = runtimeService.
                createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if (processInstance == null) {
            throw new ServiceException(0);
        }

        // 判断当前用户对该表单是否具有写权限

        // 生成表单索引
//        FormIndex formIndex = new FormIndex();

        // 将表单保存至数据库

        // 将表单索引保存至流程实例变量中
        runtimeService.setVariable(processInstanceId, formName, null);

    }

    @Override
    public String startProcess() throws ServiceException {
        String userId = ((UserDetailsImp) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUid();

        // 初始化流程参数
        Map<String, Object> map = new HashMap<>() {{
            put("client", userId);
            put("admin", "op-1");
        }};

        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey("workflow", map);

        return processInstance.getProcessInstanceId();
    }

}
