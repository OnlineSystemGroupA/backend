package com.stcos.server.service.impl;

import com.stcos.server.config.security.UserDetailsFactory;
import com.stcos.server.mapper.AdminMapper;
import com.stcos.server.mapper.ClientMapper;
import com.stcos.server.mapper.OperatorMapper;
import com.stcos.server.entity.user.Admin;
import com.stcos.server.entity.user.Client;
import com.stcos.server.entity.user.Operator;
import com.stcos.server.service.AuthService;
import com.stcos.server.exception.ServiceException;
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
        if (userType == null) //未传入userType
            throw new ServiceException(3);
        switch (userType) {
            case "admin" -> {
                Admin admin = adminMapper.getByUsernameAdmin(username);
                if(admin == null) throw new ServiceException(0);
                userDetails = userDetailsFactory.getUserDetails(admin);
            }
            case "client" -> {
                Client client = clientMapper.getByUsernameClient(username);
                if(client == null) throw new ServiceException(0);
                userDetails = userDetailsFactory.getUserDetails(client);
            }
            case "operator" -> {
                Operator operator = operatorMapper.getByUsernameOperator(username);
                if(operator == null) throw new ServiceException(0);
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
