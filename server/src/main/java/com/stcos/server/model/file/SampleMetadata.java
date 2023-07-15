package com.stcos.server.model.file;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.stcos.server.database.mysql.handler.ListTypeHandler;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.util.ArrayList;
import java.util.List;

/**
 * 样品元数据对象，用于存储样品的元数据信息
 */
 @Data
public class SampleMetadata {
    /**
     * 样品元数据 ID，保存对象时由数据库自动赋值
     */
    @TableId
    private Long sampleMetadataId;

    /**
     * 对样品具有读权限用户的 ID 列表
     */
    @TableField(value = "readable_users", jdbcType = JdbcType.BLOB, typeHandler = ListTypeHandler.class)
    private List<String> readableUsers = new ArrayList<>();

    /**
     * 对样品具有写权限用户的 ID 列表
     */
    @TableField(value = "writable_users", jdbcType = JdbcType.BLOB, typeHandler = ListTypeHandler.class)
    private List<String> writableUsers = new ArrayList<>();

    /**
     * 文件元数据 ID 列表
     */
    @TableField(value = "file_metadata_id_list", jdbcType = JdbcType.BLOB, typeHandler = ListTypeHandler.class)
    private List<Long> fileMetadataIdList = new ArrayList<>();

    /**
     * 更新文件元数据 ID 列表
     *
     * @param newFileMetadataId 新增的文件元数据 ID
     */
    public void updateFileMetadataList(Long newFileMetadataId) {
        if (fileMetadataIdList == null) {
            fileMetadataIdList = new ArrayList<>();
        }
        fileMetadataIdList.add(newFileMetadataId);
    }

    /**
     * 检查指定用户是否存在读权限
     *
     * @param userId 用户 ID
     * @return 若用户存在读权限，返回 true，否则返回 false
     */
    public boolean hasReadPermission(String userId) {
        List<String> readableUsers = getReadableUsers();
        return readableUsers != null && readableUsers.contains(userId);
    }


    /**
     * 检查指定用户是否存在写权限
     *
     * @param userId 用户 ID
     * @return 若用户存在写权限，返回 true，否则返回 false
     */
    public boolean hasWritePermission(String userId) {
        List<String> writableUsers = getWritableUsers();
        return writableUsers != null && writableUsers.contains(userId);
    }

    /**
     * 给指定用户添加写权限
     *
     * @param uid 用户 ID
     */
    public void addWritePermission(String uid) {
        writableUsers.add(uid);
    }

    /**
     * 移除特定用户的写权限
     *
     * @param uid 用户 ID
     */
    public void removeWritePermission(String uid) {
        writableUsers.remove(uid);
    }

    /**
     * 给指定用户添加读权限
     *
     * @param userId 用户 ID
     */
    public void addReadPermission(String userId) {
        readableUsers.add(userId);
    }

    /**
     * 给多个指定用户批量添加读权限
     *
     * @param users 用户 ID 列表
     */
    public void addReadPermission(List<String> users) {
        readableUsers.addAll(users);
    }
}