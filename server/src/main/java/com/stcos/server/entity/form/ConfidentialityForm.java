package com.stcos.server.entity.form;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ConfidentialityForm extends Form {
    private String client;
    private String softwareName;
}