package com.stcos.server.model.form;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/*

 */

/**
 * 软件测试记录
 *
 * @author AmadeusZQK
 * @version 1.0
 * @since 2023/5/15
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TestRecordsForm extends Form {
    private List<TestRecord> records;

    @Override
    public Map<String, String> toTemplateFormat() {
        return null;
    }

    @Data
    @Accessors(chain = true)
    public static class TestRecord {
        private String type;
        private String description;
        private String contract;
        private String premise;
        private String process;
        private String prediction;
        private String designer;
        private String result;
        private boolean matched;
        private String bugIndex;
        private String executor;
        private String date;
        private String verifier;
    }
}

