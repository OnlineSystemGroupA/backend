package com.stcos.server.entity.process.TaskConfigs;

import com.stcos.server.entity.process.TaskConfig;
import org.flowable.task.api.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/27 18:42
 */
public class ModifyTestPlanConfig extends TaskConfig {

    public ModifyTestPlanConfig() {
        super("修改测试方案", "您好！之前提出的测试方案经质量部审核未通过。一项被指派给您的\"修改测试方案\"任务已被创建，请尽快完成！");
    }

    @Override
    public List<String> getRequiredForms() {
        return new ArrayList<>(){{
            add("TestPlanForm");
        }};
    }

}
