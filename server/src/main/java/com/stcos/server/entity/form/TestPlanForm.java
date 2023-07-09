package com.stcos.server.entity.form;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * 软件测试方案
 *
 * @author AmadeusZQK
 * @version 1.0
 * @since 2023/5/15
 */
@Data
@Accessors(chain = true)
public class TestPlanForm extends Form {
    private String version;
    private List<EditRecord> editRecords;
    private String marks;
    private String systemOverview;
    private String documentOverview;
    private String baselines;
    private String reference;
    private String hardware;
    private String software;
    private String other;
    private String organization;
    private String staff;
    private String testLevel;
    private String testType;
    private String testCondition;
    private String plannedTest;
    private String testCases;
    private String time;
    private List<TimeTable> timeTables;
    private String traceability;

    @Override
    public Map<String, String> toTemplateFormat() {
        return null;
    }

    @Data
    @Accessors(chain = true)
    public static class EditRecord {
        private String version;
        private String date;
        private String amd;
        private String editor;
        private String description;
    }

    @Data
    @Accessors(chain = true)
    public static class TimeTable {
        private String task;
        private String lastTime;
        private String startTime;
        private String endTime;
    }
}

