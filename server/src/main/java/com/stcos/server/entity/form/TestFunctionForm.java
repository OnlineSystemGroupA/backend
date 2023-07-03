package com.stcos.server.entity.form;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 委托测试软件功能列表
 *
 * @author AmadeusZQK
 * @version 1.0
 * @since 2023/5/15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TestFunctionForm extends Form {
    private String softwareName;
    private String softwareVersion;
    private List<TestFunction> functions;

    @Data
    @Accessors(chain = true)
    public static class TestFunction {
        private String title;
        private List<TestFunctionItem> items;
        private Integer index;

        @Data
        @Accessors(chain = true)
        public static class TestFunctionItem {
            private String name;
            private String description;
        }
    }
}

