package com.stcos.server.entity.process.configs;

import com.stcos.server.entity.form.FormType;
import com.stcos.server.entity.process.TaskConfig;

import java.util.Set;

/**
 * 测试部审核不通过，修改委托 & 市场部审核不通过，修改委托
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/22 18:56
 */
public class ModifyApplicationConfig extends TaskConfig {

    public ModifyApplicationConfig() {
        super(
                Set.of(FormType.TYPE_APPLICATION_FORM, FormType.TYPE_TEST_FUNCTION_FORM),
                Set.of()
        );
    }

}
