package com.stcos.server.pojo.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
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
@TableName("customer")
public class Client implements UserDetails {

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
     * 邮箱
     */
    private String email;

    /**
     * 账户是否可用
     */
    private boolean enabled;

    /**
     * 账户是否到期
     */
    private boolean accountNonExpired;

    /**
     * 账户是否锁定
     */
    private boolean accountNonLocked;

    /**
     * 凭据是否到期
     */
    private boolean credentialsNonExpired;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>() {{
            // 客户一般不具有特殊权限，且只拥有有一个身份，故此处采用硬编码方式
            add(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
        }};
    }

    public Client(String username, String password, String email) {
        this.uid = "customer-" + UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = true;
    }

}
