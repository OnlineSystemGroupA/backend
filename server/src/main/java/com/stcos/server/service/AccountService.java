package com.stcos.server.service;

import com.stcos.server.exception.ServiceException;
import com.stcos.server.entity.user.Admin;
import com.stcos.server.entity.user.Client;
import com.stcos.server.entity.user.Operator;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/11 20:56
 */
public interface AccountService {
    /**
     * 根据用户名获取 Admin 对象
     *
     * @param username 用户名
     * @throws ServiceException 各异常状态码含义如下 <br>
     *                          code: <br>
     *                          0: 用户名对应的 Admin 对象不存在
     *
     */
    Admin getAdmin(String username) throws ServiceException;

    /**
     * 根据用户名获取 Client 对象
     *
     * @param username 用户名
     * @throws ServiceException 各异常状态码含义如下 <br>
     *                          code: <br>
     *                          0: 用户名对应的 Client 对象不存在
     */
    Client getClient(String username) throws ServiceException;

    /**
     * 根据用户名获取 Operator 对象
     *
     * @param username 用户名
     * @throws ServiceException 各异常状态码含义如下 <br>
     *                          code: <br>
     *                          0: 用户名对应的 Operator 对象不存在
     */
    Operator getOperator(String username) throws ServiceException;

    /**
     * 注册一个新的客户账号
     *
     * @param username 用户名
     * @param password 密码
     * @param email    邮箱
     * @throws ServiceException 各异常状态码含义如下 <br>
     *                          code: <br>
     *                          0: 用户名已存在 <br>
     */
    void register(String username, String password, String email) throws ServiceException;

}
