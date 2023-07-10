package com.stcos.server.entity.process.configs;

import com.stcos.server.entity.process.TaskConfig;

import java.util.Set;

/**
 * 客户签署合同
 *
 * @author kura
 * @version 1.0
 * @since 2023/7/6 10:51
 */
public class SignContractConfig extends TaskConfig {

    public SignContractConfig() {
        super(
                Set.of(),
                Set.of()
        );
    }

}
