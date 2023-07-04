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
 * @since 2023/5/30 10:53
 */

@Component
public class AssignTestingOperatorListener extends TaskListener {

    private OperatorService operatorService;

    @Autowired
    public void setOperatorService(OperatorService operatorService) {
        this.operatorService = operatorService;
    }

    private void updatePermission(String userId, DelegateTask task) {
        Long formMetadataId;
        // 使 NST－04－JS002－2018－软件项目委托测试申请表对用户可见
        formMetadataId = (Long) task.getVariable("ApplicationForm", false);
        formService.addReadPermission(formMetadataId, userId);

        // NST－04－JS003－2018－委托测试软件功能列表对用户可见
        formMetadataId = (Long) task.getVariable("TestFunctionForm", false);
        formService.addReadPermission(formMetadataId, userId);
    }

    @Override
    public void create(DelegateTask task) {
        // 使流程对测试部部主管可见
        String operatorUid = task.getAssignee();
        updatePermission(operatorUid, task);
        operatorService.addProcessInstance(operatorUid, task.getProcessInstanceId());
        super.create(task);
    }

    @Override
    public void complete(DelegateTask task) {
        // 使流程对测试部员工可见
        String marketingOperatorUid = (String) task.getVariable("testingOperator", false);
        updatePermission(marketingOperatorUid, task);
        operatorService.addProcessInstance(marketingOperatorUid, task.getProcessInstanceId());
        super.complete(task);
    }
}
