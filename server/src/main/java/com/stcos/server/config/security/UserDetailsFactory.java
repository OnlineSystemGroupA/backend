package com.stcos.server.config.security;

import com.stcos.server.entity.user.Admin;
import com.stcos.server.entity.user.Client;
import com.stcos.server.entity.user.Operator;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/11 21:14
 */
@Component
public class UserDetailsFactory {

    public User getUserDetails(Client client) {
        return new User(client.getUid(),
                new ArrayList<>(){{
                    add(new SimpleGrantedAuthority("ROLE_CLIENT"));
                }},
                client.getPassword(),
                client.getUsername(),
                client.getEmail(),
                client.isAccountNonExpired(),
                client.isAccountNonLocked(),
                client.isCredentialsNonExpired(),
                client.isEnabled());
    }

    public User getUserDetails(Admin admin) {
        return new User(admin.getUid(),
                admin.getAuthorities(),
                admin.getPassword(),
                admin.getUsername(),
                "",
                admin.isAccountNonExpired(),
                admin.isAccountNonLocked(),
                admin.isCredentialsNonExpired(),
                admin.isEnabled());

    }

    public User getUserDetails(Operator operator) {
        return new User(operator.getUid(),
                operator.getAuthorities(),
                operator.getPassword(),
                operator.getUsername(),
                operator.getEmail(),
                operator.isAccountNonExpired(),
                operator.isAccountNonLocked(),
                operator.isCredentialsNonExpired(),
                operator.isEnabled());
    }

}
