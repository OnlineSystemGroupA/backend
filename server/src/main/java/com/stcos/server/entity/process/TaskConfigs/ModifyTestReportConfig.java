package com.stcos.server.entity.process.TaskConfigs;

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
public class ModifyTestReportConfig implements TaskConfig {

    @Override
    public String getEmailSubject() {
        return "修改测试报告";
    }

    @Override
    public String getEmailText() {
        return "您好！之前提出的测试方案经审核未通过。一项被指派给您的\"修改测试报告\"任务已被创建，请尽快完成！";
    }

    @Override
    public List<String> getReadableForms() {
        return new ArrayList<>(){{
            add("ApplicationForm");
            add("TestPlanForm");
            add("TestReportForm");
        }};
    }

    @Override
    public List<String> getWritableForms() {
        return new ArrayList<>(){{
            add("TestReportForm");
        }};
    }

    @Override
    public List<String> getWillDisReadableForms() {
        return new ArrayList<>();
    }

    @Override
    public List<String> getWillDisWritableForms() {
        return new ArrayList<>(){{
            add("TestReportForm");
        }};
    }
}
