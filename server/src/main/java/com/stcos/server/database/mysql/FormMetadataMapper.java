package com.stcos.server.database.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stcos.server.model.form.FormMetadata;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义了对 FormMetadata 实体在 MySQL 数据库中进行操作的一些方法
 * 使用了 MyBatis 的 BaseMapper 进行基础 SQL 语句的配置，同时定义了一些 FormMetadata 实体特有的数据库操作方法
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/6/25 13:45
 */

@Repository
public interface FormMetadataMapper extends BaseMapper<FormMetadata> {
    /**
     * 根据项目 ID 从数据库中查找一个表单元数据对象
     *
     * @param projectId 需要查找的项目 ID
     * @return 查找到的 FormMetadata 实体，如果没有找到则返回 null
     */
    public default FormMetadata selectByProjectId(Long projectId){
        Map<String, Object> map = new HashMap<>();
        map.put("project_id",projectId);
        if(this.selectByMap(map).isEmpty())
            return null;
        else
            return this.selectByMap(map).get(0);
    }

    /**
     * 根据表单 ID 从数据库中查找一个表单元数据对象
     *
     * @param formId 需要查找的表单 ID
     * @return 查找到的 FormMetadata 实体，如果没有找到则返回 null
     */
    public default FormMetadata selectByFormId(Long formId){
        Map<String, Object> map = new HashMap<>();
        map.put("form_id",formId);
        if(this.selectByMap(map).isEmpty())
            return null;
        else
            return this.selectByMap(map).get(0);
    }
}
