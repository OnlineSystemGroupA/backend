package com.stcos.server.listener;

import com.stcos.server.entity.form.FormType;
import com.stcos.server.entity.process.TaskName;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

/*
   ______           ____              __        __  _             __    _      __
  / ____/__  ____  / __ \__  ______  / /_____ _/ /_(_)___  ____  / /   (_)____/ /____  ____  ___  _____
 / / __/ _ \/ __ \/ / / / / / / __ \/ __/ __ `/ __/ / __ \/ __ \/ /   / / ___/ __/ _ \/ __ \/ _ \/ ___/
/ /_/ /  __/ / / / /_/ / /_/ / /_/ / /_/ /_/ / /_/ / /_/ / / / / /___/ (__  ) /_/  __/ / / /  __/ /
\____/\___/_/ /_/\___\_\__,_/\____/\__/\__,_/\__/_/\____/_/ /_/_____/_/____/\__/\___/_/ /_/\___/_/

 */

/**
 * "市场部生成报价" 任务监听器
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/7/5 14:18
 */
@Component
public class GenQuotationListener extends OperatorTaskListener {

    public GenQuotationListener() {
        super(TaskName.NAME_TASK_08);
    }

    @Override
    public void complete(DelegateTask task) {
        super.complete(task);
        Long formMetadataId = (Long) task.getVariable(FormType.TYPE_QUOTATION_FORM);
        formService.addReadPermission(formMetadataId, (String) task.getVariable("client")); // 为客户分配读权限

    }
}
