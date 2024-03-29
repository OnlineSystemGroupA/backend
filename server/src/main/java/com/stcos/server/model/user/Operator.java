package com.stcos.server.model.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.stcos.server.database.mysql.handler.ListTypeHandler;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 工作人员（测试方）
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/3 12:40
 */

@Data
@TableName(autoResultMap = true)
public class Operator implements User {

    @TableId
    private String uid;

    private String password;

    private String jobNumber;

    private LocalDateTime createdDate;

    private String realName;

    private String department;

    private String position;

    private String email;

    private String phone;

    private boolean isManager = false;

    @TableField(exist = false)
    private String avatarPath;

    boolean accountNonExpired;

    boolean accountNonLocked;

    boolean credentialsNonExpired;

    boolean enabled;

    @TableField(value = "process_instance", jdbcType = JdbcType.BLOB, typeHandler = ListTypeHandler.class)
    private List<String> processInstanceList = new ArrayList<>();

    @TableField(value = "process_record", jdbcType = JdbcType.BLOB, typeHandler = ListTypeHandler.class)
    private List<String> processRecordList = new ArrayList<>();

    public Operator() {
        this.uid = "op-" + UUID.randomUUID();
        this.createdDate = LocalDateTime.now();
        this.processInstanceList = new ArrayList<>();
        this.processRecordList = new ArrayList<>();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>(List.of(new SimpleGrantedAuthority("ROLE_OPERATOR")));
        if (isManager) authorities.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
        return authorities;
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

    @Override
    public void addProcessInstance(String processInstanceId) {
        processInstanceList.add(processInstanceId);
        processInstanceList = new HashSet<>(processInstanceList).stream().toList();
    }

    @Override
    public boolean hasProcessInstance(String processId) {
        return processInstanceList.contains(processId);
    }
}
