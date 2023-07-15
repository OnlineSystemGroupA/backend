package com.stcos.server.service;

import com.stcos.server.model.form.Form;
import com.stcos.server.model.form.FormMetadata;
import com.stcos.server.exception.ServiceException;

import java.time.LocalDateTime;
import java.util.Set;

public interface FormService {
    /**
     * 根据表单元数据 ID 和用户 ID 获取表单对象
     *
     * @param formMetadataId 表单元数据 ID
     * @param userId 用户 ID
     * @return 返回符合条件的表单对象
     * @throws ServiceException 如果当前流程不存在或该任务对当前用户不可见或当前用户无读取权限，将抛出异常
     */
    Form getForm(Long formMetadataId, String userId) throws ServiceException;

    /**
     * 保存或更新指定流程中的指定表单
     *
     * @param formMetadataId 表单元数据 ID
     * @param form 待保存或更新的表单对象
     * @throws ServiceException 如果当前流程不存在或该任务对当前用户不可见或当前用户无修改权限，将抛出异常
     */
    void saveOrUpdateForm(Long formMetadataId, Form form) throws ServiceException;

    /**
     * 保存表单元数据
     *
     * @param formMetadata 待保存的表单元数据对象
     */
    void saveFormMetadata(FormMetadata formMetadata);

    /**
     * 判断是否存在指定 ID 的表单
     *
     * @param formMetadataId 表单元数据 ID
     * @return 如果存在，则返回 true；否则返回 false
     */
    boolean existForm(long formMetadataId);

    /**
     * 给指定的表单元数据 ID 添加写权限
     *
     * @param formMetadataId 表单元数据 ID
     * @param userId 用户 ID
     */
    void addWritePermission(Long formMetadataId, String userId);

    /**
     * 给指定的表单元数据 ID 添加读权限
     *
     * @param formMetadataId 表单元数据 ID
     * @param userId 用户 ID
     */
    void addReadPermission(Long formMetadataId, String userId);

    /**
     * 给指定的表单元数据 ID 添加多个用户的读权限
     *
     * @param formMetadataId 表单元数据 ID
     * @param userIds 用户 ID 集合
     */
    void addReadPermission(Long formMetadataId, Set<String> userIds);

    /**
     * 移除指定的表单元数据 ID 的读权限
     *
     * @param formMetadataId 表单元数据 ID
     */
    void removeReadPermission(Long formMetadataId);

    /**
     * 创建新的表单元数据
     *
     * @param projectId 项目 ID
     * @param formType 表单类型
     * @return 返回新创建的表单元数据 ID
     */
    Long createMetadata(Long projectId, String formType);

    /**
     * 移除指定的表单元数据 ID 的写权限
     *
     * @param formMetadataId 表单元数据 ID
     * @param userId 用户 ID
     */
    void removeWritePermission(Long formMetadataId, String userId);

    /**
     * 移除指定的表单元数据 ID 的写权限
     *
     * @param formMetadataId 表单元数据 ID
     */
    void removeWritePermission(Long formMetadataId);

    /**
     * 根据元数据 ID 获取表单对象
     *
     * @param metadataId 元数据 ID
     * @return 返回符合条件的表单对象
     */
    Form getForm(Long metadataId);

    /**
     * 判断指定用户是否具有指定表单元数据 ID 的写权限
     *
     * @param formMetadataId 表单元数据 ID
     * @param userId 用户 ID
     * @return 如果具有权限，返回 true；否则返回 false
     */
    boolean hasWritePermission(Long formMetadataId, String userId);

    /**
     * 判断指定用户是否具有指定表单元数据 ID 的读权限
     *
     * @param formMetadataId 表单元数据 ID
     * @param userId 用户 ID
     * @return 如果具有权限，返回 true；否则返回 false
     */
    boolean hasReadPermission(Long formMetadataId, String userId);

    /**
     * 获取指定元数据 ID 的创建日期
     *
     * @param formMetadataId 表单元数据 ID
     * @return 返回创建日期
     */
    LocalDateTime getCreatedDate(Long formMetadataId);

    /**
     * 获取指定元数据 ID 的表单状态
     *
     * @param formMetadataId 表单元数据 ID
     * @return 返回表单状态
     */
    String getFormState(Long formMetadataId);

    /**
     * 设置指定元数据 ID 的表单状态
     *
     * @param formMetadataId 表单元数据 ID
     * @param formState 表单状态
     */
    void setFormState(Long formMetadataId, String formState);
}
