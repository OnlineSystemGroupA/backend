package com.stcos.server.model.process.configs;

import com.stcos.server.model.form.FormType;

import java.util.Set;

/**
 * 测试文档归档
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/27 20:48
 */
public class ArchiveConfig extends TaskConfig {

    public ArchiveConfig() {
        super(
                Set.of(FormType.TYPE_TEST_WORK_CHECK_FORM),
                Set.of()
        );
    }

}
