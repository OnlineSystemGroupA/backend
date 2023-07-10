package com.stcos.server.entity.process.configs;

import com.stcos.server.entity.form.FormType;
import com.stcos.server.entity.process.TaskConfig;

import java.util.Set;

/**
 * 市场部修改合同草稿
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/26 15:19
 */
public class ModifyContractDraftConfig extends TaskConfig {

    public ModifyContractDraftConfig() {
        super(
                Set.of(FormType.TYPE_CONTRACT_FORM),
                Set.of()
        );
    }

}
