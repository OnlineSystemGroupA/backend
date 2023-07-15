package com.stcos.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stcos.server.model.form.FormMetadata;
import com.stcos.server.exception.ServerErrorException;

import java.util.Set;

/**
 * 这个服务接口提供了管理表单元数据的方法
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/6/25 13:43
 */
public interface FormMetadataService extends IService<FormMetadata> {

    /**
     * 创建新的表单元数据
     *
     * @param projectId 项目 ID
     * @param formType 表单类型
     * @return 表单元数据 ID
     * @throws ServerErrorException 如果数据库写入失败
     */
    Long create(Long projectId, String formType);

    /**
     * 为给定的用户赋予指定表单的读取权限
     *
     * @param formMetadataId 表单元数据 ID
     * @param userId 用户 ID
     * @throws ServerErrorException 如果数据库写入失败
     */
    void addReadPermission(Long formMetadataId, String userId);

    /**
     * 为给定的用户列表赋予指定表单的读取权限
     *
     * @param formMetadataId 表单元数据 ID
     * @param userIds 用户 ID 集合
     * @throws ServerErrorException 如果数据库写入失败
     */
    void addReadPermission(Long formMetadataId, Set<String> userIds);

    /**
     * 删除所有用户对指定表单的读取权限
     *
     * @param formMetadataId 表单元数据 ID
     * @throws ServerErrorException 如果数据库写入失败
     */
    void removeReadPermission(Long formMetadataId);

    /**
     * 为给定的用户列表赋予指定表单的写权限
     *
     * @param formMetadataId 表单元数据 ID
     * @param userId 用户 ID
     * @throws ServerErrorException 如果数据库写入失败
     */
    void addWritePermission(Long formMetadataId, String userId);

    /**
     * 删除给定用户对指定表单的写权限
     *
     * @param formMetadataId 表单元数据 ID
     * @param userId 用户 ID
     * @throws ServerErrorException 如果数据库写入失败
     */
    void removeWritePermission(Long formMetadataId, String userId);

    /**
     * 删除所有用户对指定表单的写权限
     *
     * @param formMetadataId 表单元数据 ID
     * @throws ServerErrorException 如果数据库写入失败
     */
    void removeWritePermission(Long formMetadataId);

    /**
     * 检查表单是否存在
     *
     * @param formMetadataId 表单元数据 ID
     * @return 存在返回 true，否则返回 false
     */
    boolean existForm(long formMetadataId);

    /**
     * 获取表单元数据所对应的表单 ID
     *
     * @param formMetadataId 表单元数据 ID
     * @return 表单 ID
     */
    Long getFormId(Long formMetadataId);

    /**
     * 设置表单状态
     *
     * @param formMetadataId 表单元数据 ID
     * @param formState 更新后的表单状态
     */
    void setFormState(Long formMetadataId, String formState);
}

