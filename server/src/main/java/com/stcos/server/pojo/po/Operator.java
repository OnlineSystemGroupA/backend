package com.stcos.server.pojo.po;

import com.baomidou.mybatisplus.annotation.TableId;
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

    private List<GrantedAuthority> authorities;

    private String password;

    private String username;

    boolean accountNonExpired;

    boolean accountNonLocked;

    boolean credentialsNonExpired;

    boolean enabled;


    public Operator(String uid) {
        this.uid = "operator-" + UUID.randomUUID();
    }
}
