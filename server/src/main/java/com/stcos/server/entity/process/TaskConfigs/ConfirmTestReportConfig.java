package com.stcos.server.entity.process.TaskConfigs;

import com.stcos.server.entity.process.TaskConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户确认测试报告
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/22 18:40
 */
public class ConfirmTestReportConfig implements TaskConfig {
    @Override
    public List<String> getRequiredForms() {
        return new ArrayList<>() {{
            add("ReportVerifyForm");
        }};
    }

    @Override
    public String getEmailSubject() {
        return "确认测试报告";
    }

    @Override
    public String getEmailText() {
        return "您好！您的一份测试委托的报告已经生成，请尽快前往确认。";
    }

    @Override
    public List<String> getReadableForms() {
        return new ArrayList<>() {{
            add("TestReportForm");
        }};
    }

    @Override
    public List<String> getWritableForms() {
        return new ArrayList<>() {{
            add("ReportVerifyForm");
        }};
    }

    @Override
    public List<String> getWillDisReadableForms() {
        return new ArrayList<>();
    }

    @Override
    public List<String> getWillDisWritableForms() {
        return new ArrayList<>() {{
            add("ReportVerifyForm");
        }};
    }
}
