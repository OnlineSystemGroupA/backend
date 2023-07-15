package com.stcos.server.database.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stcos.server.model.file.FileMetadata;
import org.springframework.stereotype.Repository;

/**
 * 定义了对 FileMetadata 实体在 MySQL 数据库中进行操作的一些方法
 * 使用了 MyBatis 的 BaseMapper 进行基础 SQL 语句的配置，同时定义了一些 FileMetadata 实体特有的数据库操作方法
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/22 18:07
 */
@Repository
public interface FileMetadataMapper extends BaseMapper<FileMetadata> {
    /**
     * 保存一个新的文件元数据对象到数据库
     *
     * @param fileMetadata 需要保存的 FileMetadata 实体
     */
    default void saveFileMetadata(FileMetadata fileMetadata){
        insert(fileMetadata);
    }

    /**
     * 根据文件元数据的 ID 从数据库中删除一个文件元数据对象
     *
     * @param fileMetadataId 需要删除的文件元数据 ID
     */
    default void deleteByFileMetadataId(long fileMetadataId){
        deleteById(fileMetadataId);
    }
}
