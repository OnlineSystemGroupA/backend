package com.stcos.server.entity.process.configs;

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
public class GenContractDraftConfig extends TaskConfig {

    public GenContractDraftConfig() {
        super("生成合同草稿", "您好！一项被指派给您的\"生成合同草稿\"任务已被创建，请尽快完成！");
    }


    @Override
    public List<String> getRequiredForms() {
        return new ArrayList<>(){{
            add("ContractForm");
        }};
    }


}
