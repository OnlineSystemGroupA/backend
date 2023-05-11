package com.stcos.server.pojo.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.UUID;

/**
 * 客户（委托方）
 *
 * @author masterCheDan
 * @version 1.0
 * @since 2023/5/3 21:55
 */
@Data
@NoArgsConstructor
@TableName("customer")
public class Client {

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
     * 邮箱
     */
    private String email;

    /**
     * 账户是否可用
     */
    private boolean enabled;

    /**
     * 账户是否到期
     */
    private boolean accountNonExpired;

    /**
     * 账户是否锁定
     */
    private boolean accountNonLocked;

    /**
     * 凭据是否到期
     */
    private boolean credentialsNonExpired;


    public Client(String username, String password, String email) {
        this.uid = "customer-" + UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = true;
    }

}
