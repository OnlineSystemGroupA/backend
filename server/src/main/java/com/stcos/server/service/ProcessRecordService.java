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

    /**
     * 保存流程记录
     *
     * @param processRecord 流程记录
     */
    public void saveProcessRecord(ProcessRecord processRecord);

    /**
     * 根据项目 ID 获取流程记录
     *
     * @param projectId 项目 ID
     * @return 流程记录
     */
    public ProcessRecord selectProcessRecordById(Long projectId);
}
