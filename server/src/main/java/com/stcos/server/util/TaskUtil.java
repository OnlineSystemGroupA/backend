package com.stcos.server.util;

import com.stcos.server.entity.process.TaskConfig;
import com.stcos.server.entity.process.TaskConfigs.*;
import com.stcos.server.exception.ServerErrorException;
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

    public boolean isCompletable(Task task) {
        try {
            TaskConfig taskConfig = TASK_CONFIG_MAP.get(task.getName());
            return taskConfig.isCompletable(task);
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

}
