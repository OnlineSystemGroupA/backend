package com.stcos.server.entity.process.TaskConfigs;

import com.stcos.server.entity.process.TaskConfig;
import org.flowable.task.api.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试部审核不通过，修改委托 & 市场部审核不通过，修改委托
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/22 18:56
 */
public class ModifyApplicationConfig extends TaskConfig {

    public ModifyApplicationConfig() {
        super("修改委托", "您好！一项由您提起的软件测试委托经审核没有通过，请您修改委托并重新提交。");
    }

    @Override
    public List<String> getRequiredForms() {
        return new ArrayList<>(){{
            add("ApplicationForm");
            add("TestFunctionForm");
        }};
    }
}
