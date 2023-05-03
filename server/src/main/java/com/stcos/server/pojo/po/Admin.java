package com.stcos.server.pojo.po;

import com.baomidou.mybatisplus.annotation.TableId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

/**
 * 平台管理员
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/3 12:43
 */
public class Admin implements UserDetails {

    @TableId
    private String uid;

    private String username;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public Admin(String uid) {
        this.uid = "admin-" + UUID.randomUUID();
    }

}
