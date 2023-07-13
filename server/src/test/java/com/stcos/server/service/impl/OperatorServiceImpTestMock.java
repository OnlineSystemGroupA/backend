package com.stcos.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stcos.server.database.mysql.OperatorMapper;
import com.stcos.server.entity.user.Client;
import com.stcos.server.entity.user.Operator;
import com.stcos.server.exception.ServerErrorException;
import com.stcos.server.service.OperatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OperatorServiceImpTestMock {

    @Mock
    private OperatorMapper operatorMapper;

    @InjectMocks
    private OperatorService operatorService = new OperatorServiceImp();

    private Operator operator = null;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        operator = new Operator();
        operator.setJobNumber("20xxx0100");
        operator.setPassword("testPassword");
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
        String uid = "testUid";

        operator.setUid(uid);

        when(operatorMapper.selectById(uid)).thenReturn(operator);

        Operator retrievedOperator = operatorService.getById(uid);
        assertEquals(uid, retrievedOperator.getUid());
        verify(operatorMapper, times(1)).selectById(uid);
    }

    @Test
    void getByJobNumber() {
        String jobNumber = "testJobNumber";

        operator.setJobNumber(jobNumber);

        when(operatorMapper.selectByJobNumber(jobNumber)).thenReturn(operator);

        Operator retrievedOperator = operatorService.getByJobNumber(jobNumber);
        assertEquals(jobNumber, retrievedOperator.getJobNumber());
        verify(operatorMapper, times(1)).selectByJobNumber(jobNumber);
    }

    @Test
    void getByDepartment() {
        String department = "testDepartment";
        List<Operator> operators = List.of(new Operator(), new Operator());

        when(operatorMapper.selectByDepartment(department)).thenReturn(operators);

        List<Operator> retrievedOperators = operatorService.getByDepartment(department);
        assertEquals(operators.size(), retrievedOperators.size());
        verify(operatorMapper, times(1)).selectByDepartment(department);
    }

    @Test
    void addProcessInstance() {
        String uid = "testUid";
        String processInstanceId = "testProcessInstanceId";


        when(operatorMapper.selectById(uid)).thenReturn(operator);
        when(operatorMapper.updateById(operator)).thenReturn(1);

        operatorService.addProcessInstance(uid, processInstanceId);

        assertTrue(operator.getProcessInstances().contains(processInstanceId));
        verify(operatorMapper, times(1)).selectById(uid);
        verify(operatorMapper, times(1)).updateById(operator);
    }

    @Test
    void existEmail() {
        String email = "testEmail@test.com";
        String uid = "testUid";

        QueryWrapper<Client> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        queryWrapper.ne("uid", uid);

        when(operatorMapper.exists(argThat(wrapper -> wrapper.getSqlSegment().equals(queryWrapper.getSqlSegment()))))
                .thenReturn(false);

        boolean exist = operatorService.existEmail(email, uid);
        assertFalse(exist);
        verify(operatorMapper, times(1)).exists(argThat(wrapper -> wrapper.getSqlSegment().equals(queryWrapper.getSqlSegment())));
    }

    @Test
    void existPhone() {
        String phone = "testPhone";
        String uid = "testUid";

        QueryWrapper<Client> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        queryWrapper.ne("uid", uid);

        when(operatorMapper.exists(argThat(wrapper -> wrapper.getSqlSegment().equals(queryWrapper.getSqlSegment()))))
                .thenReturn(false);

        boolean exist = operatorService.existPhone(phone, uid);
        assertFalse(exist);
        verify(operatorMapper, times(1)).exists(argThat(wrapper -> wrapper.getSqlSegment().equals(queryWrapper.getSqlSegment())));
    }

    @Test
    void getRealNameById() {
        String uid = "testUid";

        operator.setUid(uid);
        operator.setRealName("testRealName");

        when(operatorMapper.selectById(uid)).thenReturn(operator);

        String realName = operatorService.getRealNameById(uid);
        assertEquals("testRealName", realName);
        verify(operatorMapper, times(1)).selectById(uid);
    }

    @Test
    void getAll() {
        List<Operator> operators = List.of(new Operator(), new Operator());

        when(operatorMapper.selectList(null)).thenReturn(operators);

        List<Operator> retrievedOperators = operatorService.getAll();
        assertEquals(operators.size(), retrievedOperators.size());
        verify(operatorMapper, times(1)).selectList(null);
    }

    @Test
    void deleteOperator() {


        operatorService.deleteOperator(operator);

        verify(operatorMapper, times(1)).deleteById(operator);
    }

    @Test
    void createOperator() {


        when(operatorMapper.insert(operator)).thenReturn(1);

        operatorService.createOperator(operator);

        verify(operatorMapper, times(1)).insert(operator);
    }
}
