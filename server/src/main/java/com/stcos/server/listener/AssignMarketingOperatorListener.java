package com.stcos.server.listener;

import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/30 10:52
 */

@Component
public class AssignMarketingOperatorListener extends TaskListener {

    @Override
    public void create(DelegateTask task) {
        super.create(task);
    }


    @Override
    public void complete(DelegateTask task) {
        super.complete(task);
    }
}
