package com.stcos.server.database.mongo;

import com.stcos.server.model.form.Form;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FormRepository extends MongoRepository<Form, Long> {
    default void saveForm(Form form){
        insert(form);
    }

    default void updateForm(Form form){
        deleteById(form.getFormId());
        insert(form);
    }

    default Form getFormById(long formId){
        return findById(formId).orElse(null);
    }

    default void deleteByFormId(long formId){deleteById(formId);}

}
