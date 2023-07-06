package com.stcos.server.entity.process.configs;

import com.stcos.server.entity.process.TaskConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户签署合同
 *
 * @author kura
 * @version 1.0
 * @since 2023/7/6 10:51
 */
public class SignContractConfig extends TaskConfig {

    public SignContractConfig() {
        super("签署合同", "您好！一份由您提起的软件测试委托已生成合同，请尽快前方签署。");
    }

    @Override
    public List<String> getRequiredForms() {
        return new ArrayList<>();
    }
}
