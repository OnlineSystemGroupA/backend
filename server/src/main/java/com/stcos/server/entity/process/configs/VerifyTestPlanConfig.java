package com.stcos.server.entity.process.configs;

import com.stcos.server.entity.process.TaskConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 质量部审核测试方案
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/27 18:35
 */
public class VerifyTestPlanConfig extends TaskConfig {

    public VerifyTestPlanConfig() {
        super("审核测试方案", "您好！一项被指派给您的\"审核测试方案\"任务已被创建，请尽快完成！");
    }

    @Override
    public List<String> getRequiredForms() {
        return new ArrayList<>(){{
            add("TestPlanVerifyForm");
        }};
    }



}
