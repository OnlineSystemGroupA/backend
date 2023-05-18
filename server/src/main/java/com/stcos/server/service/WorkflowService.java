package com.stcos.server.service;


import com.stcos.server.entity.form.Form;
import com.stcos.server.exception.ServiceException;
import org.flowable.task.api.Task;

import java.util.List;

public interface WorkflowService {

    /**
     * 将一个任务标记为完成，跳转至下一阶段
     *
     * @param taskId 指定任务Id
     * @throws ServiceException 各异常状态码含义如下 <br>
     *                          code: <br>
     *                          0: 指定任务对该用户不可见或当前用户无完成任务权限 <br>
     *                          1: 指定任务不存在 <br>
     */
    void completeTask(String taskId) throws ServiceException;

    /**
     * 通过 id 查询某一个任务
     *
     * @param taskId 任务Id
     * @return 对应任务
     * @throws ServiceException 各异常状态码含义如下 <br>
     *                          code: <br>
     *                          0: 指定任务对该用户不可见 <br>
     *                          1: 指定任务不存在 <br>
     */
    Task getTaskById(String taskId) throws ServiceException;

    /**
     * 获取与当前用户可见的任务列表
     *
     * @return 获取到的任务列表
     * @throws ServiceException 一般不抛出异常
     */
    List<Task> getTasks() throws ServiceException;

    /**
     * 获取指定任务中的指定资源（变量）
     *
     * @param processId 指定流程Id
     * @param itemName  资源名字
     * @return 资源
     * @throws ServiceException 各异常状态码含义如下 <br>
     *                          code: <br>
     *                          0: 指定任务或资源对当前用户不可见 <br>
     *                          1: 指定任务或资源不存在 <br>
     */
    Object getTaskItem(String processId, String itemName) throws ServiceException;

    /**
     * 更新指定任务中的指定资源
     *
     * @param processInstanceId 指定流程实例 id
     * @param formName          表单名
     * @param form              表单对象
     * @throws ServiceException 各异常状态码含义如下 <br>
     *                          code: <br>
     *                          0: 没有指定资源（需要创建） <br>
     *                          1: 该任务对当前用户不可见或当前用户无修改权限 <br>
     *                          2: 指定任务不存在 <br>
     */
    void updateForm(String processInstanceId, String formName, Form form) throws ServiceException;

    /**
     * 开始新的委托流程
     *
     * @return 新开启的委托的Id
     * @throws ServiceException 一般不抛出异常
     */
    String startProcess() throws ServiceException;
}
