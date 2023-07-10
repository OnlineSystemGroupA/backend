package com.stcos.server.entity.form;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/*
    ___                ___            __  _           _    __          _ ____      ______
   /   |  ____  ____  / (_)________ _/ /_(_)___  ____| |  / /__  _____(_) __/_  __/ ____/___  _________ ___
  / /| | / __ \/ __ \/ / / ___/ __ `/ __/ / __ \/ __ \ | / / _ \/ ___/ / /_/ / / / /_  / __ \/ ___/ __ `__ \
 / ___ |/ /_/ / /_/ / / / /__/ /_/ / /_/ / /_/ / / / / |/ /  __/ /  / / __/ /_/ / __/ / /_/ / /  / / / / / /
/_/  |_/ .___/ .___/_/_/\___/\__,_/\__/_/\____/_/ /_/|___/\___/_/  /_/_/  \__, /_/    \____/_/  /_/ /_/ /_/
      /_/   /_/                                                          /____/
 */

/**
 * 软件项目委托测试申请表之审核信息
 *
 * @author AmadeusZQK
 * @version 1.0 (final)
 * @since 2023/5/15
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ApplicationVerifyForm extends Form {
    private String confidentialLevel;
    private String virusCheck;
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