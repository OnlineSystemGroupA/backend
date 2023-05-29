package com.stcos.server.service.impl;

import com.stcos.server.config.security.User;
import com.stcos.server.database.mongo.FormRepository;
import com.stcos.server.entity.form.Form;
import com.stcos.server.entity.form.FormMetadata;
import com.stcos.server.exception.ServiceException;
import com.stcos.server.database.mongo.FormMetadataRepository;
import com.stcos.server.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FormServiceImp implements FormService {
    private FormMetadataRepository formMetadataRepository;

    @Autowired
    public void setFormMetadataRepositoryRepository(FormMetadataRepository formMetadataRepository) {
        this.formMetadataRepository = formMetadataRepository;
    }

    private FormRepository formRepository;

    @Autowired
    public void setFormRepository(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    @Override
    public Form getForm(Long formMetadataId) throws ServiceException {
        // 根据表单元数据 ID 查询数据库
        FormMetadata formMetadata = formMetadataRepository.selectFormMetadataById(formMetadataId);

        // 获取当前登录用户
        String userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUid();
        // Test: String userId = "readableUser";

        // 判断当前登录用户是否具有读取权限
        if (formMetadata.hasReadPermission(userId)) {
            // 获取表单数据
            return formRepository.selectFormById(formMetadata.getFormId());
        } else {
            throw new ServiceException(1); // 无读取权限的异常
        }
    }

    @Override
    public void updateForm(Long formMetadataId, String formType, Form form) throws ServiceException {
        // 根据表单元数据 ID 查询数据库
        FormMetadata formMetadata = formMetadataRepository.selectFormMetadataById(formMetadataId);

        // 获取当前登录用户
        String userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUid();
        // Test: String userId = "writableUser";

        // 判断当前登录用户是否具有修改权限
        if (formMetadata.hasWritePermission(userId)) {
            // 保存表单
            if (!form.isSavedInDatabase()) {
                formRepository.saveForm(form); // 数据库首次保存
            } else {
                formRepository.updateForm(form); // 数据库更新
            }

            // 保存表单元数据
            if (!formMetadata.isSavedInDatabase()) {
                formMetadata.setFormId(form.getFormId());
                formMetadata.setFormType(formType);
                formMetadata.setCreatedBy(userId);
                formMetadata.setCreatedDate(LocalDateTime.now());
                formMetadata.setLastModifiedBy(userId);
                formMetadata.setLastModifiedDate(LocalDateTime.now());

                formMetadataRepository.saveFormMetadata(formMetadata); // 数据库首次保存

            } else {
                formMetadata.setLastModifiedBy(userId);
                formMetadata.setLastModifiedDate(LocalDateTime.now());

                formMetadataRepository.updateFormMetadata(formMetadata); // 数据库更新

                // Test: System.out.println("Updating");
            }

            // Test: System.out.println("formMetadataId: " + formMetadata.getFormMetadataId());
        } else {
            throw new ServiceException(1); // 无修改权限的异常
        }
    }
}