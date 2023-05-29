package com.stcos.server.entity.process.configs;

import com.stcos.server.entity.process.TaskConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户确认测试报告
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/22 18:40
 */
public class ConfirmTestReportConfig extends TaskConfig {

    public ConfirmTestReportConfig() {
        super("确认测试报告", "您好！您的一份测试委托的报告已经生成，请尽快前往确认。");
    }

    @Override
    public List<String> getRequiredForms() {
        return new ArrayList<>();
    }

}
