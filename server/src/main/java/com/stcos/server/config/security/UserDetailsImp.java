package com.stcos.server.config.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
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

@Getter
@AllArgsConstructor
public class UserDetailsImp implements UserDetails {

    private String uid;

    private List<GrantedAuthority> authorities;

    private String password;

    private String username;

    boolean accountNonExpired;

    boolean accountNonLocked;

    boolean credentialsNonExpired;

    boolean enabled;

}
