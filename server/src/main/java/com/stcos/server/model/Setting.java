package com.stcos.server.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 这个类用于表示系统的一些设置，包含设置的键（settingKey）和对应的值（settingVal）
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/6/23 17:16
 */

@Data
public class Setting {

    /**
     * 设置的键
     */
    @TableId(value = "setting_key")
    private String settingKey;

    /**
     * 设置的值
     */
    @TableField(value = "setting_val")
    private String settingVal;
}
