package com.stcos.server.entity.process.configs;

import com.stcos.server.entity.process.TaskConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户修改合同
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/26 15:28
 */
public class ModifyContractConfig extends TaskConfig {

    public ModifyContractConfig() {
        super("修改合同", "您好！一份由您提起的软件测试委托的合同审核未通过，请尽快前往修改。");
    }

    @Override
    public List<String> getRequiredForms() {
        return new ArrayList<>();
    }
}
