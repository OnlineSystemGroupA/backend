package com.stcos.server.service;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/4/24 0:23
 */
public interface EmailService {

    /**
     * 发送邮件
     *
     * @param to 发送的目标邮箱
     * @param subject 邮件标题
     * @param text 邮件正文
     */
    void sendEmail(String to, String subject, String text);

}
