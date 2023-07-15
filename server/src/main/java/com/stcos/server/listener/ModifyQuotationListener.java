package com.stcos.server.listener;

import com.stcos.server.model.form.FormState;
import com.stcos.server.model.form.FormType;
import com.stcos.server.model.process.TaskName;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

/*
    __  ___          ___ ____      ____              __        __  _             __    _      __
   /  |/  /___  ____/ (_) __/_  __/ __ \__  ______  / /_____ _/ /_(_)___  ____  / /   (_)____/ /____  ____  ___  _____
  / /|_/ / __ \/ __  / / /_/ / / / / / / / / / __ \/ __/ __ `/ __/ / __ \/ __ \/ /   / / ___/ __/ _ \/ __ \/ _ \/ ___/
 / /  / / /_/ / /_/ / / __/ /_/ / /_/ / /_/ / /_/ / /_/ /_/ / /_/ / /_/ / / / / /___/ (__  ) /_/  __/ / / /  __/ /
/_/  /_/\____/\__,_/_/_/  \__, /\___\_\__,_/\____/\__/\__,_/\__/_/\____/_/ /_/_____/_/____/\__/\___/_/ /_/\___/_/
                         /____/
 */

/**
 * "市场部修改报价"任务监听器
 *
 * @author kura
 * @version 1.0
 * @since 2023/7/9 16:19
 */
@Component
public class ModifyQuotationListener extends OperatorTaskListener {
    public ModifyQuotationListener() {
        super(TaskName.NAME_TASK_10);
    }

    @Override
    public void create(DelegateTask task) {
        super.create(task);
        Long formMetadataId = (Long) task.getVariable(FormType.TYPE_QUOTATION_FORM);
        formService.setFormState(formMetadataId, FormState.STATE_REFUSED);
    }

    @Override
    public void complete(DelegateTask task) {
        super.complete(task);
        Long formMetadataId = (Long) task.getVariable(FormType.TYPE_QUOTATION_FORM);
        formService.setFormState(formMetadataId, FormState.STATE_VERIFYING);
    }
}
