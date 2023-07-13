package com.stcos.server.model.form;

import com.stcos.server.util.FormUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/*
               ______            __                  __  ______
              / ____/___  ____  / /__________ ______/ /_/ ____/___  _________ ___
             / /   / __ \/ __ \/ __/ ___/ __ `/ ___/ __/ /_  / __ \/ ___/ __ `__ \
            / /___/ /_/ / / / / /_/ /  / /_/ / /__/ /_/ __/ / /_/ / /  / / / / / /
            \____/\____/_/ /_/\__/_/   \__,_/\___/\__/_/    \____/_/  /_/ /_/ /_/

 */

/**
 * 软件项目委托测试保密协议
 *
 * @author AmadeusZQK
 * @version 1.0 (final)
 * @since 2023/5/15
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ContractForm extends Form {
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
    @Accessors(chain = true)
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
    @Accessors(chain = true)
    public static class TrusteeInfo {
        private String representative;
        private String signatureDate;
        private String contact;
        private String address;
        private String telephone;
        private String fax;
        private String postcode;
    }

    @Override
    public Map<String, String> toTemplateFormat() {
        Map<String, String> map = new HashMap<>();
        map.put("blank1", projectName);
        map.put("blank2", client);
        map.put("blank3", trustee);
        map.put("blank4", signPlace);
        map.put("blank5", FormUtil.StdTimeTransformer.transformDate(signDate));
        map.put("blank6", FormUtil.StdTimeTransformer.transformDate(validDate));
        map.put("blank7", client);
        map.put("blank8", software);
        map.put("blank9", qualityCharacteristic);
        map.put("blank10", FormUtil.NumberToChinese.convertToChinese(testFee));
        map.put("blank11", String.valueOf(testFee).formatted("%.2f"));
        map.put("blank12", String.valueOf(testTime));
        map.put("blank13", String.valueOf(rectificationFrequency));
        map.put("blank14", String.valueOf(rectificationTime));

        map.put("blank15", client);
        map.put("blank16", clientInfo.representative);
        map.put("blank17", FormUtil.StdTimeTransformer.transformDate(clientInfo.signatureDate));
        map.put("blank18", clientInfo.contact);
        map.put("blank19", clientInfo.address);
        map.put("blank20", clientInfo.telephone);
        map.put("blank21", clientInfo.fax);
        map.put("blank22", clientInfo.bank);
        map.put("blank23", clientInfo.account);
        map.put("blank24", clientInfo.postcode);

        map.put("blank25", trustee);
        map.put("blank26", trusteeInfo.representative);
        map.put("blank27", FormUtil.StdTimeTransformer.transformDate(trusteeInfo.signatureDate));
        map.put("blank28", trusteeInfo.contact);
        map.put("blank29", trusteeInfo.address);
        map.put("blank30", trusteeInfo.postcode);
        map.put("blank31", trusteeInfo.telephone);
        map.put("blank32", trusteeInfo.fax);

        return map;
    }
}
