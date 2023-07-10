package com.stcos.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stcos.server.database.mysql.FileMetadataMapper;
import com.stcos.server.entity.file.FileMetadata;
import com.stcos.server.exception.ServerErrorException;
import com.stcos.server.service.FileMetadataService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FileMetadataServiceImp extends ServiceImpl<FileMetadataMapper, FileMetadata> implements FileMetadataService {

    @Override
    public Long create(String fileName, String fileType, Long fileSize, String updatedBy, LocalDateTime updatedDate, String filePath) {
        FileMetadata fileMetadata = new FileMetadata(fileName, fileType, fileSize, updatedBy, updatedDate, filePath);
        if (!save(fileMetadata)) throw new ServerErrorException(new RuntimeException("数据库写入错误！"));
        return fileMetadata.getFileMetadataId();
    }

    @Override
    public boolean existFile(long fileMetadataId) {
        return getById(fileMetadataId).getFilePath() != null;
    }
}
