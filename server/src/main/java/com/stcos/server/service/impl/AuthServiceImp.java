package com.stcos.server.service.impl;

import com.stcos.server.config.security.UserDetailsFactory;
import com.stcos.server.mapper.AdminMapper;
import com.stcos.server.mapper.ClientMapper;
import com.stcos.server.mapper.OperatorMapper;
import com.stcos.server.pojo.po.Admin;
import com.stcos.server.pojo.po.Client;
import com.stcos.server.pojo.po.Operator;
import com.stcos.server.service.AuthService;
import com.stcos.server.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * description
 *
 * @author Mikeal-C
 * @version 1.0
 * @since 2023/4/3 21:01
 */

@Service
public class AuthServiceImp implements AuthService {

    private UserDetailsFactory userDetailsFactory;
    @Autowired
    public void setUserDetailsFactory(UserDetailsFactory userDetailsFactory) {
        this.userDetailsFactory = userDetailsFactory;
    }

    private AdminMapper adminMapper;

    @Autowired
    public void setAdminMapper(AdminMapper adminMapper) {this.adminMapper = adminMapper;}

    private ClientMapper clientMapper;

    @Autowired
    public void setClientMapper(ClientMapper clientMapper) {this.clientMapper = clientMapper;}

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
    public UserDetails login(String username, String password, String userType) throws ServiceException {
        UserDetails userDetails;
        switch (userType) {
            case "Admin" -> {
                Admin admin = adminMapper.getByUsernameAdmin(username);
                userDetails = userDetailsFactory.getUserDetails(admin);
            }
            case "Client" -> {
                Client client = clientMapper.getByUsernameClient(username);
                userDetails = userDetailsFactory.getUserDetails(client);
            }
            case "Operator" -> {
                Operator operator = operatorMapper.getByUsernameOperator(username);
                userDetails = userDetailsFactory.getUserDetails(operator);
            }
            default -> throw new ServiceException(3);
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())) //用户名与密码不匹配
            throw new ServiceException(1);

        else if (!userDetails.isEnabled()) //用户被禁用
            throw new ServiceException(2);

        return userDetails;
    }

    // 目前不需要实现
    @Override
    public void logout() throws ServiceException {
    }

}
