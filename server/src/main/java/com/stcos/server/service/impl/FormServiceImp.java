package com.stcos.server.service.impl;

import com.stcos.server.config.security.User;
import com.stcos.server.entity.form.Form;
import com.stcos.server.entity.form.FormIndex;
import com.stcos.server.exception.ServiceException;
import com.stcos.server.mapper.FormMapper;
import com.stcos.server.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FormServiceImp implements FormService {
    private FormMapper formMapper;

    @Autowired
    public void setFormMapper(FormMapper formMapper) {
        this.formMapper = formMapper;
    }

    @Override
    public Form getForm(FormIndex formIndex) throws ServiceException {
        // 获取当前登录用户，和当前文件的可读用户列表
        String userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUid();
        List<String> readableUsers = formIndex.getReadableUsers();

        Form form;

        // 判断当前登录用户是否具有读取权限
        if (readableUsers != null && readableUsers.contains(userId)) {
            // 获取表单数据
            form = formIndex.getForm();
        } else {
            throw new ServiceException(1); // 无读取权限的异常
        }

        return form;
    }

    @Override
    public void updateForm(FormIndex formIndex, String formType, Form form) throws ServiceException {
        // 获取当前登录用户，和当前表单的可写用户列表
        String userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUid();
        List<String> writableUsers = formIndex.getWritableUsers();

        // 判断当前登录用户是否具有修改权限
        if (writableUsers != null && writableUsers.contains(userId)) {
            if(formIndex.getFormIndexId() == null) {
                // 保存表单
                formMapper.saveForm(form);

                // 初始化表单索引
                formIndex = new FormIndex(form.getFormId(), formType, userId, LocalDateTime.now(), userId, LocalDateTime.now(), form);
            } else {
                // 更新表单索引
                formIndex.setLastModifiedBy(userId); // 设置最后修改人为当前用户
                formIndex.setLastModifiedDate(LocalDateTime.now()); // 设置最后修改时间为当前时间
                formIndex.setForm(form); // 设置更新后的文件
            }
        } else {
            throw new ServiceException(1); // 无修改权限的异常
        }

        // 保存表单索引
        formMapper.saveFormIndex(formIndex);
    }
}
