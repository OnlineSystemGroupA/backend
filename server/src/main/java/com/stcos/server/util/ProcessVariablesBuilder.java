package com.stcos.server.util;

import com.stcos.server.service.FileService;
import com.stcos.server.service.FormService;
import com.stcos.server.service.ProcessDetailsService;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程变量，用于初始化流程实例
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/6/22 15:42
 */

@UtilityClass
public class ProcessVariablesBuilder extends HashMap<String, Object> {

    /**
     * 构建流程变量对象
     *
     * @param clientId              发起委托流程客户的 ID
     * @param clientRealName        发起委托流程客户的姓名
     * @param marketingManagerId    市场部主管 ID
     * @param testingManagerId      测试部主管 ID
     * @param qualityManagerId      质量部主管 ID
     * @param signatoryId           授权签字人 ID
     * @param processDetailsService processDetailsService 实例
     * @param formService           formService 实例
     * @param fileService           formService 实例
     */
    public Map<String, Object> build(String clientId, String clientRealName,
                                     String marketingManagerId, String testingManagerId, String qualityManagerId, String signatoryId,
                                     ProcessDetailsService processDetailsService, FormService formService, FileService fileService) {
        Map<String, Object> processVariables = new HashMap<>();
        /*              任务参数                */
        processVariables.put("passable", true);            // 在遇到网关时使用，用于决定流程的下一个任务，默认值为 true
        processVariables.put("description", null);         // 上一个任务完成时，被分配人对任务结果的描述
        /*              流程参与者              */
        processVariables.put("client", clientId);                      // 发起委托流程的客户
        processVariables.put("marketingManager", marketingManagerId);  // 市场部主管
        processVariables.put("testingManager", testingManagerId);      // 测试部主管
        processVariables.put("qualityManager", qualityManagerId);      // 质量部主管
        processVariables.put("signatory", signatoryId);                // 授权签字人
        processVariables.put("marketingOperator", null);               // 市场部工作人员
        processVariables.put("testingOperator", null);                 // 测试部工作人员
        /*              项目简介                */
        processVariables.put("projectId", processDetailsService.create());            // 流程详情 ID，对应前端现实的项目编号
        processVariables.put("title", "");                       // 待测试的软件项目名称
        processVariables.put("startUser", clientRealName);       // 流程发起人姓名
        processVariables.put("assignee", clientRealName);        // 当前任务被分配人姓名
        processVariables.put("startDate", LocalDateTime.now());  // 流程发起日期
        processVariables.put("finishDate", null);                // 流程结束日期
        processVariables.put("state", "进行中");                  // 流程状态
        processVariables.put("currentTask", "填写申请表");         // 当前正在进行的任务

        List<String> users = List.of(clientId, marketingManagerId, testingManagerId, qualityManagerId, signatoryId);
        List<String> usersWithoutClient = List.of(marketingManagerId, testingManagerId, qualityManagerId, signatoryId);
        /*              表单元数据              */
        processVariables.put("ApplicationForm",
                formService.createMetadata("NST－04－JS002－2018－软件项目委托测试申请表", users));   // 软件项目委托测试申请表元数据 ID
        processVariables.put("ApplicationVerifyForm",
                formService.createMetadata("NST－04－JS002－2018－软件项目委托测试申请表（审核部分）", users));  // 软件项目委托测试申请表之审核信息元数据 ID
        processVariables.put("ContractForm",
                formService.createMetadata("NST－04－JS004－2018－软件委托测试合同", users));
        processVariables.put("DocumentReviewForm",
                formService.createMetadata("NST－04－JS014－2018 - 软件文档评审表", users));     // 软件文档评审表元数据 ID
        processVariables.put("QuotationForm",
                formService.createMetadata("报价单", users));
        processVariables.put("ReportVerifyForm",
                formService.createMetadata("NST－04－JS010－2018－测试报告检查表", users));      // 测试报告检查表元数据 ID
        processVariables.put("TestFunctionForm",
                formService.createMetadata("NST－04－JS003－2018－委托测试软件功能列表", users));  // 委托测试软件功能列表元数据 ID
        processVariables.put("TestPlanForm",
                formService.createMetadata("NST－04－JS006－2018－软件测试方案", users));         // 软件测试方案元数据 ID
        processVariables.put("TestPlanVerifyForm",
                formService.createMetadata("NST－04－JS013－2018 - 测试方案评审表", users));
        processVariables.put("TestProblemForm",
                formService.createMetadata("NST－04－JS011－2018－软件测试问题清单（电子记录）", users));
        processVariables.put("TestRecordsForm",
                formService.createMetadata("NST－04－JS009－2018－软件测试记录（电子记录）", users));
        processVariables.put("TestReportForm",
                formService.createMetadata("NST－04－JS007－2018－软件测试报告", usersWithoutClient));         // 软件测试报告元数据 ID
        processVariables.put("TestWorkCheckForm",
                formService.createMetadata("NST－04－JS012－2018－软件项目委托测试工作检查表", users));   // 软件项目委托测试工作检查表元数据 ID
        /*             样品元数据              */
        processVariables.put("sampleMetadata", fileService.createMetadata());    // 样品元数据 ID

        return processVariables;
    }
}