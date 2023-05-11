package com.stcos.server.service;

import com.stcos.server.pojo.po.Admin;
import com.stcos.server.pojo.po.Client;
import com.stcos.server.pojo.po.Operator;
import org.springframework.stereotype.Service;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/11 20:56
 */
@Service
public interface AccountService {

    Admin getAdmin(String username) throws ServiceException;

    Client getClient(String username) throws ServiceException;

    Operator getOperate(String username) throws ServiceException;

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

}
