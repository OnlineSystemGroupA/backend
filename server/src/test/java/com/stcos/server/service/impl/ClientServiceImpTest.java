package com.stcos.server.service.impl;

import com.stcos.server.entity.user.Client;
import com.stcos.server.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
public class ClientServiceImpTest {

    @Autowired
    private ClientService clientService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    Client client = null;

    @BeforeEach
    void setUp() {
        client = new Client("testUsername", passwordEncoder.encode("testPassword"), "testEmail@test.com");
    }

    @Test
    void getById() {
        clientService.register(client);

        Client retrievedClient = clientService.getById(client.getUid());
        assertEquals("testUsername", retrievedClient.getUsername());
    }

    @Test
    void getByUsername() {
        clientService.register(client);
        Client retrievedClient = clientService.getByUsername("testUsername");
        assertEquals("testUsername", retrievedClient.getUsername());
    }

    @Test
    void addProcessInstance() {
        clientService.register(client);
        clientService.addProcessInstance(client.getUid(), "testProcessInstance");
        Client updatedClient = clientService.getById(client.getUid());
        assertTrue(updatedClient.getProcessInstances().contains("testProcessInstance"));
    }

    @Test
    void existEmail() {
        clientService.register(client);
        assertFalse(clientService.existEmail("testEmail@test.com", client.getUid()));
    }

    @Test
    void existPhone() {
        clientService.register(client);
        assertFalse(clientService.existPhone("testPhone", client.getUid()));
    }

    @Test
    void getAll() {
        clientService.register(client);
        List<Client> clients = clientService.getAll();
        assertTrue(clients.size() > 0);
    }

    @Test
    void deleteClient() {
        clientService.register(client);
        assertNotNull(clientService.getById(client.getUid()));
        clientService.deleteClient(client);
        assertNull(clientService.getById(client.getUid()));
    }

    @Test
    void register() {
        clientService.register(client);
        assertNotNull(clientService.getById(client.getUid()));
    }
}
