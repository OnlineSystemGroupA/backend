package com.stcos.server.repository;

import com.stcos.server.entity.form.FormIndex;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FromIndexRepository extends MongoRepository<FormIndex,Long> {
    default void saveFrom(FormIndex formIndex){
        insert(formIndex);
    }

    default FormIndex findByFormIndexId(Long formIndexId){
        return findById(formIndexId).orElse(null);
    }

}
