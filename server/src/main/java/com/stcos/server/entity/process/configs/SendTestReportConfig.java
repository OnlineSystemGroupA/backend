package com.stcos.server.entity.process.configs;

import com.stcos.server.entity.process.TaskConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 发送测试报告
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/22 19:00
 */
public class SendTestReportConfig extends TaskConfig {

    public SendTestReportConfig() {
        super("测试报告待发送", "您好！一项被指派给您的\"发送测试报告\"任务已被创建，请尽快完成！");
    }

    @Override
    public List<String> getRequiredForms() {
        return new ArrayList<>();
    }

}
