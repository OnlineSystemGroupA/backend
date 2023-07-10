package com.stcos.server.entity.process.configs;

import com.stcos.server.entity.form.FormType;
import com.stcos.server.entity.process.TaskConfig;

import java.util.Set;

/**
 * 测试部主管审核测试报告 & 客户审核测试报告 & 授权签字人审核测试报告
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/27 20:41
 */
public class VerifyTestReportConfig extends TaskConfig {

    public VerifyTestReportConfig() {
        super(
                Set.of(FormType.TYPE_REPORT_VERIFY_FORM),
                Set.of()
        );
    }

}
