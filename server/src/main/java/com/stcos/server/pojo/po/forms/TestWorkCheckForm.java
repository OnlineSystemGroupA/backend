package com.stcos.server.pojo.po.forms;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * 软件项目委托测试工作检查表
 *
 * @author AmadeusZQK
 * @version 1.0
 * @since 2023/5/15
 */
@Data
@Accessors(fluent = true)
@TableName(value = "t_test_work_check_form")
public class TestWorkCheckForm {
    private String softwareName;
    private String softwareVersion;
    private String clientCompany;
    private String startDate;
    private String expectedDueDate;
    private String tester;
    private String actualDueDate;
    private Map<String, List<WorkStep>> workSteps; // String = prelimWorks，assessments, or testSteps
    private String checker;

    @Data
    @Accessors(fluent = true)
    public static class WorkStep {
        private String name;
        private List<StepContent> contents;

        @Data
        @Accessors(fluent = true)
        public static class StepContent {
            private String content;
            private boolean isConfirmed;
        }
    }
}
