package com.stcos.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stcos.server.entity.user.Operator;

import java.util.List;

/**
 * 这个服务接口提供了管理 'Operator' 实体的方法
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/6/23 20:27
 */
public interface OperatorService extends IService<Operator> {

    /**
     * 通过给定的 UID 获取 Operator
     *
     * @param uid 需要获取的 Operator 的 UID
     * @return 给定 UID 的 Operator
     */
    Operator getById(String uid);

    /**
     * 通过给定的工号获取 Operator
     *
     * @param jobNumber 需要获取的 Operator 的工号
     * @return 给定工号的 Operator
     */
    Operator getByJobNumber(String jobNumber);

    /**
     * 通过给定的部门获取 Operator 列表
     *
     * @param department 需要获取的 Operator 的部门名称
     * @return 所在给定部门的 Operator 列表
     */
    List<Operator> getByDepartment(String department);

    /**
     * 给指定的 Operator 添加一个流程实例
     *
     * @param operatorUid 需要添加流程实例的 Operator 的 UID
     * @param processInstanceId 需要添加的流程实例的 id
     */
    void addProcessInstance(String operatorUid, String processInstanceId);

    /**
     * 检查除指定 UID 的 Operator 外，是否有 Operator 使用了指定的 Email
     *
     * @param email 需要检查的 Email
     * @param uid 需要排除的 Operator 的 UID
     * @return 如果存在其他 Operator 使用了指定 Email，返回 true，否则返回 false
     */
    boolean existEmail(String email, String uid);

    /**
     * 检查除指定 UID 的 Operator 外，是否有 Operator 使用了指定的电话号码
     *
     * @param phone 需要检查的电话号码
     * @param uid 需要排除的 Operator 的 UID
     * @return 如果存在其他 Operator 使用了指定电话号码，返回 true，否则返回 false
     */
    boolean existPhone(String phone, String uid);

    /**
     * 通过给定的UID获取Operator的真实姓名
     *
     * @param uid 需要获取真实姓名的 Operator 的 UID
     * @return 给定 UID 的 Operator 的真实姓名
     */
    String getRealNameById(String uid);

    /**
     * 获取所有的 Operator
     *
     * @return 所有的 Operator 列表
     */
    List<Operator> getAll();

    /**
     * 删除指定的 Operator
     *
     * @param operator 需要删除的 Operator
     */
    void deleteOperator(Operator operator);

    /**
     * 创建一个新的 Operator
     *
     * @param operator 需要创建的 Operator
     */
    void createOperator(Operator operator);
}
