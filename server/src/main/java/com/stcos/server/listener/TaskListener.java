package com.stcos.server.listener;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stcos.server.entity.process.TaskConfig;
import com.stcos.server.entity.user.User;
import com.stcos.server.service.*;
import com.stcos.server.util.TaskUtil;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * description
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/22 19:08
 */
public class TaskListener<S extends IService<U>, U extends User> {

    private EmailService emailService;

    protected FormService formService;

    protected S userService;

    protected ProcessDetailsService processDetailsService;

    private final List<String> requiredForms;

    /*   AUTO_WIRED BEGIN  */
    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    @Autowired
    public void setFormService(FormService formService) {
        this.formService = formService;
    }

    @Autowired
    public void setUserService(S userService) {
        this.userService = userService;
    }

    @Autowired
    public void setProcessDetailsService(ProcessDetailsService processDetailsService) {
        this.processDetailsService = processDetailsService;
    }
    /*   AUTO_WIRED END    */

    public TaskListener(String taskName) {
        requiredForms = TaskUtil.getRequiredForms(taskName);
    }

    /**
     * 更新流程变量
     *
     * @param task 当前任务
     */
    protected void updateTaskParam(DelegateTask task) {
        // 重置任务参数
        task.setVariable("passable", true, false);
        task.setVariable("description", "", false);
        // 更新流程摘要和流程详情
        task.setVariable("currentTask", task.getName(), false);
    }

    /**
     * 任务创建时被调用
     *
     * @param task 任务的委托对象
     */
    public void create(DelegateTask task) {
        updateTaskParam(task);         // 更新流程变量
        openWritePermission(task);     // 为被分配人开启对应表单的写权限
        sendEmail(task);               // 发送邮件通知被分配人
        String realName = userService.getById(task.getAssignee()).getRealName();
        updateProcessDetailsWhenCreate(task, realName);   // 更新流程详情
    }

    /**
     * 任务完成时被调用
     *
     * @param task 任务的委托对象
     */
    public void complete(DelegateTask task) {
        closeWritePermission(task);             // 关闭分配人对应表单的写权限
        updateProcessDetailsWhenComplete(task); // 更新流程详情
    }

    private void openWritePermission(DelegateTask task) {
        for (String formName : requiredForms) {
            Long formMetadataId = (Long) task.getVariable(formName);
            formService.addWritePermission(formMetadataId, task.getAssignee());
        }
    }

    private void closeWritePermission(DelegateTask task) {
        for (String formName : requiredForms) {
            Long formMetadataId = (Long) task.getVariable(formName);
            formService.removeWritePermission(formMetadataId, task.getAssignee());
        }
    }

    private void updateProcessDetailsWhenCreate(DelegateTask task, String realName) {
        // 获取项目 ID
        Long projectId = (Long) task.getVariable("projectId", false);
        // 更新流程详情
        if (realName == null) realName = (String) task.getVariable("startUser", false);
        processDetailsService.openTask(projectId, task.getName(), realName);
    }

    private void updateProcessDetailsWhenComplete(DelegateTask task) {
        // 获取项目 ID
        Long projectId = (Long) task.getVariable("projectId", false);
        // 更新流程详情
        processDetailsService.closeTask(projectId, task.getName());
    }

//    private Expression assigneeEmail;

    private void sendEmail(DelegateTask task) {
//        String subject = TaskUtil.getEmailSubject(task.getName());
//        String text = TaskUtil.getEmailText(task.getName(), null);
//        String to = (String) assigneeEmail.getValue(task);
//        emailService.sendEmail(to, subject, text);
    }








}
