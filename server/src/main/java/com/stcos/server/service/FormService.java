package com.stcos.server.service;

import com.stcos.server.entity.form.Form;
import com.stcos.server.exception.ServiceException;

public interface FormService {
    /**
     * 更新指定流程中的指定表单
     *
     * @param formMetadataId 表单元数据 ID
     * @return 表单对象
     * @throws ServiceException 各异常状态码含义如下 <br>
     *                          code: <br>
     *                          0: 当前流程不存在 <br>
     *                          1: 该任务对当前用户不可见或当前用户无读取权限 <br>
     */
    Form getForm(Long formMetadataId) throws ServiceException;

    /**
     * 更新指定流程中的指定表单
     *
     * @param formMetadataId 表单元数据 ID
     * @param formType 表单类型
     * @param form 表单的 JSON 格式
     * @throws ServiceException 各异常状态码含义如下 <br>
     *                          code: <br>
     *                          0: 当前流程不存在 <br>
     *                          1: 该任务对当前用户不可见或当前用户无修改权限 <br>
     */
    void updateForm(Long formMetadataId, String formType, Form form) throws ServiceException;

    void addWritePermission(long formMetadataId, String uid);

    void removeWritePermission(long formMetadataId, String uid);
}
