package com.stcos.server.database.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stcos.server.entity.process.ProcessDetails;
import com.stcos.server.entity.process.TaskDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessDetailsMapper extends BaseMapper<ProcessDetails> {
    List<TaskDetails> selectTaskListById(String id);
}