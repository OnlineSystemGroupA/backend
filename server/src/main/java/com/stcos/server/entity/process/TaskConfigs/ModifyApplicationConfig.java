package com.stcos.server.entity.process.TaskConfigs;

import com.stcos.server.entity.process.TaskConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试部审核不通过，修改委托 & 市场部审核不通过，修改委托
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/22 18:56
 */
public class ModifyApplicationConfig implements TaskConfig {

    @Override
    public String getEmailSubject() {
        return "修改委托";
    }

    @Override
    public String getEmailText() {
        return "您好！一项由您提起的软件测试委托经审核没有通过，请您修改委托并重新提交。";
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
