package com.stcos.server.service.impl;

import com.stcos.server.mapper.ClientMapper;
import com.stcos.server.pojo.po.Admin;
import com.stcos.server.pojo.po.Client;
import com.stcos.server.pojo.po.Operator;
import com.stcos.server.service.AccountService;
import com.stcos.server.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AccountServiceImp implements AccountService {


    private UserDetailsService userDetailsService;

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    private ClientMapper clientMapper;
    @Autowired
    public void setClientMapper(ClientMapper clientMapper) {
        this.clientMapper = clientMapper;
    }

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Admin getAdmin(String username) throws ServiceException {
        return null;
    }

    @Override
    public Client getClient(String username) throws ServiceException {
        return null;
    }

    @Override
    public Operator getOperate(String username) throws ServiceException {
        return null;
    }

    @Override
    public void register(String username, String password, String email) throws ServiceException {
        try {
            userDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) { //不存在用户名
            Client client = new Client(username, passwordEncoder.encode(password), email);
            clientMapper.addNewUser(client);
            return ;
        }

        throw new ServiceException(0);
    }
}
