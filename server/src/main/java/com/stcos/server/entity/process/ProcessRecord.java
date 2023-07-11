package com.stcos.server.entity.process;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.stcos.server.entity.file.SampleMetadata;
import com.stcos.server.entity.form.FormMetadata;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

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
@TableName("t_process_record")
public class ProcessRecord {

    @TableId(type = IdType.AUTO, value = "project_id")
    private Long projectId;

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

    @TableField(exist = false)
    private Set<FormMetadata> formMetadataSet;

    @TableField(exist = false)
    private SampleMetadata sampleMetadata;

}
