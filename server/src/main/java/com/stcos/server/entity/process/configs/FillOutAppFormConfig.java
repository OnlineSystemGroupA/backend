package com.stcos.server.entity.process.configs;

import com.stcos.server.entity.process.TaskConfig;

import java.util.ArrayList;
import java.util.List;

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
        super(null, null);
    }

    @Override
    public List<String> getRequiredForms() {
        return new ArrayList<>(){{
            add(TYPE_APPLICATION_FORM);
            add(TYPE_TEST_FUNCTION_FORM);
        }};
    }

}
