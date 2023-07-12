package com.stcos.server.database.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stcos.server.model.file.FileMetadata;
import org.springframework.stereotype.Repository;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/22 18:07
 */

@Repository
public interface FileMetadataMapper extends BaseMapper<FileMetadata> {
    default void saveFileMetadata(FileMetadata fileMetadata){
        insert(fileMetadata);
    }

    default void deleteByFileMetadataId(long fileMetadataId){
        deleteById(fileMetadataId);
    }
}
