package com.stcos.server.database.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stcos.server.model.file.SampleMetadata;
import org.springframework.stereotype.Repository;

/**
 * 定义了对 SampleMetadata 实体在 MySQL 数据库中进行操作的一些方法
 *
 * @author AmadeusZQK
 * @version 1.0
 * @since 2023/5/23 17:39
 */

@Repository
public interface SampleMetadataMapper extends BaseMapper<SampleMetadata> {

}