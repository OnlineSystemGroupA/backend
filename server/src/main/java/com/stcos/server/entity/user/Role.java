package com.stcos.server.entity.user;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
public class Role {
    @TableId
    private String roleId;

    private String roleName;
}
