package com.stcos.server.listener;

import com.stcos.server.model.form.FormState;
import com.stcos.server.model.form.FormType;
import com.stcos.server.model.process.TaskName;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

/**
 * description
 *
 * @author kura
 * @version 1.0
 * @since 2023/7/9 16:21
 */

@Component
public class VerifyContractListener extends OperatorTaskListener {
    public VerifyContractListener() {
        super(TaskName.NAME_TASK_15);
    }

    @Override
    public void create(DelegateTask task) {
        super.create(task);

        Long formMetadataId = (Long) task.getVariable(FormType.TYPE_CONTRACT_FORM);
        formService.setFormState(formMetadataId, FormState.STATE_VERIFYING);
        formMetadataId = (Long) task.getVariable(FormType.TYPE_CONFIDENTIALITY_FORM);
        formService.setFormState(formMetadataId, FormState.STATE_VERIFYING);
    }

    @Override
    public void complete(DelegateTask task) {
        super.complete(task);

        Long formMetadataId = (Long) task.getVariable(FormType.TYPE_CONTRACT_FORM);
        formService.setFormState(formMetadataId, FormState.STATE_COMPLETED);
        formMetadataId = (Long) task.getVariable(FormType.TYPE_CONFIDENTIALITY_FORM);
        formService.setFormState(formMetadataId, FormState.STATE_COMPLETED);
    }
}
