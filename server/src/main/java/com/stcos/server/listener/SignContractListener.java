package com.stcos.server.listener;

import com.stcos.server.model.process.TaskName;
import org.springframework.stereotype.Component;

/**
 * description
 *
 * @author kura
 * @version 1.0
 * @since 2023/7/9 16:20
 */

@Component
public class SignContractListener extends ClientTaskListener {


    public SignContractListener() {
        super(TaskName.NAME_TASK_17);
    }
}
