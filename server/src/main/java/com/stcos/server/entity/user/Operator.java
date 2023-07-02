package com.stcos.server.entity.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.stcos.server.util.ListTypeHandler;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

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

    @TableField(value = "process_instances", jdbcType = JdbcType.BLOB, typeHandler = ListTypeHandler.class)
    private List<String> processInstanceList = new ArrayList<>();

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


    @Override
    public Set<String> getProcessInstances() {
        return new HashSet<>(processInstanceList);
    }

    @Override
    public int getProcessesCount() {
        return processInstanceList.size();
    }
}
