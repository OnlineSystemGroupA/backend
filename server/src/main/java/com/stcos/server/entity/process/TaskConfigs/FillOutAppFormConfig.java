package com.stcos.server.entity.process.TaskConfigs;

import com.stcos.server.entity.process.TaskConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 填写申请表
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/21 22:43
 */
public class FillOutAppFormConfig implements TaskConfig { //填写申请表
    @Override
    public List<String> getRequiredForms() {
        return new ArrayList<>(){{
            add("ApplicationForm");
        }};
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
        return new ArrayList<>(){{
            add("ApplicationForm");
        }};
    }

    @Override
    public List<String> getWritableForms() {
        return new ArrayList<>(){{
            add("ApplicationForm");
        }};
    }

    @Override
    public List<String> getWillDisReadableForms() {
        return new ArrayList<>();
    }

    @Override
    public List<String> getWillDisWritableForms() {
        return new ArrayList<>(){{
            add("ApplicationForm");
        }};
    }
}
