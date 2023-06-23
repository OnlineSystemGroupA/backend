package com.stcos.server.config.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * UserDetailsImp
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/8 20:51
 */

@Getter
@AllArgsConstructor
public class User implements org.springframework.security.core.userdetails.UserDetails {

    private String uid;

    private List<GrantedAuthority> authorities;

    private String password;

    private String username;

    private String email;

    boolean accountNonExpired;

    boolean accountNonLocked;

    boolean credentialsNonExpired;

    boolean enabled;

    public String getRealName() {
        return null;
    }
}
