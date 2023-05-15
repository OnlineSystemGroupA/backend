package com.stcos.server.pojo.po.forms;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 测试方案评审表
 *
 * @author AmadeusZQK
 * @version 1.0
 * @since 2023/5/15
 */
@Data
@Accessors(fluent = true)
@TableName(value = "t_test_plan_verify_form")
public class TestPlanVerifyForm {
    private String softwareName;
    private String softwareVersion;
    private String projectId;
    private String testType;
    private List<VerifyItem> verifyItems;
    private List<VerifyEmployee> verifyEmployees;

    @Data
    @Accessors(fluent = true)
    public static class VerifyEmployee {
        private String position;
        private String suggestions;
    }

    @Data
    @Accessors(fluent = true)
    public static class VerifyItem {
        private String content;
        private boolean isPassed;
        private String explanation;
    }
}

