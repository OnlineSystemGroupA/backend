package com.stcos.server.listener;

import com.stcos.server.entity.form.ApplicationForm;
import com.stcos.server.service.ClientService;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/30 10:50
 */

@Component
public class FillOutAppFormListener extends TaskListener {

    private ClientService clientService;

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    @Transactional
    public void create(DelegateTask task) {

        String clientUid = task.getAssignee();

        // 将当前流程实例的 ID 加入客户发起且尚未结束的流程列表中
        clientService.addProcessInstance(clientUid, task.getProcessInstanceId());

        // 创建申请表和测试功能表元数据
        Long applicationFormMetadataId = formService.createMetadata("NST－04－JS002－2018－软件项目委托测试申请表", clientUid);
        Long testFunctionFormId = formService.createMetadata("NST－04－JS003－2018－委托测试软件功能列表", clientUid);

        // 为客户开放读权限
        formService.addReadPermission(applicationFormMetadataId, clientUid);
        formService.addReadPermission(testFunctionFormId, clientUid);

        // 将元数据 ID 加入流程变量中
        task.setVariable("ApplicationForm", applicationFormMetadataId);
        task.setVariable("TestFunctionForm", testFunctionFormId);

        super.create(task);
    }

    @Override
    public void complete(DelegateTask task) {
        // 更新任务详情
        Long metadataId = (Long) task.getVariable("ApplicationForm", false);
        ApplicationForm form = formService.getForm(metadataId);
        super.complete(task);
    }
}
