package com.stcos.server.entity.user;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 平台管理员
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/3 12:43
 */
@Data
public class Admin implements User {

    @TableId
    private String uid;

    private String username;

    private String password;


    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String getRealName() {
        return null;
    }

    @Override
    public Set<String> getProcessInstances() {
        return null;
    }


    @Override
    public int getProcessesCount() {
        return 0;
    }

    @Override
    public boolean hasProcessInstance(String processId) {
        return false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
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
