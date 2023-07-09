package com.stcos.server.entity.form;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value = "t_test_work_check_form")
public class TestWorkCheckForm extends Form {
    private String softwareName;
    private String softwareVersion;
    private String clientCompany;
    private String startDate;
    private String expectedDueDate;
    private String tester;
    private String actualDueDate;
    private String checker;
    private List<WorkStep> prework;
    private List<WorkStep> assessment;
    private List<WorkStep> testment;

    @Override
    public Map<String, String> toTemplateFormat() {
        return null;
    }

    @Data
    @Accessors(chain = true)
    public static class WorkStep {
        private String name;
        private List<StepContent> contents;

        @Data
        @Accessors(chain = true)
        public static class StepContent {
            private String content;
            private boolean confirmed;
        }
    }
}
