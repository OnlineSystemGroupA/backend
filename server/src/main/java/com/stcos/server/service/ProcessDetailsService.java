package com.stcos.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stcos.server.entity.process.ProcessDetails;
import com.stcos.server.entity.process.TaskDetails;
import com.stcos.server.exception.ServiceException;

import java.util.List;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/20 14:03
 */
public interface ProcessDetailsService extends IService<ProcessDetails> {

    void openTask(Long projectId, String name, String userName);

    void closeTask(Long projectId, String name);

    void update(Long projectId, String softwareName, String softwareVersion, List<String> testTypes, String startDate, String companyChineseName, String email, String address, String startUser, String telephone);

    Long create();
}
