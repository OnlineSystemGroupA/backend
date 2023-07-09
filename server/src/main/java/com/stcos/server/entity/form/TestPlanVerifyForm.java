package com.stcos.server.entity.form;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * 测试方案评审表
 *
 * @author AmadeusZQK
 * @version 1.0
 * @since 2023/5/15
 */
@Data
@Accessors(chain = true)
@TableName(value = "t_test_plan_verify_form")
public class TestPlanVerifyForm extends Form {
    private String softwareName;
    private String softwareVersion;
    private String projectId;
    private String testType;
    private List<VerifyItem> verifyItems;
    private List<VerifyEmployee> verifyEmployees;

    @Override
    public Map<String, String> toTemplateFormat() {
        return null;
    }

    @Data
    @Accessors(chain = true)
    public static class VerifyEmployee {
        private String position;
        private String suggestions;
    }

    @Data
    @Accessors(chain = true)
    public static class VerifyItem {
        private String content;
        private boolean passed;
        private String explanation;
    }
}

