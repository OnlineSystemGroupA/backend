package com.stcos.server.config.security;

import com.stcos.server.pojo.po.Admin;
import com.stcos.server.pojo.po.Client;
import com.stcos.server.pojo.po.Operator;
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

    public UserDetailsImp getUserDetails(Client client) {
        return new UserDetailsImp(client.getUid(),
                new ArrayList<>(){{
                    add(new SimpleGrantedAuthority("ROLE_CLIENT"));
                }},
                client.getPassword(),
                client.getUsername(),
                client.isAccountNonExpired(),
                client.isAccountNonLocked(),
                client.isCredentialsNonExpired(),
                client.isEnabled());
    }

    public UserDetailsImp getUserDetails(Admin admin) {
        return new UserDetailsImp(admin.getUid(),
                admin.getAuthorities(),
                admin.getPassword(),
                admin.getUsername(),
                admin.isAccountNonExpired(),
                admin.isAccountNonLocked(),
                admin.isCredentialsNonExpired(),
                admin.isEnabled());

    }

    public UserDetailsImp getUserDetails(Operator operator) {
        return new UserDetailsImp(operator.getUid(),
                operator.getAuthorities(),
                operator.getPassword(),
                operator.getUsername(),
                operator.isAccountNonExpired(),
                operator.isAccountNonLocked(),
                operator.isCredentialsNonExpired(),
                operator.isEnabled());
    }

}
