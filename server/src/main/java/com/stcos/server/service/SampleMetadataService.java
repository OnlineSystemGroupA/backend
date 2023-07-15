package com.stcos.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stcos.server.model.file.SampleMetadata;
import com.stcos.server.exception.ServerErrorException;

import java.util.List;

public interface SampleMetadataService extends IService<SampleMetadata> {
    /**
     * 创建新的样品元数据
     *
     * @return 样品元数据 ID
     * @throws ServerErrorException 如果样品元数据保存到数据库失败
     */
    Long create();

    /**
     * 将新的文件元数据 ID 添加到样品元数据的文件元数据 ID 列表中
     *
     * @param sampleMetadataId 样品元数据 ID
     * @param fileMetadataId 要添加的文件元数据 ID
     * @throws ServerErrorException 如果更新样品元数据到数据库失败
     */
    void addFileMetadata(Long sampleMetadataId, Long fileMetadataId);

    /**
     * 从样品的文件元数据 ID 列表中移除指定的元素
     *
     * @param sampleMetadataId 样品元数据 ID
     * @param fileMetadataId 要被移除的文件元数据 ID
     * @throws ServerErrorException 如果更新样品元数据到数据库失败
     */
    void removeFileMetadata(Long sampleMetadataId, Long fileMetadataId);

    /**
     * 赋予给定用户对样品的读权限
     *
     * @param sampleMetadataId 样品元数据 ID
     * @param userId 用户 ID
     * @throws ServerErrorException 如果更新样品元数据到数据库失败
     */
    void addReadPermission(Long sampleMetadataId, String userId);

    /**
     * 赋予给定用户对样品的写权限
     *
     * @param sampleMetadataId 样品元数据 ID
     * @param userId 用户 ID
     * @throws ServerErrorException 如果更新样品元数据到数据库失败
     */
    void addWritePermission(Long sampleMetadataId, String userId);

    /**
     * 删除给定用户对样品的写权限
     *
     * @param sampleMetadataId 样品元数据 ID
     * @param userId 用户 ID
     * @throws ServerErrorException 如果更新样品元数据到数据库失败
     */
    void removeWritePermission(Long sampleMetadataId, String userId);

    /**
     * 检查给定用户对样品是否具有读权限
     *
     * @param sampleMetadataId 样品元数据 ID
     * @param userId 用户 ID
     * @return 如果用户有读权限则返回 true，否则返回 false
     * @throws ServerErrorException 如果从数据库中获取样品元数据失败
     */
    boolean hasReadPermission(Long sampleMetadataId, String userId);

    /**
     * 检查给定用户对样品是否具有写权限
     *
     * @param sampleMetadataId 样品元数据 ID
     * @param userId 用户 ID
     * @return 如果用户有写权限则返回 true，否则返回 false
     * @throws ServerErrorException 如果从数据库中获取样品元数据失败
     */
    boolean hasWritePermission(Long sampleMetadataId, String userId);

    /**
     * 创建一个新的样品元数据，并为给定的用户列表赋予读取权限
     *
     * @param users 用户列表
     * @return 新创建的样品元数据的 ID
     * @throws ServerErrorException 如果样品元数据保存到数据库失败
     */
    Long create(List<String> users);
}

