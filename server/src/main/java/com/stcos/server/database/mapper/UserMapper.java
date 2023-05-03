package com.stcos.server.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stcos.server.pojo.po.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<Customer> {
}
