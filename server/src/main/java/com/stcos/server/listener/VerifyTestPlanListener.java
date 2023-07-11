package com.stcos.server.listener;

import com.stcos.server.entity.process.TaskName;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

/**
 * description
 *
 * @author kura
 * @version 1.0
 * @since 2023/7/9 16:22
 */

@Component
public class VerifyTestPlanListener extends OperatorTaskListener {

    public VerifyTestPlanListener() {
        super(TaskName.NAME_TASK_23);
    }

    @Override
    public void create(DelegateTask task) {
        super.create(task);
        String uid = task.getAssignee();
        // 使当前实例对用户可见
        userService.addProcessInstance(uid, task.getProcessInstanceId());
    }

    @Override
    public void complete(DelegateTask task) {
        super.complete(task);
    }
}
