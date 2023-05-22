package com.stcos.server.service;

import com.stcos.server.entity.file.FileMetadata;
import com.stcos.server.exception.ServiceException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface FileService {

    /**
     * 上传样品文件
     *
     * @param processInstanceId 指定流程实例 Id
     * @param userId 上传者 Id
     * @param files 样品文件
     * @return 文件元数据列表
     * @throws ServiceException 各异常状态码含义如下 <br>
     *                          code: <br>
     *                          0: 没有上传文件 <br>
     *                          1: 存储空间不足 <br>
     *                          2: 文件上传失败 <br>
     */
    List<FileMetadata> uploadSample(String processInstanceId, String userId, List<MultipartFile> files) throws ServiceException;

    /**
     * 下载样品文件
     *
     * @param fileMetadataList 文件元数据列表
     * @return 样品文件
     * @throws ServiceException 各异常状态码含义如下 <br>
     *                          code: <br>
     *                          0: 文件不存在 <br>
     *                          1: 文件下载失败 <br>
     */
    List<File> downloadSample(List<FileMetadata> fileMetadataList) throws ServiceException;

    /**
     * 删除样品中的部分文件
     *
     * @param fileMetadataList 文件元数据列表
     * @throws ServiceException 各异常状态码含义如下 <br>
     *                          code: <br>
     *                          0: 文件不存在 <br>
     *                          1: 文件删除失败 <br>
     */
    void deleteFiles(List<FileMetadata> fileMetadataList) throws ServiceException;
}
