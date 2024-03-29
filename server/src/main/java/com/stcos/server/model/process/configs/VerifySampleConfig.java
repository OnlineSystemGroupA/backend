package com.stcos.server.model.process.configs;

import com.stcos.server.model.form.FormType;

import java.util.Set;

/**
 * description
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/27 18:10
 */
public class VerifySampleConfig extends TaskConfig {

    public VerifySampleConfig() {
        super(
                Set.of(FormType.TYPE_DOCUMENT_REVIEW_FORM),
                Set.of()
        );
    }

}
