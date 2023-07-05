package com.stcos.server.listener;

import com.stcos.server.util.FormUtil;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/30 10:52
 */

@Component
public class AssignMarketingOperatorListener extends TaskListener {

    @Override
    public void create(DelegateTask task) {
        String operatorUid = task.getAssignee();

        // 使流程对市场部主管可见
        operatorService.addProcessInstance(operatorUid, task.getProcessInstanceId());
        super.create(task);
    }


    @Override
    public void complete(DelegateTask task) {
        String marketingOperatorUid = (String) task.getVariable("marketingOperator", false);

        // 更新表单读权限
        FormUtil.addReadPermission(marketingOperatorUid, task, formService);

        // 使流程对市场部员工可见
        operatorService.addProcessInstance(marketingOperatorUid, task.getProcessInstanceId());
        super.complete(task);
    }

}
