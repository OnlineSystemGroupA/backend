package com.stcos.server.database.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stcos.server.entity.process.ProcessDetails;
import com.stcos.server.entity.process.TaskDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessDetailsMapper extends BaseMapper<ProcessDetails> {

    ProcessDetails selectProcessDetails(Long projectId);

    List<TaskDetails> selectTaskDetailsByProcessId(Long processId);

    void saveProcess(ProcessDetails processDetails);

    void saveTaskDetails(List<TaskDetails> taskDetailsList);
}