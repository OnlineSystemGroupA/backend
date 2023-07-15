package com.stcos.server.service;

import com.stcos.server.model.file.FileMetadata;
import com.stcos.server.model.form.Form;
import com.stcos.server.exception.ServerErrorException;
import com.stcos.server.exception.ServiceException;
import org.springframework.core.io.Resource;
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

    /**
     * 为给定用户赋予样品的写权限
     * 
     * @param sampleMetadataId 样品元数据 ID
     * @param userId 用户 ID
     */
    void addWritePermission(Long sampleMetadataId, String userId);

    /**
     * 为给定用户赋予样品的读取权限
     * 
     * @param sampleMetadataId 样品元数据 ID
     * @param userId 用户 ID
     */
    void addReadPermission(Long sampleMetadataId, String userId);

    /**
     * 创建新的样品元数据
     * 
     * @return 被创建的样品元数据 ID
     */
    Long createMetadata();

    /**
     * 生成并返回指定表单的 PDF 文件
     *
     * @param processId  流程 ID
     * @param form       表单数据
     * @param formType   表单类型
     * @return           返回文件系统资源，即生成的 PDF文件
     */
    Resource generateFormPdf(String processId, Form form, String formType);

    /**
     * 保存上传的表单的 PDF 文件到指定位置
     *
     * @param processId  流程 ID
     * @param file       上传的 MultipartFile 文件
     * @param formType   表单类型
     * @throws ServerErrorException 如果在保存 PDF 文件时发生错误
     */
    void saveFormPdf(String processId, MultipartFile file, String formType);

    /**
     * 删除给定用户对样品的写权限
     *
     * @param sampleMetadataId 样品元数据 ID
     * @param userId 用户 ID
     */
    void removeWritePermission(Long sampleMetadataId, String userId);

    /**
     * 创建一个新的样品元数据，并为给定的用户列表赋予读取权限
     *
     * @param users 用户列表
     * @return 新创建的样品元数据的 ID
     */
    Long createMetadata(List<String> users);
}
