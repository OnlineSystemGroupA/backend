package com.stcos.server.entity.user;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName(value = "t_authority")
public class Authority {
    @TableId
    private String authorityId;

    private String authorityName;
}
