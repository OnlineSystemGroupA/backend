package com.stcos.server.util;

import com.stcos.server.entity.process.TaskConfig;
import com.stcos.server.entity.process.TaskName;
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

    /**
     * 任务名到任务配置对象的映射
     */
    private final Map<String, TaskConfig> TASK_CONFIG_MAP = new HashMap<>() {{
        /*    填写申请表    */
        put(TaskName.NAME_TASK_01, new FillOutAppFormConfig());
        /*     审核委托    */
        put(TaskName.NAME_TASK_02, new AssignMarketingOperatorConfig());
        put(TaskName.NAME_TASK_03, new AssignTestingOperatorConfig());
        put(TaskName.NAME_TASK_04, new VerifyApplicationConfig());
        put(TaskName.NAME_TASK_05, new VerifyApplicationConfig());
        put(TaskName.NAME_TASK_06, new ModifyApplicationConfig());
        put(TaskName.NAME_TASK_07, new ModifyApplicationConfig());
        /*     生成报价    */
        put(TaskName.NAME_TASK_08, new GenQuotationConfig());
        put(TaskName.NAME_TASK_09, new VerifyQuotationConfig());
        put(TaskName.NAME_TASK_10, new ModifyQuotationConfig());
        /*   生成合同草稿   */
        put(TaskName.NAME_TASK_11, new GenContractDraftConfig());
        put(TaskName.NAME_TASK_12, new VerifyContractDraftConfig());
        put(TaskName.NAME_TASK_13, new ModifyContractDraftConfig());
        put(TaskName.NAME_TASK_14, new FillOutContractConfig());
        put(TaskName.NAME_TASK_15, new VerifyContractConfig());
        put(TaskName.NAME_TASK_16, new ModifyContractConfig());
        /*     签署合同    */
        put(TaskName.NAME_TASK_17, new SignContractConfig());
        put(TaskName.NAME_TASK_18, new StampContractConfig());
        /*     上传样品    */
        put(TaskName.NAME_TASK_19, new UploadSampleConfig());
        put(TaskName.NAME_TASK_20, new VerifySampleConfig());
        put(TaskName.NAME_TASK_21, new ReUploadSampleConfig());
        /*   生成测试方案   */
        put(TaskName.NAME_TASK_22, new GenTestPlanConfig());
        put(TaskName.NAME_TASK_23, new VerifyTestPlanConfig());
        put(TaskName.NAME_TASK_24, new ModifyTestPlanConfig());
        put(TaskName.NAME_TASK_25, new ManagerVerifyTestPlanConfig());
        /*   生成测试报告   */
        put(TaskName.NAME_TASK_26, new GenTestReportConfig());
        put(TaskName.NAME_TASK_27, new VerifyTestReportConfig());
        put(TaskName.NAME_TASK_28, new VerifyTestReportConfig());
        put(TaskName.NAME_TASK_29, new VerifyTestReportConfig());
        put(TaskName.NAME_TASK_30, new ModifyTestReportConfig());
        put(TaskName.NAME_TASK_31, new ArchiveConfig());
        /*   确认测试报告   */
        put(TaskName.NAME_TASK_32, new SendTestReportConfig());
        put(TaskName.NAME_TASK_33, new ConfirmTestReportConfig());
    }};

    /**
     * 根据任务名获取对应的任务配置类对象
     *
     * @return TaskConfig 对象
     */
    public static TaskConfig getTaskConfig(String taskName) {
        return TASK_CONFIG_MAP.get(taskName);
    }

    /**
     * 判断当前任务是否满足完成条件
     *
     * @param task        当前任务
     * @param formService FormService 对象
     * @return true 表示当前任务满足完成条件，否则不满足完成条件
     */
    public boolean isCompletable(Task task, FormService formService) {
        try {
            TaskConfig taskConfig = TASK_CONFIG_MAP.get(task.getName());
            return taskConfig.isCompletable(task, formService);
        } catch (NullPointerException e) {
            throw new ServerErrorException(e);
        }
    }

    /**
     * 获取当前任务中需要被修改或创建的表单
     *
     * @param taskName 任务名
     * @return 需要被修改被创建的表单列表
     */
    public List<String> getRequiredForms(String taskName) {
        try {
            TaskConfig taskConfig = TASK_CONFIG_MAP.get(taskName);
            return taskConfig.getRequiredForms();
        } catch (NullPointerException e) {
            throw new ServerErrorException(e);
        }
    }

/*
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
*/

    /**
     * 任务名到任务所在分组序号的映射
     */
    private final Map<String, Integer> TASK_GROUP_MAP = new HashMap<>() {{
        /*    申请创建    */
        put(TaskName.NAME_TASK_01, 0);
        /*    申请审核    */
        put(TaskName.NAME_TASK_02, 1);
        put(TaskName.NAME_TASK_03, 1);
        put(TaskName.NAME_TASK_04, 1);
        put(TaskName.NAME_TASK_05, 1);
        put(TaskName.NAME_TASK_06, 1);
        put(TaskName.NAME_TASK_07, 1);
        /*    报价生成   */
        put(TaskName.NAME_TASK_08, 2);
        put(TaskName.NAME_TASK_09, 2);
        put(TaskName.NAME_TASK_10, 2);
        /*    合同谈判   */
        put(TaskName.NAME_TASK_11, 3);
        put(TaskName.NAME_TASK_12, 3);
        put(TaskName.NAME_TASK_13, 3);
        put(TaskName.NAME_TASK_14, 3);
        put(TaskName.NAME_TASK_15, 3);
        put(TaskName.NAME_TASK_16, 3);
        put(TaskName.NAME_TASK_17, 3);
        put(TaskName.NAME_TASK_18, 3);
        /*    样品上传   */
        put(TaskName.NAME_TASK_19, 4);
        put(TaskName.NAME_TASK_20, 4);
        put(TaskName.NAME_TASK_21, 4);
        /*    计划制定   */
        put(TaskName.NAME_TASK_22, 5);
        put(TaskName.NAME_TASK_23, 5);
        put(TaskName.NAME_TASK_24, 5);
        put(TaskName.NAME_TASK_25, 5);
        /*    测试进行   */
        put(TaskName.NAME_TASK_26, 6);
        /*    报告审核   */
        put(TaskName.NAME_TASK_27, 7);
        put(TaskName.NAME_TASK_28, 7);
        put(TaskName.NAME_TASK_29, 7);
        put(TaskName.NAME_TASK_30, 7);
        /*    归档处理   */
        put(TaskName.NAME_TASK_31, 8);
        /*    用户确认   */
        put(TaskName.NAME_TASK_32, 9);
        put(TaskName.NAME_TASK_33, 9);
    }};

    /**
     * 根据任务名获取任务所处于的分组
     *
     * @param taskName 指定任务名
     * @return 任务所在的分组序号
     */
    public int getTaskGroupIndex(String taskName) {
        return TASK_GROUP_MAP.get(taskName);
    }
}
