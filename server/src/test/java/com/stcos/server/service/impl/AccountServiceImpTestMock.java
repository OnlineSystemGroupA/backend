package com.stcos.server.service.impl;

import com.stcos.server.database.mysql.AdminMapper;
import com.stcos.server.entity.dto.ClientDetailsDto;
import com.stcos.server.entity.dto.OperatorDetailsDto;
import com.stcos.server.entity.user.Admin;
import com.stcos.server.entity.user.Client;
import com.stcos.server.entity.user.Operator;
import com.stcos.server.exception.ServiceException;
import com.stcos.server.service.ClientService;
import com.stcos.server.service.OperatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountServiceImpTestMock {

    @InjectMocks
    private AccountServiceImp accountService;

    @Mock
    private AdminMapper adminMapper;

    @Mock
    private ClientService clientService;

    @Mock
    private OperatorService operatorService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAdmin() throws ServiceException {
        String username = "admin";
        Admin admin = new Admin();
        when(adminMapper.getByUsernameAdmin(username)).thenReturn(admin);

        Admin result = accountService.getAdmin(username);

        assertNotNull(result);
        assertEquals(admin, result);
        verify(adminMapper, times(1)).getByUsernameAdmin(username);
    }

    @Test
    void getAdminNotFound() {
        String username = "nonExistingAdmin";
        when(adminMapper.getByUsernameAdmin(username)).thenReturn(null);

        ServiceException exception = assertThrows(ServiceException.class, () -> {
            accountService.getAdmin(username);
        });
        assertEquals(0, exception.getCode());
        verify(adminMapper, times(1)).getByUsernameAdmin(username);
    }

    @Test
    void getClient() throws ServiceException {
        String username = "testUsername";
        Client client = Mockito.mock(Client.class);
        when(clientService.getByUsername(username)).thenReturn(client);

        Client result = accountService.getClient(username);

        assertNotNull(result);
        assertEquals(client, result);
        verify(clientService, times(1)).getByUsername(username);
    }

    @Test
    void getClientNotFound() {
        String username = "nonExistingClient";
        when(clientService.getByUsername(username)).thenReturn(null);

        ServiceException exception = assertThrows(ServiceException.class, () -> {
            accountService.getClient(username);
        });
        assertEquals(0, exception.getCode());
        verify(clientService, times(1)).getByUsername(username);
    }

    @Test
    void getOperator() throws ServiceException {
        String jobNumber = "testJobNumber";
        Operator operator = new Operator();
        when(operatorService.getByJobNumber(jobNumber)).thenReturn(operator);

        Operator result = accountService.getOperator(jobNumber);

        assertNotNull(result);
        assertEquals(operator, result);
        verify(operatorService, times(1)).getByJobNumber(jobNumber);
    }

    @Test
    void getOperatorNotFound() {
        String jobNumber = "nonExistingJobNumber";
        when(operatorService.getByJobNumber(jobNumber)).thenReturn(null);

        ServiceException exception = assertThrows(ServiceException.class, () -> {
            accountService.getOperator(jobNumber);
        });
        assertEquals(0, exception.getCode());
        verify(operatorService, times(1)).getByJobNumber(jobNumber);
    }

    @Test
    void register() throws ServiceException {
        String username = "testUsername";
        String password = "testPassword";
        String email = "testEmail@test.com";
        Client client = Mockito.mock(Client.class);
        when(clientService.getByUsername(username)).thenReturn(null);

        accountService.register(username, password, email);

        verify(clientService, times(1)).getByUsername(username);
    }

    @Test
    void registerUsernameExists() {
        String username = "testUsername";
        String password = "testPassword";
        String email = "testEmail@test.com";
        Client existingClient = Mockito.mock(Client.class);
        when(clientService.getByUsername(username)).thenReturn(existingClient);

        ServiceException exception = assertThrows(ServiceException.class, () -> {
            accountService.register(username, password, email);
        });
        assertEquals(0, exception.getCode());
        verify(clientService, times(1)).getByUsername(username);
        verify(clientService, never()).register(any(Client.class));
    }

    @Test
    void updateClientDetails() throws ServiceException {
        Client client = new Client("testClientName", passwordEncoder.encode("testPassword"), "testEmail@test.com");
        ClientDetailsDto clientDetailsDto = new ClientDetailsDto();
        clientDetailsDto.setRealName("John Doe");
        clientDetailsDto.setCompany("ACME Corp");

        accountService.updateClientDetails(client, clientDetailsDto);

        assertEquals("John Doe", client.getRealName());
        assertEquals("ACME Corp", client.getCompany());
        verify(clientService, times(1)).updateById(client);
    }

    @Test
    void getOperatorsByDepartment() {
        String department = "testDepartment";
        when(operatorService.getByDepartment(department)).thenReturn(Collections.singletonList(new Operator()));

        List<Operator> result = accountService.getOperatorsByDepartment(department);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(operatorService, times(1)).getByDepartment(department);
    }

    @Test
    void updateOperatorDetails() throws ServiceException {
        Operator operator = new Operator();
        OperatorDetailsDto operatorDetailsDto = new OperatorDetailsDto();
        operatorDetailsDto.setRealName("John Doe");
        operatorDetailsDto.setDepartment("IT");

        accountService.updateOperatorDetails(operator, operatorDetailsDto);

        assertEquals("John Doe", operator.getRealName());
        assertEquals("IT", operator.getDepartment());
        verify(operatorService, times(1)).updateById(operator);
    }

    @Test
    void lockOperator() throws ServiceException {
        String uid = "testOperatorId";
        boolean doLock = true;
        Operator operator = new Operator();
        when(operatorService.getById(uid)).thenReturn(operator);

        accountService.lockOperator(uid, doLock);

        assertEquals(!doLock, operator.isAccountNonLocked());
        verify(operatorService, times(1)).updateById(operator);
    }

    @Test
    void lockOperatorNotFound() {
        String uid = "nonExistingOperator";
        boolean doLock = true;
        when(operatorService.getById(uid)).thenReturn(null);

        ServiceException exception = assertThrows(ServiceException.class, () -> {
            accountService.lockOperator(uid, doLock);
        });
        assertEquals(0, exception.getCode());
        verify(operatorService, times(1)).getById(uid);
        verify(operatorService, never()).updateById(any(Operator.class));
    }

    @Test
    void lockClient() throws ServiceException {
        String uid = "testClientId";
        boolean doLock = true;
        Client client = Mockito.mock(Client.class);
        when(clientService.getById(uid)).thenReturn(client);

        accountService.lockClient(uid, doLock);

        assertEquals(!doLock, client.isAccountNonLocked());
        verify(clientService, times(1)).updateById(client);
    }

    @Test
    void lockClientNotFound() {
        String uid = "nonExistingClient";
        boolean doLock = true;
        when(clientService.getById(uid)).thenReturn(null);

        ServiceException exception = assertThrows(ServiceException.class, () -> {
            accountService.lockClient(uid, doLock);
        });
        assertEquals(0, exception.getCode());
        verify(clientService, times(1)).getById(uid);
        verify(clientService, never()).updateById(any(Client.class));
    }

    @Test
    void getClientById() throws ServiceException {
        String uid = "testClientId";
        Client client = Mockito.mock(Client.class);
        when(clientService.getById(uid)).thenReturn(client);

        Client result = accountService.getClientById(uid);

        assertNotNull(result);
        assertEquals(client, result);
        verify(clientService, times(1)).getById(uid);
    }

    @Test
    void getClientByIdNotFound() {
        String uid = "nonExistingClient";
        when(clientService.getById(uid)).thenReturn(null);

        ServiceException exception = assertThrows(ServiceException.class, () -> {
            accountService.getClientById(uid);
        });
        assertEquals(0, exception.getCode());
        verify(clientService, times(1)).getById(uid);
    }

    @Test
    void getOperatorById() throws ServiceException {
        String uid = "testOperatorId";
        Operator operator = new Operator();
        when(operatorService.getById(uid)).thenReturn(operator);

        Operator result = accountService.getOperatorById(uid);

        assertNotNull(result);
        assertEquals(operator, result);
        verify(operatorService, times(1)).getById(uid);
    }

    @Test
    void getOperatorByIdNotFound() {
        String uid = "nonExistingOperator";
        when(operatorService.getById(uid)).thenReturn(null);

        ServiceException exception = assertThrows(ServiceException.class, () -> {
            accountService.getOperatorById(uid);
        });
        assertEquals(0, exception.getCode());
        verify(operatorService, times(1)).getById(uid);
    }

    @Test
    void getOperators() {
        List<Operator> operators = Arrays.asList(new Operator(), new Operator());
        when(operatorService.getAll()).thenReturn(operators);

        List<Operator> result = accountService.getOperators();

        assertNotNull(result);
        assertEquals(operators, result);
        verify(operatorService, times(1)).getAll();
    }

    @Test
    void getClients() {
        List<Client> clients = Arrays.asList(Mockito.mock(Client.class), Mockito.mock(Client.class));
        when(clientService.getAll()).thenReturn(clients);

        List<Client> result = accountService.getClients();

        assertNotNull(result);
        assertEquals(clients, result);
        verify(clientService, times(1)).getAll();
    }

    @Test
    void createOperator() throws ServiceException {
        String jobNumber = "testJobNumber";
        OperatorDetailsDto operatorDetailsDto = new OperatorDetailsDto();
        operatorDetailsDto.setIsNonLocked(false);
        operatorDetailsDto.setJobNumber(jobNumber);

        when(operatorService.getByJobNumber(jobNumber)).thenReturn(null);

        accountService.createOperator(operatorDetailsDto);

        verify(operatorService, times(1)).getByJobNumber(jobNumber);
        verify(operatorService, times(1)).createOperator(any(Operator.class));
    }

    @Test
    void deleteClient() throws ServiceException {
        String uid = "testClientId";
        Client client = Mockito.mock(Client.class);
        when(clientService.getById(uid)).thenReturn(client);

        accountService.deleteClient(uid);

        verify(clientService, times(1)).getById(uid);
        verify(clientService, times(1)).deleteClient(client);
    }

    @Test
    void deleteClientNotFound() {
        String uid = "nonExistingClient";
        when(clientService.getById(uid)).thenReturn(null);

        ServiceException exception = assertThrows(ServiceException.class, () -> {
            accountService.deleteClient(uid);
        });
        assertEquals(0, exception.getCode());
        verify(clientService, times(1)).getById(uid);
        verify(clientService, never()).deleteClient(any(Client.class));
    }

    @Test
    void deleteOperator() throws ServiceException {
        String uid = "testOperatorId";
        Operator operator = new Operator();
        when(operatorService.getById(uid)).thenReturn(operator);

        accountService.deleteOperator(uid);

        verify(operatorService, times(1)).getById(uid);
        verify(operatorService, times(1)).deleteOperator(operator);
    }

    @Test
    void deleteOperatorNotFound() {
        String uid = "nonExistingOperator";
        when(operatorService.getById(uid)).thenReturn(null);

        ServiceException exception = assertThrows(ServiceException.class, () -> {
            accountService.deleteOperator(uid);
        });
        assertEquals(0, exception.getCode());
        verify(operatorService, times(1)).getById(uid);
        verify(operatorService, never()).deleteOperator(any(Operator.class));
    }
}