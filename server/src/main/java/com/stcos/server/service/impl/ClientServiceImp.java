package com.stcos.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stcos.server.database.mysql.ClientMapper;
import com.stcos.server.entity.user.Client;
import com.stcos.server.exception.ServerErrorException;
import com.stcos.server.service.ClientService;
import org.springframework.stereotype.Service;

/**
 * description
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
}
