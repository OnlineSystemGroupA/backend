package com.stcos.server.entity.process.TaskConfigs;

import com.stcos.server.entity.process.TaskConfig;
import org.flowable.task.api.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试文档归档
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/27 20:48
 */
public class ArchiveConfig extends TaskConfig {

    public ArchiveConfig() {
        super("测试文档归档", "您好！一项被指派给您的\"测试文档归档\"任务已被创建，请尽快完成！");
    }

    @Override
    public List<String> getRequiredForms() {
        return new ArrayList<>(){{
            add("TestWorkCheck");
        }};
    }

}
