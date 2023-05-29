package com.stcos.server.entity.process.TaskConfigs;

import com.stcos.server.entity.process.TaskConfig;
import org.flowable.task.api.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * 填写申请表
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/21 22:43
 */
public class FillOutAppFormConfig extends TaskConfig {

    public FillOutAppFormConfig() {
        super(null, null);
    }

    @Override
    public List<String> getRequiredForms() {
        return new ArrayList<>(){{
            add("ApplicationForm");
            add("TestFunctionForm");
        }};
    }

}
