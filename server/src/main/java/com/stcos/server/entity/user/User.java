package com.stcos.server.entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * UserDetailsImp
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

}
