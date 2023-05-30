package com.stcos.server.service.impl;

import com.stcos.server.config.security.User;
import com.stcos.server.database.mongo.SampleMetadataRepository;
import com.stcos.server.database.mysql.FileMetadataMapper;
import com.stcos.server.entity.file.FileMetadata;
import com.stcos.server.entity.file.SampleMetadata;
import com.stcos.server.exception.ServiceException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.stcos.server.service.FileService;

@Service
public class FileServiceImp implements FileService {

    @Value("${file.upload.directory}")
    private String uploadDirectory;

    public void setUploadDirectory(String uploadDirectory) {
        this.uploadDirectory = uploadDirectory;
    }

    private FileMetadataMapper fileMetadataMapper;

    @Autowired
    public void setFileMetadataMapper(FileMetadataMapper fileMetadataMapper) {
        this.fileMetadataMapper = fileMetadataMapper;
    }

    private SampleMetadataRepository sampleMetadataRepository;

    @Autowired
    public void setSampleMetadataRepository(SampleMetadataRepository sampleMetadataRepository) {
        this.sampleMetadataRepository = sampleMetadataRepository;
    }

    @Override
    public List<FileMetadata> uploadSample(String processId, Long sampleMetadataId, List<MultipartFile> files) throws ServiceException {
        // 获取样品元数据
        SampleMetadata sampleMetadata = sampleMetadataRepository.selectSampleMetadataById(sampleMetadataId);

        // 获取当前登录用户
        String userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUid();

        // 判断当前登录用户是否具有上传权限
        if (sampleMetadata.hasWritePermission(userId)) {
            List<FileMetadata> fileMetadataList = new ArrayList<>();

            if (files == null || files.size() == 0) {
                throw new ServiceException(2); // 没有上传文件
            }

            File dir = new File(uploadDirectory, String.join("/", processId));
            if (!dir.exists() && !dir.mkdirs()) {
                throw new ServiceException(3); // 存储空间不足
            }

            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                String uniqueFileName = getUniqueFileName(dir.getAbsolutePath(), fileName);
                Path filePath = Paths.get(dir.getAbsolutePath(), uniqueFileName);
                try {
                    Files.write(filePath, file.getBytes());
                    FileMetadata fileMetadata = new FileMetadata(uniqueFileName, file.getContentType(), file.getSize(), userId, LocalDateTime.now(), filePath.toString());
                    fileMetadataMapper.saveFileMetadata(fileMetadata);
                    fileMetadataList.add(fileMetadata);
                } catch (IOException e) {
                    throw new ServiceException(4); // 文件上传失败
                }
            }

            // 把新旧文件元数据的列表合并
            sampleMetadata.mergeFileMetadataList(fileMetadataList);

            // 保存样品元数据
            if (!sampleMetadata.isSavedInDatabase()) {
                sampleMetadataRepository.saveSampleMetadata(sampleMetadata); // 数据库首次保存
            } else {
                sampleMetadataRepository.updateSampleMetadata(sampleMetadata); // 数据库更新
            }

            // 返回样品文件摘要
            return fileMetadataList;
        } else {
            throw new ServiceException(1); // 无上传权限的异常
        }
    }

    private String getUniqueFileName(String dirPath, String fileName) {
        String baseName = FilenameUtils.getBaseName(fileName);
        String extension = FilenameUtils.getExtension(fileName);
        String uniqueFileName = fileName;
        int counter = 2;

        // Check if the file already exists
        while (Files.exists(Paths.get(dirPath, uniqueFileName))) {
            if (extension.isEmpty()) {
                uniqueFileName = baseName + " (" + counter + ")";
            } else {
                uniqueFileName = baseName + " (" + counter + ")." + extension;
            }
            counter++;
        }

        return uniqueFileName;
    }

    @Override
    public File downloadSample(String processId, Long sampleMetadataId) throws ServiceException {
        // 获取样品元数据
        SampleMetadata sampleMetadata = sampleMetadataRepository.selectSampleMetadataById(sampleMetadataId);

        // 获取当前登录用户
        String userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUid();

        // 判断当前登录用户是否具有下载权限
        if (sampleMetadata.hasReadPermission(userId)) {
            List<FileMetadata> fileMetadataList = sampleMetadata.getFileMetadataList();
            List<File> downloadedFiles = new ArrayList<>();

            // 下载样品文件
            for (FileMetadata fileMetadata : fileMetadataList) {
                try {
                    Path filePath = Paths.get(fileMetadata.getFilePath());
                    if (Files.exists(filePath)) {
                        // 创建一个临时文件用于存储下载的文件
                        String fileName = fileMetadata.getFileName();
                        String baseName = FilenameUtils.getBaseName(fileName);
                        String extension = FilenameUtils.getExtension(fileName);
                        File tempFile = File.createTempFile(baseName, extension.isEmpty() ? "" : "." + extension);
                        // 拷贝文件到临时文件中
                        Files.copy(filePath, tempFile.toPath());
                        // 添加临时文件到下载文件列表中
                        downloadedFiles.add(tempFile);
                    } else {
                        throw new ServiceException(2); // 文件不存在
                    }
                } catch (IOException e) {
                    throw new ServiceException(3); // 文件下载失败
                }
            }

            try {
                return createZipFile(processId, downloadedFiles, fileMetadataList);
            } catch (IOException e) {
                throw new ServiceException(3); // 压缩文件时发生错误（也导致文件下载失败）
            }

        } else {
            throw new ServiceException(1); // 下载权限的异常
        }
    }

    private File createZipFile(String processId, List<File> files, List<FileMetadata> fileMetadataList) throws IOException {
        String prefix = processId + "_";
        File zipFile = File.createTempFile(prefix, ".zip");

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))) {
            for (int i = 0; i < files.size(); i++) {
                File file = files.get(i);
                FileMetadata fileMetadata = fileMetadataList.get(i);

                zos.putNextEntry(new ZipEntry(fileMetadata.getFileName()));

                byte[] bytes = Files.readAllBytes(file.toPath());
                zos.write(bytes, 0, bytes.length);
                zos.closeEntry();
            }
        }

        return zipFile;
    }

    @Override
    public void deleteSample(Long sampleMetadataId) throws ServiceException {
        // 获取样品元数据
        SampleMetadata sampleMetadata = sampleMetadataRepository.selectSampleMetadataById(sampleMetadataId);

        // 获取当前登录用户
        String userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUid();

        // 判断当前登录用户是否具有删除权限
        if (sampleMetadata.hasWritePermission(userId)) {
            List<FileMetadata> fileMetadataList = sampleMetadata.getFileMetadataList();
            for (FileMetadata fileMetadata : fileMetadataList) {
                try {
                    Path filePath = Paths.get(fileMetadata.getFilePath());
                    if (Files.exists(filePath)) {
                        Files.delete(filePath); // 删除文件
                        fileMetadataMapper.deleteByFileMetadataId(fileMetadata.getFileMetadataId()); // 删除文件元数据
                    } else {
                        throw new ServiceException(2); // 文件不存在
                    }
                } catch (IOException e) {
                    throw new ServiceException(3); // 文件删除失败
                }
            }

            // 删除样品元数据
            sampleMetadataRepository.deleteBySampleMetadataId(sampleMetadata.getSampleMetadataId());
        } else {
            throw new ServiceException(1); // 无删除权限的异常
        }
    }
}