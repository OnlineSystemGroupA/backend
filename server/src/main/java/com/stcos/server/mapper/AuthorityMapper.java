package com.stcos.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stcos.server.pojo.po.Authority;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.ArrayList;
import java.util.List;

public interface AuthorityMapper extends BaseMapper<Authority> {
    @Select("select * from t_authority left join t_user_authority on t_authority.authority_id=t_user_authority.authority_id  where t_user_authority.uid=#{userId}")
    List<Authority> getAuthorityListByUserId(String userId);

    default List<GrantedAuthority> getAuthorityListByUid(String uid){
        List<Authority> list = getAuthorityListByUserId(uid);
        List<GrantedAuthority> ret = new ArrayList<>();
        for (Authority authority : list) {
            ret.add(new SimpleGrantedAuthority(authority.getAuthorityName()));
        }
        return ret;
    }
}
