package com.stcos.server.listener;

import org.flowable.task.service.delegate.DelegateTask;

/**
 * description
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/18 14:21
 */
public interface TaskListener {

    /**
     * 任务被创建，向Assignee发送邮件
     *
     * @param task 任务
     */
    default void create(DelegateTask task) {

    }

    default void assignment(DelegateTask task) {

    }

    default void complete(DelegateTask task) {

    }

    default void delete(DelegateTask task) {

    }
}
