package com.stcos.server.listener;

import com.stcos.server.model.form.ApplicationForm;
import com.stcos.server.model.form.FormState;
import com.stcos.server.model.form.FormType;
import com.stcos.server.model.process.TaskName;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.stcos.server.model.form.FormType.TYPE_TEST_FUNCTION_FORM;

/*
    __  ___          ___ ____      ___                ___            __  _             __    _      __
   /  |/  /___  ____/ (_) __/_  __/   |  ____  ____  / (_)________ _/ /_(_)___  ____  / /   (_)____/ /____  ____  ___  _____
  / /|_/ / __ \/ __  / / /_/ / / / /| | / __ \/ __ \/ / / ___/ __ `/ __/ / __ \/ __ \/ /   / / ___/ __/ _ \/ __ \/ _ \/ ___/
 / /  / / /_/ / /_/ / / __/ /_/ / ___ |/ /_/ / /_/ / / / /__/ /_/ / /_/ / /_/ / / / / /___/ (__  ) /_/  __/ / / /  __/ /
/_/  /_/\____/\__,_/_/_/  \__, /_/  |_/ .___/ .___/_/_/\___/\__,_/\__/_/\____/_/ /_/_____/_/____/\__/\___/_/ /_/\___/_/
                         /____/      /_/   /_/
 */

/**
 * “用户修改委托”任务监听器
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/7/5 13:46
 */
@Component
public class ModifyApplicationListener extends ClientTaskListener {

    public ModifyApplicationListener() {
        super(TaskName.NAME_TASK_06);
    }

    @Override
    public void create(DelegateTask task) {
        super.create(task);

        Long metadataId = (Long) task.getVariable(FormType.TYPE_APPLICATION_FORM);
        formService.setFormState(metadataId, FormState.STATE_REFUSED);
        metadataId = (Long) task.getVariable(TYPE_TEST_FUNCTION_FORM);
        formService.setFormState(metadataId, FormState.STATE_REFUSED);
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void complete(DelegateTask task) {
        super.complete(task);

        // 更新任务详情
        Long metadataId = (Long) task.getVariable(FormType.TYPE_APPLICATION_FORM);
        ApplicationForm form = (ApplicationForm) formService.getForm(metadataId);
        task.setVariable("title", form.getSoftwareName());

        // 更新流程详情
        Long projectId = (Long) task.getVariable("projectId");
        processDetailsService.update(projectId,
                form.getSoftwareName(),
                form.getSoftwareVersion(),
                form.getTestTypes(),
                ((LocalDateTime) task.getVariable("startDate")).toString(),
                form.getCompanyChineseName(),
                form.getCompanyInfo().getEmail(),
                form.getCompanyInfo().getAddress(),
                (String) task.getVariable("startUser"),
                form.getCompanyInfo().getTelephone());

        formService.setFormState(metadataId, FormState.STATE_VERIFYING);
        metadataId = (Long) task.getVariable(TYPE_TEST_FUNCTION_FORM);
        formService.setFormState(metadataId, FormState.STATE_VERIFYING);
    }
}
