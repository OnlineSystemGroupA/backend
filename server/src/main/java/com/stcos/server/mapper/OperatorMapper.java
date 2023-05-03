package com.stcos.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stcos.server.pojo.po.Operator;
import org.springframework.stereotype.Repository;

/**
 * description
 *
 * @author
 * @version 1.0
 * @since 2023/5/3 13:21
 */

@Repository
public interface OperatorMapper extends BaseMapper<Operator> {
    Operator getByUsernameOperator(String username);
}
