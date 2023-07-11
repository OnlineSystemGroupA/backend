package com.stcos.server.entity.process.configs;

import com.stcos.server.entity.form.FormType;

import java.util.Set;

/**
 * 市场部修改报价
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/26 15:06
 */
public class ModifyQuotationConfig extends TaskConfig {

    public ModifyQuotationConfig() {
        super(
                Set.of(FormType.TYPE_QUOTATION_FORM),
                Set.of()
        );
    }

}
