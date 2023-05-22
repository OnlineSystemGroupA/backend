package com.stcos.server.entity.form;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * 软件测试报告
 *
 * @author AmadeusZQK
 * @version 1.0
 * @since 2023/5/15
 */
@Data
@Accessors(fluent = true)
@TableName(value = "t_test_report_form")
public class TestReportForm extends Form {
    private String softwareName;
    private String softwareVersion;
    private String clientCompany;
    private String testType;
    private String reportDate;
    private String projectId;
    private String sampleTime;
    private String startTime;
    private String endTime;
    private String sampleCondition;
    private String testStandard;
    private String sampleList;
    private String testConclusion;
    private String compiler;
    private String compileTime;
    private String reviewer;
    private String reviewTime;
    private String approver;
    private String approveTime;
    private String hardwareType;
    private String hardwareName;
    private String hardwareConfiguration;
    private String hardwareNum;
    private SoftwareEnvironment softwareEnvironment;
    private List<TestStandard> testStandards;
    private List<Reference> references;
    private List<FunctionTest> functionTests;
    private Map<String, List<GeneralTest>> tests; // String = efficiencyTests, portabilityTests, usabilityTests, reliabilityTests, or maintainabilityTests

    @Data
    @Accessors(fluent = true)
    public static class SoftwareEnvironment {
        private OperatingSystem operatingSystem;
        private List<Software> software; // Uncountable noun
        private AncillaryTool ancillaryTool;
        private DevelopmentTool developmentTool;
        private TestSample testSample;

        @Data
        @Accessors(fluent = true)
        public static class OperatingSystem {
            private String name;
            private String version;
        }

        @Data
        @Accessors(fluent = true)
        public static class Software {
            private String name;
            private String version;
        }

        @Data
        @Accessors(fluent = true)
        public static class AncillaryTool {
            private String name;
            private String version;
        }

        @Data
        @Accessors(fluent = true)
        public static class DevelopmentTool {
            private String name;
            private String version;
        }

        @Data
        @Accessors(fluent = true)
        public static class TestSample {
            private String name;
            private String version;
        }
    }

    @Data
    @Accessors(fluent = true)
    public static class TestStandard {
        private String standard;
    }

    @Data
    @Accessors(fluent = true)
    public static class Reference {
        private String reference;
    }

    @Data
    @Accessors(fluent = true)
    public static class FunctionTest {
        private String functionModule;
        private String functionRequirement;
        private String testResult;
    }

    @Data
    @Accessors(fluent = true)
    public static class GeneralTest {
        private String property;
        private String testExplanation;
        private String testResult;
    }
}
