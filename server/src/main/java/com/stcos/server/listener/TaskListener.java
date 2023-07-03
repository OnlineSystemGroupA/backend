package com.stcos.server.listener;

import com.stcos.server.service.EmailService;
import com.stcos.server.service.FormMetadataService;
import com.stcos.server.service.FormService;
import com.stcos.server.util.TaskUtil;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
     * @param task 当前任务
     */
    private void updateTaskParam(DelegateTask task) {
        // 重置任务参数
        task.setVariable("passable", true, false);
        task.setVariable("description", "", false);
        // 更新流程摘要和流程详情
        task.setVariable("currentTask", task.getName(), false);
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
        String subject = TaskUtil.getEmailSubject(task.getName());
        String text = TaskUtil.getEmailText(task.getName(), null);
//        String to = (String) assigneeEmail.getValue(task);
//        emailService.sendEmail(to, subject, text);
    }

    public void create(DelegateTask task) {
//        String to = (String) assigneeEmail.getValue(task);
//        System.out.println(to);
        // 更新流程变量
        updateTaskParam(task);
        // 为被分配人开启对应表单的写权限
        openWritePermission(task);
        // 发送邮件通知被分配人
        sendEmail(task);
    }

    public void complete(DelegateTask task) {

        // 为被分配人关闭对应表单的写权限
        closeWritePermission(task);

    }

}
