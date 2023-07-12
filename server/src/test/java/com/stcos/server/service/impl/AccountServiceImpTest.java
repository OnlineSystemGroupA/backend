package com.stcos.server.service.impl;

import com.stcos.server.model.dto.ClientDetailsDto;
import com.stcos.server.model.dto.OperatorDetailsDto;
import com.stcos.server.model.user.Client;
import com.stcos.server.model.user.Operator;
import com.stcos.server.exception.ServiceException;
import com.stcos.server.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
public class AccountServiceImpTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Client testClient = null;

    private Operator testOperator = null;

    private OperatorDetailsDto operatorDetailsDto = null;

    @BeforeEach
    void setUp() throws ServiceException {
        operatorDetailsDto = new OperatorDetailsDto();
        operatorDetailsDto.setJobNumber("testJobNumber");
        operatorDetailsDto.setRealName("testRealName");
        operatorDetailsDto.setDepartment("testDepartment");
        operatorDetailsDto.setPosition("testPosition");
        operatorDetailsDto.setEmail("testEmail@test.com");
        operatorDetailsDto.setPhone("testPhone");
        operatorDetailsDto.setIsNonLocked(true);
        accountService.createOperator(operatorDetailsDto);
        testOperator = accountService.getOperator(operatorDetailsDto.getJobNumber());
        
        accountService.register("testUsername", passwordEncoder.encode("testPassword"), "testEmail@test.com");
        testClient = accountService.getClient("testUsername");
    }

    @Test
    void getAdmin() {
        // Test to retrieve the test admin we created
        assertDoesNotThrow(() -> accountService.getAdmin("admin"));

        // Test to get an admin with a username that does not exist
        ServiceException exception = assertThrows(ServiceException.class, () -> accountService.getAdmin("nonExistingAdmin"));
        assertEquals(0, exception.getCode());
    }

    @Test
    void getClient() throws ServiceException {
        // Test to retrieve the test client we created
        assertDoesNotThrow(() -> accountService.getClient(testClient.getUsername()));
        assertEquals(testClient.getUsername(), accountService.getClient(testClient.getUsername()).getUsername());

        // Test to get a client with a username that does not exist
        ServiceException exception = assertThrows(ServiceException.class, () -> accountService.getClient("nonExistingClient"));
        assertEquals(0, exception.getCode());
    }

    @Test
    void getOperator() throws ServiceException {
        // Test to retrieve the test operator we created
        assertDoesNotThrow(() -> accountService.getOperator(testOperator.getJobNumber()));
        assertEquals(testOperator.getJobNumber(), accountService.getOperator(testOperator.getJobNumber()).getJobNumber());

        // Test to get an operator with a job number that does not exist
        ServiceException exception = assertThrows(ServiceException.class, () -> accountService.getOperator("nonExistingJobNumber"));
        assertEquals(0, exception.getCode());
    }

    @Test
    void register() {
        assertDoesNotThrow(() -> accountService.register("anotherTestUsername", "anotherTestPassword", "anotherTestEmail@test.com"));

        ServiceException exception = assertThrows(ServiceException.class, () -> accountService.register("testUsername", "anotherTestPassword", "anotherTestEmail@test.com"));
        assertEquals(0, exception.getCode());

        exception = assertThrows(ServiceException.class, () -> accountService.register("anotherTestUsername", "anotherTestPassword", "testEmail@test.com"));
        assertEquals(0, exception.getCode());
    }

    @Test
    void updateClientDetails() throws ServiceException {
        // Create a new ClientDetailsDto and set new fields
        ClientDetailsDto newClientDetails = new ClientDetailsDto();
        newClientDetails.setRealName("newTestName");
        newClientDetails.setCompany("newTestCompany");

        // Update the client details
        accountService.updateClientDetails(testClient, newClientDetails);

        // Retrieve the client from the database again
        Client updatedClient = accountService.getClientById(testClient.getUid());

        // Check that the updated fields match what we set
        assertEquals("newTestName", updatedClient.getRealName());
        assertEquals("newTestCompany", updatedClient.getCompany());
    }

    @Test
    void getOperatorsByDepartment() {
        assertTrue(accountService.getOperatorsByDepartment("testDepartment").size() > 0);
        assertEquals(0, accountService.getOperatorsByDepartment("nonExistingDepartment").size());
    }

    @Test
    void updateOperatorDetails() throws ServiceException {
        // Create a new operator
        operatorDetailsDto = new OperatorDetailsDto();
        operatorDetailsDto.setRealName("newTestRealName");
        operatorDetailsDto.setDepartment("newTestDepartment");
        operatorDetailsDto.setPosition("newTestPosition");
        operatorDetailsDto.setEmail("newTestEmail@test.com");
        operatorDetailsDto.setPhone("newTestPhone");

        // Update the operator details
        accountService.updateOperatorDetails(testOperator, operatorDetailsDto);

        // Retrieve the operator from the database again
        Operator updatedOperator = accountService.getOperatorById(testOperator.getUid());

        // Check that the updated fields match what we set
        assertEquals("newTestRealName", updatedOperator.getRealName());
        assertEquals("newTestDepartment", updatedOperator.getDepartment());
        assertEquals("newTestPosition", updatedOperator.getPosition());
        assertEquals("newTestEmail@test.com", updatedOperator.getEmail());
        assertEquals("newTestPhone", updatedOperator.getPhone());
    }

    @Test
    void lockOperator() throws ServiceException {
        // Locking an operator normally
        assertDoesNotThrow(() -> accountService.lockOperator(testOperator.getUid(), true));
        assertFalse(accountService.getOperatorById(testOperator.getUid()).isAccountNonLocked());

        // Locking a non-existing operator
        ServiceException exception = assertThrows(ServiceException.class, () -> accountService.lockOperator("nonExistingUid", true));
        assertEquals(0, exception.getCode());
    }

    @Test
    void lockClient() throws ServiceException {
        // Locking a client normally
        assertDoesNotThrow(() -> accountService.lockClient(testClient.getUid(), true));
        assertFalse(accountService.getClientById(testClient.getUid()).isAccountNonLocked());

        // Locking a non-existing client
        ServiceException exception = assertThrows(ServiceException.class, () -> accountService.lockClient("nonExistingUid", true));
        assertEquals(0, exception.getCode());
    }

    @Test
    void getClientById() {
        assertDoesNotThrow(() -> accountService.getClientById(testClient.getUid()));

        ServiceException exception = assertThrows(ServiceException.class, () -> accountService.getClientById("nonExistingUid"));
        assertEquals(0, exception.getCode());
    }

    @Test
    void getOperatorById() {
        assertDoesNotThrow(() -> accountService.getOperatorById(testOperator.getUid()));

        ServiceException exception = assertThrows(ServiceException.class, () -> accountService.getOperatorById("nonExistingUid"));
        assertEquals(0, exception.getCode());
    }

    @Test
    void getOperators() {
        assertNotNull(accountService.getOperators());
    }

    @Test
    void getClients() {
        assertNotNull(accountService.getClients());
    }

    @Test
    void createOperator() {
        ServiceException exception = assertThrows(ServiceException.class, () -> accountService.createOperator(operatorDetailsDto));
        assertEquals(0, exception.getCode());

        operatorDetailsDto.setJobNumber("newJobNumber");
        assertDoesNotThrow(() -> accountService.createOperator(operatorDetailsDto));
    }

    @Test
    void deleteClient() {
        // Deleting a client normally
        assertDoesNotThrow(() -> accountService.deleteClient(testClient.getUid()));
        ServiceException exception = assertThrows(ServiceException.class, () -> accountService.getClientById(testClient.getUid()));
        assertEquals(0, exception.getCode());

        // Deleting a non-existing client
        exception = assertThrows(ServiceException.class, () -> accountService.deleteClient("nonExistingUid"));
        assertEquals(0, exception.getCode());
    }

    @Test
    void deleteOperator() {
        // Deleting an operator normally
        assertDoesNotThrow(() -> accountService.deleteOperator(testOperator.getUid()));
        ServiceException exception = assertThrows(ServiceException.class, () -> accountService.getOperatorById(testOperator.getUid()));
        assertEquals(0, exception.getCode());

        // Deleting a non-existing operator
        exception = assertThrows(ServiceException.class, () -> accountService.deleteOperator("nonExistingUid"));
        assertEquals(0, exception.getCode());
    }
}