package com.stcos.server.entity.form;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class ContractForm {
    private String projectName;
    private String client;
    private String trustee;
    private String signPlace;
    private String signDate;
    private String validDate;
    private String software;
    private String qualityCharacteristic;
    private double testFee;
    private int testTime;
    private int rectificationFrequency;
    private int rectificationTime;
    private ClientInfo clientInfo;
    private TrusteeInfo trusteeInfo;

    @Data
    @Accessors(fluent = true)
    public static class ClientInfo {
        private String representative;
        private String signatureDate;
        private String contact;
        private String address;
        private String telephone;
        private String fax;
        private String bank;
        private String account;
        private String postcode;
    }

    @Data
    @Accessors(fluent = true)
    public static class TrusteeInfo {
        private String representative;
        private String signatureDate;
        private String contact;
        private String address;
        private String telephone;
        private String fax;
        private String postcode;
    }
}
