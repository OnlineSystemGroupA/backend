package com.stcos.server.entity.process.configs;

import com.stcos.server.entity.process.TaskConfig;

import java.util.Set;

/**
 * 用户审核报价
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/26 15:00
 */
public class VerifyQuotationConfig extends TaskConfig {

    public VerifyQuotationConfig() {
        super(
                Set.of(),
                Set.of()
        );
    }

}
