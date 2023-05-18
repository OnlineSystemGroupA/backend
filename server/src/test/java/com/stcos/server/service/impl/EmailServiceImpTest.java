package com.stcos.server.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/18 15:03
 */

@SpringBootTest
class EmailServiceImpTest {

    @Autowired
    private EmailServiceImp emailServiceImp;

    @Test
    void sendEmail() {
        emailServiceImp.sendEmail("201220100@smail.nju.edu.cn",
                "测试", "你醒啦，该干活了。");
    }
}