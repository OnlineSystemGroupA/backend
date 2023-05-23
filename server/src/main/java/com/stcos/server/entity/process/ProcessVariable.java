package com.stcos.server.entity.process;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/19 16:37
 */
public class ProcessVariable extends HashMap<String, Object> {

    public ProcessVariable(String startUser) {
        put("passable", true);                  // 流程控制变量
        put("description", null);               // 已完成的最后一个任务分配人的描述
        put("startUser", startUser);            // 流程发起人ID
        put("startDate", LocalDateTime.now());  // 流程启动时间
        put("finishDate", null);                // 流程结束时间
        put("state", "进行中");                  // 流程状态
        put("currentTask", "填写申请表");         // 当前正在进行的任务
        put("ApplicationForm", null);           // 委托申请表
        put("ApplicationVerifyForm", null);     // 委托申请表之审核信息
        put("DocumentReviewForm", null);        // 软件文档评审表
        put("ReportVerifyForm", null);          // 测试报告检查表
        put("TestFunctionForm", null);          // 委托测试软件功能列表
        put("TestPlanForm", null);              // 软件测试方案
        put("TestReportForm", null);            // 软件测试报告
        put("TestWorkCheckForm", null);         // 软件项目委托测试工作检查表
        put("Sample", null);                    // 样品
    }
}
