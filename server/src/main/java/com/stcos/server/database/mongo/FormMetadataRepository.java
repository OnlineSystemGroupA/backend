package com.stcos.server.repository;

import com.stcos.server.entity.form.FormMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FormMetadataRepository extends MongoRepository<FormMetadata,Long> {
    default void saveFormMetadata(FormMetadata formMetadata){
        insert(formMetadata);
    }

    default FormMetadata findByFormMetadataId(Long formMetadata){
        return findById(formMetadata).orElse(null);
    }

}
