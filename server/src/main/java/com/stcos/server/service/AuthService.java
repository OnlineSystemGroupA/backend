package com.stcos.server.service;

import com.stcos.server.exception.ServiceException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用户认证业务：注册、登录、登出
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/4/3 21:01
 */
public interface AuthService {

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     * @throws ServiceException 各异常状态码含义如下 <br>
     *                          code: <br>
     *                          0: 用户不存在 <br>
     *                          1: 用户名或密码错误 <br>
     *                          2: 账号禁用 <br>
     *                          3: userType错误 <br>
     */
    UserDetails login(String username, String password, String userType) throws ServiceException;

    /**
     * 用户注销
     *
     * @throws ServiceException 一般不抛出异常
     */
    void logout() throws ServiceException;
}
