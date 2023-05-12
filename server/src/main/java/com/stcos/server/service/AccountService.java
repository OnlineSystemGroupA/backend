package com.stcos.server.service;

import com.stcos.server.pojo.po.Admin;
import com.stcos.server.pojo.po.Client;
import com.stcos.server.pojo.po.Operator;

/**
 * description
 *
 * @author
 * @version 1.0
 * @since 2023/5/11 20:56
 */
public interface AccountService {

    Admin getAdmin(String username) throws ServiceException;

    Client getClient(String username) throws ServiceException;

    Operator getOperator(String username) throws ServiceException;

}
