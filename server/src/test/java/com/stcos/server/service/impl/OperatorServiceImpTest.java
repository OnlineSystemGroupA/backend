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

    Operator operator = null;

    @BeforeEach
    void setUp() {
        operator = new Operator();
        operator.setJobNumber("20xxx0100");
        operator.setPassword(passwordEncoder.encode("testPassword"));
        operator.setCreatedDate(LocalDateTime.now());
        operator.setEmail("testEmail@test.com");
        operator.setPhone("1234567890");
        operator.setRealName("testRealName");
        operator.setDepartment("testDepartment");
        operator.setPosition("testPosition");
        operator.setAccountNonExpired(true);
        operator.setAccountNonLocked(true);
        operator.setCredentialsNonExpired(true);
        operator.setEnabled(true);
    }

    @Test
    void getById() {
        operatorService.createOperator(operator);

        Operator retrievedOperator = operatorService.getById(operator);
        assertEquals("20xxx0100", retrievedOperator.getJobNumber());

        retrievedOperator = operatorService.getById("op-2");
        assertEquals("20xxx0001", retrievedOperator.getJobNumber());
    }

    @Test
    void getByJobNumber() {
        operatorService.createOperator(operator);
        Operator retrievedOperator = operatorService.getByJobNumber("20xxx0100");
        assertEquals("testDepartment", retrievedOperator.getDepartment());
    }

    @Test
    void getByDepartment() {
        operatorService.createOperator(operator);
        List<Operator> operators = operatorService.getByDepartment("testDepartment");
        assertTrue(operators.size() > 0);
        assertEquals("20xxx0100", operators.get(0).getJobNumber());
    }

    @Test
    void addProcessInstance() {
        operatorService.createOperator(operator);
        operatorService.addProcessInstance(operator.getUid(), "testProcessInstance");
        Operator updatedOperator = operatorService.getById(operator.getUid());
        assertTrue(updatedOperator.getProcessInstances().contains("testProcessInstance"));
    }

    @Test
    void existEmail() {
        operatorService.createOperator(operator);
        assertFalse(operatorService.existEmail("testEmail@test.com", operator.getUid()));
    }

    @Test
    void existPhone() {
        operatorService.createOperator(operator);
        assertFalse(operatorService.existPhone("1234567890", operator.getUid()));
    }

    @Test
    void getRealNameById() {
        operatorService.createOperator(operator);
        assertEquals("testRealName", operatorService.getRealNameById(operator.getUid()));
    }

    @Test
    void getAll() {
        operatorService.createOperator(operator);
        List<Operator> operators = operatorService.getAll();
        assertTrue(operators.size() > 0);
    }

    @Test
    void deleteOperator() {
        operatorService.createOperator(operator);
        assertNotNull(operatorService.getById(operator));
        operatorService.deleteOperator(operator);
        assertNull(operatorService.getById(operator));
    }

    @Test
    void createOperator() {
        operatorService.createOperator(operator);
        assertNotNull(operatorService.getById(operator));
    }
}