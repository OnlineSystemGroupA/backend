package com.stcos.server.util;

import com.stcos.server.entity.process.TaskConfig;
import com.stcos.server.entity.process.configs.*;
import com.stcos.server.exception.ServerErrorException;
import com.stcos.server.service.FormService;
import lombok.experimental.UtilityClass;
import org.flowable.task.api.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Task 工具类，用于获取任务配置信息
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/29 13:52
 */

@UtilityClass
public class TaskUtil {

    private final Map<String, TaskConfig> TASK_CONFIG_MAP = new HashMap<>() {{
        put("填写申请表", new FillOutAppFormConfig());

        put("分配市场部人员", new AssignMarketingOperatorConfig());
        put("分配测试部人员", new AssignTestingOperatorConfig());
        put("市场部审核委托", new VerifyApplicationConfig());
        put("测试部审核委托", new VerifyApplicationConfig());
        put("市场部审核不通过，修改委托", new ModifyApplicationConfig());
        put("测试部审核不通过，修改委托", new ModifyApplicationConfig());

        put("市场部生成报价", new GenQuotationConfig());
        put("客户审核报价", new VerifyQuotationConfig());
        put("市场部修改报价", new ModifyQuotationConfig());

        put("市场部生成合同草稿", new GenContractDraftConfig());
        put("客户审核合同草稿", new VerifyContractDraftConfig());
        put("市场部修改合同草稿", new ModifyContractDraftConfig());
        put("客户填写合同", new FillOutContractConfig());
        put("市场部审核合同", new VerifyContractConfig());
        put("客户修改合同", new ModifyContractConfig());

        put("客户上传待测样品", new UploadSampleConfig());
        put("测试部审核样品", new VerifySampleConfig());
        put("客户重新上传样品", new ReUploadSampleConfig());

        put("测试部生成测试方案", new GenTestPlanConfig());
        put("质量部审核测试方案", new VerifyTestPlanConfig());
        put("测试部修改测试方案", new ModifyTestPlanConfig());

        put("测试部生成测试报告", new GenTestReportConfig());
        put("测试部主管审核测试报告", new VerifyTestReportConfig());
        put("用户审核测试报告", new VerifyTestReportConfig());
        put("授权签字人审核测试报告", new VerifyTestReportConfig());
        put("测试部修改测试文档", new ModifyTestReportConfig());
        put("测试文档归档", new ArchiveConfig());

        put("市场部发送测试报告", new SendTestReportConfig());
        put("用户确认测试报告", new ConfirmTestReportConfig());
    }};

    public boolean isCompletable(Task task, FormService formService) {
        try {
            TaskConfig taskConfig = TASK_CONFIG_MAP.get(task.getName());
            return taskConfig.isCompletable(task, formService);
        } catch (NullPointerException e) {
            throw new ServerErrorException(e);
        }
    }

    public List<String> getRequiredForms(String taskName) {
        try {
            TaskConfig taskConfig = TASK_CONFIG_MAP.get(taskName);
            return taskConfig.getRequiredForms();
        } catch (NullPointerException e) {
            throw new ServerErrorException(e);
        }
    }

    public String getEmailSubject(String taskName) {
        try {
            TaskConfig taskConfig = TASK_CONFIG_MAP.get(taskName);
            return taskConfig.getEmailSubject();
        } catch (NullPointerException e) {
            throw new ServerErrorException(e);
        }
    }

    public String getEmailText(String taskName, Map<String, String> variables) {
        try {
            TaskConfig taskConfig = TASK_CONFIG_MAP.get(taskName);
            return taskConfig.getEmailText(variables);
        } catch (NullPointerException e) {
            throw new ServerErrorException(e);
        }
    }

    public static boolean isAllowedRole(String taskName, String role) {
        try {
            TaskConfig taskConfig = TASK_CONFIG_MAP.get(taskName);
            return taskConfig.isAllowedRole(role);
        } catch (NullPointerException e) {
            throw new ServerErrorException(e);
        }
    }
}
