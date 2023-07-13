package com.stcos.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stcos.server.database.mysql.ProcessDetailsMapper;
import com.stcos.server.model.process.ProcessDetails;
import com.stcos.server.service.ProcessDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private ProcessDetailsMapper processDetailsMapper;

    @Autowired
    public void setProcessDetailsMapper(ProcessDetailsMapper processDetailsMapper) {
        this.processDetailsMapper = processDetailsMapper;
    }

    @Override
    public void openTask(Long projectId, String taskName, String userName) {
        ProcessDetails processDetails = processDetailsMapper.selectProcessDetails(projectId);
        processDetails.openTask(taskName, userName);
        processDetailsMapper.saveProcessDetails(processDetails);
    }

    @Override
    public void closeTask(Long projectId, String taskName) {
        ProcessDetails processDetails = processDetailsMapper.selectProcessDetails(projectId);
        processDetails.closeTask(taskName);
        processDetailsMapper.saveProcessDetails(processDetails);
    }

    @Override
    public void update(Long projectId, String softwareName, String softwareVersion, List<String> testTypes, String startDate, String companyChineseName, String email, String address, String startUser, String telephone) {
        ProcessDetails processDetails = processDetailsMapper.selectProcessDetails(projectId);
        processDetails.update(softwareName,
                softwareVersion,
                testTypes.toString(),
                startDate,
                companyChineseName,
                email,
                address,
                startUser,
                telephone);
        updateById(processDetails);
    }

    @Override
    public Long create() {
        ProcessDetails processDetails = new ProcessDetails();
        save(processDetails);
        return processDetails.getProjectId();
    }
}
