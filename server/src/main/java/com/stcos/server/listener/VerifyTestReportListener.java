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
 * @since 2023/7/9 16:22
 */

@Component
public class VerifyTestReportListener extends OperatorTaskListener {

    public VerifyTestReportListener() {
        super(TaskName.NAME_TASK_27);
    }

    @Override
    public void create(DelegateTask task) {
        super.create(task);
        userService.addProcessInstance(task.getAssignee(), task.getProcessInstanceId());

        Long formMetadataId = (Long) task.getVariable(FormType.TYPE_REPORT_VERIFY_FORM);
        formService.setFormState(formMetadataId, FormState.STATE_WRITING);

        formMetadataId = (Long) task.getVariable(FormType.TYPE_TEST_PROBLEM_FORM);
        formService.setFormState(formMetadataId, FormState.STATE_VERIFYING);
        formMetadataId = (Long) task.getVariable(FormType.TYPE_TEST_RECORDS_FORM);
        formService.setFormState(formMetadataId, FormState.STATE_VERIFYING);
        formMetadataId = (Long) task.getVariable(FormType.TYPE_TEST_REPORT_FORM);
        formService.setFormState(formMetadataId, FormState.STATE_VERIFYING);
    }

    @Override
    public void complete(DelegateTask task) {
        super.complete(task);

        Long formMetadataId = (Long) task.getVariable(FormType.TYPE_REPORT_VERIFY_FORM);
        formService.setFormState(formMetadataId, FormState.STATE_COMPLETED);

        formMetadataId = (Long) task.getVariable(FormType.TYPE_TEST_PROBLEM_FORM);
        formService.setFormState(formMetadataId, FormState.STATE_COMPLETED);
        formMetadataId = (Long) task.getVariable(FormType.TYPE_TEST_RECORDS_FORM);
        formService.setFormState(formMetadataId, FormState.STATE_COMPLETED);
        formMetadataId = (Long) task.getVariable(FormType.TYPE_TEST_REPORT_FORM);
        formService.setFormState(formMetadataId, FormState.STATE_COMPLETED);
    }

}
