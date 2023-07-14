package com.stcos.server.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * SampleMetadataDto
 */

@JsonTypeName("SampleMetadata")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-07-14T16:46:32.420866100+08:00[Asia/Shanghai]")
public class SampleMetadataDto {

  private Long sampleMetadataId;

  @Valid
  private List<String> readableUsers;

  @Valid
  private List<String> writableUsers;

  @Valid
  private List<Long> fileMetadataIdList;

  /**
   * Default constructor
   * @deprecated Use {@link SampleMetadataDto#SampleMetadataDto(Long)}
   */
  @Deprecated
  public SampleMetadataDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SampleMetadataDto(Long sampleMetadataId) {
    this.sampleMetadataId = sampleMetadataId;
  }

  public SampleMetadataDto sampleMetadataId(Long sampleMetadataId) {
    this.sampleMetadataId = sampleMetadataId;
    return this;
  }

  /**
   * 样品元数据ID，保存对象时由数据库自动赋值
   * @return sampleMetadataId
  */
  @NotNull 
  @Schema(name = "sampleMetadataId", description = "样品元数据ID，保存对象时由数据库自动赋值", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("sampleMetadataId")
  public Long getSampleMetadataId() {
    return sampleMetadataId;
  }

  public void setSampleMetadataId(Long sampleMetadataId) {
    this.sampleMetadataId = sampleMetadataId;
  }

  public SampleMetadataDto readableUsers(List<String> readableUsers) {
    this.readableUsers = readableUsers;
    return this;
  }

  public SampleMetadataDto addReadableUsersItem(String readableUsersItem) {
    if (this.readableUsers == null) {
      this.readableUsers = new ArrayList<>();
    }
    this.readableUsers.add(readableUsersItem);
    return this;
  }

  /**
   * 对样品具有读权限用户的ID列表
   * @return readableUsers
  */
  
  @Schema(name = "readableUsers", description = "对样品具有读权限用户的ID列表", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("readableUsers")
  public List<String> getReadableUsers() {
    return readableUsers;
  }

  public void setReadableUsers(List<String> readableUsers) {
    this.readableUsers = readableUsers;
  }

  public SampleMetadataDto writableUsers(List<String> writableUsers) {
    this.writableUsers = writableUsers;
    return this;
  }

  public SampleMetadataDto addWritableUsersItem(String writableUsersItem) {
    if (this.writableUsers == null) {
      this.writableUsers = new ArrayList<>();
    }
    this.writableUsers.add(writableUsersItem);
    return this;
  }

  /**
   * 对样品具有写权限用户的ID列表
   * @return writableUsers
  */
  
  @Schema(name = "writableUsers", description = "对样品具有写权限用户的ID列表", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("writableUsers")
  public List<String> getWritableUsers() {
    return writableUsers;
  }

  public void setWritableUsers(List<String> writableUsers) {
    this.writableUsers = writableUsers;
  }

  public SampleMetadataDto fileMetadataIdList(List<Long> fileMetadataIdList) {
    this.fileMetadataIdList = fileMetadataIdList;
    return this;
  }

  public SampleMetadataDto addFileMetadataIdListItem(Long fileMetadataIdListItem) {
    if (this.fileMetadataIdList == null) {
      this.fileMetadataIdList = new ArrayList<>();
    }
    this.fileMetadataIdList.add(fileMetadataIdListItem);
    return this;
  }

  /**
   * 文件元数据ID列表
   * @return fileMetadataIdList
  */
  
  @Schema(name = "fileMetadataIdList", description = "文件元数据ID列表", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("fileMetadataIdList")
  public List<Long> getFileMetadataIdList() {
    return fileMetadataIdList;
  }

  public void setFileMetadataIdList(List<Long> fileMetadataIdList) {
    this.fileMetadataIdList = fileMetadataIdList;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SampleMetadataDto sampleMetadata = (SampleMetadataDto) o;
    return Objects.equals(this.sampleMetadataId, sampleMetadata.sampleMetadataId) &&
        Objects.equals(this.readableUsers, sampleMetadata.readableUsers) &&
        Objects.equals(this.writableUsers, sampleMetadata.writableUsers) &&
        Objects.equals(this.fileMetadataIdList, sampleMetadata.fileMetadataIdList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sampleMetadataId, readableUsers, writableUsers, fileMetadataIdList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SampleMetadataDto {\n");
    sb.append("    sampleMetadataId: ").append(toIndentedString(sampleMetadataId)).append("\n");
    sb.append("    readableUsers: ").append(toIndentedString(readableUsers)).append("\n");
    sb.append("    writableUsers: ").append(toIndentedString(writableUsers)).append("\n");
    sb.append("    fileMetadataIdList: ").append(toIndentedString(fileMetadataIdList)).append("\n");
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

