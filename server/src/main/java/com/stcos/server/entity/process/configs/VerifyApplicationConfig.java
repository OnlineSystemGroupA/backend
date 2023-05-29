package com.stcos.server.entity.process.configs;

import com.stcos.server.entity.process.TaskConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 市场部审核委托 & 测试部审核委托
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/22 19:04
 */
public class VerifyApplicationConfig extends TaskConfig {


    public VerifyApplicationConfig() {
        super("审核用户委托", "您好！一项被指派给您的\"审核用户委托\"任务已被创建，请尽快完成！");
    }
    @Override
    public List<String> getRequiredForms() {
        return new ArrayList<>(){{
            add("ApplicationVerifyForm");
        }};
    }

}
