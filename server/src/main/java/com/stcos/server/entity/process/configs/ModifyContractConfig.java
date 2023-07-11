package com.stcos.server.entity.process.configs;

import com.stcos.server.entity.form.FormType;

import java.util.Set;

/**
 * 用户修改合同
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/26 15:28
 */
public class ModifyContractConfig extends TaskConfig {

    public ModifyContractConfig() {
        super(
                Set.of(FormType.TYPE_CONTRACT_FORM),
                Set.of()
        );
    }

}
