package com.stcos.server.model.process;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stcos.server.model.file.SampleMetadata;
import com.stcos.server.database.mongo.AutoId;
import com.stcos.server.model.form.FormMetadata;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.List;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/6/27 14:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "process_record")
public class ProcessRecord {
    @AutoId
    @JsonIgnore
    @MongoId(targetType = FieldType.INT64)
    private long projectId = -1;

    private String clientId;

    private String marketingManagerId;

    private String testingManagerId;

    private String qualityManagerId;

    private String signatoryId;

    private String marketingOperatorId;

    private String testingOperatorId;

    private String startUserName;

    private String title;

    private LocalDateTime startDate;

    private LocalDateTime finishDate;

    private List<FormMetadata> formMetadataList;

    private SampleMetadata sampleMetadata;

}
