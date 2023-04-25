package com.stcos.server.pojo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/4/3 21:55
 */

@Data
@Entity
@NoArgsConstructor
public class TempUser implements UserDetails {

    @Id
    String uuid;

    String username;

    String password;

    public TempUser(String username, String password) {
        uuid = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
    }

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
}
