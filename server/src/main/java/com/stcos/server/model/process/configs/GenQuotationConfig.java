package com.stcos.server.model.process.configs;

import com.stcos.server.model.form.FormType;

import java.util.Set;

/**
 * 市场部生成报价
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/22 18:52
 */
public class GenQuotationConfig extends TaskConfig {

    public GenQuotationConfig() {
        super(
                Set.of(FormType.TYPE_QUOTATION_FORM),
                Set.of()
        );
    }

}
