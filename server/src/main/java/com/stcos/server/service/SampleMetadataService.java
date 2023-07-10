package com.stcos.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stcos.server.entity.file.SampleMetadata;

import java.util.List;

public interface SampleMetadataService extends IService<SampleMetadata> {
    Long create();

    void update(SampleMetadata sampleMetadata);

    void removeFileMetadataById(Long SampleMetadataId, Long fileMetadataId);

    void addReadPermission(Long sampleMetadataId, String userId);

    void addWritePermission(Long sampleMetadataId, String userId);

    void removeWritePermission(Long fileMetadataId, String userId);

//    boolean existSample(long sampleMetadataId);
//
//    Long getFileId(Long metadataId);

    Long create(List<String> users);
}

