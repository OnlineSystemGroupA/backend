package com.stcos.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stcos.server.database.mysql.ProcessRecordMapper;
import com.stcos.server.entity.process.ProcessRecord;
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

    ProcessRecordMapper processRecordMapper;

    @Autowired
    void setProcessRecordMapper(ProcessRecordMapper processRecordMapper) {
        this.processRecordMapper = processRecordMapper;
    }

    public void saveProcessRecord(ProcessRecord processRecord){
        processRecordMapper.saveFull(processRecord);
    }

    public ProcessRecord selectProcessRecordById(Long projectId){
        return processRecordMapper.findAWithBAndC(projectId);
    }

}
