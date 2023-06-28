package com.stcos.server.service.impl;

import com.stcos.server.database.mysql.AdminMapper;
import com.stcos.server.database.mysql.ClientMapper;
import com.stcos.server.database.mysql.OperatorMapper;
import com.stcos.server.entity.user.Admin;
import com.stcos.server.entity.user.Client;
import com.stcos.server.entity.user.Operator;
import com.stcos.server.service.AccountService;
import com.stcos.server.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImp implements AccountService {

    private AdminMapper adminMapper;

    @Autowired
    public void setAdminMapper(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    private ClientMapper clientMapper;

    @Autowired
    public void setClientMapper(ClientMapper clientMapper) {
        this.clientMapper = clientMapper;
    }

    private OperatorMapper operatorMapper;

    @Autowired
    public void setOperatorMapper(OperatorMapper operatorMapper) {
        this.operatorMapper = operatorMapper;
    }

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Admin getAdmin(String username) throws ServiceException {
        Admin admin = adminMapper.getByUsernameAdmin(username);
        if (admin == null) {
            throw new ServiceException(0); // Admin not found
        }
        return admin;
    }

    @Override
    public Client getClient(String username) throws ServiceException {
        Client client = clientMapper.getByUsernameClient(username);
        if (client == null) {
            throw new ServiceException(0); // Client not found
        }
        return client;
    }

    @Override
    public Operator getOperator(String jobNumber) throws ServiceException {
        Operator operator = operatorMapper.selectByJobNumber(jobNumber);
        if (operator == null) {
            throw new ServiceException(0); // Operator not found
        }
        return operator;
    }

    @Override
    public void register(String username, String password, String email) throws ServiceException {
         Client client = clientMapper.getByUsernameClient(username);
         if (client != null)
             throw new ServiceException(0);
         client = new Client(username, passwordEncoder.encode(password), email);
        clientMapper.addNewUser(client);
    }

    @Override
    public String getTestingManagerId() {
        return null;
    }

    @Override
    public String getMarketingManagerId() {
        return null;
    }

    @Override
    public String getQualityManagerId() {
        return null;
    }
}
