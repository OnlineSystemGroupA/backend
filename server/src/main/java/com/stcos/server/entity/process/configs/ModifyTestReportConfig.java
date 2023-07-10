package com.stcos.server.entity.process.configs;

import com.stcos.server.entity.form.FormType;
import com.stcos.server.entity.process.TaskConfig;

import java.util.Set;

/**
 * description
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/27 20:44
 */
public class ModifyTestReportConfig extends TaskConfig {

    public ModifyTestReportConfig() {
        super(
                Set.of(FormType.TYPE_TEST_REPORT_FORM, FormType.TYPE_TEST_RECORDS_FORM, FormType.TYPE_TEST_PROBLEM_FORM),
                Set.of()
        );
    }

}
