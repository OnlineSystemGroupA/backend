package com.stcos.server.service.impl;

import com.stcos.server.model.file.FileMetadata;
import com.stcos.server.model.file.SampleMetadata;
import com.stcos.server.model.form.Form;
import com.stcos.server.model.form.FormType;
import com.stcos.server.model.form.TestReportForm;
import com.stcos.server.model.user.Client;
import com.stcos.server.exception.ServiceException;
import com.stcos.server.service.FileMetadataService;
import com.stcos.server.service.FileService;
import com.stcos.server.service.SampleMetadataService;
import com.stcos.server.util.FormUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
@Rollback
class FileServiceImpTest {

    @Autowired
    private FileService fileService;

    @Autowired
    private FileMetadataService fileMetadataService;

    @Autowired
    private SampleMetadataService sampleMetadataService;

    private final String testOriginalFileName = "test.txt";
    private final String testContentType = "text/plain";
    private final String testProcessId = "testProcessId";
    private MultipartFile testMultipartFile;
    private Client mockUser;
    private SampleMetadata sampleMetadata;

    @BeforeEach
    void setUp() throws IOException {
        // Create a User object
        mockUser = new Client("testUser", "testPassword", "testEmail@test.com");
        mockUser.setUid("testUid");

        // Create a mock Authentication object
        Authentication authentication = Mockito.mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(mockUser);

        // Create a mock SecurityContext object
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        // Set the mock SecurityContext object to SecurityContextHolder
        SecurityContextHolder.setContext(securityContext);

        // Set up a real file for testing
        Path tempFilePath = Files.createTempFile("temp", ".txt");
        Files.write(tempFilePath, "Test content".getBytes()); // Write something into the file
        InputStream is = Files.newInputStream(tempFilePath);
        testMultipartFile = new MockMultipartFile(testOriginalFileName, testOriginalFileName, testContentType, is);

        // Create a sample metadata
        sampleMetadata = new SampleMetadata();
        sampleMetadataService.save(sampleMetadata);
    }

    @Test
    void uploadSampleSuccessful() throws Exception {
        // Add write permission
        sampleMetadataService.addWritePermission(sampleMetadata.getSampleMetadataId(), mockUser.getUid());

        // Call the method under test
        FileMetadata fileMetadata = fileService.uploadSample(testProcessId, sampleMetadata.getSampleMetadataId(), testMultipartFile);

        // Validate the results
        assertNotNull(fileMetadata);
        assertEquals(mockUser.getUid(), fileMetadata.getUpdatedBy());
        assertEquals(1, sampleMetadataService.getById(sampleMetadata.getSampleMetadataId()).getFileMetadataIdList().size());

        // Verify that the file is stored correctly
        Path path = Paths.get(fileMetadata.getFilePath());
        assertTrue(Files.exists(path)); // Check if the file exists

        fileService.deleteSample(sampleMetadata.getSampleMetadataId());
    }

    @Test
    void uploadSampleWithInvalidPermission() {
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            fileService.uploadSample(testProcessId, sampleMetadata.getSampleMetadataId(), testMultipartFile);
        });
        assertEquals(1, exception.getCode());
    }

    @Test
    void uploadSampleWithNoFile() {
        // Add write permission
        sampleMetadataService.addWritePermission(sampleMetadata.getSampleMetadataId(), mockUser.getUid());

        // Create an empty file
        MultipartFile emptyFile = new MockMultipartFile(testOriginalFileName, testOriginalFileName, testContentType, new byte[0]);

        ServiceException exception = assertThrows(ServiceException.class, () -> {
            fileService.uploadSample(testProcessId, sampleMetadata.getSampleMetadataId(), emptyFile);
        });
        assertEquals(2, exception.getCode());
    }

    @Test
    void downloadSampleSuccessful() throws ServiceException, IOException {
        // Add write permission
        sampleMetadataService.addWritePermission(sampleMetadata.getSampleMetadataId(), mockUser.getUid());

        // Call the method under test
        FileMetadata fileMetadata = fileService.uploadSample(testProcessId, sampleMetadata.getSampleMetadataId(), testMultipartFile);

        // Add read permission
        sampleMetadataService.addReadPermission(sampleMetadata.getSampleMetadataId(), mockUser.getUid());

        // Verify that the file is stored correctly
        Path path = Paths.get(fileMetadata.getFilePath());
        assertTrue(Files.exists(path)); // Check if the file exists

        // Download the sample
        File zipFile = assertDoesNotThrow(() -> fileService.downloadSample(testProcessId, sampleMetadata.getSampleMetadataId()));

        // Prepare the directory to unzip the file
        Path unzipDir = Files.createTempDirectory("unzip");
        try (ZipFile zip = new ZipFile(zipFile)) {
            // Extract all entries
            for (ZipEntry entry : Collections.list(zip.entries())) {
                path = unzipDir.resolve(entry.getName());
                Files.copy(zip.getInputStream(entry), path, StandardCopyOption.REPLACE_EXISTING);
            }
        }

        List<String> fileNames = Files.list(unzipDir)
                .map(Path::getFileName)
                .map(Path::toString)
                .toList();
        assertEquals(1, fileNames.size());

        SampleMetadata retrievedSampleMetadata = sampleMetadataService.getById(sampleMetadata.getSampleMetadataId());
        assertEquals(1, retrievedSampleMetadata.getFileMetadataIdList().size());

        for (Long fileMetadataId : retrievedSampleMetadata.getFileMetadataIdList()) {
            FileMetadata metadata = fileMetadataService.getById(fileMetadataId);
            assertTrue(fileNames.contains(metadata.getFileName()));
        }

        fileService.deleteSample(sampleMetadata.getSampleMetadataId());
    }

    @Test
    void downloadSampleWithInvalidPermission() {
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            fileService.downloadSample(testProcessId, sampleMetadata.getSampleMetadataId());
        });
        assertEquals(1, exception.getCode());
    }

    @Test
    void downloadSampleFileDoesNotExist() throws ServiceException {
        // Add write permission
        sampleMetadataService.addWritePermission(sampleMetadata.getSampleMetadataId(), mockUser.getUid());

        // Call the method under test
        FileMetadata fileMetadata = fileService.uploadSample(testProcessId, sampleMetadata.getSampleMetadataId(), testMultipartFile);

        FileMetadata mockFileMetadata = new FileMetadata("mockFileName", "mockFileType", 123L, "mockUpdatedBy", LocalDateTime.now(), "mockFilePath");
        fileMetadataService.save(mockFileMetadata);
        sampleMetadataService.addFileMetadata(sampleMetadata.getSampleMetadataId(), mockFileMetadata.getFileMetadataId());
        
        // Add read permission
        sampleMetadataService.addReadPermission(sampleMetadata.getSampleMetadataId(), mockUser.getUid());

        ServiceException exception = assertThrows(ServiceException.class, () -> {
            fileService.downloadSample(testProcessId, sampleMetadata.getSampleMetadataId());
        });
        assertEquals(2, exception.getCode());

        sampleMetadataService.removeFileMetadata(sampleMetadata.getSampleMetadataId(), mockFileMetadata.getFileMetadataId());
        fileService.deleteSample(sampleMetadata.getSampleMetadataId());
    }

    @Test
    void deleteSampleSuccessful() throws ServiceException {
        // Add write permission
        sampleMetadataService.addWritePermission(sampleMetadata.getSampleMetadataId(), mockUser.getUid());

        // Call the method under test
        FileMetadata fileMetadata = fileService.uploadSample(testProcessId, sampleMetadata.getSampleMetadataId(), testMultipartFile);

        // Verify that the file is stored correctly
        Path path = Paths.get(fileMetadata.getFilePath());
        assertTrue(Files.exists(path)); // Check if the file exists

        assertDoesNotThrow(() -> {
            fileService.deleteSample(sampleMetadata.getSampleMetadataId());
        });

        // Verify that the deleted file does not exist
        assertFalse(Files.exists(path)); // Check if the file exists
    }

    @Test
    void deleteSampleNoPermission() {
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            fileService.deleteSample(sampleMetadata.getSampleMetadataId());
        });
        assertEquals(1, exception.getCode());
    }

    @Test
    void deleteSampleFileDoesNotExist() throws ServiceException {
        // Add write permission
        sampleMetadataService.addWritePermission(sampleMetadata.getSampleMetadataId(), mockUser.getUid());

        // Call the method under test
        FileMetadata fileMetadata = fileService.uploadSample(testProcessId, sampleMetadata.getSampleMetadataId(), testMultipartFile);

        FileMetadata mockFileMetadata = new FileMetadata("mockFileName", "mockFileType", 123L, "mockUpdatedBy", LocalDateTime.now(), "mockFilePath");
        fileMetadataService.save(mockFileMetadata);
        sampleMetadataService.addFileMetadata(sampleMetadata.getSampleMetadataId(), mockFileMetadata.getFileMetadataId());

        ServiceException exception = assertThrows(ServiceException.class, () -> {
            fileService.deleteSample(sampleMetadata.getSampleMetadataId());
        });
        assertEquals(2, exception.getCode());

        sampleMetadataService.removeFileMetadata(sampleMetadata.getSampleMetadataId(), mockFileMetadata.getFileMetadataId());
        assertDoesNotThrow(() -> fileService.deleteSample(sampleMetadata.getSampleMetadataId()));
    }

    @Test
    void addReadPermission() {
        assertFalse(sampleMetadataService.hasWritePermission(sampleMetadata.getSampleMetadataId(), mockUser.getUid()));

        fileService.addWritePermission(sampleMetadata.getSampleMetadataId(), mockUser.getUid());
        assertTrue(sampleMetadataService.hasWritePermission(sampleMetadata.getSampleMetadataId(), mockUser.getUid()));
    }

    @Test
    void addWritePermission() {
        assertFalse(sampleMetadataService.hasReadPermission(sampleMetadata.getSampleMetadataId(), mockUser.getUid()));

        fileService.addReadPermission(sampleMetadata.getSampleMetadataId(), mockUser.getUid());
        assertTrue(sampleMetadataService.hasReadPermission(sampleMetadata.getSampleMetadataId(), mockUser.getUid()));
    }

    @Test
    void removeWritePermission() {
        fileService.addWritePermission(sampleMetadata.getSampleMetadataId(), mockUser.getUid());
        assertTrue(sampleMetadataService.hasWritePermission(sampleMetadata.getSampleMetadataId(), mockUser.getUid()));

        fileService.removeWritePermission(sampleMetadata.getSampleMetadataId(), mockUser.getUid());
        assertFalse(sampleMetadataService.hasWritePermission(sampleMetadata.getSampleMetadataId(), mockUser.getUid()));
    }

    @Test
    void createMetadata() {
        Long sampleMetadataId = fileService.createMetadata();
        assertNotNull(sampleMetadataId);
    }

    @Test
    void testCreateMetadata() {
        List<String> users = new ArrayList<>();
        users.add("testUser1");
        users.add("testUser2");

        Long sampleMetadataId = fileService.createMetadata(users);
        List<String> readableUsers = sampleMetadataService.getById(sampleMetadataId).getReadableUsers();
        assertTrue(readableUsers.contains("testUser1"));
        assertTrue(readableUsers.contains("testUser2"));
    }

    private final String PATH_ROOT = "./data/files";

    private final String PATH_FORM = "/forms";

    @Test
    void generateFormPdf() throws IOException {
        Path targetDirPath = Paths.get(PATH_ROOT, testProcessId, PATH_FORM);
        Files.createDirectories(targetDirPath);

        TestReportForm form = new TestReportForm();  // Initialize this with appropriate test data
        String formType = FormType.TYPE_TEST_REPORT_FORM;
        String formName = FormUtil.formType2Chinese(FormType.TYPE_TEST_REPORT_FORM);
        String docxPath = Paths.get(targetDirPath.toString(), formName + ".docx").toString();
        String pdfPath = Paths.get(targetDirPath.toString(), formName + ".pdf").toString();

        // Create the DOCX file
        File docxFile = new File(docxPath);
        if (docxFile.exists()) {
            docxFile.delete();
        }
        assertTrue(docxFile.createNewFile());

        // Call the method
        Resource result = fileService.generateFormPdf(testProcessId, form, formType);

        // The PDF file should be created
        File pdfFile = new File(pdfPath);
        assertTrue(pdfFile.exists());

        // The returned resource should be the PDF file
        assertEquals(pdfFile.getAbsolutePath(), result.getFile().getAbsolutePath());

        tearDown(targetDirPath);
    }

    @Test
    void saveFormPdf() throws IOException {
        Path sourceDirPath = Path.of(PATH_ROOT + "/" + testProcessId + PATH_FORM);
        Files.createDirectories(sourceDirPath);
        Path targetDirPath = Path.of(PATH_ROOT + "/" + testProcessId + "Mock" + PATH_FORM);
        Files.createDirectories(targetDirPath);

        TestReportForm form = new TestReportForm();
        String formType = FormType.TYPE_TEST_REPORT_FORM;

        Resource result = fileService.generateFormPdf(testProcessId, form, formType);
        MultipartFile multipartFile = new MockMultipartFile(
                "file",
                result.getFilename(),
                "pdf/application",
                result.getInputStream()
        );

        fileService.saveFormPdf(testProcessId + "Mock", multipartFile, formType);

        byte[] file1 = Files.readAllBytes(Paths.get(sourceDirPath.toString(), FormUtil.formType2Chinese(FormType.TYPE_TEST_REPORT_FORM) + ".pdf"));
        byte[] file2 = Files.readAllBytes(Paths.get(targetDirPath.toString(), FormUtil.formType2Chinese(FormType.TYPE_TEST_REPORT_FORM) + ".pdf"));

        assertArrayEquals(file1, file2);
        
        tearDown(sourceDirPath);
        tearDown(targetDirPath);
    }

    void tearDown(Path tempDir) {
        // Delete temporary directory and all its contents
        try {
            Files.walk(tempDir)
                    .map(Path::toFile)
                    .sorted((o1, o2) -> -o1.compareTo(o2))
                    .forEach(File::delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}