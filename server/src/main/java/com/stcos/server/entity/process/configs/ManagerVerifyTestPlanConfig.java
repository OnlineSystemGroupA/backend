package com.stcos.server.entity.process.configs;

import com.stcos.server.entity.process.TaskConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试部主管审核测试方案
 *
 * @author kura
 * @version 1.0
 * @since 2023/7/5 20:48
 */
public class ManagerVerifyTestPlanConfig extends TaskConfig {
    public ManagerVerifyTestPlanConfig() {
        super("审核测试方案", "您好！一项被指派给您的\"审核测试方案\"任务已被创建，请尽快完成！");
    }

    @Override
    public List<String> getRequiredForms() {
        return new ArrayList<>(){{
            add("TestPlanVerifyForm");
        }};
    }

}
