package com.stcos.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stcos.server.entity.user.Operator;

import java.util.List;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/6/23 20:27
 */
public interface OperatorService extends IService<Operator> {

    Operator getById(String uid);

    Operator getByJobNumber(String jobNumber);

    List<Operator> getByDepartment(String department);

    void addProcessInstance(String operatorUid, String processInstanceId);

    boolean existEmail(String email, String uid);

    boolean existPhone(String phone, String uid);
}
