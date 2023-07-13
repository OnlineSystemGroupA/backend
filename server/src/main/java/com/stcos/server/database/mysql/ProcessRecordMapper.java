package com.stcos.server.database.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stcos.server.entity.file.SampleMetadata;
import com.stcos.server.entity.form.FormMetadata;
import com.stcos.server.entity.process.ProcessRecord;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/6/27 14:06
 */
@Repository
public interface ProcessRecordMapper extends BaseMapper<ProcessRecord> {
    @Select("SELECT a.*, c.* " +
            "FROM t_process_record a " +
            "LEFT JOIN t_form_metadata b ON a.project_id = b.project_id " +
            "LEFT JOIN t_record_sample cam ON a.project_id = cam.record_id " +
            "LEFT JOIN t_sample_metadata c ON cam.sample_id = c.sample_metadata_id " +
            "WHERE a.project_id = #{projectId}")
    @Results({
            @Result(property = "projectId", column = "project_id"),
            @Result(property = "clientId", column = "client_id"),
            @Result(property = "marketingManagerId", column = "marketing_manager_id"),
            @Result(property = "testingManagerId", column = "testing_manager_id"),
            @Result(property = "qualityManagerId", column = "quality_manager_id"),
            @Result(property = "signatoryId", column = "signatory_id"),
            @Result(property = "marketingOperatorId", column = "marketing_operator_id"),
            @Result(property = "testingOperatorId", column = "testing_operator_id"),
            @Result(property = "startUserName", column = "start_user_name"),
            @Result(property = "title", column = "title"),
            @Result(property = "startDate", column = "start_date"),
            @Result(property = "finishDate", column = "finish_date"),
            @Result(property = "formMetadataSet", javaType = List.class, column = "project_id",
                    many = @Many(select = "com.stcos.server.database.mysql.FormMetadataMapper.selectByProjectId")),
            @Result(property = "sampleMetadata", javaType = SampleMetadata.class, column = "sample_metadata_id",
                    one = @One(select = "com.stcos.server.database.mysql.SampleMetadataMapper.selectById"))
    })
    ProcessRecord findAWithBAndC(@Param("projectId") Long projectId);

    @Insert("INSERT INTO t_process_record " +
            "(project_id, " +
            "client_id, " +
            "marketing_manager_id, " +
            "testing_manager_id, " +
            "quality_manager_id, " +
            "signatory_id, " +
            "marketing_operator_id, " +
            "testing_operator_id, " +
            "start_user_name, " +
            "title, " +
            "start_date, " +
            "finish_date) " +
            "VALUES " +
            "(#{processRecord.projectId}," +
            "#{processRecord.clientId}," +
            "#{processRecord.marketingManagerId}," +
            "#{processRecord.testingManagerId}," +
            "#{processRecord.qualityManagerId}," +
            "#{processRecord.signatoryId}," +
            "#{processRecord.marketingOperatorId}," +
            "#{processRecord.testingOperatorId}," +
            "#{processRecord.startUserName}," +
            "#{processRecord.title}," +
            "#{processRecord.startDate}," +
            "#{processRecord.finishDate}" +
            ")")
    @Options(useGeneratedKeys = true, keyProperty = "projectId")
    void saveRecord(@Param("processRecord") ProcessRecord processRecord);

    @Insert("INSERT INTO t_form_metadata " +
            "(form_metadata_id, form_id, project_id, form_type, form_state, created_by, created_date, last_modified_by, last_modified_date, readable_users, writable_users) " +
            "VALUES " +
            "(#{formMetadata.formMetadataId}," +
            "#{formMetadata.formId}," +
            "#{formMetadata.projectId}," +
            "#{formMetadata.formType}," +
            "#{formMetadata.formState}," +
            "#{formMetadata.createdBy}," +
            "#{formMetadata.createdDate}," +
            "#{formMetadata.lastModifiedBy}," +
            "#{formMetadata.lastModifiedDate}," +
            "#{formMetadata.readableUsers}," +
            "#{formMetadata.writableUsers}" +
            ")")
    void saveB(@Param("formMetadata") FormMetadata formMetadata);

    @Insert("INSERT INTO t_sample_metadata " +
            "(sample_metadata_id, " +
            "readable_users, " +
            "writable_users, " +
            "file_metadata_id_list) " +
            "VALUES " +
            "(#{sampleMetadata.sampleMetadataId}," +
            "#{sampleMetadata.readableUsers}," +
            "#{sampleMetadata.writableUsers}," +
            "#{sampleMetadata.fileMetadataIdList}" +
            ")")
    void saveSample(@Param("sampleMetadata") SampleMetadata sampleMetadata);

    @Insert("INSERT INTO t_record_sample " +
            "(record_id, sample_id) " +
            "VALUES " +
            "(#{recordId}, #{sampleId})")
    void saveABRelation(@Param("recordId") Long recordId, @Param("sampleId") Long sampleId);

    default void saveFull(ProcessRecord processRecord){
        this.saveRecord(processRecord);
        for (FormMetadata formMetadata : processRecord.getFormMetadataSet()) {
            this.saveB(formMetadata);
        }
        this.saveSample(processRecord.getSampleMetadata());
        this.saveABRelation(processRecord.getProjectId(),processRecord.getSampleMetadata().getSampleMetadataId());
    }
}
