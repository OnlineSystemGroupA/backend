package com.stcos.server.util;

import com.stcos.server.entity.form.ApplicationForm;
import com.stcos.server.entity.form.FormMetadata;
import org.junit.jupiter.api.Test;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/17 15:05
 */
class JSONUtilTest {

    @Test
    void test() {
        ApplicationForm applicationForm = new ApplicationForm();
        System.out.println(JSONUtil.toJSONString(applicationForm));
    }

}