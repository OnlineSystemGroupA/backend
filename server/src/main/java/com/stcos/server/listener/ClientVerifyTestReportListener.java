package com.stcos.server.listener;

import com.stcos.server.model.process.TaskName;
import org.springframework.stereotype.Component;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/7/10 19:42
 */
@Component
public class ClientVerifyTestReportListener extends ClientTaskListener {
    public ClientVerifyTestReportListener() {
        super(TaskName.NAME_TASK_28);
    }
}
