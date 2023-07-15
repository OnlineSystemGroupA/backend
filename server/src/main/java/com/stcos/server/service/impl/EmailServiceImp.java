package com.stcos.server.service.impl;

import com.stcos.server.service.EmailService;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Properties;

/**
 * 邮件服务实现类
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/4/24 0:24
 */
@Service
public class EmailServiceImp implements EmailService, JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("用户委托完成！");
    }

    private JavaMailSender javaMailSender;

    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Value("${spring.mail.username}")
    String from;

    @Override
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 发件人
        simpleMailMessage.setFrom(from);
        System.out.println(from);
        // 收件人
        simpleMailMessage.setTo(to);
        // 邮件主题
        simpleMailMessage.setSubject(subject);
        // 邮件内容
        simpleMailMessage.setText(text);
        javaMailSender.send(simpleMailMessage);
    }
}
