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


    /**
     * 为指定流程实例添加任务详情
     *
     * @param processInstanceId 流程实例 ID
     * @param taskDetails 需要添加的任务详情对象
     * @return 添加成功返回 true，否则返回 false
     * @throws ServiceException 出现额外的意外情况时抛出的异常，需要各位在实现的过程中自行设计
     */
    boolean addTaskDetails(String processInstanceId, TaskDetails taskDetails) throws ServiceException;

    void openTask(Long projectId, String name, String realName);

    void closeTask(Long projectId, String name);

    void update(Long projectId, String softwareName, String softwareVersion, List<String> testTypes, String startDate, String companyChineseName, String email, String address, String startUser, String telephone);

    Long create();
}
