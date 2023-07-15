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
import java.util.Map;

/**
 * 该类用于表示一个处理记录，它包含了该记录的各种属性如项目 ID，各种角色的 ID，开始和结束日期，表单元数据 ID 的映射等
 * 它被标注为一个 MongoDB 的文档并被映射到 "process_record" 集合
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
    /**
     * 项目 ID
     */
    @AutoId
    @JsonIgnore
    @MongoId(targetType = FieldType.INT64)
    private long projectId = -1;

    /**
     * 客户 ID
     */
    private String clientId;

    /**
     * 市场部主管 ID
     */
    private String marketingManagerId;

    /**
     * 测试部主管 ID
     */
    private String testingManagerId;

    /**
     * 质量部主管 ID
     */
    private String qualityManagerId;

    /**
     * 签字人 ID
     */
    private String signatoryId;

    /**
     * 市场部员工 ID
     */
    private String marketingOperatorId;

    /**
     * 测试部员工 ID
     */
    private String testingOperatorId;

    /**
     * 启动项目的用户名称
     */
    private String startUserName;

    /**
     * 标题
     */
    private String title;

    /**
     * 开始日期
     */
    private LocalDateTime startDate;

    /**
     * 结束日期
     */
    private LocalDateTime finishDate;

    /**
     * 表单元数据 ID 的映射
     */
    private Map<String, Long> formMetadataIdMap;

    /**
     * 样本元数据
     */
    private SampleMetadata sampleMetadata;

}
