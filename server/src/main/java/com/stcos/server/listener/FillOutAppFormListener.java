package com.stcos.server.listener;

import com.stcos.server.model.form.ApplicationForm;
import com.stcos.server.model.form.FormState;
import com.stcos.server.model.process.TaskName;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.stcos.server.model.form.FormType.*;
import static com.stcos.server.model.process.ProcessVariables.*;

/*
        _______ ________        __  ___                ______                     __    _      __
       / ____(_) / / __ \__  __/ /_/   |  ____  ____  / ____/___  _________ ___  / /   (_)____/ /____  ____  ___  _____
      / /_  / / / / / / / / / / __/ /| | / __ \/ __ \/ /_  / __ \/ ___/ __ `__ \/ /   / / ___/ __/ _ \/ __ \/ _ \/ ___/
     / __/ / / / / /_/ / /_/ / /_/ ___ |/ /_/ / /_/ / __/ / /_/ / /  / / / / / / /___/ (__  ) /_/  __/ / / /  __/ /
    /_/   /_/_/_/\____/\__,_/\__/_/  |_/ .___/ .___/_/    \____/_/  /_/ /_/ /_/_____/_/____/\__/\___/_/ /_/\___/_/
                                      /_/   /_/
 */

/**
 * “填写申请表”任务监听器
 *
 * @author Kekwy
 * @version 2.0
 * @since 2023/5/30 10:50
 */
@Component
public class FillOutAppFormListener extends ClientTaskListener {

    public FillOutAppFormListener() {
        super(TaskName.NAME_TASK_01);
    }

    @Override
    public void create(DelegateTask task) {
        super.create(task);
        // 使当前实例对用户可见
        userService.addProcessInstance(task.getAssignee(), task.getProcessInstanceId());
    }

    @Override
    public void complete(DelegateTask task) {
        super.complete(task);

        // 更新任务详情
        Long metadataId = (Long) task.getVariable(TYPE_APPLICATION_FORM);
        ApplicationForm form = (ApplicationForm) formService.getForm(metadataId);
        task.setVariable(VAR_TITLE, form.getSoftwareName());

        // 更新流程详情
        Long projectId = (Long) task.getVariable(VAR_PROJECT_ID);
        processDetailsService.update(projectId,
                form.getSoftwareName(),
                form.getSoftwareVersion(),
                form.getTestTypes(),
                ((LocalDateTime) task.getVariable(VAR_START_DATE)).toString(),
                form.getCompanyChineseName(),
                form.getCompanyInfo().getEmail(),
                form.getCompanyInfo().getAddress(),
                (String) task.getVariable(VAR_START_USER),
                form.getCompanyInfo().getTelephone());

        formService.setFormState(metadataId, FormState.STATE_VERIFYING);
        metadataId = (Long) task.getVariable(TYPE_TEST_FUNCTION_FORM);
        formService.setFormState(metadataId, FormState.STATE_VERIFYING);

        metadataId = (Long) task.getVariable(TYPE_TEST_WORK_CHECK_FORM);
        formService.setFormState(metadataId, FormState.STATE_WRITING);
    }
}
