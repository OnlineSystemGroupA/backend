package com.stcos.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stcos.server.entity.file.FileMetadata;

import java.time.LocalDateTime;

public interface FileMetadataService extends IService<FileMetadata> {
    Long create(String fileName, String fileType, Long fileSize, String updatedBy, LocalDateTime updatedDate, String filePath);

    boolean existFile(long fileMetadataId);

//    Long getFileId(Long metadataId);

//    Long create(String fileName, List<String> users);
}
