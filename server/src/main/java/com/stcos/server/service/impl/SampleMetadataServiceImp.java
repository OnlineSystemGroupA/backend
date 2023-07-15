package com.stcos.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stcos.server.database.mysql.SampleMetadataMapper;
import com.stcos.server.model.file.SampleMetadata;
import com.stcos.server.exception.ServerErrorException;
import com.stcos.server.service.SampleMetadataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SampleMetadataServiceImp extends ServiceImpl<SampleMetadataMapper, SampleMetadata> implements SampleMetadataService {

    @Override
    public Long create() {
        SampleMetadata sampleMetadata = new SampleMetadata();
        if (!save(sampleMetadata)) throw new ServerErrorException(new RuntimeException("数据库写入错误！"));
        return sampleMetadata.getSampleMetadataId();
    }

    @Override
    public void addFileMetadata(Long sampleMetadataId, Long fileMetadataId) {
        SampleMetadata sampleMetadata = getById(sampleMetadataId);
        sampleMetadata.updateFileMetadataList(fileMetadataId);
        if (!updateById(sampleMetadata)) throw new ServerErrorException(new RuntimeException("数据库写入错误！"));
    }

    @Override
    public void removeFileMetadata(Long sampleMetadataId, Long fileMetadataId) {
        SampleMetadata sampleMetadata = getById(sampleMetadataId);
        sampleMetadata.getFileMetadataIdList().remove(fileMetadataId);
        if (!updateById(sampleMetadata)) throw new ServerErrorException(new RuntimeException("数据库写入错误！"));
    }

    @Override
    public void addReadPermission(Long sampleMetadataId, String userId) {
        SampleMetadata sampleMetadata = getById(sampleMetadataId);
        sampleMetadata.addReadPermission(userId);
        if (!updateById(sampleMetadata)) throw new ServerErrorException(new RuntimeException("数据库写入错误！"));
    }

    @Override
    public void addWritePermission(Long sampleMetadataId, String userId) {
        SampleMetadata sampleMetadata = getById(sampleMetadataId);
        sampleMetadata.addWritePermission(userId);
        if (!updateById(sampleMetadata)) throw new ServerErrorException(new RuntimeException("数据库写入错误！"));
    }

    @Override
    public void removeWritePermission(Long sampleMetadataId, String userId) {
        SampleMetadata sampleMetadata = getById(sampleMetadataId);
        sampleMetadata.removeWritePermission(userId);
        if (!updateById(sampleMetadata)) throw new ServerErrorException(new RuntimeException("数据库写入错误！"));
    }

    @Override
    public boolean hasReadPermission(Long sampleMetadataId, String userId) {
        SampleMetadata sampleMetadata = getById(sampleMetadataId);
        return sampleMetadata.hasReadPermission(userId);
    }

    @Override
    public boolean hasWritePermission(Long sampleMetadataId, String userId) {
        SampleMetadata sampleMetadata = getById(sampleMetadataId);
        return sampleMetadata.hasWritePermission(userId);
    }

    @Override
    public Long create(List<String> users) {
        SampleMetadata sampleMetadata = new SampleMetadata();
        sampleMetadata.addReadPermission(users);
        if (!save(sampleMetadata)) throw new ServerErrorException(new RuntimeException("数据库写入错误！"));
        return sampleMetadata.getSampleMetadataId();
    }
}
