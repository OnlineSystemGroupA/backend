package com.stcos.server.database.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stcos.server.entity.Setting;
import org.springframework.stereotype.Repository;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/6/23 16:44
 */

@Repository
public interface SettingMapper extends BaseMapper<Setting> {
}
