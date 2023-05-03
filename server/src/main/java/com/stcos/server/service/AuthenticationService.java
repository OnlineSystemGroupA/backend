package com.stcos.server.service;

import com.stcos.server.pojo.dto.TokenDto;

/**
 * 用户认证业务：
 * 注册、登录、登出。
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/4/3 21:01
 */
public interface AuthenticationService {

    /**
     * 注册一个新的客户账号
     * @param username 用户名
     * @param password 密码
     * @param email 邮箱
     * @throws ServiceException 各异常状态码含义如下 <br>
     * code: <br>
     *    0: 用户名已存在
     */
    void register(String username, String password, String email) throws ServiceException;


    TokenDto operatorLogin(String username, String password)  throws ServiceException;

    TokenDto customerLogin(String username, String password)  throws ServiceException;

    TokenDto adminLogin(String username, String password)  throws ServiceException;

    /**
     * 用户注销
     * @throws ServiceException 一般不抛出异常
     */
    void logout()  throws ServiceException;
}
