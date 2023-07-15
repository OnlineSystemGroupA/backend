package com.stcos.server.database.mongo;

import com.stcos.server.model.process.ProcessRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 这个接口继承自 MongoRepository，作为操作 MongoDB 中 ProcessRecord 文档的 CRUD 接口
 * 其中，ProcessRecord 是对应的领域模型类，Long 是其主键的类型
 */
public interface ProcessRecordRepository extends MongoRepository<ProcessRecord, Long> {

}
