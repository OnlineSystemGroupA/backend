package com.stcos.server.entity.process.TaskConfigs;

import com.stcos.server.entity.process.TaskConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户重新上传样品
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/27 18:15
 */
public class ReUploadSampleConfig implements TaskConfig {

    @Override
    public String getEmailSubject() {
        return "重新上传样品";
    }

    @Override
    public String getEmailText() {
        return "您好！一份由您提起的软件测试委托中上传的样品审核未通过，请尽快重新上传。";
    }

    @Override
    public List<String> getReadableForms() {
        return new ArrayList<>(){{
            add("ApplicationForm");
        }};
    }

    @Override
    public List<String> getWritableForms() {
        return new ArrayList<>();
    }

    @Override
    public List<String> getWillDisReadableForms() {
        return new ArrayList<>();
    }

    @Override
    public List<String> getWillDisWritableForms() {
        return new ArrayList<>();
    }

}
