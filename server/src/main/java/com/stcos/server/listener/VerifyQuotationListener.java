package com.stcos.server.listener;

import com.stcos.server.entity.form.FormState;
import com.stcos.server.entity.form.FormType;
import com.stcos.server.entity.process.TaskName;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

/*
 _    __          _ ____      ____              __        __  _             __    _      __
| |  / /__  _____(_) __/_  __/ __ \__  ______  / /_____ _/ /_(_)___  ____  / /   (_)____/ /____  ____  ___  _____
| | / / _ \/ ___/ / /_/ / / / / / / / / / __ \/ __/ __ `/ __/ / __ \/ __ \/ /   / / ___/ __/ _ \/ __ \/ _ \/ ___/
| |/ /  __/ /  / / __/ /_/ / /_/ / /_/ / /_/ / /_/ /_/ / /_/ / /_/ / / / / /___/ (__  ) /_/  __/ / / /  __/ /
|___/\___/_/  /_/_/  \__, /\___\_\__,_/\____/\__/\__,_/\__/_/\____/_/ /_/_____/_/____/\__/\___/_/ /_/\___/_/
                    /____/
 */

/**
 * "客户审核报价"任务监听器
 *
 * @author kura
 * @version 1.0
 * @since 2023/7/9 16:21
 */
@Component
public class VerifyQuotationListener extends ClientTaskListener {

    public VerifyQuotationListener() {
        super(TaskName.NAME_TASK_09);
    }

    @Override
    public void create(DelegateTask task) {
        super.create(task);
        Long formMetadataId = (Long) task.getVariable(FormType.TYPE_QUOTATION_FORM);
        formService.setFormState(formMetadataId, FormState.STATE_VERIFYING);
    }

    @Override
    public void complete(DelegateTask task) {
        super.complete(task);
        Long formMetadataId = (Long) task.getVariable(FormType.TYPE_QUOTATION_FORM);
        formService.setFormState(formMetadataId, FormState.STATE_COMPLETED);
    }
}
