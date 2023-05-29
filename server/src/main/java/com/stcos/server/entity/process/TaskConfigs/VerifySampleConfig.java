package com.stcos.server.entity.process.TaskConfigs;

import com.stcos.server.entity.process.TaskConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/27 18:10
 */
public class VerifySampleConfig implements TaskConfig {

    @Override
    public String getEmailSubject() {
        return "审核样品";
    }

    @Override
    public String getEmailText() {
        return "您好！一项被指派给您的\"审核样品\"任务已被创建，请尽快完成！";
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
        return new ArrayList<>();
    }

    @Override
    public List<String> getWillDisWritableForms() {
        return new ArrayList<>();
    }
}
