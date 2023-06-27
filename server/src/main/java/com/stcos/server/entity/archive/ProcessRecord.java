package com.stcos.server.entity.archive;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/6/27 14:01
 */
@Data
public class ProcessRecord {

    @TableId(type = IdType.AUTO, value = "record_id")
    private Long id;

    private String temp = "呼啦啦跳跳猪";

}
