package com.stcos.server.util;

import com.stcos.server.entity.form.FormIndex;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/17 15:05
 */
class JSONUtilTest {

    @Test
    void toJSONString() {
        System.out.println(JSONUtil.toJSONString(new FormIndex()));
    }
}