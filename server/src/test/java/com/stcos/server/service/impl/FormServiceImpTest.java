package com.stcos.server.service.impl;

import com.stcos.server.database.mongo.FormRepository;
import com.stcos.server.entity.form.Form;
import com.stcos.server.entity.form.FormMetadata;
import com.stcos.server.entity.form.FormType;
import com.stcos.server.entity.form.TestReportForm;
import com.stcos.server.entity.user.Client;
import com.stcos.server.exception.ServiceException;
import com.stcos.server.service.FormMetadataService;
import com.stcos.server.service.FormService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FormServiceImpTest {

    @Autowired
    private FormService formService;

    @Autowired
    private FormMetadataService formMetadataService;

    @Autowired
    private FormRepository formRepository;

    Long formMetadataId = null;

    TestReportForm form = null;

    Client mockUser = null;

    String testUid = "testUid";

    @BeforeEach
    void setUp() {
        // 准备测试数据
        formMetadataId = formMetadataService.create(1L, FormType.TYPE_TEST_REPORT_FORM);
        form = new TestReportForm();
        form.setSoftwareName("testSoftwareName");

        // 创建一个 User 对象
        mockUser = new Client("testUser", "testPassword", "testEmail");
        mockUser.setUid(testUid);

        // 创建一个模拟的 Authentication 对象
        Authentication authentication = Mockito.mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(mockUser);

        // 创建一个模拟的 SecurityContext 对象
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        // 将模拟的 SecurityContext 对象设置到 SecurityContextHolder 中
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void saveOrUpdateForm_WithoutWritePermission_ThrowsServiceException() {
        // 期望抛出 ServiceException
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            formService.saveOrUpdateForm(formMetadataId, form);
        });
        assertEquals(1, exception.getCode());
    }

    @Test
    void saveOrUpdateForm_WithWritePermission() throws ServiceException {
        // 模拟当前登录用户有修改权限的情况
        formMetadataService.addWritePermission(formMetadataId, testUid); // Test "addWritePermission()"

        formService.saveOrUpdateForm(formMetadataId, form);

        // 更新表单
        form.setSoftwareVersion("Version 1.0");

        formService.saveOrUpdateForm(formMetadataId, form);
    }

    @Test
    void getForm_WithoutReadPermission_ThrowsServiceException() throws Exception {
        // 期望抛出 ServiceException
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            formService.getForm(formMetadataId, mockUser.getUid());
        });
        assertEquals(1, exception.getCode());
    }

    @Test
    void getForm_WithReadPermission_ThrowsServiceException() throws Exception {
        // 模拟当前登录用户有修改权限的情况
        formService.addWritePermission(formMetadataId, testUid);

        formService.saveOrUpdateForm(formMetadataId, form);

        // 模拟当前登录用户有读取权限的情况
        formService.addReadPermission(formMetadataId, testUid);

        TestReportForm retrievedForm = (TestReportForm) formService.getForm(formMetadataId, mockUser.getUid());
        assertEquals(form.getSoftwareName(), retrievedForm.getSoftwareName());
    }

    @Test
    void existForm() throws ServiceException {
        formService.addWritePermission(formMetadataId, testUid);
        assertFalse(formService.existForm(formMetadataId));

        formService.saveOrUpdateForm(formMetadataId, form);
        assertTrue(formService.existForm(formMetadataId));
    }

    @Test
    void addWritePermission() {
        assertFalse(formService.hasWritePermission(formMetadataId, testUid));

        formService.addWritePermission(formMetadataId, testUid);
        assertTrue(formService.hasWritePermission(formMetadataId, testUid));
    }

    @Test
    void addReadPermission() {
        assertFalse(formService.hasReadPermission(formMetadataId, testUid));

        formService.addReadPermission(formMetadataId, testUid);
        assertTrue(formService.hasReadPermission(formMetadataId, testUid));
    }

    @Test
    void testAddReadPermission1() {
        assertFalse(formService.hasReadPermission(formMetadataId, "testUser1"));
        assertFalse(formService.hasReadPermission(formMetadataId, "testUser2"));

        Set<String> users = new HashSet<>();
        users.add("testUser1");
        users.add("testUser2");

        formService.addReadPermission(formMetadataId, users);

        assertTrue(formService.hasReadPermission(formMetadataId, "testUser1"));
        assertTrue(formService.hasReadPermission(formMetadataId, "testUser2"));
    }

    @Test
    void removeReadPermission() {
        Set<String> users = new HashSet<>();
        users.add("testUser1");
        users.add("testUser2");

        formService.addReadPermission(formMetadataId, users);

        assertTrue(formService.hasReadPermission(formMetadataId, "testUser1"));
        assertTrue(formService.hasReadPermission(formMetadataId, "testUser2"));

        formService.removeReadPermission(formMetadataId);

        assertFalse(formService.hasReadPermission(formMetadataId, "testUser1"));
        assertFalse(formService.hasReadPermission(formMetadataId, "testUser2"));
    }

    @Test
    void createMetadata() {
        Long projectId = 1L;
        String formType = FormType.TYPE_TEST_REPORT_FORM;

        Long createdFormMetadataId = formService.createMetadata(projectId, formType);

        FormMetadata retrievedFormMetadata = formMetadataService.getById(createdFormMetadataId);
        assertEquals(projectId, retrievedFormMetadata.getProjectId());
        assertEquals(formType, retrievedFormMetadata.getFormType());
    }

    @Test
    void removeWritePermission() {
        formService.addWritePermission(formMetadataId, testUid);
        assertTrue(formService.hasWritePermission(formMetadataId, testUid));

        formService.removeWritePermission(formMetadataId, testUid);
        assertFalse(formService.hasWritePermission(formMetadataId, testUid));
    }

    @Test
    void testRemoveWritePermission() {
        formService.addWritePermission(formMetadataId, testUid);
        assertTrue(formService.hasWritePermission(formMetadataId, testUid));

        formService.removeWritePermission(formMetadataId);
        assertFalse(formService.hasWritePermission(formMetadataId, testUid));
    }

    @Test
    void getForm() throws ServiceException {
        formService.addWritePermission(formMetadataId, testUid);
        formService.saveOrUpdateForm(formMetadataId, form);

        Form retrievedForm = formService.getForm(formMetadataId);
        assertEquals(form.getSoftwareName(), ((TestReportForm) retrievedForm).getSoftwareName());
    }

    @Test
    void hasWritePermission() {
        assertFalse(formService.hasWritePermission(formMetadataId, testUid));

        formService.addWritePermission(formMetadataId, testUid);
        assertTrue(formService.hasWritePermission(formMetadataId, testUid));
    }

    @Test
    void hasReadPermission() {
        assertFalse(formService.hasReadPermission(formMetadataId, testUid));

        formService.addReadPermission(formMetadataId, testUid);
        assertTrue(formService.hasReadPermission(formMetadataId, testUid));
    }
}