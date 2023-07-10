package com.stcos.server.listener;

import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/7/4 15:32
 */

@Component
public class VerifyApplicationListener extends TaskListener {

    @Override
    public void create(DelegateTask task) {
        Long formMetadataId = (Long) task.getVariable("ApplicationVerifyForm", false);
        // 若不存在则创建
        if (formMetadataId == null) {
            formMetadataId = formService.createMetadata("NST－04－JS002－2018－软件项目委托测试申请表（审核信息部分）", task.getAssignee());
            task.setVariable("ApplicationVerifyForm", formMetadataId, false);
            // 为客户、市场部员工/主管、测试部部员工/主管分配读权限
            formService.addReadPermission(formMetadataId, (String) task.getVariable("client", false));
            formService.addReadPermission(formMetadataId, (String) task.getVariable("marketingManager", false));
            formService.addReadPermission(formMetadataId, (String) task.getVariable("marketingOperator", false));
            formService.addReadPermission(formMetadataId, (String) task.getVariable("testingManager", false));
            formService.addReadPermission(formMetadataId, (String) task.getVariable("testingOperator", false));
        }
        super.create(task);
    }

    @Override
    public void complete(DelegateTask task) {
        super.complete(task);
    }
}
