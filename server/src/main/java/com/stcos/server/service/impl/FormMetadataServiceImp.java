package com.stcos.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stcos.server.database.mysql.FormMetadataMapper;
import com.stcos.server.entity.form.FormMetadata;
import com.stcos.server.exception.ServerErrorException;
import com.stcos.server.service.FormMetadataService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/6/25 13:45
 */

@Service
public class FormMetadataServiceImp extends ServiceImpl<FormMetadataMapper, FormMetadata> implements FormMetadataService {


    @Override
    public Long create(String formName) {
        FormMetadata formMetadata = new FormMetadata(formName);
        if (!save(formMetadata)) throw new ServerErrorException(new RuntimeException("数据库写入错误！"));
        return formMetadata.getFormMetadataId();
    }

    @Override
    public void addReadPermission(Long formMetadataId, String userId) {
        FormMetadata formMetadata = getById(formMetadataId);
        formMetadata.addReadPermission(userId);
        if (!updateById(formMetadata)) throw new ServerErrorException(new RuntimeException("数据库写入错误！"));
    }

    @Override
    public void addWritePermission(Long formMetadataId, String userId) {
        FormMetadata formMetadata = getById(formMetadataId);
        formMetadata.addWritePermission(userId);
        if (!updateById(formMetadata)) throw new ServerErrorException(new RuntimeException("数据库写入错误！"));
    }

    @Override
    public void removeWritePermission(Long formMetadataId, String userId) {
        FormMetadata formMetadata = getById(formMetadataId);
        formMetadata.removeWritePermission(userId);
        if (!updateById(formMetadata)) throw new ServerErrorException(new RuntimeException("数据库写入错误！"));
    }

    @Override
    public boolean existForm(long formMetadataId) {
        return getById(formMetadataId).getFormId() != -1;
    }

    @Override
    public Long getFormId(Long formMetadataId) {
        return getById(formMetadataId).getFormId();
    }

    @Override
    public Long create(String formName, List<String> users) {
        FormMetadata formMetadata = new FormMetadata(formName);
        formMetadata.addReadPermission(users);
        if (!save(formMetadata)) throw new ServerErrorException(new RuntimeException("数据库写入错误！"));
        return formMetadata.getFormMetadataId();
    }
}
