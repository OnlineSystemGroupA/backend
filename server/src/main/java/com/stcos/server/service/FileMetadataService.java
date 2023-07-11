package com.stcos.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stcos.server.entity.file.FileMetadata;
import com.stcos.server.exception.ServerErrorException;

import java.time.LocalDateTime;

public interface FileMetadataService extends IService<FileMetadata> {
    /**
     * 创建一个新的文件元数据记录并保存到数据库
     *
     * @param fileName 文件名
     * @param fileType 文件类型
     * @param fileSize 文件大小
     * @param updatedBy 更新者
     * @param updatedDate 更新日期
     * @param filePath 文件路径
     * @return 返回新创建的文件元数据的 ID
     * @throws ServerErrorException 如果在保存文件元数据到数据库时发生错误
     */
    Long create(String fileName, String fileType, Long fileSize, String updatedBy, LocalDateTime updatedDate, String filePath);

    /**
     * 检查指定文件是否存在
     *
     * @param fileMetadataId 文件元数据 ID
     * @return 如果文件存在则返回 true，否则返回 false
     */
    boolean existFile(long fileMetadataId);
}
