package com.stcos.server.service.impl;

import com.stcos.server.database.mysql.AdminMapper;
import com.stcos.server.database.mysql.ClientMapper;
import com.stcos.server.database.mysql.OperatorMapper;
import com.stcos.server.entity.user.Client;
import com.stcos.server.entity.user.Operator;
import com.stcos.server.entity.user.User;
import com.stcos.server.exception.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthServiceImpTest {

    @Autowired
    private AuthServiceImp authServiceImp;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 测试客户端正常登录
    @Test
    void clientLogin() {
        Random random = new Random();
        int randomNumber = random.nextInt(2147483647); 

        String uid = String.valueOf(randomNumber); 
        
        Client testClient = new Client("testClient" + uid, passwordEncoder.encode("testPassword"), "testEmail");
        clientMapper.insert(testClient);
        try {
            authServiceImp.login("testClient" + uid, "testPassword", "client");
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    // 测试客户端密码错误
    @Test
    void clientLoginWrongPassword() {
        Random random = new Random();
        int randomNumber = random.nextInt(2147483647); 

        String uid = String.valueOf(randomNumber);
        
        Client testClient = new Client("testClient" + uid, passwordEncoder.encode("testPassword"), "testEmail");
        clientMapper.insert(testClient);
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            authServiceImp.login("testClient" + uid, "wrongPassword", "client");
        });
        assertEquals(1, exception.getCode());
    }

    // 测试用户不存在
    @Test
    void userNotExist() {
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            authServiceImp.login("nonexistentUser", "testPassword", "client");
        });
        assertEquals(0, exception.getCode());
    }

    // 测试错误的用户类型
    @Test
    void wrongUserType() {
        Random random = new Random();
        int randomNumber = random.nextInt();

        String uid = String.valueOf(randomNumber);
        
        Client testClient = new Client("testClient" + uid, passwordEncoder.encode("testPassword"), "testEmail");
        clientMapper.insert(testClient);
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            authServiceImp.login("testClient" + uid, "testPassword", "wrongUserType");
        });
        assertEquals(3, exception.getCode());
    }

    // 测试未传入 userType
    @Test
    void noUserType() {
        Random random = new Random();
        int randomNumber = random.nextInt(2147483647); 

        String uid = String.valueOf(randomNumber);
        
        Client testClient = new Client("testClient" + uid, passwordEncoder.encode("testPassword"), "testEmail");
        clientMapper.insert(testClient);
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            authServiceImp.login("testClient" + uid + UUID.randomUUID(), "testPassword", null);
        });
        assertEquals(3, exception.getCode());
    }

    // 测试用户被禁用
    @Test
    void clientDisabled() {
        Random random = new Random();
        int randomNumber = random.nextInt(2147483647); 

        String uid = String.valueOf(randomNumber);
        
        Client testClient = new Client("testClient" + uid, passwordEncoder.encode("testPassword"), "testEmail");
        testClient.setEnabled(false);
        clientMapper.insert(testClient);

        ServiceException exception = assertThrows(ServiceException.class, () -> {
            authServiceImp.login("testClient" + uid, "testPassword", "client");
        });
        assertEquals(2, exception.getCode());
    }

    // 测试员工正常登录
    @Test
    void operatorLogin() {
        try {
            authServiceImp.login("20xxx0001", "123456", "operator");
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    // 测试员工密码错误
    @Test
    void operatorLoginWrongPassword() {
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            authServiceImp.login("20xxx0001", "wrongPassword", "operator");
        });
        assertEquals(1, exception.getCode());
    }

    // 测试管理员正常登录
    @Test
    void adminLogin() {
        try {
            authServiceImp.login("admin", "123456", "admin");
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    // 测试管理员密码错误
    @Test
    void adminLoginWrongPassword() {
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            authServiceImp.login("admin", "wrongPassword", "admin");
        });
        assertEquals(1, exception.getCode());
    }
}