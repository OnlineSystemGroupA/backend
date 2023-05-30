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
     * @param files 样品文件列表
     * @return 样品文件元数据列表
     * @throws ServiceException 各异常状态码含义如下 <br>
     *                          code: <br>
     *                          1: 用户无上传权限 <br>
     *                          2: 没有上传文件 <br>
     *                          3: 存储空间不足 <br>
     *                          4: 文件上传失败 <br>
     */
    List<FileMetadata> uploadSample(String processId, Long sampleMetadataId, List<MultipartFile> files) throws ServiceException;

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
}
