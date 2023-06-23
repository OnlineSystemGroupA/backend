package com.stcos.server.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/6/23 17:16
 */

@Data
public class Setting {

    @TableField(value = "setting_key")
    private String key;

    @TableField(value = "setting_val")
    private String val;

}
