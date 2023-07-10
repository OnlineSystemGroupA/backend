package com.stcos.server.listener;

import com.stcos.server.entity.process.TaskName;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

/*
    ___              _             __  ___           __        __  _             ____                        __             __    _      __
   /   |  __________(_)___ _____  /  |/  /___ ______/ /_____  / /_(_)___  ____ _/ __ \____  ___  _________ _/ /_____  _____/ /   (_)____/ /____  ____  ___  _____
  / /| | / ___/ ___/ / __ `/ __ \/ /|_/ / __ `/ ___/ //_/ _ \/ __/ / __ \/ __ `/ / / / __ \/ _ \/ ___/ __ `/ __/ __ \/ ___/ /   / / ___/ __/ _ \/ __ \/ _ \/ ___/
 / ___ |(__  |__  ) / /_/ / / / / /  / / /_/ / /  / ,< /  __/ /_/ / / / / /_/ / /_/ / /_/ /  __/ /  / /_/ / /_/ /_/ / /  / /___/ (__  ) /_/  __/ / / /  __/ /
/_/  |_/____/____/_/\__, /_/ /_/_/  /_/\__,_/_/  /_/|_|\___/\__/_/_/ /_/\__, /\____/ .___/\___/_/   \__,_/\__/\____/_/  /_____/_/____/\__/\___/_/ /_/\___/_/
                   /____/                                              /____/     /_/
 */

/**
 * “分配市场部人员”任务监听器
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/30 10:52
 */

@Component
public class AssignMarketingOperatorListener extends OperatorTaskListener {

    public AssignMarketingOperatorListener() {
        super(TaskName.NAME_TASK_02);
    }

    @Override
    public void create(DelegateTask task) {
        super.create(task);
        String uid = task.getAssignee();
        userService.addProcessInstance(uid, task.getProcessInstanceId());   // 使流程对市场部主管可见
    }

    @Override
    public void complete(DelegateTask task) {
        super.complete(task);
        String marketingOperatorUid = (String) task.getVariable("marketingOperator", false);
        userService.addProcessInstance(marketingOperatorUid, task.getProcessInstanceId()); // 使流程对市场部员工可见
    }

}
