package com.stcos.server.database.mongo;

import com.stcos.server.model.file.SampleMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SampleMetadataRepository extends MongoRepository<SampleMetadata,Long> {
    default public void saveSampleMetadata(SampleMetadata sampleMetadata){
        insert(sampleMetadata);
    }

    default public void updateSampleMetadata(SampleMetadata sampleMetadata){
        deleteById(sampleMetadata.getSampleMetadataId());
        insert(sampleMetadata);
    }

    default public void deleteBySampleMetadataId(Long sampleMetadataId){
        deleteById(sampleMetadataId);
    }

    default public SampleMetadata selectSampleMetadataById(Long sampleMetadataId){
        return findById(sampleMetadataId).orElse(null);
    }
}
