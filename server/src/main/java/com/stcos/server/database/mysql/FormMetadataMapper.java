package com.stcos.server.database.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stcos.server.model.form.FormMetadata;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/6/25 13:45
 */

@Repository
public interface FormMetadataMapper extends BaseMapper<FormMetadata> {
    public default FormMetadata selectByProjectId(Long projectId){
        Map<String, Object> map = new HashMap<>();
        map.put("projectId",projectId);
        if(this.selectByMap(map).isEmpty())
            return null;
        else
            return this.selectByMap(map).get(0);
    }
    public default FormMetadata selectByFormId(Long formId){
        Map<String, Object> map = new HashMap<>();
        map.put("formId",formId);
        if(this.selectByMap(map).isEmpty())
            return null;
        else
            return this.selectByMap(map).get(0);
    }
}
