package com.stcos.server.entity.process.configs;

import com.stcos.server.entity.form.FormType;
import com.stcos.server.entity.process.TaskConfig;

import java.util.Set;

/**
 * 质量部审核测试方案
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/27 18:35
 */
public class VerifyTestPlanConfig extends TaskConfig {

    public VerifyTestPlanConfig() {
        super(
                Set.of(FormType.TYPE_TEST_PLAN_VERIFY_FORM),
                Set.of()
        );
    }

}
