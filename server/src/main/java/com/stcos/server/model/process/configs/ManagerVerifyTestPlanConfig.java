package com.stcos.server.model.process.configs;

import com.stcos.server.model.form.FormType;

import java.util.Set;

/**
 * 测试部主管审核测试方案
 *
 * @author kura
 * @version 1.0
 * @since 2023/7/5 20:48
 */
public class ManagerVerifyTestPlanConfig extends TaskConfig {

    public ManagerVerifyTestPlanConfig() {
        super(
                Set.of(FormType.TYPE_TEST_PLAN_VERIFY_FORM),
                Set.of()
        );
    }

}
