package com.stcos.server.entity.form;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 软件测试记录
 *
 * @author AmadeusZQK
 * @version 1.0
 * @since 2023/5/15
 */
@Data
@Accessors(fluent = true)
@TableName(value = "t_test_records_form")
public class TestRecordsForm extends Form {
    private List<TestRecord> records;

    @Data
    @Accessors(fluent = true)
    public static class TestRecord {
        private String type;
        private String description;
        private String contract;
        private String premise;
        private String process;
        private String prediction;
        private String designer;
        private String result;
        private boolean isMatched;
        private String bugIndex;
        private String executor;
        private String date;
        private String verifier;
    }
}

