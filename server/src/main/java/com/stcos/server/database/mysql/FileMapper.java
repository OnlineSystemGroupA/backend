package com.stcos.server.database.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stcos.server.entity.file.FileMetadata;
import com.stcos.server.entity.file.SampleMetadata;
import com.stcos.server.entity.user.Client;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/22 18:07
 */

@Repository
public interface FileMapper extends BaseMapper<FileMetadata> {
    default void saveFileMetadata(FileMetadata fileMetadata){
        insert(fileMetadata);
    }

    default void deleteByFileMetadataId(long fileMetadataId){
        deleteById(fileMetadataId);
    }
}
