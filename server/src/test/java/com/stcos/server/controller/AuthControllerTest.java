package com.stcos.server.controller;

import com.stcos.server.service.AuthService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.doReturn;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/11 21:47
 */
@SpringBootTest
class AuthControllerTest {

    @Autowired
    AuthController authController;

    @MockBean
    AuthService authService;


    @Before
    public void initAuthService() {
//        doReturn().when()
    }

    @Test
    public void login() {



    }
}