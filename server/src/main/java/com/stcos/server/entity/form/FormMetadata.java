package com.stcos.server.entity.form;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.stcos.server.database.mysql.handler.ListTypeHandler;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
                ______                     __  ___     __            __      __
               / ____/___  _________ ___  /  |/  /__  / /_____ _____/ /___ _/ /_____ _
              / /_  / __ \/ ___/ __ `__ \/ /|_/ / _ \/ __/ __ `/ __  / __ `/ __/ __ `/
             / __/ / /_/ / /  / / / / / / /  / /  __/ /_/ /_/ / /_/ / /_/ / /_/ /_/ /
            /_/    \____/_/  /_/ /_/ /_/_/  /_/\___/\__/\__,_/\__,_/\__,_/\__/\__,_/

 */

/**
 * 表单的索引类
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/17 9:51
 */
@Data
@TableName(autoResultMap = true)
public class FormMetadata {

    /**
     * 表单元数据 ID，保存对象时由数据库自动赋值
     */
    @TableId(type = IdType.AUTO)
    private Long formMetadataId;

    /**
     * 表单元数据对应表单的 ID
     */
    private long formId = -1;

    /**
     * 表单类型
     */
    private String formType;

    /**
     * 表单的创建者 (userId)
     */
    private String createdBy = null;

    /**
     * 表单创建时间
     */
    private LocalDateTime createdDate = null;

    /**
     * 表单最后一次被谁修改 (userId)
     */
    private String lastModifiedBy = null;

    /**
     * 表单最后一次被修改的时间
     */
    private LocalDateTime lastModifiedDate = null;

    /**
     * 对表单具有读权限用户的 ID 列表
     */
    @TableField(value = "readable_users", jdbcType = JdbcType.BLOB, typeHandler = ListTypeHandler.class)
    private List<String> readableUsers;

    /**
     * 对表单具有写权限用户的 ID 列表
     */
    @TableField(value = "writable_users", jdbcType = JdbcType.BLOB, typeHandler = ListTypeHandler.class)
    private List<String> writableUsers;

    public FormMetadata(String formType) {
        this.formType = formType;
        readableUsers = new ArrayList<>();
        writableUsers = new ArrayList<>();
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        this.lastModifiedBy = createdBy;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
        this.lastModifiedDate = createdDate;
    }

    /**
     * 判断目标用户是否具有该表单的读权限
     *
     * @param userId 目标用户 ID
     */
    public boolean hasReadPermission(String userId) {
        List<String> readableUsers = getReadableUsers();
        return readableUsers != null && readableUsers.contains(userId);
    }

    /**
     * 判断目标用户是否具有该表单的写权限
     *
     * @param uid 目标用户 ID
     */
    public boolean hasWritePermission(String uid) {
        List<String> writableUsers = getWritableUsers();
        return writableUsers != null && writableUsers.contains(uid);
    }

    /**
     * 为目标用户赋予当前表单的写权限
     *
     * @param uid 目标用户 ID
     */
    public void addWritePermission(String uid) {
        writableUsers.add(uid);
    }

    /**
     * 移除目标用户对该表单的写权限
     *
     * @param uid 目标用户 ID
     */
    public void removeWritePermission(String uid) {
        writableUsers.remove(uid);
    }

    /**
     * 移除所有用户对该表单的写权限
     */
    public void removeWritePermission() {
        writableUsers.clear();
    }

    /**
     * 为目标用户赋予当前表单的读权限
     *
     * @param uid 目标用户
     */
    public void addReadPermission(String uid) {
        readableUsers.add(uid);
    }

    /**
     * 为指定的所有用户赋予当前表单的读权限
     *
     * @param users 用户 uid 列表
     */
    public void addReadPermission(List<String> users) {
        readableUsers.addAll(users);
    }
}
