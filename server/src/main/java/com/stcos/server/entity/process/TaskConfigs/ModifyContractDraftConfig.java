package com.stcos.server.entity.process.TaskConfigs;

import com.stcos.server.entity.process.TaskConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 市场部修改合同草稿
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/26 15:19
 */
public class ModifyContractDraftConfig implements TaskConfig {

    @Override
    public String getEmailSubject() {
        return "修改合同草稿";
    }

    @Override
    public String getEmailText() {
        return "您好！之前提出的合同草稿不被用户接受。一项被指派给您的\"修改合同草稿\"任务已被创建，请尽快完成！";
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
