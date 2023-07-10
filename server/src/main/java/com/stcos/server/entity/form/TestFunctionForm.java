package com.stcos.server.entity.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/*
          ______          __  ______                 __  _             ______
         /_  __/__  _____/ /_/ ____/_  ______  _____/ /_(_)___  ____  / ____/___  _________ ___
          / / / _ \/ ___/ __/ /_  / / / / __ \/ ___/ __/ / __ \/ __ \/ /_  / __ \/ ___/ __ `__ \
         / / /  __(__  ) /_/ __/ / /_/ / / / / /__/ /_/ / /_/ / / / / __/ / /_/ / /  / / / / / /
        /_/  \___/____/\__/_/    \__,_/_/ /_/\___/\__/_/\____/_/ /_/_/    \____/_/  /_/ /_/ /_/

 */

/**
 * 委托测试软件功能列表
 *
 * @author AmadeusZQK
 * @version 1.0
 * @since 2023/5/15
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TestFunctionForm extends Form {
    private String softwareName;
    private String softwareVersion;
    private List<TestFunction> functions;

    @Override
    public Map<String, String> toTemplateFormat() {
        return null;
    }

    @Data
    @Accessors(chain = true)
    @JsonIgnoreProperties(value = {"index"})
    public static class TestFunction {
        private String title;
        private List<TestFunctionItem> items;

        @Data
        @JsonIgnoreProperties(value = {"index"})
        @Accessors(chain = true)
        public static class TestFunctionItem {
            private String name;
            private String description;
        }
    }
}

