package com.stcos.server.util;

import com.stcos.server.service.FileService;
import com.stcos.server.service.FormService;
import com.stcos.server.service.ProcessDetailsService;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.stcos.server.entity.process.ProcessVariables.*;

/*
    ____                                _    __           _       __    __          ____        _ __    __
   / __ \_________  ________  _________| |  / /___ ______(_)___ _/ /_  / /__  _____/ __ )__  __(_) /___/ /__  _____
  / /_/ / ___/ __ \/ ___/ _ \/ ___/ ___/ | / / __ `/ ___/ / __ `/ __ \/ / _ \/ ___/ __  / / / / / / __  / _ \/ ___/
 / ____/ /  / /_/ / /__/  __(__  |__  )| |/ / /_/ / /  / / /_/ / /_/ / /  __(__  ) /_/ / /_/ / / / /_/ /  __/ /
/_/   /_/   \____/\___/\___/____/____/ |___/\__,_/_/  /_/\__,_/_.___/_/\___/____/_____/\__,_/_/_/\__,_/\___/_/

 */

/**
 * 流程变量，用于初始化流程实例
 *
 * @author kura
 * @version 1.5
 * @since 2023/6/22 15:42
 */
@UtilityClass
public class ProcessVariablesBuilder {

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
        Long projectId = processDetailsService.create();
        Map<String, Object> variableMap = new HashMap<>();
        /*              任务参数                */
        variableMap.put(VAR_PASSABLE, true);            // 在遇到网关时使用，用于决定流程的下一个任务，默认值为 true
        variableMap.put(VAR_DESCRIPTION, null);         // 上一个任务完成时，被分配人对任务结果的描述
        /*              流程参与者              */
        variableMap.put(VAR_CLIENT, clientId);                       // 发起委托流程的客户
        variableMap.put(VAR_MARKETING_MANAGER, marketingManagerId);  // 市场部主管
        variableMap.put(VAR_TESTING_MANAGER, testingManagerId);      // 测试部主管
        variableMap.put(VAR_QUALITY_MANAGER, qualityManagerId);      // 质量部主管
        variableMap.put(VAR_SIGNATORY, signatoryId);                 // 授权签字人
        variableMap.put(VAR_MARKETING_OPERATOR, null);               // 市场部工作人员
        variableMap.put(VAR_TESTING_OPERATOR, null);                 // 测试部工作人员
        /*              项目简介                */
        variableMap.put(VAR_PROJECT_ID, projectId);    // 流程详情 ID，对应前端现实的项目编号
        variableMap.put(VAR_TITLE, "");                                     // 待测试的软件项目名称
        variableMap.put(VAR_START_USER, clientRealName);                    // 流程发起人姓名
        variableMap.put(VAR_ASSIGNEE, clientRealName);                      // 当前任务被分配人姓名
        variableMap.put(VAR_START_DATE, LocalDateTime.now());               // 流程发起日期
        variableMap.put(VAR_FINISH_DATE, null);                             // 流程结束日期
        variableMap.put(VAR_STATE, "进行中");                    // 流程状态
        variableMap.put(VAR_CURRENT_TASK, "填写申请表");          // 当前正在进行的任务
        /*              表单元数据              */
        variableMap.put(VAR_APPLICATION_FORM_METADATA,
                formService.createMetadata(projectId, "软件项目委托测试申请表"));        // 软件项目委托测试申请表元数据 ID
        variableMap.put(VAR_APPLICATION_VERIFY_FORM_METADATA,
                formService.createMetadata(projectId, "软件项目委托测试申请表（审核部分）")); // 软件项目委托测试申请表之审核信息元数据 ID
        variableMap.put(VAR_CONTRACT_FORM_METADATA,
                formService.createMetadata(projectId, "软件委托测试合同"));           // 软件委托测试合同元数据 ID
        variableMap.put(VAR_CONFIDENTIALITY_FORM_METADATA,
                formService.createMetadata(projectId, "软件项目委托测试保密协议"));    // 软件项目委托测试保密协议元数据 ID
        variableMap.put(VAR_DOCUMENT_REVIEW_FORM_METADATA,
                formService.createMetadata(projectId, "软件文档评审表"));            // 软件文档评审表元数据 ID
        variableMap.put(VAR_QUOTATION_FORM_METADATA,
                formService.createMetadata(projectId, "报价单"));                  // 报价单元数据 ID
        variableMap.put(VAR_REPORT_VERIFY_FORM_METADATA,
                formService.createMetadata(projectId, "测试报告检查表"));             // 测试报告检查表元数据 ID
        variableMap.put(VAR_TEST_FUNCTION_FORM_METADATA,
                formService.createMetadata(projectId, "委托测试软件功能列表"));        // 委托测试软件功能列表元数据 ID
        variableMap.put(VAR_TEST_PLAN_FORM_METADATA,
                formService.createMetadata(projectId, "软件测试方案"));              // 软件测试方案元数据 ID
        variableMap.put(VAR_TEST_PLAN_VERIFY_FORM_METADATA,
                formService.createMetadata(projectId, "测试方案评审表"));            // 测试方案评审表元数据 ID
        variableMap.put(VAR_TEST_PROBLEM_FORM_METADATA,
                formService.createMetadata(projectId, "软件测试问题清单"));          // 软件测试问题清单元数据 ID
        variableMap.put(VAR_TEST_RECORDS_FORM_METADATA,
                formService.createMetadata(projectId, "软件测试记录"));             // 软件测试记录元数据 ID
        variableMap.put(VAR_TEST_REPORT_FORM_METADATA,
                formService.createMetadata(projectId, "软件测试报告")); // 软件测试报告元数据 ID
        variableMap.put(VAR_TEST_WORK_CHECK_FORM_METADATA,
                formService.createMetadata(projectId, "软件项目委托测试工作检查表"));   // 软件项目委托测试工作检查表元数据 ID
        /*             样品元数据              */
        variableMap.put(VAR_SAMPLE_METADATA, fileService.createMetadata());             // 样品元数据 ID
        return variableMap;
    }
}
