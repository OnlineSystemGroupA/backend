package com.stcos.server.entity.process.TaskConfigs;

import com.stcos.server.entity.process.TaskConfig;

import java.util.List;

/**
 * 后续处理
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/22 18:58
 */
public class PostProcessingConfig implements TaskConfig {
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
