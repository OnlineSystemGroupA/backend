package com.stcos.server.entity.process.configs;

import com.stcos.server.entity.form.FormType;
import com.stcos.server.entity.process.TaskConfig;

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
