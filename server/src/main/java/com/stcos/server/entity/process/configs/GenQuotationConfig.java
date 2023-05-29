package com.stcos.server.entity.process.configs;

import com.stcos.server.entity.process.TaskConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 市场部生成报价
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/22 18:52
 */
public class GenQuotationConfig extends TaskConfig {

    public GenQuotationConfig() {
        super("生成报价", "您好！一项被指派给您的\"生成报价\"任务已被创建，请尽快完成！");
    }

    @Override
    public List<String> getRequiredForms() {
        return new ArrayList<>(){{
            add("QuotationForm");
        }};
    }


}
