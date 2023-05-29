package com.stcos.server.entity.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.UUID;

/**
 * 平台管理员
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/3 12:43
 */
@Data
public class Admin {

    @TableId
    private String uid;

    @TableField(exist=false)
    private List<GrantedAuthority> authorities;

    private String password;

    private String username;

    boolean accountNonExpired;

    boolean accountNonLocked;

    boolean credentialsNonExpired;

    boolean enabled;


    public Admin(String username) {
        this.uid = "ad-" + UUID.randomUUID();
        this.username = username;
    }

}
