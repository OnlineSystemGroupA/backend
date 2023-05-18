package com.stcos.server.listener;

import lombok.experimental.Delegate;
import org.flowable.task.service.delegate.DelegateTask;

/**
 * description
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/18 14:21
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
