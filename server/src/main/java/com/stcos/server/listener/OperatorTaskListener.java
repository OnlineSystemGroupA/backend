package com.stcos.server.listener;

import com.stcos.server.entity.user.Operator;
import com.stcos.server.service.OperatorService;
import org.flowable.task.service.delegate.DelegateTask;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/7/10 18:36
 */
public class OperatorTaskListener extends TaskListener<OperatorService, Operator> {

    public OperatorTaskListener(String taskName) {
        super(taskName);
    }

    @Override
    protected void updateTaskParam(DelegateTask task) {
        super.updateTaskParam(task);
        task.setVariable("assignee", userService.getById(task.getAssignee()).getRealName());
    }
}
