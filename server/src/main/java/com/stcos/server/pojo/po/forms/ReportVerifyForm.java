package com.stcos.server.pojo.po.forms;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 测试报告检查表
 *
 * @author AmadeusZQK
 * @version 1.0
 * @since 2023/5/15
 */
@Data
@Accessors(fluent = true)
@TableName(value = "t_report_verify_form")
public class ReportVerifyForm {
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
}