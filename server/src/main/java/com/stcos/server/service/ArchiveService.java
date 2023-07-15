package com.stcos.server.service;

import com.stcos.server.exception.ServiceException;
import com.stcos.server.model.dto.FormInfoDto;
import com.stcos.server.model.form.Form;
import com.stcos.server.model.process.ProcessDetails;
import com.stcos.server.model.process.ProcessRecord;

import java.util.List;

public interface ArchiveService {

    /**
     * 获取指定页数、每页数量、排序规则和分配状态的流程记录列表
     *
     * @param pageIndex 页数
     * @param numPerPage 每页的数量
     * @param orderBy 排序规则
     * @param assigned 其值为 true 表示获取当前有任务被分配给当前用户的项目
     * @return 流程记录列表
     * @throws ServiceException 各异常状态码含义如下 <br>
     *                          code: <br>
     *                          0: 待排序的键无效 <br>
     */
    List<ProcessRecord> getProcessRecords(Integer pageIndex, Integer numPerPage, String orderBy, Boolean assigned) throws ServiceException;

    /**
     * 获取当前用户可见的流程数量
     *
     * @return 流程数量
     */
    Integer getProcessCount();

    /**
     * 根据项目 ID 获取流程详情
     *
     * @param projectId 项目 ID
     * @return 流程详情
     */
    ProcessDetails getProcessDetails(Long projectId);

    /**
     * 根据项目 ID 获取所有表单信息
     *
     * @param projectId 项目 ID
     * @return 表单信息列表
     */
    List<FormInfoDto> getFormInfo(Long projectId);

    /**
     * 根据项目 ID 和表单类型获取表单
     *
     * @param projectId 项目 ID
     * @param formType 表单类型
     * @return 表单对象
     */
    Form getForm(Long projectId, String formType) throws ServiceException;
}
