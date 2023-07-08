package com.stcos.server.entity.form;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
public class ConfidentialityForm extends Form {
    private String client;
    private String softwareName;

    @Override
    public Map<String, String> toTemplateFormat() {
        return null;
    }
}