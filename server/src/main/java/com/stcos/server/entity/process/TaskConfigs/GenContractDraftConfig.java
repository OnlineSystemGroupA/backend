package com.stcos.server.entity.process.TaskConfigs;

import com.stcos.server.entity.process.TaskConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 市场部生成合同草稿
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/22 18:50
 */
public class GenContractDraftConfig implements TaskConfig {

    @Override
    public String getEmailSubject() {
        return "生成合同草稿";
    }

    @Override
    public String getEmailText() {
        return "您好！一项被指派给您的\"生成合同草稿\"任务已被创建，请尽快完成！";
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
