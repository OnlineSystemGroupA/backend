package com.stcos.server.entity.form;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Accessors(chain = true)
@TableName(value = "t_test_report_form")
public class TestReportForm extends Form {
    private String softwareName;
    private String softwareVersion;
    private String clientCompany;
    private String testType;
    private String reportDate;
    private String projectId;
    private String sampleDate;
    private String startDate;
    private String endDate;
    private String sampleCondition;
    private String testStandard;
    private String sampleList;
    private String testConclusion;
    private String compiler;
    private String compileDate;
    private String reviewer;
    private String reviewDate;
    private String approver;
    private String approveDate;
    private String hardwareType;
    private String hardwareName;
    private String hardwareConfiguration;
    private String hardwareNum;
    private String telephone;
    private String fax;
    private String address;
    private String website;
    private String contract;
    private String email;
    private SoftwareEnvironment softwareEnvironment;
    private String networkEnvironment;
    private List<String> testStandards;
    private List<String> references;
    private List<FunctionTest> functionTests;
    private List<GeneralTest> efficiencyTests;
    private List<GeneralTest> portabilityTests;
    private List<GeneralTest> usabilityTests;
    private List<GeneralTest> reliabilityTests;
    private List<GeneralTest> maintainabilityTests;


    @Override
    public Map<String, String> toTemplateFormat() {
        return null;
    }

    @Data
    @Accessors(chain = true)
    public static class SoftwareEnvironment {
        private OperatingSystem operatingSystem;
        private List<Software> software; // Uncountable noun
        private AncillaryTool ancillaryTool;
        private DevelopmentTool developmentTool;
        private TestSample testSample;

        @Data
        @Accessors(chain = true)
        public static class OperatingSystem {
            private String name;
            private String version;
        }

        @Data
        @Accessors(chain = true)
        public static class Software {
            private String name;
            private String version;
        }

        @Data
        @Accessors(chain = true)
        public static class AncillaryTool {
            private String name;
            private String version;
        }

        @Data
        @Accessors(chain = true)
        public static class DevelopmentTool {
            private String name;
            private String version;
        }

        @Data
        @Accessors(chain = true)
        public static class TestSample {
            private String name;
            private String version;
        }
    }

    @Data
    @Accessors(chain = true)
    public static class FunctionTest {
        private String functionModule;
        private String functionRequirement;
        private String testResult;
    }

    @Data
    @Accessors(chain = true)
    public static class GeneralTest {
        private String property;
        private String testExplanation;
        private String testResult;
    }
}
