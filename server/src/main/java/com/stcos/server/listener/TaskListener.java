package com.stcos.server.listener;

import org.flowable.task.service.delegate.DelegateTask;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/18 14:04
 */
public interface TaskListener {

    default void create(DelegateTask task) {
    }

    default void assignment(DelegateTask task) {
    }

    default void complete(DelegateTask task) {
    }

    default void delete(DelegateTask task) {
    }

}
