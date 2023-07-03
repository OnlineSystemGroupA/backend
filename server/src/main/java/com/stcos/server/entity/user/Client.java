package com.stcos.server.entity.user;


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
     * 账号创建时间
     */
    private LocalDateTime createdDate;

    /**
     * 用户的真实姓名
     */
    private String realName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 性别
     */
    private String gender;

    /**
     * 公司名称
     */
    private String company;

    /**
     * 公司电话号
     */
    private String companyTelephone;

    /**
     * 公司传真
     */
    private String companyFax;

    /**
     * 公司地址
     */
    private String companyAddress;

    /**
     * 公司邮编
     */
    private String companyPostcode;

    /**
     * 公司网址
     */
    private String companyWebsite;

    /**
     * 公司邮箱
     */
    private String companyEmail;

    /**
     * 公司手机号码
     */
    private String companyPhone;

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

    @TableField(value = "processes_instance", jdbcType = JdbcType.BLOB, typeHandler = ListTypeHandler.class)
    private List<String> processInstanceList;

    @TableField(value = "processes_record", jdbcType = JdbcType.BLOB, typeHandler = ListTypeHandler.class)
    private List<String> processRecordList;

    public Client(String username, String password, String email) {
        this.uid = "cl-" + UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdDate = LocalDateTime.now();
        this.processInstanceList = new ArrayList<>();
        this.processRecordList = new ArrayList<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_CLIENT"));
    }

    @Override
    public void addProcessInstance(String processInstanceId) {
        processInstanceList.add(processInstanceId);
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
    public boolean hasProcessInstance(String processId) {
        return processInstanceList.contains(processId);
    }
}
