package com.stcos.server.entity.process.configs;

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
public class ReUploadSampleConfig extends TaskConfig {

    public ReUploadSampleConfig() {
        super("重新上传样品", "您好！一份由您提起的软件测试委托中上传的样品审核未通过，请尽快重新上传。");
    }

    @Override
    public List<String> getRequiredForms() {
        return new ArrayList<>();
    }


}
