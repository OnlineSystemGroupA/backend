package com.stcos.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stcos.server.entity.user.Client;

import java.util.List;

/**
 * 这个服务接口提供了管理 'Client' 实体的方法
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/6/25 13:29
 */
public interface ClientService extends IService<Client> {

    /**
     * 根据用户 id 获取客户信息
     *
     * @param uid 用户 id
     * @return 对应的客户信息
     */
    Client getById(String uid);

    /**
     * 保存或更新客户信息
     *
     * @param client 需要保存或更新的客户信息
     * @return 保存或更新成功返回 true，否则返回 false
     */
    boolean saveOrUpdate(Client client);

    /**
     * 向客户中添加流程实例 id
     *
     * @param clientUid 客户的用户 id
     * @param processInstanceId 需要添加的流程实例 id
     */
    void addProcessInstance(String clientUid, String processInstanceId);

    /**
     * 根据用户名获取客户信息
     *
     * @param username 用户名
     * @return 对应的客户信息
     */
    Client getByUsername(String username);

    /**
     * 注册新客户
     *
     * @param client 需要注册的客户信息
     */
    void register(Client client);

    /**
     * 判断手机号是否已经存在
     *
     * @param phone 手机号
     * @param uid 用户 id，该用户的手机号不在此考虑范围内
     * @return 手机号存在返回 true，否则返回 false
     */
    boolean existPhone(String phone, String uid);

    /**
     * 判断邮箱是否已经存在
     *
     * @param email 邮箱
     * @param uid 用户 id，该用户的邮箱不在此考虑范围内
     * @return 邮箱存在返回 true，否则返回 false
     */
    boolean existEmail(String email, String uid);

    /**
     * 获取所有客户的信息
     *
     * @return 包含所有客户信息的列表
     */
    List<Client> getAll();

    /**
     * 删除指定的客户
     *
     * @param client 需要删除的客户信息
     */
    void deleteClient(Client client);
}
