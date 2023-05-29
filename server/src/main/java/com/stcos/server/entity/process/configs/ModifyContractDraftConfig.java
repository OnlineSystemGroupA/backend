package com.stcos.server.entity.process.configs;

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
public class ModifyContractDraftConfig extends TaskConfig {


    public ModifyContractDraftConfig() {
        super("修改合同草稿", "您好！之前提出的合同草稿不被用户接受。一项被指派给您的\"修改合同草稿\"任务已被创建，请尽快完成！");
    }

    @Override
    public List<String> getRequiredForms() {
        return new ArrayList<>();
    }
}
