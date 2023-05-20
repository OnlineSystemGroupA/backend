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
                "确认测试报告", ",你醒啦？该干活了");

        String subject = "确认测试报告";
        //String text = "您好！您的一份测试委托的报告已经生成，请尽快前往确认。";
        String text = "AmadeusZQK,你醒啦？该干活了";
        emailServiceImp.sendEmail("1138705975@qq.com", subject, text);
    }

    @Test
    void testSendEmail() {
    }
}