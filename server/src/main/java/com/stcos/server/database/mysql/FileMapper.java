package com.stcos.server.database.mysql;

import com.stcos.server.entity.file.FileMetadata;
import com.stcos.server.entity.file.SampleMetadata;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/22 18:07
 */
public interface FileMapper {
    void saveFileMetadata(FileMetadata fileMetadata);

    void deleteByFileMetadataId(Long fileMetadataId);

    void saveSample(SampleMetadata sampleMetadata);

    void deleteBySampleId(Long sampleId);

    SampleMetadata selectBySampleId(Long sampleId);
}
