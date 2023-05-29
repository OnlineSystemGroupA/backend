package com.stcos.server.entity.process.TaskConfigs;

import com.stcos.server.entity.process.TaskConfig;
import org.flowable.task.api.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户审核报价
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/26 15:00
 */
public class VerifyQuotationConfig extends TaskConfig {

    public VerifyQuotationConfig() {
        super("处理报价", "您好！一份由您提起的软件测试委托已生成报价，请尽快前方确认。");
    }
    @Override
    public List<String> getRequiredForms() {
        return new ArrayList<>();
    }

}
