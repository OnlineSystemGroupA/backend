package com.stcos.server.entity.process.TaskConfigs;

import com.stcos.server.entity.process.TaskConfig;

import java.util.List;

/**
 * 签署合同
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/22 19:02
 */
public class SignContractConfig implements TaskConfig {
    @Override
    public List<String> getRequiredForms() {
        return null;
    }

    @Override
    public String getEmailSubject() {
        return null;
    }

    @Override
    public String getEmailText() {
        return null;
    }

    @Override
    public List<String> getReadableForms() {
        return null;
    }

    @Override
    public List<String> getWritableForms() {
        return null;
    }

    @Override
    public List<String> getWillDisReadableForms() {
        return null;
    }

    @Override
    public List<String> getWillDisWritableForms() {
        return null;
    }
}
