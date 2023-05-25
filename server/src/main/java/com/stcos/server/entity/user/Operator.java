package com.stcos.server.entity.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.UUID;

/**
 * 工作人员（测试方）
 *
 * @author
 * @version 1.0
 * @since 2023/5/3 12:40
 */

@Data
public class Operator {

    @TableId
    private String uid;

    @TableField(exist=false)
    private List<GrantedAuthority> authorities;

    private String password;

    private String username;


    @TableField(exist=false)
    private String realName;


    @TableField(exist=false)
    private String department;

    @TableField(exist=false)
    private String position;

    private String email;

    @TableField(exist=false)
    private String phone;

    @TableField(exist=false)
    private String avatarPath;

    boolean accountNonExpired;

    boolean accountNonLocked;

    boolean credentialsNonExpired;

    boolean enabled;


    public Operator(String uid) {
        this.uid = "op-" + UUID.randomUUID();
    }


}
