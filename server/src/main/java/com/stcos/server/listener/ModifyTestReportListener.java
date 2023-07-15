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
 * @since 2023/7/9 16:19
 */

@Component
public class ModifyTestReportListener extends OperatorTaskListener {

    public ModifyTestReportListener() {
        super(TaskName.NAME_TASK_30);
    }

    @Override
    public void create(DelegateTask task) {
        super.create(task);

        Long formMetadataId = (Long) task.getVariable(FormType.TYPE_TEST_REPORT_FORM);
        formService.setFormState(formMetadataId, FormState.STATE_REFUSED);
    }
}
