package com.stcos.server.database.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stcos.server.model.Setting;
import org.springframework.stereotype.Repository;

/**
 * 对 Setting 实体进行操作的 Mapper 接口
 * 提供了对数据库中设置表进行 CRUD 操作的方法
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/6/23 16:44
 */

@Repository
public interface SettingMapper extends BaseMapper<Setting> {

}
