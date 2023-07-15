package com.stcos.server.model.process.configs;

import com.stcos.server.model.form.FormType;

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
                Set.of(FormType.TYPE_CONTRACT_FORM),
                Set.of()
        );
    }

}
