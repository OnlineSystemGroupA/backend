package com.stcos.server.model.process.configs;

import com.stcos.server.model.form.FormType;

import java.util.Set;

/**
 * 填写申请表
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/21 22:43
 */
public class FillOutAppFormConfig extends TaskConfig {

    public FillOutAppFormConfig() {
        super(
                Set.of(FormType.TYPE_APPLICATION_FORM, FormType.TYPE_TEST_FUNCTION_FORM),
                Set.of()
        );
    }

}
