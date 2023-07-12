package com.stcos.server.service.impl;

import com.stcos.server.database.mysql.FileMetadataMapper;
import com.stcos.server.database.mysql.SampleMetadataMapper;
import com.stcos.server.model.file.FileMetadata;
import com.stcos.server.model.file.SampleMetadata;
import com.stcos.server.model.form.Form;
import com.stcos.server.model.user.User;
import com.stcos.server.exception.ServerErrorException;
import com.stcos.server.exception.ServiceException;
import com.stcos.server.service.FileMetadataService;
import com.stcos.server.service.FileService;
import com.stcos.server.util.FormUtil;
import com.stcos.server.util.WordAndPdfUtil;
import com.stcos.server.service.SampleMetadataService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class FileServiceImp implements FileService {

    @Value("${file.upload.directory}")
    private String uploadDirectory;

    public void setUploadDirectory(String uploadDirectory) {
        this.uploadDirectory = uploadDirectory;
    }

    private SampleMetadataMapper sampleMetadataMapper;

    @Autowired
    public void setSampleMetadataMapper(SampleMetadataMapper sampleMetadataMapper) {
        this.sampleMetadataMapper = sampleMetadataMapper;
    }

    private FileMetadataMapper fileMetadataMapper;

    @Autowired
    public void setFileMetadataMapper(FileMetadataMapper fileMetadataMapper) {
        this.fileMetadataMapper = fileMetadataMapper;
    }

    private FileMetadataService fileMetadataService;

    @Autowired
    public void setFileMetadataService(FileMetadataService fileMetadataService) {
        this.fileMetadataService = fileMetadataService;
    }

    private SampleMetadataService sampleMetadataService;

    @Autowired
    public void setSampleMetadataService(SampleMetadataService sampleMetadataService) {
        this.sampleMetadataService = sampleMetadataService;
    }

    @Override
    public FileMetadata uploadSample(String processId, Long sampleMetadataId, MultipartFile file) throws ServiceException {
        // 获取样品元数据
        SampleMetadata sampleMetadata = sampleMetadataService.getById(sampleMetadataId);

        // 获取当前登录用户，和当前样品的可写用户列表
        String userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUid();

//        sampleMetadata.addWritePermission(userId);
        // 判断当前登录用户是否具有上传权限
        if (sampleMetadataService.hasWritePermission(sampleMetadataId, userId)) {
            Long fileMetadataId = null;
            FileMetadata fileMetadata = null;

            if (file == null || file.isEmpty()) {
                throw new ServiceException(2); // 没有上传文件
            }

            File dir = new File(uploadDirectory, String.join("/", processId));
            if (!dir.exists() && !dir.mkdirs()) {
                throw new ServiceException(3); // 存储空间不足
            }

            String fileName = file.getOriginalFilename();
            String uniqueFileName = getUniqueFileName(dir.getAbsolutePath(), fileName);
            Path filePath = Paths.get(dir.getAbsolutePath(), uniqueFileName);
            try {
                Files.write(filePath, file.getBytes());
                fileMetadataId = fileMetadataService.create(uniqueFileName, file.getContentType(), file.getSize(), userId, LocalDateTime.now(), filePath.toString());
                fileMetadata = fileMetadataService.getById(fileMetadataId);
            } catch (IOException e) {
                throw new ServiceException(4); // 文件上传失败
            }

            // 更新样品元数据
            sampleMetadataService.addFileMetadata(sampleMetadataId, fileMetadataId);

            // 返回样品文件摘要
            return fileMetadata;

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
        SampleMetadata sampleMetadata = sampleMetadataService.getById(sampleMetadataId);

        // 获取当前登录用户，和当前样品的可读用户列表
        String userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUid();

//        sampleMetadata.addReadPermission(userId);
        // 判断当前登录用户是否具有下载权限
        if (sampleMetadataService.hasReadPermission(sampleMetadataId, userId)) {
            List<Long> fileMetadataIdList = sampleMetadata.getFileMetadataIdList();
            List<File> downloadedFiles = new ArrayList<>();

            // 下载样品文件
            for (Number fileMetadataId : fileMetadataIdList) {
                try {
                    FileMetadata fileMetadata = fileMetadataService.getById(fileMetadataId);
                    Path filePath = Paths.get(fileMetadata.getFilePath());
                    if (Files.exists(filePath)) {
                        // 创建一个临时文件用于存储下载的文件
                        String fileName = fileMetadata.getFileName();
                        String baseName = FilenameUtils.getBaseName(fileName);
                        String extension = FilenameUtils.getExtension(fileName);
                        File tempFile = File.createTempFile(baseName, extension.isEmpty() ? "" : "." + extension);
                        // 拷贝文件到临时文件中
                        Files.copy(filePath, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
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
                return createZipFile(processId, downloadedFiles, fileMetadataIdList);
            } catch (IOException e) {
                throw new ServiceException(3); // 压缩文件时发生错误（也导致文件下载失败）
            }

        } else {
            throw new ServiceException(1); // 下载权限的异常
        }
    }

    private File createZipFile(String processId, List<File> files, List<Long> fileMetadataIdList) throws IOException {
        String prefix = processId + "_";
        File zipFile = File.createTempFile(prefix, ".zip");

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))) {
            for (int i = 0; i < files.size(); i++) {
                File file = files.get(i);
                Long fileMetadataId = fileMetadataIdList.get(i);
                FileMetadata fileMetadata = fileMetadataService.getById(fileMetadataId);

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
        SampleMetadata sampleMetadata = sampleMetadataService.getById(sampleMetadataId);

        // 获取当前登录用户，和当前样品的可写用户列表
        String userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUid();
        List<String> writableUsers = sampleMetadata.getWritableUsers();

        // 判断当前登录用户是否具有删除权限
        if (writableUsers != null && writableUsers.contains(userId)) {
            List<Long> fileMetadataIdList = sampleMetadata.getFileMetadataIdList();
            for (Long fileMetadataId : fileMetadataIdList) {
                try {
                    FileMetadata fileMetadata = fileMetadataService.getById(fileMetadataId);
                    Path filePath = Paths.get(fileMetadata.getFilePath());
                    if (Files.exists(filePath)) {
                        // 删除文件
                        Files.delete(filePath);
                        // 删除文件元数据
                        fileMetadataService.removeById(fileMetadataId);
                        // 从样品元数据中删除文件元数据 ID
                        sampleMetadataService.removeFileMetadata(sampleMetadataId, fileMetadataId);
                    } else {
                        throw new ServiceException(2); // 文件不存在
                    }
                } catch (IOException e) {
                    throw new ServiceException(3); // 文件删除失败
                }
            }
        } else {
            throw new ServiceException(1); // 无删除权限的异常
        }
    }

    @Override
    public void addReadPermission(Long sampleMetadataId, String userId) {
        sampleMetadataService.addReadPermission(sampleMetadataId, userId);
    }

    @Override
    public void addWritePermission(Long sampleMetadataId, String userId) {
        sampleMetadataService.addWritePermission(sampleMetadataId, userId);
    }

    @Override
    public void removeWritePermission(Long sampleMetadataId, String userId) {
        sampleMetadataService.removeWritePermission(sampleMetadataId, userId);
    }

    @Override
    public Long createMetadata() {
        return sampleMetadataService.create();
    }

    @Override
    public Long createMetadata(List<String> users) {
        return sampleMetadataService.create(users);
    }

    private final String PATH_ROOT = "./data/files";

    private final String PATH_FORM = "/forms";

    @Override
    public Resource generateFormPdf(String processId, Form form, String formType) {
        String fileName = FormUtil.formName2Chinese(formType);  // 获取表单对应的中文文件名
        String filePathDoc = PATH_ROOT + "/" + processId + PATH_FORM + "/" + fileName + ".docx";
        String filePathPdf = PATH_ROOT + "/" + processId + PATH_FORM + "/" + fileName + ".pdf";
        File file = new File(filePathPdf);
        if (file.exists()) return new FileSystemResource(file);
        File docFile = FormUtil.replaceSpecialText(form, formType, filePathDoc);
        WordAndPdfUtil.word2Pdf(filePathDoc, filePathPdf);      // 将 docx 文件转换为 pdf
        //noinspection ResultOfMethodCallIgnored
        docFile.delete();                                       // 删除生成的中间 docx 文件
        return new FileSystemResource(filePathPdf);             // 从磁盘加载目标文件
    }

    @Override
    public void saveFormPdf(String processId, MultipartFile file, String formType) {
        String fileName = FormUtil.formName2Chinese(formType);     // 获取表单对应的中文文件名
        String filePath = PATH_ROOT + "/" + processId + PATH_FORM + "/" + fileName + "pdf";
        try {
            FileOutputStream fOS = new FileOutputStream(filePath); // 创建文件输出流
            fOS.write(file.getBytes());                            // 将数据写入目标文件
            fOS.close();                                           // 关闭文件输出流
        } catch (IOException e) {
            throw new ServerErrorException(e);
        }
    }
}