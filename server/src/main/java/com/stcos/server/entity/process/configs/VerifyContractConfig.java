package com.stcos.server.entity.process.configs;

import com.stcos.server.entity.process.TaskConfig;

import java.util.Set;

/**
 * 市场部审核合同
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/26 15:26
 */
public class VerifyContractConfig extends TaskConfig {

    public VerifyContractConfig() {
        super(
                Set.of(),
                Set.of()
        );
    }

}
