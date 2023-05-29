package com.stcos.server.entity.process.TaskConfigs;

import com.stcos.server.entity.process.TaskConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 分配市场部人员 & 分配测试部人员
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/21 23:37
 */
public class AssignOperatorConfig implements TaskConfig {

    @Override
    public String getEmailSubject() {
        return "分配工作人员";
    }

    @Override
    public String getEmailText() {
        return "您好！一项被指派给您的\"分配工作人员\"任务已被创建，请尽快完成！";
    }

    @Override
    public List<String> getReadableForms() {
        return new ArrayList<>(){{
            add("ApplicationForm");
        }};
    }

    @Override
    public List<String> getWritableForms() {
        return new ArrayList<>();
    }

    @Override
    public List<String> getWillDisReadableForms() {
        return new ArrayList<>(){{
            add("ApplicationForm");
        }};
    }

    @Override
    public List<String> getWillDisWritableForms() {
        return new ArrayList<>();
    }
}
