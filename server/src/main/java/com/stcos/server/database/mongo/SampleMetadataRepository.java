package com.stcos.server.database.mongo;

import com.stcos.server.entity.file.SampleMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.ClassUtils.isPresent;

public interface SampleMetadataRepository extends MongoRepository<SampleMetadata,Long> {
    default public void saveSample(SampleMetadata sampleMetadata){
        insert(sampleMetadata);
    }

    default public void updateSample(SampleMetadata sampleMetadata){
        deleteById(sampleMetadata.getSampleMetadataId());
        insert(sampleMetadata);
    }

    default public void deleteBySampleId(Long sampleId){
        deleteById(sampleId);
    }

    default public SampleMetadata selectBySampleId(Long sampleId){return findById(sampleId).orElse(null);}
}
