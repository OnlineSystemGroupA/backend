package com.stcos.server.model.user;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Set;

/**
 * User
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/8 20:51
 */

public interface User extends UserDetails {

    /**
     * 获取用户邮箱
     */
    String getEmail();

    /**
     * 获取用户姓名
     */
    String getRealName();

    /**
     * 获取用户 ID
     */
    String getUid();

    default void addProcessInstance(String processInstanceId) {}

    default void addProcessRecord(Long processInstanceId) {}

    Set<String> getProcessInstances();

    int getProcessesCount();

    default int getProcessRecordCount(){return 0;};

    boolean hasProcessInstance(String processId);

    default boolean hasProcessRecord(String processId){return false;};

    default List<Long> getProcessRecords(){return null;};
}
