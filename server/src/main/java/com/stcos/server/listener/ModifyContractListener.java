package com.stcos.server.listener;

import com.stcos.server.entity.form.FormState;
import com.stcos.server.entity.form.FormType;
import com.stcos.server.entity.process.TaskName;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

/*
    __  ___          ___ ____      ______            __                  __  __    _      __
   /  |/  /___  ____/ (_) __/_  __/ ____/___  ____  / /__________ ______/ /_/ /   (_)____/ /____  ____  ___  _____
  / /|_/ / __ \/ __  / / /_/ / / / /   / __ \/ __ \/ __/ ___/ __ `/ ___/ __/ /   / / ___/ __/ _ \/ __ \/ _ \/ ___/
 / /  / / /_/ / /_/ / / __/ /_/ / /___/ /_/ / / / / /_/ /  / /_/ / /__/ /_/ /___/ (__  ) /_/  __/ / / /  __/ /
/_/  /_/\____/\__,_/_/_/  \__, /\____/\____/_/ /_/\__/_/   \__,_/\___/\__/_____/_/____/\__/\___/_/ /_/\___/_/
                         /____/
 */

/**
 * "客户修改合同"任务监听器
 *
 * @author kura
 * @version 1.0
 * @since 2023/7/9 16:18
 */
@Component
public class ModifyContractListener extends ClientTaskListener {
    public ModifyContractListener() {
        super(TaskName.NAME_TASK_16);
    }

    @Override
    public void create(DelegateTask task) {
        super.create(task);

        Long formMetadataId = (Long) task.getVariable(FormType.TYPE_CONTRACT_FORM);
        formService.setFormState(formMetadataId, FormState.STATE_REFUSED);
        formMetadataId = (Long) task.getVariable(FormType.TYPE_CONFIDENTIALITY_FORM);
        formService.setFormState(formMetadataId, FormState.STATE_REFUSED);
    }

    @Override
    public void complete(DelegateTask task) {
        super.complete(task);
        Long formMetadataId = (Long) task.getVariable(FormType.TYPE_CONTRACT_FORM);
        formService.setFormState(formMetadataId, FormState.STATE_VERIFYING);
        formMetadataId = (Long) task.getVariable(FormType.TYPE_CONFIDENTIALITY_FORM);
        formService.setFormState(formMetadataId, FormState.STATE_VERIFYING);
    }
}
