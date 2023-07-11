package com.stcos.server.listener;

import com.stcos.server.entity.user.Client;
import com.stcos.server.service.ClientService;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/7/10 17:40
 */
public class ClientTaskListener extends TaskListener<ClientService, Client> {

    public ClientTaskListener(String taskName) {
        super(taskName);
    }
}
