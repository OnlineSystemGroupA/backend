package com.stcos.server.entity.process;

import com.stcos.server.entity.user.User;


import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * 流程变量，用于初始化流程实例
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/6/22 15:42
 */
public class ProcessVariables extends HashMap<String, Object> {

    /**
     * ProcessVariables
     *
     * @param client           发起委托流程的客户
     * @param marketingManager 市场部主管
     * @param testingManager   测试部主管
     * @param qualityManager   质量部主管
     * @param signatory        授权签字人
     */
    public ProcessVariables(User client, User marketingManager,
                            User testingManager, User qualityManager, User signatory) {
        /*              任务参数                */
        put("passable", true);            // 在遇到网关时使用，用于决定流程的下一个任务，默认值为 true
        put("description", null);         // 上一个任务完成时，被分配人对任务结果的描述
        /*              流程参与者              */
        put("client", client);                      // 发起委托流程的客户
        put("marketingManager", marketingManager);  // 市场部主管
        put("testingManager", testingManager);      // 测试部主管
        put("qualityManager", qualityManager);      // 质量部主管
        put("signatory", signatory);                // 授权签字人
        put("marketingOperator", null);             // 市场部工作人员
        put("testingOperator", null);               // 测试部工作人员
        /*              项目简介                */
        put("title", null);                     // 待测试的软件项目名称
        put("startUser", client.getRealName()); // 流程发起人姓名
        put("startDate", LocalDateTime.now());  // 流程发起日期
        put("finishDate", null);                // 流程结束日期
        put("state", "进行中");              // 流程状态
        put("currentTask", "填写申请表");     // 当前正在进行的任务
        /*              表单元数据              */
        put("ApplicationForm", null);        // 软件项目委托测试申请表元数据 ID
        put("ApplicationVerifyForm", null);  // 软件项目委托测试申请表之审核信息元数据 ID
        put("DocumentReviewForm", null);     // 软件文档评审表元数据 ID
        put("ReportVerifyForm", null);       // 测试报告检查表元数据 ID
        put("TestFunctionForm", null);       // 委托测试软件功能列表元数据 ID
        put("TestPlanForm", null);           // 软件测试方案元数据 ID
        put("TestReportForm", null);         // 软件测试报告元数据 ID
        put("TestWorkCheckForm", null);      // 软件项目委托测试工作检查表元数据 ID
        /*             样品元数据              */
        put("sampleMetadata", null);    // 样品元数据 ID
    }
}
