package com.stcos.server.entity.file;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FileMetadata {
    /**
     * 文件元数据 ID，保存对象时由数据库自动赋值
     */
    private Long fileMetadataId = null;

    /**
     * 文件名称
     */
    private String fileName = null;

    /**
     * 文件类型
     */
    private String fileType = null;

    /**
     * 文件大小
     */
    private Long fileSize = null;

    /**
     * 文件上传者 (userId)
     */
    private String updatedBy = null;

    /**
     * 文件上传时间
     */
    private LocalDateTime updatedDate = null;

    /**
     * 文件在服务器磁盘上的路径
     */
    private String filePath = null;

    public FileMetadata(String fileName, String fileType, Long fileSize, String updatedBy, LocalDateTime updatedDate, String filePath) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
        this.filePath = filePath;
    }
}

