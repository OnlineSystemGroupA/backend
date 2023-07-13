package com.stcos.server.database.mongo;

import com.stcos.server.model.form.FormMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;

@Deprecated
public interface FormMetadataRepository extends MongoRepository<FormMetadata, Long> {

    default void saveFormMetadata(FormMetadata formMetadata) {
        insert(formMetadata);
    }

    default void updateFormMetadata(FormMetadata formMetadata) {
        deleteById(formMetadata.getFormMetadataId());
        insert(formMetadata);
    }

    default FormMetadata selectFormMetadataById(Long formMetadataId) {
        return findById(formMetadataId).orElse(null);
    }

    default void deleteFormMetadataById(long formMetadataId) {
        deleteById(formMetadataId);
    }
}
