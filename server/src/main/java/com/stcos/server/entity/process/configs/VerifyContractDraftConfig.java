package com.stcos.server.entity.process.configs;

import com.stcos.server.entity.process.TaskConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户审核合同草稿
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/26 15:15
 */
public class VerifyContractDraftConfig extends TaskConfig {

    public VerifyContractDraftConfig() {
        super("审核合同草稿", "您好！一份由您提起的软件测试委托已生成合同草稿，请尽快前方确认。");
    }

    @Override
    public List<String> getRequiredForms() {
        return new ArrayList<>();
    }

}
