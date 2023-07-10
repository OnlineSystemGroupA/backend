package com.stcos.server.entity.form;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/*
          ______          __ _       __           __   ________              __   ______
         /_  __/__  _____/ /| |     / /___  _____/ /__/ ____/ /_  ___  _____/ /__/ ____/___  _________ ___
          / / / _ \/ ___/ __/ | /| / / __ \/ ___/ //_/ /   / __ \/ _ \/ ___/ //_/ /_  / __ \/ ___/ __ `__ \
         / / /  __(__  ) /_ | |/ |/ / /_/ / /  / ,< / /___/ / / /  __/ /__/ ,< / __/ / /_/ / /  / / / / / /
        /_/  \___/____/\__/ |__/|__/\____/_/  /_/|_|\____/_/ /_/\___/\___/_/|_/_/    \____/_/  /_/ /_/ /_/

 */

/**
 * 软件项目委托测试工作检查表
 *
 * @author AmadeusZQK
 * @version 1.0
 * @since 2023/5/15
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
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
