package com.stcos.server.database.mongo;

import com.stcos.server.model.process.ProcessRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProcessRecordRepository extends MongoRepository<ProcessRecord,Long> {
}
