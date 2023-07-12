package com.stcos.server.model.form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stcos.server.util.JSONUtil;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.HashMap;
import java.util.Map;

/*
                                ______
                               / ____/___  _________ ___
                              / /_  / __ \/ ___/ __ `__ \
                             / __/ / /_/ / /  / / / / / /
                            /_/    \____/_/  /_/ /_/ /_/

 */

/**
 * 表单基类
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/17 9:57
 */
@Data
@Document(collection = "form")
public abstract class Form {

    @AutoId
    @JsonIgnore
    @MongoId(targetType = FieldType.INT64)
    private long formId = -1;

    private static final Map<String, Class<? extends Form>> FORM_TYPE_CLASS_MAP = new HashMap<>() {{
        put(FormType.TYPE_APPLICATION_FORM, ApplicationForm.class);
        put(FormType.TYPE_APPLICATION_VERIFY_FORM, ApplicationVerifyForm.class);
        put(FormType.TYPE_CONFIDENTIALITY_FORM, ConfidentialityForm.class);
        put(FormType.TYPE_CONTRACT_FORM, ContractForm.class);
        put(FormType.TYPE_DOCUMENT_REVIEW_FORM, DocumentReviewForm.class);
        put(FormType.TYPE_QUOTATION_FORM, QuotationForm.class);
        put(FormType.TYPE_REPORT_VERIFY_FORM, ReportVerifyForm.class);
        put(FormType.TYPE_TEST_FUNCTION_FORM, TestFunctionForm.class);
        put(FormType.TYPE_TEST_PLAN_FORM, TestPlanForm.class);
        put(FormType.TYPE_TEST_PLAN_VERIFY_FORM, TestPlanVerifyForm.class);
        put(FormType.TYPE_TEST_RECORDS_FORM, TestRecordsForm.class);
        put(FormType.TYPE_TEST_REPORT_FORM, TestReportForm.class);
        put(FormType.TYPE_TEST_WORK_CHECK_FORM, TestWorkCheckForm.class);
        put(FormType.TYPE_TEST_PROBLEM_FORM, TestProblemForm.class);
    }};

    /**
     * 通过表单名将前端传入的表单 JSON 字符串转换为对应的表单类
     *
     * @param formType 表单名
     * @param formData 表单数据 JSON 字符串
     * @return 如构建失败返回 null，否则返回构建成功地表单对象
     */
    public static Form buildForm(String formType, String formData) {
        Class<? extends Form> formClass = FORM_TYPE_CLASS_MAP.get(formType);
        if (formClass == null) return null;
        return JSONUtil.parseObject(formData, formClass);
    }

    /**
     * 返回模板文件中占位符与表单中对应数据的键值对
     */
    public Map<String, String> toTemplateFormat() {
        return null;
    }

}