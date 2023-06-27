package com.stcos.server.database.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stcos.server.entity.form.FormMetadata;
import org.apache.ibatis.annotations.Options;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/6/25 13:45
 */
//@Options(flushCache = Options.FlushCachePolicy.TRUE)
public interface FormMetadataMapper extends BaseMapper<FormMetadata> {
}
