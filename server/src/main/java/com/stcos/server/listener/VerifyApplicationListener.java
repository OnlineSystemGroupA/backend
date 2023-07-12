package com.stcos.server.listener;

import com.stcos.server.model.form.FormState;
import com.stcos.server.model.process.TaskName;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

import static com.stcos.server.model.form.FormType.*;

/*
 _    __          _ ____      ___                ___            __  _             __    _      __
| |  / /__  _____(_) __/_  __/   |  ____  ____  / (_)________ _/ /_(_)___  ____  / /   (_)____/ /____  ____  ___  _____
| | / / _ \/ ___/ / /_/ / / / /| | / __ \/ __ \/ / / ___/ __ `/ __/ / __ \/ __ \/ /   / / ___/ __/ _ \/ __ \/ _ \/ ___/
| |/ /  __/ /  / / __/ /_/ / ___ |/ /_/ / /_/ / / / /__/ /_/ / /_/ / /_/ / / / / /___/ (__  ) /_/  __/ / / /  __/ /
|___/\___/_/  /_/_/  \__, /_/  |_/ .___/ .___/_/_/\___/\__,_/\__/_/\____/_/ /_/_____/_/____/\__/\___/_/ /_/\___/_/
                    /____/      /_/   /_/
 */

/**
 * “审核申请表”任务监听器
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/7/4 15:32
 */
@Component
public class VerifyApplicationListener extends OperatorTaskListener {

    public VerifyApplicationListener() {
        super(TaskName.NAME_TASK_04);
    }

    @Override
    public void create(DelegateTask task) {
        super.create(task);
        userService.addProcessInstance(task.getAssignee(), task.getProcessInstanceId());

        Long metadataId = (Long) task.getVariable(TYPE_APPLICATION_FORM);
        formService.setFormState(metadataId, FormState.STATE_VERIFYING);
        metadataId = (Long) task.getVariable(TYPE_TEST_FUNCTION_FORM);
        formService.setFormState(metadataId, FormState.STATE_VERIFYING);

        metadataId = (Long) task.getVariable(TYPE_APPLICATION_VERIFY_FORM);
        formService.setFormState(metadataId, FormState.STATE_WRITING);
    }

    @Override
    public void complete(DelegateTask task) {
        super.complete(task);

        Long metadataId = (Long) task.getVariable(TYPE_APPLICATION_FORM);
        formService.setFormState(metadataId, FormState.STATE_COMPLETED);
        metadataId = (Long) task.getVariable(TYPE_TEST_FUNCTION_FORM);
        formService.setFormState(metadataId, FormState.STATE_COMPLETED);

        metadataId = (Long) task.getVariable(TYPE_APPLICATION_VERIFY_FORM);
        formService.setFormState(metadataId, FormState.STATE_COMPLETED);
    }
}
