package com.stcos.server.service;

import com.stcos.server.model.dto.ClientDetailsDto;
import com.stcos.server.model.dto.OperatorDetailsDto;
import com.stcos.server.exception.ServiceException;
import com.stcos.server.model.user.Admin;
import com.stcos.server.model.user.Client;
import com.stcos.server.model.user.Operator;

import java.util.List;

/**
 * 这个服务接口提供了管理不同类型账户的方法
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

    /**
     * 获取当前测试部主管的 ID
     *
     * @return 测试部主管 ID
     */
    String getTestingManagerId();

    /**
     * 获取当前市场部主管的 ID
     *
     * @return 市场部主管 ID
     */
    String getMarketingManagerId();

    /**
     * 获取当前质量部主管的 ID
     *
     * @return 质量部主管 ID
     */
    String getQualityManagerId();

    /**
     * 更新客户详细信息
     *
     * @param client 客户对象
     * @param clientDetailsDto 客户详细信息传输对象
     * @throws ServiceException 服务异常
     */
    void updateClientDetails(Client client, ClientDetailsDto clientDetailsDto) throws ServiceException;

    /**
     * 根据部门获取运营人员列表
     *
     * @param department 部门名称
     * @return 运营人员列表
     */
    List<Operator> getOperatorsByDepartment(String department);

    /**
     * 更新运营人员详细信息
     *
     * @param operator 运营人员对象
     * @param operatorDetailsDto 运营人员详细信息传输对象
     * @throws ServiceException 服务异常
     */
    void updateOperatorDetails(Operator operator, OperatorDetailsDto operatorDetailsDto) throws ServiceException;

    /**
     * 锁定或解锁运营人员账户
     *
     * @param uid 运营人员的用户唯一标识
     * @param doLock 是否锁定，true为锁定，false为解锁
     * @throws ServiceException 服务异常
     */
    void lockOperator(String uid, Boolean doLock) throws ServiceException;


    /**
     * 锁定或解锁客户账户
     *
     * @param uid 客户的用户唯一标识
     * @param doLock 是否锁定，true为锁定，false为解锁
     * @throws ServiceException 服务异常
     */
    void lockClient(String uid, Boolean doLock) throws ServiceException;

    /**
     * 根据用户唯一标识获取客户对象
     *
     * @param uid 用户唯一标识
     * @throws ServiceException 服务异常
     * @return 客户对象
     */
    Client getClientById(String uid) throws ServiceException;

    /**
     * 根据用户唯一标识获取运营人员对象
     *
     * @param uid 用户唯一标识
     * @throws ServiceException 服务异常
     * @return 运营人员对象
     */
    Operator getOperatorById(String uid) throws ServiceException;

    /**
     * 获取所有运营人员列表
     *
     * @return 运营人员列表
     */
    List<Operator> getOperators();

    /**
     * 获取所有客户列表
     *
     * @return 客户列表
     */
    List<Client> getClients();

    /**
     * 创建新的运营人员账户
     *
     * @param operatorDetailsDto 运营人员详细信息传输对象
     * @throws ServiceException 服务异常
     */
    void createOperator(OperatorDetailsDto operatorDetailsDto) throws ServiceException;

    /**
     * 根据用户唯一标识删除客户账户
     *
     * @param uid 用户唯一标识
     * @throws ServiceException 服务异常
     */
    void deleteClient(String uid) throws ServiceException;

    /**
     * 根据用户唯一标识删除运营人员账户
     *
     * @param uid 用户唯一标识
     * @throws ServiceException 服务异常
     */
    void deleteOperator(String uid) throws ServiceException;
}
