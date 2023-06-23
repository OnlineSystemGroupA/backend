package com.stcos.server.entity.user;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * 客户（委托方）
 *
 * @author masterCheDan
 * @version 1.0
 * @since 2023/5/3 21:55
 */
@Data
@NoArgsConstructor
public class Client implements User {

    /**
     * primary key settings
     */
    @TableId
    private String uid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户的真名
     */
    private String realName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 账户是否可用
     */
    private boolean enabled = true;

    /**
     * 账户是否到期
     */
    private boolean accountNonExpired = true;

    /**
     * 账户是否锁定
     */
    private boolean accountNonLocked = true;

    /**
     * 凭据是否到期
     */
    private boolean credentialsNonExpired = true;


    public Client(String username, String password, String email) {
        this.uid = "cl-" + UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_CLIENT"));
    }

}
