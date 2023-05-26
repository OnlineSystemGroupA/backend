package com.stcos.server.entity.form;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 软件项目委托测试申请表
 *
 * @author AmadeusZQK
 * @version 1.0
 * @since 2023/5/15
 */
@Data
@Accessors(fluent = true)
@TableName(value = "t_application_form")
public class ApplicationForm extends Form {
    private List<String> testTypes;
    private String softwareName;
    private String softwareVersion;
    private String companyChineseName;
    private String companyEnglishName;
    private String developmentDepartment;
    private String companyType;
    private String description;
    private List<String> testStandards;
    private List<String> testAspects;
    private List<SoftwareScale> softwareScales;
    private String softwareType;
    private List<ClientSystem> clientSystems;
    private double clientMemory;
    private String clientOtherRequirement;
    private List<String> serverNames;
    private double serverMemory;
    private double serverDisk;
    private String serverOtherRequirement;
    private String serverSystem;
    private String serverSystemVersion;
    private String serverLanguage;
    private List<String> serverFrames;
    private String serverDatabase;
    private String serverMiddleware;
    private String serverOtherSoftware;
    private String networkEnvironment;
    private List<Medium> media;
    private String documents;
    private String expireHandle;
    private String expectedDate;
    private CompanyInfo companyInfo;

    @Data
    @Accessors(fluent = true)
    public static class SoftwareScale {
        private String scaleDescription;
        private int scale;
    }

    @Data
    @Accessors(fluent = true)
    public static class ClientSystem {
        private String system;
        private String version;
    }

    @Data
    @Accessors(fluent = true)
    public static class Medium {
        private String mediumType;
        private int num;
    }

    @Data
    @Accessors(fluent = true)
    public static class CompanyInfo {
        private String telephone;
        private String fax;
        private String address;
        private String postcode;
        private String contractPerson;
        private String mobile;
        private String email;
        private String website;
    }
}

