package com.stcos.server.database.mongo;

import com.stcos.server.entity.file.SampleMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SampleMetadataRepository extends MongoRepository<SampleMetadata,Long> {
    default void saveSample(SampleMetadata sampleMetadata){
        insert(sampleMetadata);
    }

    default void updateSample(SampleMetadata sampleMetadata){
        deleteById(sampleMetadata.getSampleMetadataId());
        insert(sampleMetadata);
    }

    default void deleteBySampleId(Long sampleId){
        deleteById(sampleId);
    }

    default SampleMetadata selectBySampleId(Long sampleId){return findById(sampleId).orElse(null);}
}
