package com.stcos.server.listener;

import com.stcos.server.entity.process.TaskName;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

/*
    _______ ________        __  ______            __                  __  __    _      __
   / ____(_) / / __ \__  __/ /_/ ____/___  ____  / /__________ ______/ /_/ /   (_)____/ /____  ____  ___  _____
  / /_  / / / / / / / / / / __/ /   / __ \/ __ \/ __/ ___/ __ `/ ___/ __/ /   / / ___/ __/ _ \/ __ \/ _ \/ ___/
 / __/ / / / / /_/ / /_/ / /_/ /___/ /_/ / / / / /_/ /  / /_/ / /__/ /_/ /___/ (__  ) /_/  __/ / / /  __/ /
/_/   /_/_/_/\____/\__,_/\__/\____/\____/_/ /_/\__/_/   \__,_/\___/\__/_____/_/____/\__/\___/_/ /_/\___/_/

 */

/**
 * "客户填写合同"任务监听器
 *
 * @author kura
 * @version 1.0
 * @since 2023/7/9 16:13
 */
@Component
public class FillOutContractListener extends ClientTaskListener {

    public FillOutContractListener() {
        super(TaskName.NAME_TASK_14);
    }

    @Override
    public void complete(DelegateTask task) {
        super.complete(task);

    }
}
