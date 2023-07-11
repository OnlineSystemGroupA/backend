package com.stcos.server.service.impl;

import com.stcos.server.entity.user.Operator;
import com.stcos.server.service.OperatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
public class OperatorServiceImpTest {

    @Autowired
    private OperatorService operatorService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Operator testOperator = null;

    @BeforeEach
    void setUp() {
        testOperator = new Operator();
        testOperator.setJobNumber("20xxx0100");
        testOperator.setPassword(passwordEncoder.encode("testPassword"));
        testOperator.setCreatedDate(LocalDateTime.now());
        testOperator.setEmail("testEmail@test.com");
        testOperator.setPhone("1234567890");
        testOperator.setRealName("testRealName");
        testOperator.setDepartment("testDepartment");
        testOperator.setPosition("testPosition");
        testOperator.setAccountNonExpired(true);
        testOperator.setAccountNonLocked(true);
        testOperator.setCredentialsNonExpired(true);
        testOperator.setEnabled(true);
    }

    @Test
    void getById() {
        operatorService.createOperator(testOperator);

        Operator retrievedOperator = operatorService.getById(testOperator);
        assertEquals("20xxx0100", retrievedOperator.getJobNumber());

        retrievedOperator = operatorService.getById("op-2");
        assertEquals("20xxx0001", retrievedOperator.getJobNumber());
    }

    @Test
    void getByJobNumber() {
        operatorService.createOperator(testOperator);
        Operator retrievedOperator = operatorService.getByJobNumber("20xxx0100");
        assertEquals("testDepartment", retrievedOperator.getDepartment());
    }

    @Test
    void getByDepartment() {
        operatorService.createOperator(testOperator);
        List<Operator> operators = operatorService.getByDepartment("testDepartment");
        assertTrue(operators.size() > 0);
        assertEquals("20xxx0100", operators.get(0).getJobNumber());
    }

    @Test
    void addProcessInstance() {
        operatorService.createOperator(testOperator);
        operatorService.addProcessInstance(testOperator.getUid(), "testProcessInstance");
        Operator updatedOperator = operatorService.getById(testOperator.getUid());
        assertTrue(updatedOperator.getProcessInstances().contains("testProcessInstance"));
    }

    @Test
    void existEmail() {
        operatorService.createOperator(testOperator);
        assertFalse(operatorService.existEmail("testEmail@test.com", testOperator.getUid()));
    }

    @Test
    void existPhone() {
        operatorService.createOperator(testOperator);
        assertFalse(operatorService.existPhone("1234567890", testOperator.getUid()));
    }

    @Test
    void getRealNameById() {
        operatorService.createOperator(testOperator);
        assertEquals("testRealName", operatorService.getRealNameById(testOperator.getUid()));
    }

    @Test
    void getAll() {
        operatorService.createOperator(testOperator);
        List<Operator> operators = operatorService.getAll();
        assertTrue(operators.size() > 0);
    }

    @Test
    void deleteOperator() {
        operatorService.createOperator(testOperator);
        assertNotNull(operatorService.getById(testOperator));
        operatorService.deleteOperator(testOperator);
        assertNull(operatorService.getById(testOperator));
    }

    @Test
    void createOperator() {
        operatorService.createOperator(testOperator);
        assertNotNull(operatorService.getById(testOperator));
    }
}