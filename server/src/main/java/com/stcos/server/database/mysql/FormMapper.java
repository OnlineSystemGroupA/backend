package com.stcos.server.database.mysql;

import com.stcos.server.model.form.Form;
import com.stcos.server.model.form.FormMetadata;
import org.springframework.stereotype.Repository;

/**
 * 对 Form 及 FormMetadata 实体进行操作的 Mapper 接口
 * 提供了对数据库中表单表及表单元数据表进行保存和查询操作的方法
 * 注意，该接口已被标记为 Deprecated（已弃用）
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/22 18:08
 */

@Deprecated
@Repository
public interface FormMapper {

    /**
     * 保存 Form 实体至数据库
     *
     * @param form 待保存的 Form 实体
     */
    void saveForm(Form form);

    /**
     * 保存 FormMetadata 实体至数据库
     *
     * @param formMetadata 待保存的 FormMetadata 实体
     */
    void saveFormMetadata(FormMetadata formMetadata);

    /**
     * 根据 FormMetadata 的 ID 在数据库中进行查询
     *
     * @param formMetadataId FormMetadata 的 ID
     * @return 查询到的 FormMetadata 实体
     */
    FormMetadata selectByFormMetadataId(Long formMetadataId);
}
