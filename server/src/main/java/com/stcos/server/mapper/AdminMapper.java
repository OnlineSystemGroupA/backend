package com.stcos.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stcos.server.pojo.po.Admin;
import org.springframework.stereotype.Repository;

/**
 * description
 *
 * @author
 * @version 1.0
 * @since 2023/5/3 13:21
 */

@Repository
public interface AdminMapper extends BaseMapper<Admin> {
    Admin getByUsernameAdmin(String username);
}
