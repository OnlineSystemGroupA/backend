package com.stcos.server.entity.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * 工作人员（测试方）
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/3 12:40
 */

@Data
public class Operator implements User {

    @TableId
    private String uid;

    private String password;

    private String jobNumber;

    private String realName;


    @TableField(exist = false)
    private String department;

    @TableField(exist = false)
    private String position;

    private String email;

    @TableField(exist = false)
    private String phone;

    @TableField(exist = false)
    private String avatarPath;

    boolean accountNonExpired;

    boolean accountNonLocked;

    boolean credentialsNonExpired;

    boolean enabled;


    public Operator(String uid) {
        this.uid = "op-" + UUID.randomUUID();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_OPERATOR"));
    }

    @Override
    public String getUsername() {
        return jobNumber;
    }
}
