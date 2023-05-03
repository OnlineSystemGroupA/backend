package com.stcos.server.service.impl;

import com.stcos.server.pojo.dto.TokenDto;
import com.stcos.server.service.AuthenticationService;
import com.stcos.server.service.ServiceException;
import org.springframework.stereotype.Service;

/**
 * description
 *
 * @author Mikeal-C
 * @version 1.0
 * @since 2023/4/3 21:01
 */

@Service
public class AuthenticationServiceImp implements AuthenticationService {


    @Override
    public void register(String username, String password, String email) throws ServiceException {

    }

    @Override
    public TokenDto login(String username, String password) throws ServiceException {
        return null;
    }

    // 目前不需要实现
    @Override
    public void logout() throws ServiceException {
    }

}
