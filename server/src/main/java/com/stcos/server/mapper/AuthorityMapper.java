package com.stcos.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stcos.server.pojo.po.Authority;
import com.stcos.server.pojo.po.Role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AuthorityMapper extends BaseMapper<Authority> {
    default List<String> getAuthorityByUid(String uid){
        Map<String, Object> map = new HashMap<>();
        map.put("uid",uid);
        List<Authority> list = this.selectByMap(map);
        if(list.isEmpty())
            return null;
        else{
            List<String> ret = new ArrayList<>();
            for (Authority authority : list) {
                ret.add(authority.getAuthority());
            }
            return ret;
        }
    }

}
