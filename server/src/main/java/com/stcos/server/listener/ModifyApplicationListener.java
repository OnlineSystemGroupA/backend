package com.stcos.server.listener;

import com.stcos.server.entity.form.ApplicationForm;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/7/5 13:46
 */

@Component
public class ModifyApplicationListener extends TaskListener {

    @Override
    public void complete(DelegateTask task) {

        // 更新任务详情
        Long metadataId = (Long) task.getVariable("ApplicationForm", false);
        ApplicationForm form = (ApplicationForm) formService.getForm(metadataId);
        task.setVariable("title", form.getSoftwareName(), false);

        super.complete(task);
    }
}
