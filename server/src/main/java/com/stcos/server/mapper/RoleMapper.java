package com.stcos.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stcos.server.pojo.po.Role;
import org.apache.ibatis.annotations.Select;
import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据指定的userId去user和user_role关联表查出该user所属role列表
     * @param userId
     * @return
     */

    @Select("select * from t_role left join t_user_role on t_role.role_id=t_user_role.role_id  where t_user_role.uid=#{userId}")
    List<Role> getRoleListByUserId(String userId);

    default String getRoleNameByUid(String uid){
        List<Role> list = getRoleListByUserId(uid);
        if(list.isEmpty())
            return null;
        else
            return list.get(0).getRoleName();
    }
}