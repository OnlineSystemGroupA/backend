package com.stcos.server.pojo.po;

import com.baomidou.mybatisplus.annotation.TableId;

import java.util.UUID;

/**
 * 工作人员（测试方）
 *
 * @author
 * @version 1.0
 * @since 2023/5/3 12:40
 */

public class Operator {

    @TableId
    private String uid;


    public Operator(String uid) {
        this.uid = "operator-" + UUID.randomUUID();
    }
}
