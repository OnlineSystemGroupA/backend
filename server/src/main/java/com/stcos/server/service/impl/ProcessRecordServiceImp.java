package com.stcos.server.service.impl;

import com.stcos.server.database.mongo.ProcessRecordRepository;
import com.stcos.server.model.process.ProcessRecord;
import com.stcos.server.service.ProcessRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 流程记录服务实现类
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/6/27 14:08
 */
@Service
public class ProcessRecordServiceImp implements ProcessRecordService {

    ProcessRecordRepository processRecordRepository;

    @Autowired
    void setProcessRecordMapper(ProcessRecordRepository processRecordRepository) {
        this.processRecordRepository = processRecordRepository;
    }

    public void saveProcessRecord(ProcessRecord processRecord){
        processRecordRepository.save(processRecord);
    }

    public ProcessRecord selectProcessRecordById(Long projectId){
        return processRecordRepository.findById(projectId).get();
    }
}
