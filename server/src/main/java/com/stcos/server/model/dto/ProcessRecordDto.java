package com.stcos.server.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.stcos.server.model.dto.FormMetadataDto;
import com.stcos.server.model.dto.SampleMetadataDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ProcessRecordDto
 */

@JsonTypeName("ProcessRecord")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-07-14T16:46:32.420866100+08:00[Asia/Shanghai]")
public class ProcessRecordDto {

  private Long projectId;

  private String clientId;

  private String marketDepartmentSupervisorId;

  private String testingSupervisorId;

  private String qualitySupervisorId;

  private String signatoryId;

  private String marketDepartmentEmployeeId;

  private String testingEmployeeId;

  private String startUserName;

  private String title;

  private String startDate;

  private String finishDate;

  @Valid
  private List<@Valid FormMetadataDto> formMetadataList;

  private SampleMetadataDto sampleMetadata;

  /**
   * Default constructor
   * @deprecated Use {@link ProcessRecordDto#ProcessRecordDto(Long, String, String, String)}
   */
  @Deprecated
  public ProcessRecordDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ProcessRecordDto(Long projectId, String title, String startDate, String finishDate) {
    this.projectId = projectId;
    this.title = title;
    this.startDate = startDate;
    this.finishDate = finishDate;
  }

  public ProcessRecordDto projectId(Long projectId) {
    this.projectId = projectId;
    return this;
  }

  /**
   * 测试项目号
   * @return projectId
  */
  @NotNull 
  @Schema(name = "projectId", description = "测试项目号", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("projectId")
  public Long getProjectId() {
    return projectId;
  }

  public void setProjectId(Long projectId) {
    this.projectId = projectId;
  }

  public ProcessRecordDto clientId(String clientId) {
    this.clientId = clientId;
    return this;
  }

  /**
   * 客户 ID
   * @return clientId
  */
  
  @Schema(name = "clientId", description = "客户 ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("clientId")
  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public ProcessRecordDto marketDepartmentSupervisorId(String marketDepartmentSupervisorId) {
    this.marketDepartmentSupervisorId = marketDepartmentSupervisorId;
    return this;
  }

  /**
   * 市场部主管 ID
   * @return marketDepartmentSupervisorId
  */
  
  @Schema(name = "marketDepartmentSupervisorId", description = "市场部主管 ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("marketDepartmentSupervisorId")
  public String getMarketDepartmentSupervisorId() {
    return marketDepartmentSupervisorId;
  }

  public void setMarketDepartmentSupervisorId(String marketDepartmentSupervisorId) {
    this.marketDepartmentSupervisorId = marketDepartmentSupervisorId;
  }

  public ProcessRecordDto testingSupervisorId(String testingSupervisorId) {
    this.testingSupervisorId = testingSupervisorId;
    return this;
  }

  /**
   * 测试部主管 ID
   * @return testingSupervisorId
  */
  
  @Schema(name = "testingSupervisorId", description = "测试部主管 ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("testingSupervisorId")
  public String getTestingSupervisorId() {
    return testingSupervisorId;
  }

  public void setTestingSupervisorId(String testingSupervisorId) {
    this.testingSupervisorId = testingSupervisorId;
  }

  public ProcessRecordDto qualitySupervisorId(String qualitySupervisorId) {
    this.qualitySupervisorId = qualitySupervisorId;
    return this;
  }

  /**
   * 质量部主管 ID
   * @return qualitySupervisorId
  */
  
  @Schema(name = "qualitySupervisorId", description = "质量部主管 ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("qualitySupervisorId")
  public String getQualitySupervisorId() {
    return qualitySupervisorId;
  }

  public void setQualitySupervisorId(String qualitySupervisorId) {
    this.qualitySupervisorId = qualitySupervisorId;
  }

  public ProcessRecordDto signatoryId(String signatoryId) {
    this.signatoryId = signatoryId;
    return this;
  }

  /**
   * 签字人 ID
   * @return signatoryId
  */
  
  @Schema(name = "signatoryId", description = "签字人 ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("signatoryId")
  public String getSignatoryId() {
    return signatoryId;
  }

  public void setSignatoryId(String signatoryId) {
    this.signatoryId = signatoryId;
  }

  public ProcessRecordDto marketDepartmentEmployeeId(String marketDepartmentEmployeeId) {
    this.marketDepartmentEmployeeId = marketDepartmentEmployeeId;
    return this;
  }

  /**
   * 市场部员工 ID
   * @return marketDepartmentEmployeeId
  */
  
  @Schema(name = "marketDepartmentEmployeeId", description = "市场部员工 ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("marketDepartmentEmployeeId")
  public String getMarketDepartmentEmployeeId() {
    return marketDepartmentEmployeeId;
  }

  public void setMarketDepartmentEmployeeId(String marketDepartmentEmployeeId) {
    this.marketDepartmentEmployeeId = marketDepartmentEmployeeId;
  }

  public ProcessRecordDto testingEmployeeId(String testingEmployeeId) {
    this.testingEmployeeId = testingEmployeeId;
    return this;
  }

  /**
   * 测试部员工 ID
   * @return testingEmployeeId
  */
  
  @Schema(name = "testingEmployeeId", description = "测试部员工 ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("testingEmployeeId")
  public String getTestingEmployeeId() {
    return testingEmployeeId;
  }

  public void setTestingEmployeeId(String testingEmployeeId) {
    this.testingEmployeeId = testingEmployeeId;
  }

  public ProcessRecordDto startUserName(String startUserName) {
    this.startUserName = startUserName;
    return this;
  }

  /**
   * 启动用户名称
   * @return startUserName
  */
  
  @Schema(name = "startUserName", description = "启动用户名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("startUserName")
  public String getStartUserName() {
    return startUserName;
  }

  public void setStartUserName(String startUserName) {
    this.startUserName = startUserName;
  }

  public ProcessRecordDto title(String title) {
    this.title = title;
    return this;
  }

  /**
   * 软件项目名
   * @return title
  */
  @NotNull 
  @Schema(name = "title", description = "软件项目名", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public ProcessRecordDto startDate(String startDate) {
    this.startDate = startDate;
    return this;
  }

  /**
   * 测试开始时间
   * @return startDate
  */
  @NotNull 
  @Schema(name = "startDate", description = "测试开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("startDate")
  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public ProcessRecordDto finishDate(String finishDate) {
    this.finishDate = finishDate;
    return this;
  }

  /**
   * 测试结束时间
   * @return finishDate
  */
  @NotNull 
  @Schema(name = "finishDate", description = "测试结束时间", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("finishDate")
  public String getFinishDate() {
    return finishDate;
  }

  public void setFinishDate(String finishDate) {
    this.finishDate = finishDate;
  }

  public ProcessRecordDto formMetadataList(List<@Valid FormMetadataDto> formMetadataList) {
    this.formMetadataList = formMetadataList;
    return this;
  }

  public ProcessRecordDto addFormMetadataListItem(FormMetadataDto formMetadataListItem) {
    if (this.formMetadataList == null) {
      this.formMetadataList = new ArrayList<>();
    }
    this.formMetadataList.add(formMetadataListItem);
    return this;
  }

  /**
   * 表单元数据列表
   * @return formMetadataList
  */
  @Valid 
  @Schema(name = "formMetadataList", description = "表单元数据列表", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("formMetadataList")
  public List<@Valid FormMetadataDto> getFormMetadataList() {
    return formMetadataList;
  }

  public void setFormMetadataList(List<@Valid FormMetadataDto> formMetadataList) {
    this.formMetadataList = formMetadataList;
  }

  public ProcessRecordDto sampleMetadata(SampleMetadataDto sampleMetadata) {
    this.sampleMetadata = sampleMetadata;
    return this;
  }

  /**
   * Get sampleMetadata
   * @return sampleMetadata
  */
  @Valid 
  @Schema(name = "sampleMetadata", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("sampleMetadata")
  public SampleMetadataDto getSampleMetadata() {
    return sampleMetadata;
  }

  public void setSampleMetadata(SampleMetadataDto sampleMetadata) {
    this.sampleMetadata = sampleMetadata;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProcessRecordDto processRecord = (ProcessRecordDto) o;
    return Objects.equals(this.projectId, processRecord.projectId) &&
        Objects.equals(this.clientId, processRecord.clientId) &&
        Objects.equals(this.marketDepartmentSupervisorId, processRecord.marketDepartmentSupervisorId) &&
        Objects.equals(this.testingSupervisorId, processRecord.testingSupervisorId) &&
        Objects.equals(this.qualitySupervisorId, processRecord.qualitySupervisorId) &&
        Objects.equals(this.signatoryId, processRecord.signatoryId) &&
        Objects.equals(this.marketDepartmentEmployeeId, processRecord.marketDepartmentEmployeeId) &&
        Objects.equals(this.testingEmployeeId, processRecord.testingEmployeeId) &&
        Objects.equals(this.startUserName, processRecord.startUserName) &&
        Objects.equals(this.title, processRecord.title) &&
        Objects.equals(this.startDate, processRecord.startDate) &&
        Objects.equals(this.finishDate, processRecord.finishDate) &&
        Objects.equals(this.formMetadataList, processRecord.formMetadataList) &&
        Objects.equals(this.sampleMetadata, processRecord.sampleMetadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(projectId, clientId, marketDepartmentSupervisorId, testingSupervisorId, qualitySupervisorId, signatoryId, marketDepartmentEmployeeId, testingEmployeeId, startUserName, title, startDate, finishDate, formMetadataList, sampleMetadata);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProcessRecordDto {\n");
    sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
    sb.append("    clientId: ").append(toIndentedString(clientId)).append("\n");
    sb.append("    marketDepartmentSupervisorId: ").append(toIndentedString(marketDepartmentSupervisorId)).append("\n");
    sb.append("    testingSupervisorId: ").append(toIndentedString(testingSupervisorId)).append("\n");
    sb.append("    qualitySupervisorId: ").append(toIndentedString(qualitySupervisorId)).append("\n");
    sb.append("    signatoryId: ").append(toIndentedString(signatoryId)).append("\n");
    sb.append("    marketDepartmentEmployeeId: ").append(toIndentedString(marketDepartmentEmployeeId)).append("\n");
    sb.append("    testingEmployeeId: ").append(toIndentedString(testingEmployeeId)).append("\n");
    sb.append("    startUserName: ").append(toIndentedString(startUserName)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    finishDate: ").append(toIndentedString(finishDate)).append("\n");
    sb.append("    formMetadataList: ").append(toIndentedString(formMetadataList)).append("\n");
    sb.append("    sampleMetadata: ").append(toIndentedString(sampleMetadata)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

