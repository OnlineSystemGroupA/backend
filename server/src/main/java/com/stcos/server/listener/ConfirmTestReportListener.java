package com.stcos.server.listener;

import com.stcos.server.model.process.TaskName;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

/**
 * description
 *
 * @author kura
 * @version 1.0
 * @since 2023/7/9 15:59
 */
@Component
public class ConfirmTestReportListener extends ClientTaskListener {

    public ConfirmTestReportListener() {
        super(TaskName.NAME_TASK_33);
    }

    @Override
    public void complete(DelegateTask task) {
        super.complete(task);

        // 执行归档操作
    }
}
