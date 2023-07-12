package com.stcos.server.entity.file;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.stcos.server.database.mysql.handler.ListTypeHandler;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.util.ArrayList;
import java.util.List;

@Data
public class SampleMetadata {
    /**
     * 样品元数据 ID，保存对象时由数据库自动赋值
     */
    @TableId
    private long sampleMetadataId;

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

    public void updateFileMetadataList(Long newFileMetadataId) {
        if (fileMetadataIdList == null) {
            fileMetadataIdList = new ArrayList<>();
        }
        fileMetadataIdList.add(newFileMetadataId);
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

    public void addReadPermission(List<String> users) {
        readableUsers.addAll(users);
    }
}