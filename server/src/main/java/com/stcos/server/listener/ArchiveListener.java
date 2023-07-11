package com.stcos.server.listener;

import com.stcos.server.entity.process.TaskName;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

/**
 * description
 *
 * @author kura
 * @version 1.0
 * @since 2023/7/9 15:58
 */
@Component
public class ArchiveListener extends OperatorTaskListener {

    public ArchiveListener() {
        super(TaskName.NAME_TASK_31);
    }

    @Override
    public void create(DelegateTask task) {
        super.create(task);


    }

    @Override
    public void complete(DelegateTask task) {
        super.complete(task);


    }
}
