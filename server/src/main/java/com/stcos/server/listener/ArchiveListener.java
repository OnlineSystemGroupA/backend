package com.stcos.server.listener;

import com.stcos.server.entity.form.FormState;
import com.stcos.server.entity.process.TaskName;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

import static com.stcos.server.entity.form.FormType.TYPE_TEST_WORK_CHECK_FORM;

/**
 * description
 *
 * @author kura
 * @version 1.0
 * @since 2023/7/9 15:58
 */
@Component
public class ArchiveListener extends OperatorTaskListener {

    public ArchiveListener() {
        super(TaskName.NAME_TASK_31);
    }

    @Override
    public void complete(DelegateTask task) {
        super.complete(task);

        Long metadataId = (Long) task.getVariable(TYPE_TEST_WORK_CHECK_FORM);
        formService.setFormState(metadataId, FormState.STATE_COMPLETED);
    }
}
