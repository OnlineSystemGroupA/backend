package com.stcos.server.listener;

import com.stcos.server.entity.process.TaskName;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

/*
    ___              _           ______          __  _             ____                        __             __    _      __
   /   |  __________(_)___ _____/_  __/__  _____/ /_(_)___  ____ _/ __ \____  ___  _________ _/ /_____  _____/ /   (_)____/ /____  ____  ___  _____
  / /| | / ___/ ___/ / __ `/ __ \/ / / _ \/ ___/ __/ / __ \/ __ `/ / / / __ \/ _ \/ ___/ __ `/ __/ __ \/ ___/ /   / / ___/ __/ _ \/ __ \/ _ \/ ___/
 / ___ |(__  |__  ) / /_/ / / / / / /  __(__  ) /_/ / / / / /_/ / /_/ / /_/ /  __/ /  / /_/ / /_/ /_/ / /  / /___/ (__  ) /_/  __/ / / /  __/ /
/_/  |_/____/____/_/\__, /_/ /_/_/  \___/____/\__/_/_/ /_/\__, /\____/ .___/\___/_/   \__,_/\__/\____/_/  /_____/_/____/\__/\___/_/ /_/\___/_/
                   /____/                                /____/     /_/
 */

/**
 * “分配测试部人员”任务监听器
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/30 10:53
 */
@Component
public class AssignTestingOperatorListener extends OperatorTaskListener {

    public AssignTestingOperatorListener() {
        super(TaskName.NAME_TASK_03);
    }

    @Override
    public void create(DelegateTask task) {
        super.create(task);
        // 使流程对测试部部主管可见
        String uid = task.getAssignee();
        userService.addProcessInstance(uid, task.getProcessInstanceId());
    }

    @Override
    public void complete(DelegateTask task) {
        super.complete(task);
        // 使流程对测试部员工可见
        String testingOperatorUid = (String) task.getVariable("testingOperator", false);
        userService.addProcessInstance(testingOperatorUid, task.getProcessInstanceId());
    }
}
