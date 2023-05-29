package com.stcos.server.entity.process.TaskConfigs;

import com.stcos.server.entity.process.TaskConfig;
import org.flowable.task.api.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * 上传样品
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/22 19:04
 */
public class UploadSampleConfig extends TaskConfig {

    public UploadSampleConfig() {
        super("上传样品", "您好！一份由您提起的软件测试委托需要上传样品，请尽快前往上传。");
    }

    @Override
    public List<String> getRequiredForms() {
        return new ArrayList<>();
    }

}
