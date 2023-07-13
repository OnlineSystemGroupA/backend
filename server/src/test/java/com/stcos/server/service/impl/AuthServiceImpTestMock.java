package com.stcos.server.service.impl;

import com.stcos.server.database.mysql.AdminMapper;
import com.stcos.server.database.mysql.ClientMapper;
import com.stcos.server.database.mysql.OperatorMapper;
import com.stcos.server.entity.user.Admin;
import com.stcos.server.entity.user.Client;
import com.stcos.server.entity.user.Operator;
import com.stcos.server.entity.user.User;
import com.stcos.server.exception.ServiceException;
import com.stcos.server.service.impl.AuthServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceImpTestMock {

    @InjectMocks
    private AuthServiceImp authServiceImp;

    @Mock
    private AdminMapper adminMapper;

    @Mock
    private ClientMapper clientMapper;

    @Mock
    private OperatorMapper operatorMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void clientLoginSuccessful() throws ServiceException {
        String username = "testClient";
        String password = "testPassword";
        String userType = "client";

        Client testClient = Mockito.mock(Client.class);
        when(clientMapper.getByUsernameClient(username)).thenReturn(testClient);
        when(passwordEncoder.matches(password, testClient.getPassword())).thenReturn(true);
        when(testClient.isEnabled()).thenReturn(true);

        UserDetails userDetails = authServiceImp.login(username, password, userType);
        assertNotNull(userDetails);
        assertEquals(testClient, userDetails);

        verify(clientMapper, times(1)).getByUsernameClient(username);
        verifyNoMoreInteractions(clientMapper, operatorMapper);
    }

    @Test
    void clientLoginWrongPassword() {
        String username = "testClient";
        String password = "wrongPassword";
        String userType = "client";

        Client testClient = new Client(username, passwordEncoder.encode("testPassword"), "testEmail@test.com");
        when(clientMapper.getByUsernameClient(username)).thenReturn(testClient);
        when(passwordEncoder.matches(password, testClient.getPassword())).thenReturn(false);

        ServiceException exception = assertThrows(ServiceException.class, () -> {
            authServiceImp.login(username, password, userType);
        });
        assertEquals(1, exception.getCode());

        verify(clientMapper, times(1)).getByUsernameClient(username);
        verifyNoMoreInteractions(clientMapper, operatorMapper);
    }

    @Test
    void clientNotExist() {
        String username = "nonexistentClient";
        String password = "testPassword";
        String userType = "client";

        when(clientMapper.getByUsernameClient(username)).thenReturn(null);

        ServiceException exception = assertThrows(ServiceException.class, () -> {
            authServiceImp.login(username, password, userType);
        });
        assertEquals(0, exception.getCode());

        verify(clientMapper, times(1)).getByUsernameClient(username);
        verifyNoMoreInteractions(clientMapper, operatorMapper);
    }

    @Test
    void wrongUserType() {
        String username = "testUser";
        String password = "testPassword";
        String userType = "wrongUserType";

        ServiceException exception = assertThrows(ServiceException.class, () -> {
            authServiceImp.login(username, password, userType);
        });
        assertEquals(3, exception.getCode());
    }

    @Test
    void noUserType() {
        String username = "testUser";
        String password = "testPassword";
        String userType = null;

        ServiceException exception = assertThrows(ServiceException.class, () -> {
            authServiceImp.login(username, password, userType);
        });
        assertEquals(3, exception.getCode());
    }

    @Test
    void clientDisabled() {
        String username = "testClient";
        String password = "testPassword";
        String userType = "client";

        Client testClient = new Client(username, passwordEncoder.encode(password), "testEmail@test.com");
        testClient.setEnabled(false);
        when(clientMapper.getByUsernameClient(username)).thenReturn(testClient);
        when(passwordEncoder.matches(password, testClient.getPassword())).thenReturn(true);

        ServiceException exception = assertThrows(ServiceException.class, () -> {
            authServiceImp.login(username, password, userType);
        });
        assertEquals(2, exception.getCode());

        verify(clientMapper, times(1)).getByUsernameClient(username);
        verifyNoMoreInteractions(clientMapper, operatorMapper);
    }

    @Test
    void operatorLoginSuccessful() throws ServiceException {
        String jobNumber = "20xxx0001";
        String password = "123456";

        Operator testOperator = Mockito.mock(Operator.class);
        testOperator.setJobNumber(jobNumber);
        testOperator.setPassword(passwordEncoder.encode(password));
        when(operatorMapper.selectByJobNumber(jobNumber)).thenReturn(testOperator);
        when(passwordEncoder.matches(password, testOperator.getPassword())).thenReturn(true);
        when(testOperator.isEnabled()).thenReturn(true);

        UserDetails userDetails = authServiceImp.login(jobNumber, password, "operator");
        assertNotNull(userDetails);
        assertEquals(testOperator, userDetails);

        verify(operatorMapper, times(1)).selectByJobNumber(jobNumber);
        verifyNoMoreInteractions(clientMapper, operatorMapper);
    }

    @Test
    void operatorLoginWrongPassword() {
        String jobNumber = "20xxx0001";
        String password = "wrongPassword";
        String userType = "operator";

        Operator testOperator = Mockito.mock(Operator.class);
        testOperator.setJobNumber(jobNumber);
        testOperator.setPassword(passwordEncoder.encode("123456"));
        when(operatorMapper.selectByJobNumber(jobNumber)).thenReturn(testOperator);
        when(passwordEncoder.matches(password, testOperator.getPassword())).thenReturn(false);

        ServiceException exception = assertThrows(ServiceException.class, () -> {
            authServiceImp.login(jobNumber, password, userType);
        });
        assertEquals(1, exception.getCode());

        verify(operatorMapper, times(1)).selectByJobNumber(jobNumber);
        verifyNoMoreInteractions(clientMapper, operatorMapper);
    }

    @Test
    void operatorNotExist() {
        String jobNumber = "nonexistentOperator";
        String password = "123456";
        String userType = "operator";

        when(operatorMapper.selectByJobNumber(jobNumber)).thenReturn(null);

        ServiceException exception = assertThrows(ServiceException.class, () -> {
            authServiceImp.login(jobNumber, password, userType);
        });
        assertEquals(0, exception.getCode());

        verify(operatorMapper, times(1)).selectByJobNumber(jobNumber);
        verifyNoMoreInteractions(clientMapper, operatorMapper);
    }

    @Test
    void adminLoginSuccessful() throws ServiceException {
        String username = "admin";
        String password = "123456";

        Admin testAdmin = Mockito.mock(Admin.class);
        when(adminMapper.getByUsernameAdmin(username)).thenReturn(testAdmin);
        when(passwordEncoder.matches(password, testAdmin.getPassword())).thenReturn(true);
        when(testAdmin.isEnabled()).thenReturn(true);

        UserDetails userDetails = authServiceImp.login(username, password, "admin");
        assertNotNull(userDetails);
        assertEquals(testAdmin, userDetails);

        verify(adminMapper, times(1)).getByUsernameAdmin(username);
        verifyNoMoreInteractions(adminMapper, clientMapper, operatorMapper);
    }

    @Test
    void adminLoginWrongPassword() {
        String username = "admin";
        String password = "wrongPassword";
        String userType = "admin";

        Admin testAdmin = new Admin();
        when(adminMapper.getByUsernameAdmin(username)).thenReturn(testAdmin);
        when(passwordEncoder.matches(password, testAdmin.getPassword())).thenReturn(false);

        ServiceException exception = assertThrows(ServiceException.class, () -> {
            authServiceImp.login(username, password, userType);
        });
        assertEquals(1, exception.getCode());

        verify(adminMapper, times(1)).getByUsernameAdmin(username);
        verifyNoMoreInteractions(adminMapper, clientMapper, operatorMapper);
    }

    @Test
    void adminNotExist() {
        String username = "nonexistentAdmin";
        String password = "123456";
        String userType = "admin";

        when(adminMapper.getByUsernameAdmin(username)).thenReturn(null);

        ServiceException exception = assertThrows(ServiceException.class, () -> {
            authServiceImp.login(username, password, userType);
        });
        assertEquals(0, exception.getCode());

        verify(adminMapper, times(1)).getByUsernameAdmin(username);
        verifyNoMoreInteractions(adminMapper, clientMapper, operatorMapper);
    }
}
