package com.stcos.server.listener;

import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/30 10:50
 */

@Component
public class FillOutAppFormListener extends TaskListener {

    @Override
    protected void create(DelegateTask task) {
        super.create(task);
    }

}
