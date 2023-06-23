package com.stcos.server.service;

import com.stcos.server.entity.user.Operator;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/6/23 20:27
 */
public interface OperatorService {

    Operator getById(String uid);

}
