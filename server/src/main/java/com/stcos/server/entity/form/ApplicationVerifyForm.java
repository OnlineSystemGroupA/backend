package com.stcos.server.entity.form;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 软件项目委托测试申请表之审核信息
 *
 * @author AmadeusZQK
 * @version 1.0
 * @since 2023/5/15
 */
@Data
@Accessors(chain = true)
@TableName(value = "t_application_verify_form")
public class ApplicationVerifyForm extends Form {
    private String confidentialLevel;
    private boolean isVirusCheck;
    private String virusScanner;
    private List<String> softwareSamples;
    private List<String> requirementDocuments;
    private List<String> userDocuments;
    private List<String> operatingDocuments;
    private String otherDocument;
    private String confirmation;
    private String acceptance;
    private String projectId;
    private String notes;
}