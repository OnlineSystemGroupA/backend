package com.stcos.server.config.workflow;

import com.stcos.server.entity.process.TaskConfigs.*;
import com.stcos.server.entity.process.TaskConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/18 11:46
 */

@Configuration
public class TaskConfigConfigurer {

    @Bean
    public Map<String, TaskConfig> taskConfigMap() {
        return new HashMap<>(){{
            put("填写申请表", new FillOutAppFormConfig());
            put("分配工作人员", new AssignOperatorConfig());
            put("用户确认测试报告", new ConfirmTestReportConfig());
            put("生成合同草稿", new GenContractDraftConfig());
            put("生成报价", new GenQuotationConfig());
            put("生成测试方案", new GenTestPlanConfig());
            put("生成测试报告", new GenTestReportConfig());
            put("用户修改委托", new ModifyApplicationConfig());
            put("后续处理", new PostProcessingConfig());
            put("发送测试报告", new SendTestReportConfig());
            put("签署合同", new SignContractConfig());
            put("上传样品", new UploadSampleConfig());
            put("审核用户委托", new VerifyApplicationConfig());
        }};
    }

}

/*
    问题：
    TestFunctionForm被哪个任务需要？
 */