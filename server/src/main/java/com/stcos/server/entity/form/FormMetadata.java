package com.stcos.server.entity.form;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.stcos.server.util.ListTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.type.JdbcType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 表单的索引类
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/17 9:51
 */
@Data
@NoArgsConstructor
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
    private String formName = null;

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
    @TableField(jdbcType = JdbcType.BLOB, typeHandler = ListTypeHandler.class)
    private List<String> readableUsers = new ArrayList<>();

    /**
     * 对表单具有写权限用户的 ID 列表
     */
    @TableField(jdbcType = JdbcType.BLOB, typeHandler = ListTypeHandler.class)
    private List<String> writableUsers = new ArrayList<>();

    public FormMetadata(
            String formName,
            String createdBy) {
        this.formName = formName;
        this.createdBy = createdBy;
        this.createdDate = LocalDateTime.now();
        this.lastModifiedBy = createdBy;
        this.lastModifiedDate = LocalDateTime.now();
    }

    public boolean hasReadPermission(String userId) {
        List<String> readableUsers = getReadableUsers();
        return readableUsers != null && readableUsers.contains(userId);
    }

    public boolean hasWritePermission(String userId) {
        List<String> writableUsers = getWritableUsers();
        return writableUsers != null && writableUsers.contains(userId);
    }

    public void addWritePermission(String uid) {
        writableUsers.add(uid);
    }

    public void removeWritePermission(String uid) {
        writableUsers.remove(uid);
    }

    public void addReadPermission(String startUserId) {
        readableUsers.add(startUserId);
    }
}
