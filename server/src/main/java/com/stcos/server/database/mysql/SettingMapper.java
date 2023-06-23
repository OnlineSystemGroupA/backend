package com.stcos.server.database.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stcos.server.entity.Setting;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/6/23 16:44
 */

public interface SettingMapper extends BaseMapper<Setting> {

    Setting selectByKey(String key);

}
