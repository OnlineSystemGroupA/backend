package com.stcos.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stcos.server.database.mysql.ProcessRecordMapper;
import com.stcos.server.entity.archive.ProcessRecord;
import com.stcos.server.exception.ServerErrorException;
import com.stcos.server.service.ProcessRecordService;
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
    @Override
    public Long create() {
        ProcessRecord processRecord = new ProcessRecord();
        if(!save(processRecord)) throw new ServerErrorException(new RuntimeException());
        return processRecord.getId();
    }
}
