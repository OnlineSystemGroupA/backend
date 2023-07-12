package com.stcos.server.service;

import com.stcos.server.model.form.Form;
import com.stcos.server.model.form.FormMetadata;
import com.stcos.server.exception.ServiceException;

import java.time.LocalDateTime;
import java.util.Set;

public interface FormService {
    /**
     * 更新指定流程中的指定表单
     *
     * @param formMetadataId 表单元数据 ID
     * @return 表单对象
     * @throws ServiceException 各异常状态码含义如下 <br>
     *                          code: <br>
     *                          0: 当前流程不存在 <br>
     *                          1: 该任务对当前用户不可见或当前用户无读取权限 <br>
     */
    Form getForm(Long formMetadataId, String userId) throws ServiceException;

    /**
     * 更新指定流程中的指定表单
     *
     * @param formMetadataId 表单元数据 ID
     * @param form           表单的 JSON 格式
     * @throws ServiceException 各异常状态码含义如下 <br>
     *                          code: <br>
     *                          0: 当前流程不存在 <br>
     *                          1: 该任务对当前用户不可见或当前用户无修改权限 <br>
     */
    void saveOrUpdateForm(Long formMetadataId, Form form) throws ServiceException;

    /**
     * 保存表单索引
     *
     * @param formMetadata 待保存的表单元数据
     */
    void saveFormMetadata(FormMetadata formMetadata);

    /**
     * 查找是否存在表单
     *
     * @param formMetadataId 表单元数据Id
     * @return 存在表单则返回true
     */
    boolean existForm(long formMetadataId);

    void addWritePermission(Long formMetadataId, String userId);

    void addReadPermission(Long formMetadataId, String userId);

    void addReadPermission(Long formMetadataId, Set<String> userIds);

    void removeReadPermission(Long formMetadataId);

    Long createMetadata(Long projectId, String formType);

    void removeWritePermission(Long formMetadataId, String userId);

    void removeWritePermission(Long formMetadataId);

    Form getForm(Long metadataId);

    boolean hasWritePermission(Long formMetadataId, String userId);

    boolean hasReadPermission(Long formMetadataId, String userId);

    LocalDateTime getCreatedDate(Long metadataId);

    String getFormState(Long metadataId);

    void setFormState(Long formMetadataId, String formState);
}
