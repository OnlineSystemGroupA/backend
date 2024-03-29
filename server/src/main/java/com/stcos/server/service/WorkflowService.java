package com.stcos.server.service;

import com.stcos.server.model.dto.FormInfoDto;
import com.stcos.server.model.file.FileMetadata;
import com.stcos.server.model.form.Form;
import com.stcos.server.model.process.ProcessDetails;
import com.stcos.server.exception.ServiceException;
import org.flowable.task.api.Task;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface WorkflowService {

    /**
     * 将一个任务标记为完成，跳转至下一阶段
     *
     * @param processId 流程 ID
     * @throws ServiceException 各异常状态码含义如下 <br>
     *                          code: <br>
     *                          0: 指定任务对该用户不可见或当前用户无完成任务权限 <br>
     *                          1: 指定任务不存在 <br>
     */
    void completeTask(String processId, Boolean passable) throws ServiceException;

    /**
     * 通过 id 查询某一个任务
     *
     * @param taskId 任务Id
     * @return 对应任务实例
     */
    Task getTaskById(String taskId);

    /**
     * 获取与当前用户可见的任务列表
     *
     * @return 获取到的任务列表
     * @throws ServiceException 一般不抛出异常
     */
    List<Task> getTasks(int pageIndex, int numPerPage, String orderBy, Boolean assigned) throws ServiceException;

    /**
     * 获取指定流程中的指定表单
     *
     * @param processId 指定流程实例 Id
     * @param formType  表单类型
     * @return 表单的 JSON 格式
     * @throws ServiceException 各异常状态码含义如下 <br>
     *                          code: <br>
     *                          0: 当前流程不存在 <br>
     *                          1: 该任务对当前用户不可见或当前用户无读取权限 <br>
     */
    Form getForm(String processId, String formType) throws ServiceException;

    /**
     * 更新指定流程中的指定表单
     *
     * @param processId 指定流程实例 Id
     * @param formType  表单类型
     * @param form      表单的 JSON 格式
     * @throws ServiceException 各异常状态码含义如下 <br>
     *                          code: <br>
     *                          0: 当前流程不存在 <br>
     *                          1: 该任务对当前用户不可见或当前用户无修改权限 <br>
     */
    void updateForm(String processId, String formType, Form form) throws ServiceException;

    /**
     * 上传样品文件
     *
     * @param processInstanceId 指定流程实例 Id
     * @param file 样品文件
     * @return 样品文件摘要
     * @throws ServiceException 各异常状态码含义如下 <br>
     *                          code: <br>
     *                          0: 当前流程不存在 <br>
     *                          1: 该任务对当前用户不可见或当前用户无上传权限 <br>
     *                          2: 没有上传文件 <br>
     *                          3: 存储空间不足 <br>
     *                          4: 文件上传失败 <br>
     */
    FileMetadata uploadSample(String processInstanceId, MultipartFile file) throws ServiceException;

    /**
     * 下载样品文件
     *
     * @param processInstanceId 指定流程实例 Id
     * @return 样品的压缩文件
     * @throws ServiceException 各异常状态码含义如下 <br>
     *                          code: <br>
     *                          0: 当前流程不存在 <br>
     *                          1: 该任务对当前用户不可见或当前用户无下载权限 <br>
     *                          2: 文件不存在 <br>
     *                          3: 文件下载失败 <br>
     */
    File downloadSample(String processInstanceId) throws ServiceException;

    /**
     * 删除样品或部分文件
     *
     * @param processInstanceId 指定流程实例 Id
     * @throws ServiceException 各异常状态码含义如下 <br>
     *                          code: <br>
     *                          0: 当前流程不存在 <br>
     *                          1: 该任务对当前用户不可见或当前用户无下载权限 <br>
     *                          2: 文件不存在 <br>
     *                          3: 文件删除失败 <br>
     */
    void deleteSample(String processInstanceId) throws ServiceException;

    /**
     * 开始新的委托流程
     *
     * @return 新开启的委托的 Id
     * @throws ServiceException 一般不抛出异常
     */
    String startProcess() throws ServiceException;

    /**
     * 获取当前用户可见的流程数量
     *
     * @return 当前用户可见的流程数量
     */
    int getProcessCount();

    /**
     * 为指定的流程设置参与者
     *
     * @param processId 指定的流程 ID
     * @param userId 要添加的用户 ID
     * @throws ServiceException 异常状态码含义如下 <br>
     *                          code: <br>
     *                          0: 指定流程对当前用户不可见 <br>
     *                          1: 用户不存在 <br>
     */
    void setParticipants(String processId, String userId) throws ServiceException;

    /**
     * 获取指定流程的详细信息
     * @param processId 指定的流程 ID
     * @return 包含流程详细信息的 ProcessDetails 对象
     * @throws ServiceException 异常状态码含义如下 <br>
     *                          code: <br>
     *                          0: 指定流程对当前用户不可见 <br>
     */
    ProcessDetails getProcessDetails(String processId) throws ServiceException;

    /**
     * 获取指定流程和表单类型的表单文件
     * @param processId 指定的流程 ID
     * @param formType 指定的表单类型
     * @return 包含表单文件的 Resource 对象
     * @throws ServiceException 异常状态码含义如下 <br>
     *                          code: <br>
     *                          0: 指定流程对当前用户不可见 <br>
     */
    Resource getFormFile(String processId, String formType) throws ServiceException;

    /**
     * 保存表单文件到指定的流程和表单类型
     * @param processId 指定的流程 ID
     * @param formType 指定的表单类型
     * @param file 要保存的表单文件
     * @throws ServiceException 异常状态码含义如下 <br>
     *                          code: <br>
     *                          0: 指定流程对当前用户不可见 <br>
     *                          1: 用户没有写权限 <br>
     */
    void saveFileForm(String processId, String formType, MultipartFile file) throws ServiceException;

    /**
     * 获取指定流程的所有表单信息
     * @param processId 指定的流程 ID
     * @return 包含所有表单信息的列表
     * @throws ServiceException 异常状态码含义如下 <br>
     *                          code: <br>
     *                          0: 指定流程实例不存在 <br>
     */
    List<FormInfoDto> getFormInfo(String processId) throws ServiceException;
}
