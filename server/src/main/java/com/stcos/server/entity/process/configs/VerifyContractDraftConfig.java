package com.stcos.server.entity.process.configs;

import com.stcos.server.entity.process.TaskConfig;

import java.util.Set;

/**
 * 客户审核合同草稿
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/26 15:15
 */
public class VerifyContractDraftConfig extends TaskConfig {

    public VerifyContractDraftConfig() {
        super(
                Set.of(),
                Set.of()
        );
    }

}
