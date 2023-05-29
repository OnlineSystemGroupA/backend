package com.stcos.server.service;

import com.stcos.server.entity.dto.FileMetadataDto;
import com.stcos.server.entity.file.Sample;
import com.stcos.server.exception.ServiceException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface FileService {

    /**
     * 上传样品文件
     *
     * @param processId 指定流程实例 Id
     * @param sample 样品对象
     * @param files 样品文件列表
     * @return 文件元数据列表
     * @throws ServiceException 各异常状态码含义如下 <br>
     *                          code: <br>
     *                          1: 用户无上传权限 <br>
     *                          2: 没有上传文件 <br>
     *                          3: 存储空间不足 <br>
     *                          4: 文件上传失败 <br>
     */
    List<FileMetadataDto> uploadSample(String processId, Sample sample, List<MultipartFile> files) throws ServiceException;

    /**
     * 下载样品文件
     *
     * @param processId 指定流程实例 Id
     * @param sample 样品对象
     * @return 样品文件列表的单一压缩文件
     * @throws ServiceException 各异常状态码含义如下 <br>
     *                          code: <br>
     *                          1: 用户无下载权限 <br>
     *                          2: 文件不存在 <br>
     *                          3: 文件下载失败（文件本身的下载失败或压缩文件时失败） <br>
     */
    File downloadSample(String processId, Sample sample) throws ServiceException;

    /**
     * 删除样品文件
     *
     * @param sample 样品对象
     * @throws ServiceException 各异常状态码含义如下 <br>
     *                          code: <br>
     *                          1: 用户无删除权限 <br>
     *                          2: 文件不存在 <br>
     *                          3: 文件删除失败 <br>
     */
    void deleteSample(Sample sample) throws ServiceException;
}
