package com.stcos.server.listener;

import org.flowable.task.service.delegate.DelegateTask;

/**
 * description
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/18 14:27
 */
public class AssignOperatorListener implements TaskListener{ //分配工作人员

    @Override
    public void create(DelegateTask task) {
        TaskListener.super.create(task);
    }
}
