package com.stcos.server.entity.process.configs;

import com.stcos.server.entity.form.FormType;
import com.stcos.server.entity.process.TaskConfig;

import java.util.Set;

/**
 * 客户填写合同
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/26 15:23
 */
public class FillOutContractConfig extends TaskConfig {

    public FillOutContractConfig() {
        super(
                Set.of(FormType.TYPE_CONTRACT_FORM),
                Set.of()
        );
    }

}
