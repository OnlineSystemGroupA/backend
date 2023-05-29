package com.stcos.server.entity.process.configs;

import com.stcos.server.entity.process.TaskConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/27 20:44
 */
public class ModifyTestReportConfig extends TaskConfig {

    public ModifyTestReportConfig() {
        super("修改测试报告", "您好！之前提出的测试方案经审核未通过。一项被指派给您的\"修改测试报告\"任务已被创建，请尽快完成！");
    }

    @Override
    public List<String> getRequiredForms() {
        return new ArrayList<>(){{
            add("TestReportForm");
            add("TestRecordsForm");
            add("TestProblemForm");
        }};
    }
}
