package com.stcos.server.entity.form;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/*
          ______          __  ____  __          _    __          _ ____      ______
         /_  __/__  _____/ /_/ __ \/ /___ _____| |  / /__  _____(_) __/_  __/ ____/___  _________ ___
          / / / _ \/ ___/ __/ /_/ / / __ `/ __ \ | / / _ \/ ___/ / /_/ / / / /_  / __ \/ ___/ __ `__ \
         / / /  __(__  ) /_/ ____/ / /_/ / / / / |/ /  __/ /  / / __/ /_/ / __/ / /_/ / /  / / / / / /
        /_/  \___/____/\__/_/   /_/\__,_/_/ /_/|___/\___/_/  /_/_/  \__, /_/    \____/_/  /_/ /_/ /_/
                                                                   /____/
 */

/**
 * 测试方案评审表
 *
 * @author AmadeusZQK
 * @version 1.0
 * @since 2023/5/15
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
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

