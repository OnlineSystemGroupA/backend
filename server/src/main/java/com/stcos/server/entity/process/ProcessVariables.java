package com.stcos.server.entity.process;

import java.util.Set;

/**
 * 统一定义流程变量名
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/7/10 19:52
 */
public class ProcessVariables {
    /*              任务参数                */
    public static final String VAR_PASSABLE = "passable";
    public static final String VAR_DESCRIPTION = "description";
    /*              流程参与者              */
    public static final String VAR_CLIENT = "client";
    public static final String VAR_MARKETING_MANAGER = "marketingManager";
    public static final String VAR_TESTING_MANAGER = "testingManager";
    public static final String VAR_QUALITY_MANAGER = "qualityManager";
    public static final String VAR_SIGNATORY = "signatory";
    public static final String VAR_MARKETING_OPERATOR = "marketingOperator";
    public static final String VAR_TESTING_OPERATOR = "testingOperator";
    /*              项目简介                */
    public static final String VAR_PROJECT_ID = "projectId";
    public static final String VAR_TITLE = "title";
    public static final String VAR_START_USER = "startUser";
    public static final String VAR_ASSIGNEE = "assignee";
    public static final String VAR_START_DATE = "startDate";
    public static final String VAR_FINISH_DATE = "finishDate";
    public static final String VAR_STATE = "state";
    public static final String VAR_CURRENT_TASK = "currentTask";
    /*              表单元数据              */
    public static final String VAR_APPLICATION_FORM_METADATA = "ApplicationForm";
    public static final String VAR_APPLICATION_VERIFY_FORM_METADATA = "ApplicationVerifyForm";
    public static final String VAR_CONTRACT_FORM_METADATA = "ContractForm";
    public static final String VAR_CONFIDENTIALITY_FORM_METADATA = "ConfidentialityForm";
    public static final String VAR_DOCUMENT_REVIEW_FORM_METADATA = "DocumentReviewForm";
    public static final String VAR_QUOTATION_FORM_METADATA = "QuotationForm";
    public static final String VAR_REPORT_VERIFY_FORM_METADATA = "ReportVerifyForm";
    public static final String VAR_TEST_FUNCTION_FORM_METADATA = "TestFunctionForm";
    public static final String VAR_TEST_PLAN_FORM_METADATA = "TestPlanForm";
    public static final String VAR_TEST_PLAN_VERIFY_FORM_METADATA = "TestPlanVerifyForm";
    public static final String VAR_TEST_PROBLEM_FORM_METADATA = "TestProblemForm";
    public static final String VAR_TEST_RECORDS_FORM_METADATA = "TestRecordsForm";
    public static final String VAR_TEST_REPORT_FORM_METADATA = "TestReportForm";
    public static final String VAR_TEST_WORK_CHECK_FORM_METADATA = "TestWorkCheckForm";
    /*             样品元数据              */
    public static final String VAR_SAMPLE_METADATA = "sampleMetadata";

    public static final Set<String> FORM_METADATA_SET = Set.of(
            VAR_APPLICATION_FORM_METADATA,
            VAR_APPLICATION_VERIFY_FORM_METADATA,
            VAR_CONTRACT_FORM_METADATA,
            VAR_CONFIDENTIALITY_FORM_METADATA,
            VAR_DOCUMENT_REVIEW_FORM_METADATA,
            VAR_QUOTATION_FORM_METADATA,
            VAR_REPORT_VERIFY_FORM_METADATA,
            VAR_TEST_FUNCTION_FORM_METADATA,
            VAR_TEST_PLAN_FORM_METADATA,
            VAR_TEST_PLAN_VERIFY_FORM_METADATA,
            VAR_TEST_PROBLEM_FORM_METADATA,
            VAR_TEST_RECORDS_FORM_METADATA,
            VAR_TEST_REPORT_FORM_METADATA,
            VAR_TEST_WORK_CHECK_FORM_METADATA
    );

    public static final Set<String> PARTICIPANT_SET = Set.of(
            VAR_CLIENT,
            VAR_MARKETING_MANAGER,
            VAR_TESTING_MANAGER,
            VAR_QUALITY_MANAGER,
            VAR_SIGNATORY,
            VAR_MARKETING_OPERATOR,
            VAR_TESTING_OPERATOR
    );

    public static final Set<String> PARTICIPANT_WHITOUT_CLIENT_SET = Set.of(
            VAR_MARKETING_MANAGER,
            VAR_TESTING_MANAGER,
            VAR_QUALITY_MANAGER,
            VAR_SIGNATORY,
            VAR_MARKETING_OPERATOR,
            VAR_TESTING_OPERATOR
    );

}
