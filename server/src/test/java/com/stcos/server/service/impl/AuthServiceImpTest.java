package com.stcos.server.service.impl;

import com.stcos.server.database.mysql.ClientMapper;
import com.stcos.server.model.user.Client;
import com.stcos.server.exception.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class AuthServiceImpTest {

    @Autowired
    private AuthServiceImp authServiceImp;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Test normal client login
    @Test
    void clientLogin() throws ServiceException {
        Random random = new Random();
        int randomNumber = random.nextInt(2147483647);

        String uid = String.valueOf(randomNumber);

        Client testClient = new Client("testClient" + uid, passwordEncoder.encode("testPassword"), "testEmail@test.com");
        clientMapper.insert(testClient);

        authServiceImp.login("testClient" + uid, "testPassword", "client");
    }

    // Test client login with wrong password
    @Test
    void clientLoginWrongPassword() {
        Random random = new Random();
        int randomNumber = random.nextInt(2147483647);

        String uid = String.valueOf(randomNumber);

        Client testClient = new Client("testClient" + uid, passwordEncoder.encode("testPassword"), "testEmail@test.com");
        clientMapper.insert(testClient);
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            authServiceImp.login("testClient" + uid, "wrongPassword", "client");
        });
        assertEquals(1, exception.getCode());
    }

    // Test non-existing client
    @Test
    void clientNotExist() {
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            authServiceImp.login("nonexistentClient", "testPassword", "client");
        });
        assertEquals(0, exception.getCode());
    }

    // Test wrong user type
    @Test
    void wrongUserType() {
        Random random = new Random();
        int randomNumber = random.nextInt();

        String uid = String.valueOf(randomNumber);

        Client testClient = new Client("testClient" + uid, passwordEncoder.encode("testPassword"), "testEmail@test.com");
        clientMapper.insert(testClient);
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            authServiceImp.login("testClient" + uid, "testPassword", "wrongUserType");
        });
        assertEquals(3, exception.getCode());
    }

    // Test no user type provided
    @Test
    void noUserType() {
        Random random = new Random();
        int randomNumber = random.nextInt(2147483647);

        String uid = String.valueOf(randomNumber);

        Client testClient = new Client("testClient" + uid, passwordEncoder.encode("testPassword"), "testEmail@test.com");
        clientMapper.insert(testClient);
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            authServiceImp.login("testClient" + uid + UUID.randomUUID(), "testPassword", null);
        });
        assertEquals(3, exception.getCode());
    }

    // Test disabled client
    @Test
    void clientDisabled() {
        Random random = new Random();
        int randomNumber = random.nextInt(2147483647);

        String uid = String.valueOf(randomNumber);

        Client testClient = new Client("testClient" + uid, passwordEncoder.encode("testPassword"), "testEmail@test.com");
        testClient.setEnabled(false);
        clientMapper.insert(testClient);

        ServiceException exception = assertThrows(ServiceException.class, () -> {
            authServiceImp.login("testClient" + uid, "testPassword", "client");
        });
        assertEquals(2, exception.getCode());
    }

    // Test normal operator login
    @Test
    void operatorLogin() throws ServiceException {
        authServiceImp.login("20xxx0001", "123456", "operator");
    }

    // Test operator login with wrong password
    @Test
    void operatorLoginWrongPassword() {
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            authServiceImp.login("20xxx0001", "wrongPassword", "operator");
        });
        assertEquals(1, exception.getCode());
    }

    // Test non-existing operator
    @Test
    void operatorNotExist() {
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            authServiceImp.login("nonexistentOperator", "123456", "operator");
        });
        assertEquals(0, exception.getCode());
    }

    // Test normal admin login
    @Test
    void adminLogin() throws ServiceException {
        authServiceImp.login("admin", "123456", "admin");
    }

    // Test admin login with wrong password
    @Test
    void adminLoginWrongPassword() {
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            authServiceImp.login("admin", "wrongPassword", "admin");
        });
        assertEquals(1, exception.getCode());
    }

    // Test non-existing admin
    @Test
    void adminNotExist() {
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            authServiceImp.login("nonexistentAdmin", "123456", "admin");
        });
        assertEquals(0, exception.getCode());
    }
}