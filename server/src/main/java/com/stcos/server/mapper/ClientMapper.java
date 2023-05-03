package com.stcos.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stcos.server.pojo.po.Client;
import org.springframework.stereotype.Repository;

/**
 * description
 *
 * @author materCheDan
 * @version 1.0
 * @since 2023/5/3 13:21
 */
@Repository
public interface ClientMapper extends BaseMapper<Client> {
    Client getByUsernameClient(String username);
}
