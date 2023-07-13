package com.stcos.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stcos.server.database.mongo.ProcessRecordRepository;
import com.stcos.server.database.mysql.ProcessRecordMapper;
import com.stcos.server.model.process.ProcessRecord;
import com.stcos.server.service.ProcessRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/6/27 14:08
 */
@Service
public class ProcessRecordServiceImp extends ServiceImpl<ProcessRecordMapper, ProcessRecord> implements ProcessRecordService {

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
