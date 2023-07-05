package com.stcos.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stcos.server.database.mysql.ProcessDetailsMapper;
import com.stcos.server.entity.process.ProcessDetails;
import com.stcos.server.entity.process.TaskDetails;
import com.stcos.server.exception.ServiceException;
import com.stcos.server.service.ProcessDetailsService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/7/5 15:07
 */
@Component
public class ProcessDetailsServiceImp extends ServiceImpl<ProcessDetailsMapper, ProcessDetails> implements ProcessDetailsService {

    @Override
    public boolean addTaskDetails(String processInstanceId, TaskDetails taskDetails) throws ServiceException {
        return false;
    }

    @Override
    public void openTask(Long projectId, String taskName, String userName) {
        ProcessDetails processDetails = getById(projectId);
        processDetails.openTask(taskName, userName);
        updateById(processDetails);
    }

    @Override
    public void closeTask(Long projectId, String taskName) {
        ProcessDetails processDetails = getById(projectId);
        processDetails.closeTask(taskName);
        updateById(processDetails);
    }

    @Override
    public void update(Long projectId, String softwareName, String softwareVersion, List<String> testTypes, String startDate, String companyChineseName, String email, String address) {
        ProcessDetails processDetails = getById(projectId);
        processDetails.update(softwareName,
                softwareVersion,
                testTypes.toString(),
                startDate,
                companyChineseName,
                email,
                address);
        updateById(processDetails);
    }

    @Override
    public Long create() {
        ProcessDetails processDetails = new ProcessDetails();
        save(processDetails);
        return processDetails.getProjectId();
    }

}