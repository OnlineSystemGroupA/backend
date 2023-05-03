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
     *
     * @param username 用户名
     * @param password 密码
     * @param email    邮箱
     * @throws ServiceException 各异常状态码含义如下 <br>
     *                          code: <br>
     *                          0: 用户名已存在
     */
    void register(String username, String password, String email) throws ServiceException;

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 包含用户名的 token
     * @throws ServiceException 各异常状态码含义如下 <br>
     *                          code: <br>
     *                          0: 用户不存在 <br>
     *                          1: 用户名或密码错误 <br>
     *                          2: 账号禁用 <br>
     */
    TokenDto login(String username, String password) throws ServiceException;

    /**
     * 用户注销
     *
     * @throws ServiceException 一般不抛出异常
     */
    void logout() throws ServiceException;
}
