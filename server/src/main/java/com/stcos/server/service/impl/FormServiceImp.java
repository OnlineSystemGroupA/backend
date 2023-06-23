package com.stcos.server.service.impl;

import com.stcos.server.entity.user.User;
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
        FormMetadata formMetadata = formMetadataRepository.findByFormMetadataId(formMetadataId);

        // 获取当前登录用户
        String userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUid();
        // Test: String userId = "readableUser";

        // 判断当前登录用户是否具有读取权限
        if (formMetadata.hasReadPermission(userId)) {
            // 获取表单数据
            return formRepository.findByFormId(formMetadata.getFormId());
        } else {
            throw new ServiceException(1); // 无读取权限的异常
        }
    }

    @Override
    public void updateForm(Long formMetadataId, String formType, Form form) throws ServiceException {
        // 根据表单元数据 ID 查询数据库
        FormMetadata formMetadata = formMetadataRepository.findByFormMetadataId(formMetadataId);

        // 获取当前登录用户
        String userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUid();
        // Test: String userId = "writableUser";

        // 判断当前登录用户是否具有修改权限
        if (formMetadata.hasWritePermission(userId)) {
            // 保存表单
            formRepository.saveForm(form);

            if(formMetadata.getFormMetadataId() == -1) {
                // 初始化表单元数据
//                formMetadata = new FormMetadata(form.getFormId(), formType, userId, LocalDateTime.now(), userId, LocalDateTime.now());
            } else {
                // 更新表单元数据

                // Test: System.out.println("Updating");

                formMetadata.setLastModifiedBy(userId); // 设置最后修改人为当前用户
                formMetadata.setLastModifiedDate(LocalDateTime.now()); // 设置最后修改时间为当前时间
            }

            // 保存表单元数据
            formMetadataRepository.saveFormMetadata(formMetadata);

            // Test: System.out.println("formMetadataId: " + formMetadata.getFormMetadataId());
        } else {
            throw new ServiceException(1); // 无修改权限的异常
        }
    }

    /**
     * 为用户赋予指定表单的写权限
     *
     * @param formMetadataId 指定表单对应表单元数据的 ID
     * @param uid 需要获取写权限的用户 ID
     */
    @Override
    public void addWritePermission(long formMetadataId, String uid) {
        FormMetadata formMetadata = formMetadataRepository.findByFormMetadataId(formMetadataId);
        formMetadata.addWritePermission(uid);
        formMetadataRepository.saveFormMetadata(formMetadata);
    }

    @Override
    public void removeWritePermission(long formMetadataId, String uid) {
        FormMetadata formMetadata = formMetadataRepository.findByFormMetadataId(formMetadataId);
        formMetadata.removeWritePermission(uid);
        formMetadataRepository.saveFormMetadata(formMetadata);
    }

    @Override
    public void saveFormMetadata(FormMetadata formMetadata) {

    }

    @Override
    public boolean existForm(long formMetadataId) {
        return false;
    }
}