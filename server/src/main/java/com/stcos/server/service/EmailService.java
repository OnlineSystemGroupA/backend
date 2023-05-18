package com.stcos.server.service;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/4/24 0:23
 */
public interface EmailService {

    void sendEmail(String to, String subject, String text);

}
