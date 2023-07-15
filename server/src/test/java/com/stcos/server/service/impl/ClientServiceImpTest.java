package com.stcos.server.service.impl;

import com.stcos.server.model.user.Client;
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

    private Client testClient = null;

    @BeforeEach
    void setUp() {
        testClient = new Client("testUsername", passwordEncoder.encode("testPassword"), "testEmail@test.com");
    }

    @Test
    void getById() {
        clientService.register(testClient);

        Client retrievedClient = clientService.getById(testClient.getUid());
        assertEquals("testUsername", retrievedClient.getUsername());
    }

    @Test
    void getByUsername() {
        clientService.register(testClient);
        Client retrievedClient = clientService.getByUsername("testUsername");
        assertEquals("testUsername", retrievedClient.getUsername());
    }

    @Test
    void addProcessInstance() {
        clientService.register(testClient);
        clientService.addProcessInstance(testClient.getUid(), "testProcessInstance");
        Client updatedClient = clientService.getById(testClient.getUid());
        assertTrue(updatedClient.getProcessInstances().contains("testProcessInstance"));
    }

    @Test
    void existEmail() {
        clientService.register(testClient);
        assertFalse(clientService.existEmail("testEmail@test.com", testClient.getUid()));
    }

    @Test
    void existPhone() {
        clientService.register(testClient);
        assertFalse(clientService.existPhone("testPhone", testClient.getUid()));
    }

    @Test
    void getAll() {
        clientService.register(testClient);
        List<Client> clients = clientService.getAll();
        assertTrue(clients.size() > 0);
    }

    @Test
    void deleteClient() {
        clientService.register(testClient);
        assertNotNull(clientService.getById(testClient.getUid()));
        clientService.deleteClient(testClient);
        assertNull(clientService.getById(testClient.getUid()));
    }

    @Test
    void register() {
        clientService.register(testClient);
        assertNotNull(clientService.getById(testClient.getUid()));
    }
}
