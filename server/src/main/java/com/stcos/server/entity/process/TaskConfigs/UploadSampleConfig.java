package com.stcos.server.entity.process.TaskConfigs;

import com.stcos.server.entity.process.TaskConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 上传样品
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/22 19:04
 */
public class UploadSampleConfig implements TaskConfig {

    @Override
    public String getEmailSubject() {
        return "上传样品";
    }

    @Override
    public String getEmailText() {
        return "您好！一份由您提起的软件测试委托需要上传样品，请尽快前往上传。";
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
