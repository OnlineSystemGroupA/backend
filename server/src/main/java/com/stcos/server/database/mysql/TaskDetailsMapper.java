package com.stcos.server.database.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stcos.server.model.process.TaskDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDetailsMapper extends BaseMapper<TaskDetails> {
}
