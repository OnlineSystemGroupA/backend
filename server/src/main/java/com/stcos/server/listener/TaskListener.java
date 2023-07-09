package com.stcos.server.listener;

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
@Component
public class TaskListener {

    private EmailService emailService;

    protected FormService formService;

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    @Autowired
    public void setFormService(FormService formService) {
        this.formService = formService;
    }

    /**
     * 更新流程变量
     *
     * @param task     当前任务
     * @param realName
     */
    private void updateTaskParam(DelegateTask task, String realName) {
        // 重置任务参数
        task.setVariable("passable", true, false);
        task.setVariable("description", "", false);
        // 更新流程摘要和流程详情
        task.setVariable("currentTask", task.getName(), false);
        if (realName != null)
            task.setVariable("assignee", realName, false);
    }

    private void openWritePermission(DelegateTask task) {
        List<String> requiredForms = TaskUtil.getRequiredForms(task.getName());
        for (String formName : requiredForms) {
            Long formMetadataId = (Long) task.getVariable(formName);
            formService.addWritePermission(formMetadataId, task.getAssignee());
        }
    }

    private void closeWritePermission(DelegateTask task) {
        List<String> requiredForms = TaskUtil.getRequiredForms(task.getName());
        for (String formName : requiredForms) {
            Long formMetadataId = (Long) task.getVariable(formName);
            formService.removeWritePermission(formMetadataId, task.getAssignee());
        }
    }

//    private Expression assigneeEmail;

    private void sendEmail(DelegateTask task) {
//        String subject = TaskUtil.getEmailSubject(task.getName());
//        String text = TaskUtil.getEmailText(task.getName(), null);
//        String to = (String) assigneeEmail.getValue(task);
//        emailService.sendEmail(to, subject, text);
    }

    public void create(DelegateTask task) {
//        String to = (String) assigneeEmail.getValue(task);
//        System.out.println(to);

        String realName = operatorService.getRealNameById(task.getAssignee());
        // 更新流程变量
        updateTaskParam(task, realName);
        // 为被分配人开启对应表单的写权限
        openWritePermission(task);
        // 发送邮件通知被分配人
        sendEmail(task);
        // 更新流程详情
        updateProcessDetailsWhenCreate(task, realName);
    }


    public void complete(DelegateTask task) {
        // 关闭分配人对应表单的写权限
        closeWritePermission(task);
        // 更新流程详情
        updateProcessDetailsWhenComplete(task);
    }

    protected OperatorService operatorService;

    protected ProcessDetailsService processDetailsService;

    @Autowired
    public void setProcessDetailsService(ProcessDetailsService processDetailsService) {
        this.processDetailsService = processDetailsService;
    }

    private void updateProcessDetailsWhenCreate(DelegateTask task, String realName) {
        // 获取项目 ID
        Long projectId = (Long) task.getVariable("projectId", false);
        // 更新流程详情
        if (realName == null)
            realName = (String) task.getVariable("startUser", false);
        processDetailsService.openTask(projectId, task.getName(), realName);
    }

    private void updateProcessDetailsWhenComplete(DelegateTask task) {
        // 获取项目 ID
        Long projectId = (Long) task.getVariable("projectId", false);
        // 更新流程详情
        processDetailsService.closeTask(projectId, task.getName());
    }


    @Autowired
    public void setOperatorService(OperatorService operatorService) {
        this.operatorService = operatorService;
    }
}
