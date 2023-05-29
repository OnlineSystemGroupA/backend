package com.stcos.server.entity.process.configs;

import com.stcos.server.entity.process.TaskConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 市场部修改报价
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/26 15:06
 */
public class ModifyQuotationConfig extends TaskConfig {

    public ModifyQuotationConfig() {
        super("修改报价", "您好！之前提出的委托报价不被用户接受。一项被指派给您的\"修改报价\"任务已被创建，请尽快完成！");
    }

    @Override
    public List<String> getRequiredForms() {
        return new ArrayList<>(){{
            add("QuotationForm");
        }};
    }


}
