package com.stcos.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stcos.server.database.mysql.OperatorMapper;
import com.stcos.server.entity.user.Client;
import com.stcos.server.entity.user.Operator;
import com.stcos.server.exception.ServerErrorException;
import com.stcos.server.service.OperatorService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/6/23 20:30
 */

@Service
public class OperatorServiceImp extends ServiceImpl<OperatorMapper, Operator> implements OperatorService {

    @Override
    public Operator getById(String uid) {
        return super.getById(uid);
    }

    @Override
    public Operator getByJobNumber(String jobNumber) {
        return baseMapper.selectByJobNumber(jobNumber);
    }

    @Override
    public List<Operator> getByDepartment(String department) {
        return baseMapper.selectByDepartment(department);
    }

    @Override
    public void addProcessInstance(String uid, String processInstanceId) {
        Operator operator = getById(uid);
        operator.addProcessInstance(processInstanceId);
        if (!updateById(operator)) throw new ServerErrorException(new RuntimeException());
    }

    @Override
    public boolean existEmail(String email, String uid) {
        QueryWrapper<Operator> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        queryWrapper.ne("uid", uid);
        return baseMapper.exists(queryWrapper);
    }

    @Override
    public boolean existPhone(String phone, String uid) {
        QueryWrapper<Operator> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        queryWrapper.ne("uid", uid);
        return baseMapper.exists(queryWrapper);
    }

}
