package com.stcos.server.entity.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/*
            ___                ___            __  _             ______
           /   |  ____  ____  / (_)________ _/ /_(_)___  ____  / ____/___  _________ ___
          / /| | / __ \/ __ \/ / / ___/ __ `/ __/ / __ \/ __ \/ /_  / __ \/ ___/ __ `__ \
         / ___ |/ /_/ / /_/ / / / /__/ /_/ / /_/ / /_/ / / / / __/ / /_/ / /  / / / / / /
        /_/  |_/ .___/ .___/_/_/\___/\__,_/\__/_/\____/_/ /_/_/    \____/_/  /_/ /_/ /_/
              /_/   /_/
 */

/**
 * 软件项目委托测试申请表
 *
 * @author AmadeusZQK
 * @version 1.1 (final)
 * @since 2023/5/15
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
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
    private List<String> softwareType;
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

    @Override
    public Map<String, String> toTemplateFormat() {
        return null;
    }

    @Data
    @Accessors(chain = true)
    public static class SoftwareScale {
        private String scaleDescription;
        private int scale;
    }

    @Data
    @Accessors(chain = true)
    @JsonIgnoreProperties(value = {"vforKey"})
    public static class ClientSystem {
        private String system;
        private String version;
    }

    @Data
    @Accessors(chain = true)
    public static class Medium {
        private String mediumType;
        private int num;
    }

    @Data
    @Accessors(chain = true)
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
