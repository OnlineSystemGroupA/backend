package com.stcos.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stcos.server.pojo.po.Authority;
import com.stcos.server.pojo.po.Role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface RoleMapper extends BaseMapper<Role> {
    default String getAuthorityByUid(String uid){
        Map<String, Object> map = new HashMap<>();
        map.put("uid",uid);
        List<Role> list = this.selectByMap(map);
        if(list.isEmpty())
            return null;
        else
            return list.get(0).getRole();
    }

}
