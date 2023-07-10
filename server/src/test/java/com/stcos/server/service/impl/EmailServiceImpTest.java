package com.stcos.server.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.*;

class EmailServiceImpTest {

    @InjectMocks
    EmailServiceImp emailService;

    @Mock
    JavaMailSender javaMailSender;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void sendEmail() {
        String to = "test@example.com";
        String subject = "test subject";
        String text = "test text";
        emailService.sendEmail(to, subject, text);

        verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}
