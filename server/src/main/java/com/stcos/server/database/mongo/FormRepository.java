package com.stcos.server.database.mongo;

import com.stcos.server.entity.form.Form;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FormRepository extends MongoRepository<Form, Long> {
    default void saveFrom(Form form){
        insert(form);
    }

    default Form findByFormId(Long formId){
        return findById(formId).orElse(null);
    }

}
