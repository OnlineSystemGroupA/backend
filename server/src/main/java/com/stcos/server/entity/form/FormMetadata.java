package com.stcos.server.entity.form;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.NoArgsConstructor;

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
@Document
@NoArgsConstructor
public class FormMetadata {

    /**
     * 表单元数据 ID，保存对象时由数据库自动赋值
     */
    @Id
    @AutoId
    private Long formMetadataId = null;

    /**
     * 表单元数据对应表单的 ID
     */
    private Long formId = null;

    /**
     * 表单类型
     */
    private String formType = null;

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
    private List<String> readableUsers = new ArrayList<>();

    /**
     * 对表单具有写权限用户的 ID 列表
     */
    private List<String> writableUsers = new ArrayList<>();

    public FormMetadata(
            Long formId,
            String formType,
            String createdBy,
            LocalDateTime createdDate,
            String lastModifiedBy,
            LocalDateTime lastModifiedDate) {
        this.formId = formId;
        this.formType = formType;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = lastModifiedDate;
    }

    public boolean hasReadPermission(String userId) {
        List<String> readableUsers = getReadableUsers();
        return readableUsers != null && readableUsers.contains(userId);
    }

    public boolean hasWritePermission(String userId) {
        List<String> writableUsers = getWritableUsers();
        return writableUsers != null && writableUsers.contains(userId);
    }
}
