package com.stcos.server.listener;

import com.stcos.server.entity.process.TaskName;
import org.springframework.stereotype.Component;

/**
 * description
 *
 * @author kura
 * @version 1.0
 * @since 2023/7/9 16:22
 */

@Component
public class VerifyTestReportListener extends OperatorTaskListener {

    public VerifyTestReportListener() {
        super(TaskName.NAME_TASK_27);
    }
}
