package com.stcos.server.model.form;

/*
                    ______                   ______
                   / ____/___  _________ ___/_  __/_  ______  ___
                  / /_  / __ \/ ___/ __ `__ \/ / / / / / __ \/ _ \
                 / __/ / /_/ / /  / / / / / / / / /_/ / /_/ /  __/
                /_/    \____/_/  /_/ /_/ /_/_/  \__, / .___/\___/
                                               /____/_/
 */

import java.util.Set;

/**
 * 统一定义表单类型
 *
 * @author AmadeusZQK
 * @version 1.1 (final)
 * @since 2023/7/9 12:49
 */
public class FormType {

    public final static String TYPE_APPLICATION_FORM = "ApplicationForm";
    public final static String TYPE_APPLICATION_VERIFY_FORM = "ApplicationVerifyForm";
    public final static String TYPE_CONTRACT_FORM = "ContractForm";
    public final static String TYPE_CONFIDENTIALITY_FORM = "ConfidentialityForm";
    public final static String TYPE_DOCUMENT_REVIEW_FORM = "DocumentReviewForm";
    public final static String TYPE_QUOTATION_FORM = "QuotationForm";
    public final static String TYPE_REPORT_VERIFY_FORM = "ReportVerifyForm";
    public final static String TYPE_TEST_FUNCTION_FORM = "TestFunctionForm";
    public final static String TYPE_TEST_PLAN_FORM = "TestPlanForm";
    public final static String TYPE_TEST_PLAN_VERIFY_FORM = "TestPlanVerifyForm";
    public final static String TYPE_TEST_PROBLEM_FORM = "TestProblemForm";
    public final static String TYPE_TEST_RECORDS_FORM = "TestRecordsForm";
    public final static String TYPE_TEST_REPORT_FORM = "TestReportForm";
    public final static String TYPE_TEST_WORK_CHECK_FORM = "TestWorkCheckForm";
    public final static Set<String> FORM_TYPE_SET = Set.of(
            TYPE_APPLICATION_FORM,
            TYPE_APPLICATION_VERIFY_FORM,
            TYPE_CONTRACT_FORM,
            TYPE_CONFIDENTIALITY_FORM,
            TYPE_DOCUMENT_REVIEW_FORM,
            TYPE_QUOTATION_FORM,
            TYPE_REPORT_VERIFY_FORM,
            TYPE_TEST_FUNCTION_FORM,
            TYPE_TEST_PLAN_FORM,
            TYPE_TEST_PLAN_VERIFY_FORM,
            TYPE_TEST_PROBLEM_FORM,
            TYPE_TEST_RECORDS_FORM,
            TYPE_TEST_REPORT_FORM,
            TYPE_TEST_WORK_CHECK_FORM
    );

}
