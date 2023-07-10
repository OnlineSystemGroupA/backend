package com.stcos.server.entity.process.configs;

import com.stcos.server.entity.process.TaskConfig;

import java.util.Set;

/**
 * 发送测试报告
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/22 19:00
 */
public class SendTestReportConfig extends TaskConfig {

    public SendTestReportConfig() {
        super(
                Set.of(),
                Set.of()
        );
    }

}
