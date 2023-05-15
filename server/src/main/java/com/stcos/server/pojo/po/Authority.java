package com.stcos.server.pojo.po;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


@Data
public class Authority {
    @TableId
    private String uid;

    private String authority;
}
