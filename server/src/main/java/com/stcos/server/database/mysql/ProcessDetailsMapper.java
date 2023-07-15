package com.stcos.server.database.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stcos.server.model.process.ProcessDetails;
import com.stcos.server.model.process.TaskDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 定义了对 ProcessDetails 实体在 MySQL 数据库中进行操作的一些方法
 * 使用了 MyBatis 的 BaseMapper 进行基础 SQL 语句的配置，同时定义了一些 ProcessDetails 实体特有的数据库操作方法
 */

@Repository
public interface ProcessDetailsMapper extends BaseMapper<ProcessDetails> {

    /**
     * 根据项目 ID 查询对应的 ProcessDetails
     *
     * @param projectId 项目 ID
     * @return 对应的 ProcessDetails 实体，如果没有找到则返回 null
     */
    ProcessDetails selectProcessDetails(Long projectId);

    /**
     * 根据流程 ID 查询对应的 TaskDetails 列表
     *
     * @param processId 流程 ID
     * @return 对应的 TaskDetails 列表，如果没有找到则返回空列表
     */
    List<TaskDetails> selectTaskDetailsByProcessId(Long processId);

    /**
     * 保存 ProcessDetails 实体到数据库
     *
     * @param processDetails 需要保存的 ProcessDetails 实体
     */
    void saveProcessDetails(ProcessDetails processDetails);
}
