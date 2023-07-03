package com.stcos.server.entity.form;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * 报价单
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/29 12:22
 */

@Data
@Accessors(fluent = true)
public class QuotationForm extends Form {
    private String quotationDate;
    private String validDate;
    private String software;
    private double testFee;
    private double reportFee;
    private int reportNum;
}