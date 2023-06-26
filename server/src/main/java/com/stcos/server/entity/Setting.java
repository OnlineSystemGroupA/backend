package com.stcos.server.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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

    @TableId(value = "setting_key")
    private String settingKey;

    @TableField(value = "setting_val")
    private String settingVal;

}
