package com.stcos.server.listener;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stcos.server.model.process.ProcessVariables;
import com.stcos.server.model.user.User;
import com.stcos.server.service.EmailService;
import com.stcos.server.service.FormService;
import com.stcos.server.service.ProcessDetailsService;
import com.stcos.server.util.FormUtil;
import com.stcos.server.util.TaskUtil;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

import static com.stcos.server.model.process.ProcessVariables.VAR_PROJECT_ID;
import static com.stcos.server.model.process.ProcessVariables.VAR_START_USER;

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

    private final Set<String> requiredForms;

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
        task.setVariable("passable", true);
        task.setVariable("description", "");
        // 更新流程摘要和流程详情
        task.setVariable("currentTask", task.getName());
    }

    /**
     * 任务创建时被调用
     *
     * @param task 任务的委托对象
     */
    public void create(DelegateTask task) {
        updateTaskParam(task);              // 更新流程变量
        closeOthersReadPermission(task);    // 暂时关闭其他用户对当前任务中需要修改表单的读权限
        openWritePermission(task);          // 为被分配人开启对应表单的写权限
        sendEmail(task);                    // 发送邮件通知被分配人
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
        openOthersReadPermission(task);         // 恢复其他用户对相关表单的读权限
        updateProcessDetailsWhenComplete(task); // 更新流程详情
    }

    private void openOthersReadPermission(DelegateTask task) {
        Set<String> uidSet = new HashSet<>();
        Set<String> uidWithoutClientSet = new HashSet<>();
        for (String participant : ProcessVariables.PARTICIPANT_SET) {
            String uid = (String) task.getVariable(participant);
            if (uid != null) uidSet.add(uid);
        }
        for (String participant : ProcessVariables.PARTICIPANT_WHITOUT_CLIENT_SET) {
            String uid = (String) task.getVariable(participant);
            if (uid != null) uidWithoutClientSet.add(uid);
        }
        for (String formType : requiredForms) {
            Long formMetadataId = (Long) task.getVariable(formType);
            if (FormUtil.isClientReadable(formType)) formService.addReadPermission(formMetadataId, uidSet);
            else formService.addReadPermission(formMetadataId, uidWithoutClientSet);
        }
    }

    private void closeOthersReadPermission(DelegateTask task) {
        for (String formType : requiredForms) {
            Long formMetadataId = (Long) task.getVariable(formType);
            formService.removeReadPermission(formMetadataId);
            formService.addReadPermission(formMetadataId, task.getAssignee());
        }
    }


    private void openWritePermission(DelegateTask task) {
        for (String formType : requiredForms) {
            Long formMetadataId = (Long) task.getVariable(formType);
            formService.addWritePermission(formMetadataId, task.getAssignee());
        }
    }

    private void closeWritePermission(DelegateTask task) {
        for (String formType : requiredForms) {
            Long formMetadataId = (Long) task.getVariable(formType);
            formService.removeWritePermission(formMetadataId, task.getAssignee());
        }
    }

    private void updateProcessDetailsWhenCreate(DelegateTask task, String realName) {
        // 获取项目 ID
        Long projectId = (Long) task.getVariable(VAR_PROJECT_ID);
        // 更新流程详情
        if (realName == null) realName = (String) task.getVariable(VAR_START_USER);
        processDetailsService.openTask(projectId, task.getName(), realName);
    }

    private void updateProcessDetailsWhenComplete(DelegateTask task) {
        // 获取项目 ID
        Long projectId = (Long) task.getVariable(VAR_PROJECT_ID);
        // 更新流程详情
        processDetailsService.closeTask(projectId, task.getName());
    }

    private void sendEmail(DelegateTask task) {
    }
}
