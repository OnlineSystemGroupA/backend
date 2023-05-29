package com.stcos.server.entity.process.configs;

import com.stcos.server.entity.process.TaskConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 市场部审核合同
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/26 15:26
 */
public class VerifyContractConfig extends TaskConfig {

    public VerifyContractConfig() {
        super("审核合同", "您好！一项被指派给您的\"审核合同\"任务已被创建，请尽快完成！");
    }

    @Override
    public List<String> getRequiredForms() {
        return new ArrayList<>();
    }


}
