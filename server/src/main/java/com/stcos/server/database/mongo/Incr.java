package com.stcos.server.database.mongo;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Incr {
    @Id
    private String id;
    private String collectionName;
    private Long incrId;
}