package com.stcos.server.entity.dto;

import com.stcos.server.entity.file.FileMetadata;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FileMetadataDto {
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

    public FileMetadataDto(Long fileMetadataId, String fileName, String fileType, Long fileSize, String updatedBy, LocalDateTime updatedDate) {
        this.fileMetadataId = fileMetadataId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
    }

    public FileMetadataDto(FileMetadata fileMetadata) {
        this.fileMetadataId = fileMetadata.getFileMetadataId();
        this.fileName = fileMetadata.getFileName();
        this.fileType = fileMetadata.getFileType();
        this.fileSize = fileMetadata.getFileSize();
        this.updatedBy = fileMetadata.getUpdatedBy();
        this.updatedDate = fileMetadata.getUpdatedDate();
    }
}