package com.stcos.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stcos.server.database.mysql.ProcessDetailsMapper;
import com.stcos.server.entity.process.ProcessDetails;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 服务接口，定义了与处理流程详情相关的操作
 * 这些操作包括打开任务、关闭任务、更新流程详情、以及创建新的流程详情等
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/20 14:03
 */
public interface ProcessDetailsService extends IService<ProcessDetails> {

    /**
     * 打开指定的任务
     *
     * @param projectId 项目的 ID
     * @param taskName 任务的名称
     * @param userName 用户的真实名字
     */
    void openTask(Long projectId, String taskName, String userName);

    /**
     * 关闭指定的任务
     *
     * @param projectId 项目的 ID
     * @param taskName 任务的名称
     */
    void closeTask(Long projectId, String taskName);

    /**
     * 更新流程详情
     *
     * @param projectId 项目的 ID
     * @param softwareName 软件的名称
     * @param softwareVersion 软件的版本
     * @param testTypes 测试类型列表
     * @param startDate 开始日期
     * @param companyChineseName 公司的中文名
     * @param email 邮箱地址
     * @param address 地址
     * @param startUser 启动用户
     * @param telephone 电话号码
     */
    void update(Long projectId, String softwareName, String softwareVersion, List<String> testTypes, String startDate, String companyChineseName, String email, String address, String startUser, String telephone);

    /**
     * 创建一个新的流程详情记录，并返回新创建的流程详情的项目 ID
     *
     * @return 新创建的流程详情的项目 ID
     */
    Long create();
}