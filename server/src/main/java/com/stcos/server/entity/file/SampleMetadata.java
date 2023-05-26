package com.stcos.server.entity.file;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SampleMetadata {
    /**
     * 样品列表 ID，保存对象时由数据库自动赋值
     */
    private Long sampleId;

    /**
     * 对样品具有读权限用户的 ID 列表
     */
    private List<String> readableUsers = new ArrayList<>();

    /**
     * 对样品具有写权限用户的 ID 列表
     */
    private List<String> writableUsers = new ArrayList<>();

    /**
     * 文件元数据列表
     */
    private List<FileMetadata> fileMetadataList = new ArrayList<>();

    public void mergeFileMetadataList(List<FileMetadata> newList) {
        if (fileMetadataList == null) {
            fileMetadataList = new ArrayList<>(newList);
        } else {
            fileMetadataList.addAll(newList);
        }
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