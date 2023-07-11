package com.stcos.server.listener;

import com.stcos.server.entity.process.TaskName;
import org.springframework.stereotype.Component;

/**
 * description
 *
 * @author kura
 * @version 1.0
 * @since 2023/7/9 16:19
 */

@Component
public class ModifyTestPlanListener extends OperatorTaskListener {
    public ModifyTestPlanListener() {
        super(TaskName.NAME_TASK_24);
    }
}
