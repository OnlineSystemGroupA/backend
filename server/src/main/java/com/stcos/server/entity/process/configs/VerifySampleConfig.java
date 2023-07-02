package com.stcos.server.entity.process.configs;

import com.stcos.server.entity.process.TaskConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/27 18:10
 */
public class VerifySampleConfig extends TaskConfig {

    public VerifySampleConfig() {
        super("审核样品", "您好！一项被指派给您的\"审核样品\"任务已被创建，请尽快完成！");
    }

    @Override
    public List<String> getRequiredForms() {
        return new ArrayList<>(){{
            add("DocumentReviewForm");
        }};
    }


}