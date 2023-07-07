package com.stcos.server.entity.process;

import com.stcos.server.service.FormService;
import com.stcos.server.service.ProcessDetailsService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

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
     * @param clientId              发起委托流程的客户 ID
     * @param marketingManagerId    市场部主管 ID
     * @param testingManagerId      测试部主管 ID
     * @param qualityManagerId      质量部主管 ID
     * @param signatoryId           授权签字人 ID
     * @param processDetailsService
     * @param formService
     */
    public ProcessVariables(String clientId, String clientRealName,
                            String marketingManagerId, String testingManagerId, String qualityManagerId, String signatoryId,
                            ProcessDetailsService processDetailsService, FormService formService) {
        /*              任务参数                */
        put("passable", true);            // 在遇到网关时使用，用于决定流程的下一个任务，默认值为 true
        put("description", null);         // 上一个任务完成时，被分配人对任务结果的描述
        /*              流程参与者              */
        put("client", clientId);                      // 发起委托流程的客户
        put("marketingManager", marketingManagerId);  // 市场部主管
        put("testingManager", testingManagerId);      // 测试部主管
        put("qualityManager", qualityManagerId);      // 质量部主管
        put("signatory", signatoryId);                // 授权签字人
        put("marketingOperator", null);               // 市场部工作人员
        put("testingOperator", null);                 // 测试部工作人员
        /*              项目简介                */
        put("projectId", processDetailsService.create());            // 流程详情 ID，对应前端现实的项目编号
        put("title", "");                       // 待测试的软件项目名称
        put("startUser", clientRealName);       // 流程发起人姓名
        put("assignee", clientRealName);        // 当前任务被分配人姓名
        put("startDate", LocalDateTime.now());  // 流程发起日期
        put("finishDate", null);                // 流程结束日期
        put("state", "进行中");                  // 流程状态
        put("currentTask", "填写申请表");         // 当前正在进行的任务

        List<String> users = List.of(clientId, marketingManagerId, testingManagerId, qualityManagerId, signatoryId);
        List<String> usersWithoutClient = List.of(marketingManagerId, testingManagerId, qualityManagerId, signatoryId);
        /*              表单元数据              */
        put("ApplicationForm",
                formService.createMetadata("NST－04－JS002－2018－软件项目委托测试申请表", users));   // 软件项目委托测试申请表元数据 ID
        put("ApplicationVerifyForm",
                formService.createMetadata("NST－04－JS002－2018－软件项目委托测试申请表（审核部分）", users));  // 软件项目委托测试申请表之审核信息元数据 ID
        put("ContractForm",
                formService.createMetadata("NST－04－JS004－2018－软件委托测试合同", users));
        put("DocumentReviewForm",
                formService.createMetadata("NST－04－JS014－2018 - 软件文档评审表", users));     // 软件文档评审表元数据 ID
        put("QuotationForm",
                formService.createMetadata("报价单", users));
        put("ReportVerifyForm",
                formService.createMetadata("NST－04－JS010－2018－测试报告检查表", users));      // 测试报告检查表元数据 ID
        put("TestFunctionForm",
                formService.createMetadata("NST－04－JS003－2018－委托测试软件功能列表", users));  // 委托测试软件功能列表元数据 ID
        put("TestPlanForm",
                formService.createMetadata("NST－04－JS006－2018－软件测试方案", users));         // 软件测试方案元数据 ID
        put("TestPlanVerifyForm",
                formService.createMetadata("NST－04－JS013－2018 - 测试方案评审表", users));
        put("TestProblemForm",
                formService.createMetadata("NST－04－JS011－2018－软件测试问题清单（电子记录）", users));
        put("TestRecordsForm",
                formService.createMetadata("NST－04－JS009－2018－软件测试记录（电子记录）", users));
        put("TestReportForm",
                formService.createMetadata("NST－04－JS007－2018－软件测试报告", users));         // 软件测试报告元数据 ID
        put("TestWorkCheckForm",
                formService.createMetadata("NST－04－JS012－2018－软件项目委托测试工作检查表", users));   // 软件项目委托测试工作检查表元数据 ID
        /*             样品元数据              */
        put("sampleMetadata", null);    // 样品元数据 ID
    }
}
