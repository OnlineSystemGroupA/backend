package com.stcos.server.service.impl;

import com.stcos.server.config.security.User;
import com.stcos.server.entity.form.Form;
import com.stcos.server.entity.form.FormMetadata;
import com.stcos.server.exception.ServiceException;
import com.stcos.server.repository.FormMetadataRepository;
import com.stcos.server.repository.FormRepository;
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
    public Form getForm(FormMetadata formMetadata) throws ServiceException {
        // 获取当前登录用户
//        String userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUid();
        String userId = "111";

        // 判断当前登录用户是否具有读取权限
        if (formMetadata.hasReadPermission(userId)) {
            // 获取表单数据
            return formRepository.findByFormId(formMetadata.getFormId());
        } else {
            throw new ServiceException(1); // 无读取权限的异常
        }
    }

    @Override
    public void updateForm(FormMetadata formMetadata, String formType, Form form) throws ServiceException {
        // 获取当前登录用户
//        String userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUid();
        String userId = "112";

        // 判断当前登录用户是否具有修改权限
        if (formMetadata.hasWritePermission(userId)) {
            // 保存表单
            formRepository.saveFrom(form);

            if(formMetadata.getFormMetadataId() == null) {
                // 初始化表单元数据
                formMetadata = new FormMetadata(form.getFormId(), formType, userId, LocalDateTime.now(), userId, LocalDateTime.now());
            } else {
                // 更新表单元数据
                formMetadata.setLastModifiedBy(userId); // 设置最后修改人为当前用户
                formMetadata.setLastModifiedDate(LocalDateTime.now()); // 设置最后修改时间为当前时间
            }

            // 保存表单元数据
            formMetadataRepository.saveFormMetadata(formMetadata);
        } else {
            throw new ServiceException(1); // 无修改权限的异常
        }
    }
}