package com.stcos.server.database.mongo;

import com.stcos.server.model.form.Form;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 表单仓库，用于在 MongoDB 中进行表单的 CRUD 操作
 */
public interface FormRepository extends MongoRepository<Form, Long> {

    /**
     * 将表单保存到数据库
     *
     * @param form 要保存的表单对象
     */
    default void saveForm(Form form) {
        insert(form);
    }

    /**
     * 更新数据库中的表单
     *
     * @param form 更新后的表单对象
     */
    default void updateForm(Form form) {
        deleteById(form.getFormId());
        insert(form);
    }

    /**
     * 根据 ID 从数据库中获取表单
     *
     * @param formId 表单的 ID
     * @return 指定 ID 的表单对象，如果未找到则返回 null
     */
    default Form getFormById(long formId) {
        return findById(formId).orElse(null);
    }

    /**
     * 根据 ID 从数据库中删除表单
     *
     * @param formId 要删除的表单的 ID
     */
    default void deleteByFormId(long formId) {
        deleteById(formId);
    }
}
