package com.stcos.server.entity.process.configs;

import com.stcos.server.entity.form.FormType;
import com.stcos.server.entity.process.TaskConfig;

import java.util.Set;

/**
 * 市场部审核委托 & 测试部审核委托
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/22 19:04
 */
public class VerifyApplicationConfig extends TaskConfig {

    public VerifyApplicationConfig() {
        super(
                Set.of(FormType.TYPE_APPLICATION_VERIFY_FORM),
                Set.of()
        );
    }

}
