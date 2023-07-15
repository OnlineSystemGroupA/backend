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
 * @since 2023/7/9 16:15
 */
@Component
public class ManagerVerifyTestPlanListener extends OperatorTaskListener {

    public ManagerVerifyTestPlanListener() {
        super(TaskName.NAME_TASK_25);
    }

    @Override
    public void complete(DelegateTask task) {
        super.complete(task);

        Long formMetadataId = (Long) task.getVariable(FormType.TYPE_TEST_PLAN_FORM);
        formService.setFormState(formMetadataId, FormState.STATE_COMPLETED);
    }
}
