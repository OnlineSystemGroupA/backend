package com.stcos.server.model.form;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Map;

/*
            ____                        __ _    __          _ ____      ______
           / __ \___  ____  ____  _____/ /| |  / /__  _____(_) __/_  __/ ____/___  _________ ___
          / /_/ / _ \/ __ \/ __ \/ ___/ __/ | / / _ \/ ___/ / /_/ / / / /_  / __ \/ ___/ __ `__ \
         / _, _/  __/ /_/ / /_/ / /  / /_ | |/ /  __/ /  / / __/ /_/ / __/ / /_/ / /  / / / / / /
        /_/ |_|\___/ .___/\____/_/   \__/ |___/\___/_/  /_/_/  \__, /_/    \____/_/  /_/ /_/ /_/
                  /_/                                         /____/
 */

/**
 * 测试报告检查表
 *
 * @author AmadeusZQK
 * @version 1.0
 * @since 2023/5/15
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ReportVerifyForm extends Form {
    private String softwareName;
    private String client;
    private boolean isReportIdChecked;
    private boolean isPagesChecked;
    private boolean isSoftwareNameChecked;
    private boolean isSoftwareVersionChecked;
    private boolean isClientChecked;
    private boolean isFinishDateChecked;
    private boolean isClientAddressChecked;
    private boolean isIndexChecked;
    private boolean isSampleChecked;
    private boolean isSoftwareAndHardwareChecked;
    private boolean isCharacterChecked;
    private boolean isSentenceChecked;
    private boolean isFormatChecked;
    private boolean isReportChecked;

    @Override
    public Map<String, String> toTemplateFormat() {
        return null;
    }
}