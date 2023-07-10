package com.stcos.server.service;

import com.stcos.server.entity.file.FileMetadata;
import com.stcos.server.exception.ServiceException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface FileService {

    /**
     * 上传样品
     *
     * @param processId 指定流程实例 ID
     * @param sampleMetadataId 样品元数据 ID
     * @param file 样品文件
     * @return 样品文件元数据
     * @throws ServiceException 各异常状态码含义如下 <br>
     *                          code: <br>
     *                          1: 用户无上传权限 <br>
     *                          2: 没有上传文件 <br>
     *                          3: 存储空间不足 <br>
     *                          4: 文件上传失败 <br>
     */
    FileMetadata uploadSample(String processId, Long sampleMetadataId, MultipartFile file) throws ServiceException;

    /**
     * 下载样品
     *
     * @param processId 指定流程实例 ID
     * @param sampleMetadataId 样品元数据 ID
     * @return 样品文件列表的单一压缩文件
     * @throws ServiceException 各异常状态码含义如下 <br>
     *                          code: <br>
     *                          1: 用户无下载权限 <br>
     *                          2: 文件不存在 <br>
     *                          3: 文件下载失败（文件本身的下载失败或压缩文件时失败） <br>
     */
    File downloadSample(String processId, Long sampleMetadataId) throws ServiceException;

    /**
     * 删除样品
     *
     * @param sampleMetadataId 样品元数据 ID
     * @throws ServiceException 各异常状态码含义如下 <br>
     *                          code: <br>
     *                          1: 用户无删除权限 <br>
     *                          2: 文件不存在 <br>
     *                          3: 文件删除失败 <br>
     */
    void deleteSample(Long sampleMetadataId) throws ServiceException;

//    boolean existSample(long sampleMetadataId);

    void addWritePermission(Long sampleMetadataId, String assignee);

    void addReadPermission(Long sampleMetadataId, String userId);

    Long createMetadata();

    Long createMetadata(List<String> users);

    void removeWritePermission(Long sampleMetadataId, String assignee);

//    Sample getSample(Long metadataId);
}
