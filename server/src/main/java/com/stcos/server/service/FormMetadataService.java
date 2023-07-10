package com.stcos.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stcos.server.entity.form.FormMetadata;

import java.util.List;
import java.util.Set;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/6/25 13:43
 */
public interface FormMetadataService extends IService<FormMetadata> {
    Long create(String formName);

    void addReadPermission(Long formMetadataId, String userId);

    void addReadPermission(Long formMetadataId, Set<String> userId);

    void removeReadPermission(Long formMetadataId);

    void addWritePermission(Long formMetadataId, String userId);

    void removeWritePermission(Long formMetadataId, String userId);

    void removeWritePermission(Long formMetadataId);

    boolean existForm(long formMetadataId);

    Long getFormId(Long metadataId);

}
