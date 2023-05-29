package com.stcos.server.entity.process.TaskConfigs;

import com.stcos.server.entity.process.TaskConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户填写合同
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/26 15:23
 */
public class FillOutContractConfig implements TaskConfig {

    @Override
    public String getEmailSubject() {
        return "填写合同";
    }

    @Override
    public String getEmailText() {
        return "您好！一份由您提起的软件测试委托已生成合同，请尽快前方填写。";
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
