package com.stcos.server.mapper;

import com.stcos.server.entity.form.Form;
import com.stcos.server.entity.form.FormIndex;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/22 18:08
 */
public interface FormMapper {
    void saveForm(Form form);

    void saveFormIndex(FormIndex formIndex);

    FormIndex selectByFormIndexId(Long formIndexId);
}
