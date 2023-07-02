package com.stcos.server.database.mysql;

import com.stcos.server.entity.form.Form;
import com.stcos.server.entity.form.FormMetadata;
import org.springframework.stereotype.Repository;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/22 18:08
 */
@Repository
public interface FormMapper {
    void saveForm(Form form);

    void saveFormIndex(FormMetadata formMetadata);

    FormMetadata selectByFormIndexId(Long formIndexId);
}
