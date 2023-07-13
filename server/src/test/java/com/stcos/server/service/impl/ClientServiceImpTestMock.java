package com.stcos.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stcos.server.database.mysql.ClientMapper;
import com.stcos.server.entity.user.Client;
import com.stcos.server.exception.ServerErrorException;
import com.stcos.server.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceImpTestMock {

    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientService clientService = new ClientServiceImp();
    
    private Client client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        client = new Client("testUsername", "testPassword", "testEmail@test.com");
    }

    @Test
    void getById() {
        String uid = "testUid";
        
        client.setUid(uid);

        when(clientMapper.selectById(uid)).thenReturn(client);

        Client retrievedClient = clientService.getById(uid);
        assertEquals(uid, retrievedClient.getUid());
        verify(clientMapper, times(1)).selectById(uid);
    }

    @Test
    void getByUsername() {
        String username = "testUsername";
        
        client.setUsername(username);

        when(clientMapper.getByUsernameClient(username)).thenReturn(client);

        Client retrievedClient = clientService.getByUsername(username);
        assertEquals(username, retrievedClient.getUsername());
        verify(clientMapper, times(1)).getByUsernameClient(username);
    }

    @Test
    void addProcessInstance() {
        String uid = "testUid";
        String processInstanceId = "testProcessInstanceId";


        when(clientMapper.selectById(uid)).thenReturn(client);
        when(clientMapper.updateById(client)).thenReturn(1);

        clientService.addProcessInstance(uid, processInstanceId);

        assertTrue(client.getProcessInstances().contains(processInstanceId));
        verify(clientMapper, times(1)).selectById(uid);
        verify(clientMapper, times(1)).updateById(client);
    }

    @Test
    void existEmail() {
        String email = "testEmail@test.com";
        String uid = "testUid";

        QueryWrapper<Client> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        queryWrapper.ne("uid", uid);

        when(clientMapper.exists(argThat(wrapper -> wrapper.getSqlSegment().equals(queryWrapper.getSqlSegment()))))
                .thenReturn(false);

        boolean exist = clientService.existEmail(email, uid);
        assertFalse(exist);
        verify(clientMapper, times(1)).exists(argThat(wrapper -> wrapper.getSqlSegment().equals(queryWrapper.getSqlSegment())));
    }

    @Test
    void existPhone() {
        String phone = "testPhone";
        String uid = "testUid";

        QueryWrapper<Client> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        queryWrapper.ne("uid", uid);

        when(clientMapper.exists(argThat(wrapper -> wrapper.getSqlSegment().equals(queryWrapper.getSqlSegment()))))
                .thenReturn(false);

        boolean exist = clientService.existPhone(phone, uid);
        assertFalse(exist);
        verify(clientMapper, times(1)).exists(argThat(wrapper -> wrapper.getSqlSegment().equals(queryWrapper.getSqlSegment())));
    }

    @Test
    void getAll() {
        List<Client> clients = List.of(new Client("testUsername1", "testPassword", "testEmail@test.com"),
        new Client("testUsername2", "testPassword", "testEmail@test.com"));

        when(clientMapper.selectList(null)).thenReturn(clients);

        List<Client> retrievedClients = clientService.getAll();
        assertEquals(clients.size(), retrievedClients.size());
        verify(clientMapper, times(1)).selectList(null);
    }

    @Test
    void deleteClient() {
        clientService.deleteClient(client);

        verify(clientMapper, times(1)).deleteById(client);
    }

    @Test
    void register() {
        Client newClient = new Client("newTestUsername", "testPassword", "testEmail@test.com");

        clientService.register(client);

        verify(clientMapper, times(1)).addNewUser(client);
    }
}
