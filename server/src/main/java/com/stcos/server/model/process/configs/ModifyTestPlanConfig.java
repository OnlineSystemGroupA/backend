package com.stcos.server.model.process.configs;

import com.stcos.server.model.form.FormType;

import java.util.Set;

/**
 * description
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/27 18:42
 */
public class ModifyTestPlanConfig extends TaskConfig {

    public ModifyTestPlanConfig() {
        super(
                Set.of(FormType.TYPE_TEST_PLAN_FORM),
                Set.of()
        );
    }

}
