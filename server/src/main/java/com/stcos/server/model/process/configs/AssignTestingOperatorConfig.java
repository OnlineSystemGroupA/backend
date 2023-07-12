package com.stcos.server.model.process.configs;

import com.stcos.server.model.process.ProcessVariables;

import java.util.Set;

/**
 * 分配测试部人员
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/21 23:37
 */
public class AssignTestingOperatorConfig extends TaskConfig {

    public AssignTestingOperatorConfig() {
        super(
                Set.of(),
                Set.of(ProcessVariables.VAR_TESTING_OPERATOR)
        );
    }

}
