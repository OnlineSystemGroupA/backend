package com.stcos.server.entity.process.configs;

import com.stcos.server.entity.process.TaskConfig;
import com.stcos.server.service.FormService;
import org.flowable.task.api.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 分配测试部人员
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/21 23:37
 */
public class AssignTestingOperatorConfig extends TaskConfig {

    public AssignTestingOperatorConfig() {
        super("分配工作人员", "您好！一项被指派给您的\"分配工作人员\"任务已被创建，请尽快完成！");
    }


    @Override
    public boolean isCompletable(Task task, FormService formService) {
        Map<String, Object> processVariables = task.getProcessVariables();
        return processVariables.get("testingOperator") != null;
    }

    @Override
    public List<String> getRequiredForms() {
        return new ArrayList<>();
    }

}
