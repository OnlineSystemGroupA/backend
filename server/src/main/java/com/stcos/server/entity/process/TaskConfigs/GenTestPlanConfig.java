package com.stcos.server.entity.process.TaskConfigs;

import com.stcos.server.entity.process.TaskConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试部生成测试方案
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/22 18:53
 */
public class GenTestPlanConfig implements TaskConfig {

    @Override
    public String getEmailSubject() {
        return "生成测试方案";
    }

    @Override
    public String getEmailText() {
        return "您好！一项被指派给您的\"生成测试方案\"任务已被创建，请尽快完成！";
    }

    @Override
    public List<String> getReadableForms() {
        return new ArrayList<>(){{
            add("ApplicationForm");
            add("TestPlanForm");
        }};
    }

    @Override
    public List<String> getWritableForms() {
        return new ArrayList<>(){{
            add("TestPlanForm");
        }};
    }

    @Override
    public List<String> getWillDisReadableForms() {
        return new ArrayList<>();
    }

    @Override
    public List<String> getWillDisWritableForms() {
        return new ArrayList<>(){{
            add("TestPlanForm");
        }};
    }
}
