package com.stcos.server.listener;

import com.stcos.server.entity.form.FormType;
import com.stcos.server.entity.process.TaskName;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

/*
 _    __          _ ____      ___                ___            __  _             __    _      __
| |  / /__  _____(_) __/_  __/   |  ____  ____  / (_)________ _/ /_(_)___  ____  / /   (_)____/ /____  ____  ___  _____
| | / / _ \/ ___/ / /_/ / / / /| | / __ \/ __ \/ / / ___/ __ `/ __/ / __ \/ __ \/ /   / / ___/ __/ _ \/ __ \/ _ \/ ___/
| |/ /  __/ /  / / __/ /_/ / ___ |/ /_/ / /_/ / / / /__/ /_/ / /_/ / /_/ / / / / /___/ (__  ) /_/  __/ / / /  __/ /
|___/\___/_/  /_/_/  \__, /_/  |_/ .___/ .___/_/_/\___/\__,_/\__/_/\____/_/ /_/_____/_/____/\__/\___/_/ /_/\___/_/
                    /____/      /_/   /_/
 */

/**
 * “审核申请表”任务监听器
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/7/4 15:32
 */
@Component
public class VerifyApplicationListener extends OperatorTaskListener {

    public VerifyApplicationListener() {
        super(TaskName.NAME_TASK_04);
    }

    @Override
    public void create(DelegateTask task) {
        userService.addProcessInstance(task.getAssignee(), task.getProcessInstanceId());
        super.create(task);
    }

    @Override
    public void complete(DelegateTask task) {
        super.complete(task);
        Long formMetadataId = (Long) task.getVariable(FormType.TYPE_APPLICATION_VERIFY_FORM);
        formService.addReadPermission(formMetadataId, (String) task.getVariable("client")); // 为客户分配读权限
    }
}
