package com.stcos.server.entity.process.configs;

import com.stcos.server.entity.form.FormType;

import java.util.Set;

/**
 * 测试部生成测试报告
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/22 18:54
 */
public class GenTestReportConfig extends TaskConfig {

    public GenTestReportConfig() {
        super(
                Set.of(FormType.TYPE_TEST_REPORT_FORM, FormType.TYPE_TEST_RECORDS_FORM, FormType.TYPE_TEST_PROBLEM_FORM),
                Set.of()
        );
    }

}
