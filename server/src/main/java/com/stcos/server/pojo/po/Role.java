package com.stcos.server.pojo.po;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Role {
    @TableId
    private String uid;

    private String role;
}
