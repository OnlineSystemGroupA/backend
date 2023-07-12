package com.stcos.server.service.impl;

import com.stcos.server.database.mongo.FormRepository;
import com.stcos.server.model.form.Form;
import com.stcos.server.model.form.FormMetadata;
import com.stcos.server.model.user.User;
import com.stcos.server.exception.ServiceException;
import com.stcos.server.service.FormMetadataService;
import com.stcos.server.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class FormServiceImp implements FormService {
    private FormMetadataService formMetadataService;

    @Autowired
    public void setFormMetadataService(FormMetadataService formMetadataService) {
        this.formMetadataService = formMetadataService;
    }

    private FormRepository formRepository;

    @Autowired
    public void setFormRepository(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    @Override
    public Form getForm(Long formMetadataId, String userId) throws ServiceException {
        // 根据表单元数据 ID 查询数据库
        FormMetadata formMetadata = formMetadataService.getById(formMetadataId);

        // 判断当前登录用户是否具有读取权限
        if (formMetadata.hasReadPermission(userId)) {
            // 获取表单数据
            return formRepository.getFormById(formMetadata.getFormId());
        } else {
            throw new ServiceException(1); // 无读取权限的异常
        }
    }

    @Override
    public void saveOrUpdateForm(Long formMetadataId, Form form) throws ServiceException {
        // 根据表单元数据 ID 查询数据库
        FormMetadata formMetadata = formMetadataService.getById(formMetadataId);

        // 获取当前登录用户
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 判断当前登录用户是否具有修改权限
        if (formMetadata.hasWritePermission(user.getUid())) {

            if (formMetadata.getFormId() == -1) {
                // 新建表单
                formRepository.save(form);
                // 设置表单元数据中表单 ID 字段
                formMetadata.setFormId(form.getFormId());
                // 设置表单创建人与表单创建时间
                formMetadata.setCreatedBy(user.getUid());
                formMetadata.setCreatedDate(LocalDateTime.now());

            } else {
                // 更新表单
                form.setFormId(formMetadata.getFormId());
                formRepository.save(form);
                formMetadata.setLastModifiedBy(user.getUid());          // 设置最后修改人为当前用户
                formMetadata.setLastModifiedDate(LocalDateTime.now());  // 设置最后修改时间为当前时间
            }
            // 将表单元数据写回数据库
            formMetadataService.updateById(formMetadata);
        } else {
            throw new ServiceException(1); // 无修改权限的异常
        }

    }

    @Override
    public void saveFormMetadata(FormMetadata formMetadata) {

    }

    @Override
    public boolean existForm(long formMetadataId) {
        return formMetadataService.existForm(formMetadataId);
    }

    @Override
    public void addWritePermission(Long formMetadataId, String userId) {
        formMetadataService.addWritePermission(formMetadataId, userId);
    }

    @Override
    public void addReadPermission(Long formMetadataId, String userId) {
        formMetadataService.addReadPermission(formMetadataId, userId);
    }

    @Override
    public void addReadPermission(Long formMetadataId, Set<String> userIds) {
        formMetadataService.addReadPermission(formMetadataId, userIds);
    }

    @Override
    public void removeReadPermission(Long formMetadataId) {
        formMetadataService.removeReadPermission(formMetadataId);
    }

    @Override
    public Long createMetadata(Long projectId, String formType) {
        return formMetadataService.create(projectId, formType);
    }

    @Override
    public void removeWritePermission(Long formMetadataId, String userId) {
        formMetadataService.removeWritePermission(formMetadataId, userId);
    }

    @Override
    public void removeWritePermission(Long formMetadataId) {
        formMetadataService.removeWritePermission(formMetadataId);
    }

    @Override
    public Form getForm(Long metadataId) {
        Long formId = formMetadataService.getFormId(metadataId);
        return formRepository.getFormById(formId);
    }

    @Override
    public boolean hasWritePermission(Long formMetadataId, String userId) {
        FormMetadata formMetadata = formMetadataService.getById(formMetadataId);
        return formMetadata.hasWritePermission(userId);
    }

    @Override
    public boolean hasReadPermission(Long formMetadataId, String userId) {
        FormMetadata formMetadata = formMetadataService.getById(formMetadataId);
        return formMetadata.hasReadPermission(userId);
    }

    @Override
    public LocalDateTime getCreatedDate(Long formMetadataId) {
        FormMetadata formMetadata = formMetadataService.getById(formMetadataId);
        return formMetadata.getCreatedDate();
    }

    @Override
    public String getFormState(Long formMetadataId) {
        FormMetadata formMetadata = formMetadataService.getById(formMetadataId);
        return formMetadata.getFormState();
    }

    @Override
    public void setFormState(Long formMetadataId, String formState) {
        formMetadataService.setFormState(formMetadataId, formState);
    }
}