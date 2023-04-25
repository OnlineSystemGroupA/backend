package com.stcos.server.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User implements UserDetails {

    //primary key settings
    @TableId
    private String uid;

    private String username;

    private String password;

    private String email;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

    public User(String username, String password, String email) {
        this.uid=UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
        this.email = email;
    }

    //private Permission permissions;

}
