package com.stcos.server.listener;

import com.stcos.server.util.FormUtil;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/30 10:53
 */

@Component
public class AssignTestingOperatorListener extends TaskListener {

    @Override
    public void create(DelegateTask task) {
        // 使流程对测试部部主管可见
        String operatorUid = task.getAssignee();
        operatorService.addProcessInstance(operatorUid, task.getProcessInstanceId());
        super.create(task);
    }

    @Override
    public void complete(DelegateTask task) {
        // 使流程对测试部员工可见
        String testingOperatorUid = (String) task.getVariable("testingOperator", false);
        operatorService.addProcessInstance(testingOperatorUid, task.getProcessInstanceId());
        FormUtil.addReadPermission(testingOperatorUid, task, formService);
        super.complete(task);
    }
}
