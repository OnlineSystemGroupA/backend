package com.stcos.server.entity.form;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/17 9:57
 */

@Data
public abstract class Form {

    private Long formId;

    // TODO 酌情设计一些与表单操作有关的共用方法

    // TODO
    private static final Map<String, Class<?extends Form>> FORM_NAME_CLASS_MAP = new HashMap<>(){{
        put("", null);
    }};


    public static Form buildForm(String formName, String formData) {

        return null;
    }

}
