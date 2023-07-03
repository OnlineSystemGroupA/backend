package com.stcos.server.listener;

import com.stcos.server.service.OperatorService;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.beans.factory.annotation.Autowired;
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

    private OperatorService operatorService;

    @Autowired
    public void setOperatorService(OperatorService operatorService) {
        this.operatorService = operatorService;
    }

    @Override
    public void create(DelegateTask task) {
        String operatorUid = task.getAssignee();

        // 使流程对市场部主管可见
        operatorService.addProcessInstance(operatorUid, task.getProcessInstanceId());

        super.create(task);
    }


    @Override
    public void complete(DelegateTask task) {
        super.complete(task);
    }


}
