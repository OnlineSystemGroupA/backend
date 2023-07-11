package com.stcos.server.listener;

import com.stcos.server.entity.process.TaskName;
import org.springframework.stereotype.Component;

/**
 * description
 *
 * @author kura
 * @version 1.0
 * @since 2023/7/9 16:21
 */

@Component
public class VerifySampleListener extends OperatorTaskListener {
    public VerifySampleListener() {
        super(TaskName.NAME_TASK_20);
    }
}
