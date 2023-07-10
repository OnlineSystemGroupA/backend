package com.stcos.server.listener;

import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/7/5 14:18
 */

@Component
public class GenQuotationListener extends TaskListener {

    @Override
    public void create(DelegateTask task) {
        Long formMetadataId = (Long) task.getVariable("QuotationForm", false);
        // 若不存在则创建
        if (formMetadataId == null) {
            formMetadataId = formService.createMetadata("报价单", task.getAssignee());
            task.setVariable("QuotationForm", formMetadataId, false);
            // 为客户、市场部员工/主管、测试部部员工/主管分配读权限
            formService.addReadPermission(formMetadataId, (String) task.getVariable("client", false));
            formService.addReadPermission(formMetadataId, (String) task.getVariable("marketingManager", false));
            formService.addReadPermission(formMetadataId, (String) task.getVariable("marketingOperator", false));
            formService.addReadPermission(formMetadataId, (String) task.getVariable("testingManager", false));
            formService.addReadPermission(formMetadataId, (String) task.getVariable("testingOperator", false));
        }
        super.create(task);
    }
}
