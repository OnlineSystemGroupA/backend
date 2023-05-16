package com.stcos.server.pojo.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "t_role")
public class Role {
    @TableId
    private String roleId;

    private String roleName;
}
