package com.stcos.server.entity.process.configs;

import com.stcos.server.entity.process.TaskConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 市场部盖章合同
 *
 * @author kura
 * @version 1.0
 * @since 2023/7/6 10:55
 */
public class StampContractConfig extends TaskConfig {

    public StampContractConfig() {
        super("合同盖章", "您好！客户已签署合同。一项被指派给您的\"合同盖章\"任务已被创建，请尽快完成！");
    }

    @Override
    public List<String> getRequiredForms() {
        return new ArrayList<>();
    }
}
