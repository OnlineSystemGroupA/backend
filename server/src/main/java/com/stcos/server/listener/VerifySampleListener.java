package com.stcos.server.listener;

import com.stcos.server.entity.form.FormState;
import com.stcos.server.entity.form.FormType;
import com.stcos.server.entity.process.TaskName;
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
public class VerifySampleListener extends OperatorTaskListener {
    public VerifySampleListener() {
        super(TaskName.NAME_TASK_20);
    }

    @Override
    public void complete(DelegateTask task) {
        super.complete(task);

        Long formMetadataId = (Long) task.getVariable(FormType.TYPE_DOCUMENT_REVIEW_FORM);
        formService.setFormState(formMetadataId, FormState.STATE_COMPLETED);
    }
}
