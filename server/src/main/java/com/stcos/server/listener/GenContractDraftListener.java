package com.stcos.server.listener;

import com.stcos.server.model.form.FormType;
import com.stcos.server.model.process.TaskName;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

/*
   ______           ______            __                  __  ____             ______  __    _      __
  / ____/__  ____  / ____/___  ____  / /__________ ______/ /_/ __ \_________ _/ __/ /_/ /   (_)____/ /____  ____  ___  _____
 / / __/ _ \/ __ \/ /   / __ \/ __ \/ __/ ___/ __ `/ ___/ __/ / / / ___/ __ `/ /_/ __/ /   / / ___/ __/ _ \/ __ \/ _ \/ ___/
/ /_/ /  __/ / / / /___/ /_/ / / / / /_/ /  / /_/ / /__/ /_/ /_/ / /  / /_/ / __/ /_/ /___/ (__  ) /_/  __/ / / /  __/ /
\____/\___/_/ /_/\____/\____/_/ /_/\__/_/   \__,_/\___/\__/_____/_/   \__,_/_/  \__/_____/_/____/\__/\___/_/ /_/\___/_/

 */

/**
 * "市场部生成合同草稿"任务监听器
 *
 * @author kura
 * @version 1.0
 * @since 2023/7/9 16:14
 */
@Component
public class GenContractDraftListener extends OperatorTaskListener {

    public GenContractDraftListener() {
        super(TaskName.NAME_TASK_11);
    }

    @Override
    public void complete(DelegateTask task) {
        super.complete(task);
        Long formMetadataId = (Long) task.getVariable(FormType.TYPE_CONTRACT_FORM);
        formService.addReadPermission(formMetadataId, (String) task.getVariable("client")); // 为客户分配读权限
        formMetadataId = (Long) task.getVariable(FormType.TYPE_CONFIDENTIALITY_FORM);
        formService.addReadPermission(formMetadataId, (String) task.getVariable("client")); // 为客户分配读权限
    }
}
