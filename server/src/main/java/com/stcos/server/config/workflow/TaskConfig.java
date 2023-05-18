package com.stcos.server.config.workflow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/18 11:46
 */

@Configuration
public class TaskConfig {

    @Bean
    public Map<String, List<String>> taskRequiredVarMap() {
        return new HashMap<>() {{
            put("填写委托", List.of("ApplicationForm"));
            put("分配工作人员", List.of("assignee"));  //被分配的工作人员
            put("审核用户委托", List.of("ApplicationVerifyForm", "passed"));
            put("用户修改委托", List.of("ApplicationForm"));
            put("生成报价", List.of("Quotation")); //报价
            put("生成合同草稿", List.of("ContractDraft")); //合同草稿
            put("签署合同", List.of("Contract")); //合同
            put("上传样品", List.of("Sample")); //样品
            put("生成测试方案", List.of("TestPlanForm"));
            put("生成测试报告", List.of("TestReportForm"));
            put("用户确认测试结果", List.of("ReportVerifyForm"));
            put("后续处理", List.of("DocumentReviewForm"));
        }};
    }

}
