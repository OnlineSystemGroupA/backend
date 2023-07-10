package com.stcos.server.entity.form;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Map;

/*
           ______            _____     __           __  _       ___ __        ______
          / ____/___  ____  / __(_)___/ /__  ____  / /_(_)___ _/ (_) /___  __/ ____/___  _________ ___
         / /   / __ \/ __ \/ /_/ / __  / _ \/ __ \/ __/ / __ `/ / / __/ / / / /_  / __ \/ ___/ __ `__ \
        / /___/ /_/ / / / / __/ / /_/ /  __/ / / / /_/ / /_/ / / / /_/ /_/ / __/ / /_/ / /  / / / / / /
        \____/\____/_/ /_/_/ /_/\__,_/\___/_/ /_/\__/_/\__,_/_/_/\__/\__, /_/    \____/_/  /_/ /_/ /_/
                                                                    /____/
 */

/**
 * 软件项目委托测试保密协议
 *
 * @author AmadeusZQK
 * @version 1.0 (final)
 * @since 2023/5/15
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ConfidentialityForm extends Form {
    private String client;
    private String softwareName;

    @Override
    public Map<String, String> toTemplateFormat() {
        return null;
    }
}