package com.stcos.server.entity.form;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Map;

/*
               ____              __        __  _             ______
              / __ \__  ______  / /_____ _/ /_(_)___  ____  / ____/___  _________ ___
             / / / / / / / __ \/ __/ __ `/ __/ / __ \/ __ \/ /_  / __ \/ ___/ __ `__ \
            / /_/ / /_/ / /_/ / /_/ /_/ / /_/ / /_/ / / / / __/ / /_/ / /  / / / / / /
            \___\_\__,_/\____/\__/\__,_/\__/_/\____/_/ /_/_/    \____/_/  /_/ /_/ /_/

 */

/**
 * 报价单
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/29 12:22
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class QuotationForm extends Form {
    private String quotationDate;
    private String validDate;
    private String software;
    private double testFee;
    private double reportFee;
    private int reportNum;

    @Override
    public Map<String, String> toTemplateFormat() {
        return null;
    }
}