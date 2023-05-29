package com.stcos.server.entity.process.TaskConfigs;

import com.stcos.server.entity.process.TaskConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户审核报价
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/26 15:00
 */
public class VerifyQuotationConfig implements TaskConfig {

    @Override
    public String getEmailSubject() {
        return "处理报价";
    }

    @Override
    public String getEmailText() {
        return "您好！一份由您提起的软件测试委托已生成报价，请尽快前方确认。";
    }

    @Override
    public List<String> getReadableForms() {
        return new ArrayList<>(){{
            add("ApplicationForm");
        }};
    }

    @Override
    public List<String> getWritableForms() {
        return new ArrayList<>();
    }

    @Override
    public List<String> getWillDisReadableForms() {
        return new ArrayList<>();
    }

    @Override
    public List<String> getWillDisWritableForms() {
        return new ArrayList<>();
    }
}
