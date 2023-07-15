package com.stcos.server.database.mongo;

import com.stcos.server.model.form.FormMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 表单元数据仓库，用于在 MongoDB 中进行表单元数据的 CRUD 操作
 * 注意：该仓库已经废弃
 */
@Deprecated
public interface FormMetadataRepository extends MongoRepository<FormMetadata, Long> {

    /**
     * 保存表单元数据到数据库
     *
     * @param formMetadata 表单元数据对象
     */
    default void saveFormMetadata(FormMetadata formMetadata) {
        insert(formMetadata);
    }

    /**
     * 更新数据库中的表单元数据
     *
     * @param formMetadata 更新后的表单元数据对象
     */
    default void updateFormMetadata(FormMetadata formMetadata) {
        deleteById(formMetadata.getFormMetadataId());
        insert(formMetadata);
    }

    /**
     * 根据 ID 从数据库中检索表单元数据
     *
     * @param formMetadataId 表单元数据的ID
     * @return 指定ID的表单元数据对象，如果未找到则返回 null
     */
    default FormMetadata selectFormMetadataById(Long formMetadataId) {
        return findById(formMetadataId).orElse(null);
    }

    /**
     * 根据 ID 从数据库中删除表单元数据
     *
     * @param formMetadataId 要删除的表单元数据的ID
     */
    default void deleteFormMetadataById(long formMetadataId) {
        deleteById(formMetadataId);
    }
}
