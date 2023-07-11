package com.stcos.server.service.impl;

import com.stcos.server.database.mysql.AdminMapper;
import com.stcos.server.database.mysql.ClientMapper;
import com.stcos.server.database.mysql.OperatorMapper;
import com.stcos.server.exception.ServiceException;
import com.stcos.server.service.AuthService;
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
    public UserDetails login(String username, String password, String userType) throws ServiceException {
        UserDetails userDetails = null;
        if (userType == null) // 未传入 userType
            throw new ServiceException(3);
        switch (userType) {
            case "admin" -> userDetails = adminMapper.getByUsernameAdmin(username);
            case "client" -> userDetails = clientMapper.getByUsernameClient(username);
            case "operator" -> userDetails = operatorMapper.selectByJobNumber(username);
            default -> throw new ServiceException(3);
        }

        if (userDetails == null) throw new ServiceException(0);

        if (!passwordEncoder.matches(password, userDetails.getPassword())) // 用户名与密码不匹配
            throw new ServiceException(1);

        else if (!userDetails.isEnabled()) // 用户被禁用
            throw new ServiceException(2);

        return userDetails;
    }

    // 目前不需要实现
    @Override
    public void logout() throws ServiceException {
    }

}
