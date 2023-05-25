package com.stcos.server.entity.form;

import com.stcos.server.util.JSONUtil;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/17 9:57
 */

@Data
public abstract class Form {

    private Long formId;

    // TODO 酌情设计一些与表单操作有关的共用方法

    private static final Map<String, Class<? extends Form>> FORM_NAME_CLASS_MAP = new HashMap<>(){{
        put("ApplicationForm", ApplicationForm.class);
        put("ApplicationVerifyForm", ApplicationVerifyForm.class);
        put("DocumentReviewForm", DocumentReviewForm.class);
        put("ReportVerifyForm", ReportVerifyForm.class);
        put("TestFunctionForm", TestFunctionForm.class);
        put("TestPlanForm", TestPlanForm.class);
        put("TestPlanVerifyForm", TestPlanVerifyForm.class);
        put("TestRecordsForm", TestRecordsForm.class);
        put("TestReportForm", TestReportForm.class);
        put("TestWorkCheckForm", TestWorkCheckForm.class);
    }};

    /**
     * 通过表单名将前端传入的表单 JSON 字符串转换为对应的表单类
     *
     * @param formType 表单名
     * @param formData 表单数据 JSON 字符串
     * @return 如构建失败返回 null，否则返回构建成功地表单对象
     */
    public static Form buildForm(String formType, String formData) {
        Class<? extends Form> formClass = FORM_NAME_CLASS_MAP.get(formType);
        if (formClass == null) return null;
        return JSONUtil.parseObject(formData, formClass);
    }
}
