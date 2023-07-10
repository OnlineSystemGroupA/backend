package com.stcos.server.entity.process.configs;

import com.stcos.server.entity.process.TaskConfig;

import java.util.Set;

import static com.stcos.server.entity.form.FormType.TYPE_APPLICATION_FORM;
import static com.stcos.server.entity.form.FormType.TYPE_TEST_FUNCTION_FORM;

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
                Set.of(TYPE_APPLICATION_FORM, TYPE_TEST_FUNCTION_FORM),
                Set.of()
        );
    }

}
