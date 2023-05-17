package com.stcos.server.entity.form;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
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
@Accessors(fluent = true)
@TableName(value = "t_test_function_form")
public class TestFunctionForm extends Form {
    private String softwareName;
    private String softwareVersion;
    private List<TestFunction> functions;

    @Data
    @Accessors(fluent = true)
    public static class TestFunction {
        private String name;
        private List<TestFunctionItem> items;

        @Data
        @Accessors(fluent = true)
        public static class TestFunctionItem {
            private String name;
            private String description;
        }
    }
}

