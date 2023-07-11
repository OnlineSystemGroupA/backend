package com.stcos.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stcos.server.entity.archive.ProcessRecord;
import com.stcos.server.exception.ServerErrorException;

/**
 * 这个服务接口提供了管理流程记录的方法
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/6/27 14:08
 */
public interface ProcessRecordService extends IService<ProcessRecord> {
    /**
     * 创建并保存新的 ProcessRecord 对象
     *
     * @return 新创建的 ProcessRecord 对象的 ID
     * @throws ServerErrorException 如果在保存新的 ProcessRecord 对象到数据库中出现问题
     */
    Long create();
}
