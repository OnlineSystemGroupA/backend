package com.stcos.server.listener;

import org.flowable.task.service.delegate.DelegateTask;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/30 10:53
 */
public class AssignTestingOperatorListener extends TaskListener {

    @Override
    public void create(DelegateTask task) {
        super.create(task);
    }
}
