package com.stcos.server.listener;

import com.stcos.server.model.process.ProcessDetails;
import com.stcos.server.model.process.ProcessVariables;
import com.stcos.server.model.process.TaskName;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * description
 *
 * @author kura
 * @version 1.0
 * @since 2023/7/9 16:20
 */

@Component
public class SendTestReportListener extends OperatorTaskListener {
    public SendTestReportListener() {
        super(TaskName.NAME_TASK_32);
    }

    @Override
    public void complete(DelegateTask task) {
        super.complete(task);
        ProcessDetails processDetails = processDetailsService.getById((Serializable) task.getVariable(ProcessVariables.VAR_PROJECT_ID));
        processDetails.setDueDate(LocalDateTime.now());
        processDetailsService.updateById(processDetails);
    }
}
