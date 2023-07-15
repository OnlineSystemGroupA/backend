package com.stcos.server.model.process.configs;

import com.stcos.server.model.form.FormType;

import java.util.Set;

/**
 * 市场部生成合同草稿
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/22 18:50
 */
public class GenContractDraftConfig extends TaskConfig {

    public GenContractDraftConfig() {
        super(
                Set.of(FormType.TYPE_CONTRACT_FORM, FormType.TYPE_CONFIDENTIALITY_FORM),
                Set.of()
        );
    }

}
