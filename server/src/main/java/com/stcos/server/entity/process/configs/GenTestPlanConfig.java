package com.stcos.server.entity.process.configs;

import com.stcos.server.entity.form.FormType;
import com.stcos.server.entity.process.TaskConfig;

import java.util.Set;

/**
 * 测试部生成测试方案
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/22 18:53
 */
public class GenTestPlanConfig extends TaskConfig {

    public GenTestPlanConfig() {
        super(
                Set.of(FormType.TYPE_TEST_PLAN_FORM),
                Set.of()
        );
    }

}
