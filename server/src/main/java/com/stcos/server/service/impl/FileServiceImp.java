package com.stcos.server.service.impl;

import com.stcos.server.entity.file.FileMetadata;
import com.stcos.server.exception.ServiceException;
import com.stcos.server.mapper.FileMapper;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.stcos.server.service.FileService;

@Service
public class FileServiceImp implements FileService {

    @Value("${file.upload.directory}")
    private String uploadDirectory;

    public void setUploadDirectory(String uploadDirectory) {
        this.uploadDirectory = uploadDirectory;
    }

    private FileMapper fileMapper;

    @Autowired
    public void setFileMapper(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    @Override
    public List<FileMetadata> uploadSample(String processId, String userId, List<MultipartFile> files) throws ServiceException {
        if (files == null || files.size() == 0) {
            throw new ServiceException(0); // 没有上传文件
        }

        File dir = new File(uploadDirectory, String.join("/", processId));
        if (!dir.exists() && !dir.mkdirs()) {
            throw new ServiceException(1); // 存储空间不足
        }

        List<FileMetadata> fileMetadataList = new ArrayList<>();
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            String uniqueFileName = getUniqueFileName(dir.getAbsolutePath(), fileName);
            Path filePath = Paths.get(dir.getAbsolutePath(), uniqueFileName);
            try {
                Files.write(filePath, file.getBytes());
                FileMetadata fileMetadata = new FileMetadata(uniqueFileName, file.getContentType(), file.getSize(), userId, LocalDateTime.now(), filePath.toString());
                fileMapper.saveFileMetadata(fileMetadata);
                fileMetadataList.add(fileMetadata);
            } catch (IOException e) {
                throw new ServiceException(2); // 文件上传失败
            }
        }

        return fileMetadataList;
    }

    private String getUniqueFileName(String dirPath, String fileName) {
        String baseName = FilenameUtils.getBaseName(fileName);
        String extension = FilenameUtils.getExtension(fileName);
        String uniqueFileName = fileName;
        int counter = 1;

        // Check if the file already exists
        while (Files.exists(Paths.get(dirPath, uniqueFileName))) {
            uniqueFileName = baseName + " (" + counter + ")" + "." + extension;
            counter++;
        }

        return uniqueFileName;
    }

    public List<File> downloadSample(List<FileMetadata> fileMetadataList) throws ServiceException {
        List<File> downloadedFiles = new ArrayList<>();

        for (FileMetadata fileMetadata : fileMetadataList) {
            try {
                Path filePath = Paths.get(fileMetadata.getFilePath());
                if (Files.exists(filePath)) {
                    // 创建一个临时文件用于存储下载的文件
                    File tempFile = File.createTempFile(fileMetadata.getFileName(), fileMetadata.getFileType());
                    // 拷贝文件到临时文件中
                    Files.copy(filePath, tempFile.toPath());
                    // 添加临时文件到下载文件列表中
                    downloadedFiles.add(tempFile);
                } else {
                    throw new ServiceException(0); // 文件不存在
                }
            } catch (IOException e) {
                throw new ServiceException(1); // 文件下载失败
            }
        }
        return downloadedFiles;
    }

    public void deleteFiles(List<FileMetadata> fileMetadataList) throws ServiceException {
        for (FileMetadata fileMetadata : fileMetadataList) {
            try {
                Path filePath = Paths.get(fileMetadata.getFilePath());
                if (Files.exists(filePath)) {
                    // 删除文件
                    Files.delete(filePath);
                    // 删除文件元数据
                    fileMapper.deleteByFileMetadataId(fileMetadata.getFileMetadataId());
                } else {
                    throw new ServiceException(0); // 文件不存在
                }
            } catch (IOException e) {
                throw new ServiceException(1); // 文件删除失败
            }
        }
    }
}