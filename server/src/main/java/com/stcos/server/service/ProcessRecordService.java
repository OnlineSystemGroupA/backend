package com.stcos.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stcos.server.model.process.ProcessRecord;

/**
 * 这个服务接口提供了管理流程记录的方法
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/6/27 14:08
 */
public interface ProcessRecordService {

    public void saveProcessRecord(ProcessRecord processRecord);

    public ProcessRecord selectProcessRecordById(Long projectId);
}
