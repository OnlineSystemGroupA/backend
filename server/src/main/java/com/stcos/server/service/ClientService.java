package com.stcos.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stcos.server.model.user.Client;

import java.util.List;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/6/25 13:29
 */
public interface ClientService extends IService<Client> {

    Client getById(String uid);

    boolean saveOrUpdate(Client client);

    void addProcessInstance(String clientUid, String processInstanceId);

    Client getByUsername(String username);

    void register(Client client);

    boolean existPhone(String phone, String uid);

    boolean existEmail(String email, String uid);

    List<Client> getAll();

    void deleteClient(Client client);
}
