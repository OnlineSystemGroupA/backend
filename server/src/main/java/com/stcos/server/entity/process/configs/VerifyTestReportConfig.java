package com.stcos.server.entity.process.configs;

import com.stcos.server.entity.process.TaskConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试部主管审核测试报告 & 客户审核测试报告 & 授权签字人审核测试报告
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/27 20:41
 */
public class VerifyTestReportConfig extends TaskConfig {

    public VerifyTestReportConfig() {
        super("审核测试报告", "您好！一项被指派给您的\"审核测试报告\"任务已被创建，请尽快完成！");
    }
    @Override
    public List<String> getRequiredForms() {
        return new ArrayList<>(){{
            add("ReportVerifyForm");
        }};
    }


}
