package com.stcos.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stcos.server.database.mysql.ClientMapper;
import com.stcos.server.model.user.Client;
import com.stcos.server.exception.ServerErrorException;
import com.stcos.server.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户信息管理服务实现类
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/6/25 13:30
 */

@Service
public class ClientServiceImp extends ServiceImpl<ClientMapper, Client> implements ClientService {

    @Override
    public Client getById(String uid) {
        return baseMapper.selectById(uid);
    }

    @Override
    public void addProcessInstance(String uid, String processInstanceId) {
        Client client = getById(uid);
        client.addProcessInstance(processInstanceId);
        if (!updateById(client)) throw new ServerErrorException(new RuntimeException());
    }

    @Override
    public Client getByUsername(String username) {
        return this.baseMapper.getByUsernameClient(username);
    }

    @Override
    public void register(Client client) {
        this.baseMapper.addNewUser(client);
    }

    @Override
    public boolean existPhone(String phone, String uid) {
        QueryWrapper<Client> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        queryWrapper.ne("uid", uid);
        return baseMapper.exists(queryWrapper);
    }

    @Override
    public boolean existEmail(String email, String uid) {
        QueryWrapper<Client> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        queryWrapper.ne("uid", uid);
        return baseMapper.exists(queryWrapper);
    }

    @Override
    public List<Client> getAll() {
        return baseMapper.selectList(null);
    }

    @Override
    public void deleteClient(Client client) {
        baseMapper.deleteById(client);
    }
}
