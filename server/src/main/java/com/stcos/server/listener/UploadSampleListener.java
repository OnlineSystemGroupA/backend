package com.stcos.server.listener;

import com.stcos.server.entity.process.TaskName;
import org.springframework.stereotype.Component;

/**
 * description
 *
 * @author kura
 * @version 1.0
 * @since 2023/7/9 16:20
 */

@Component
public class UploadSampleListener extends ClientTaskListener {

    public UploadSampleListener() {
        super(TaskName.NAME_TASK_19);
    }
}
