package com.stcos.server.entity.user;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.stcos.server.util.ListTypeHandler;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * 客户（委托方）
 *
 * @author masterCheDan
 * @version 1.0
 * @since 2023/5/3 21:55
 */
@Data
@TableName(autoResultMap = true)
public class Client implements User {

    /**
     * primary key settings
     */
    @TableId
    private String uid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户的真名
     */
    private String realName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 账户是否可用
     */
    private boolean enabled = true;

    /**
     * 账户是否到期
     */
    private boolean accountNonExpired = true;

    /**
     * 账户是否锁定
     */
    private boolean accountNonLocked = true;

    /**
     * 凭据是否到期
     */
    private boolean credentialsNonExpired = true;

    @TableField(value = "processes_active", jdbcType = JdbcType.BLOB, typeHandler = ListTypeHandler.class)
    private List<String> processInstanceList = new ArrayList<>();

    public Client(String username, String password, String email) {
        this.uid = "cl-" + UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_CLIENT"));
    }

    @Override
    public void addProcessInstance(String processInstanceId) {
        processInstanceList.add(processInstanceId);
    }
}
