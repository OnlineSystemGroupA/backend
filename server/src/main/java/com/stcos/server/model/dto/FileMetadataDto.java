package com.stcos.server.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * FileMetadataDto
 */

@JsonTypeName("fileMetadata")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-21T21:36:55.308582200+08:00[Asia/Shanghai]")
public class FileMetadataDto {

  private Long fileMetadataId;

  private String fileName;

  private String fileType;

  /**
   * Default constructor
   * @deprecated Use {@link FileMetadataDto#FileMetadataDto(Long, String, String)}
   */
  @Deprecated
  public FileMetadataDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public FileMetadataDto(Long fileMetadataId, String fileName, String fileType) {
    this.fileMetadataId = fileMetadataId;
    this.fileName = fileName;
    this.fileType = fileType;
  }

  public FileMetadataDto fileMetadataId(Long fileMetadataId) {
    this.fileMetadataId = fileMetadataId;
    return this;
  }

  /**
   * 文件元数据 ID
   * @return fileMetadataId
  */
  @NotNull
  @Schema(name = "fileMetadataId", description = "文件元数据 ID", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("fileMetadataId")
  public Long getFileMetadataId() {
    return fileMetadataId;
  }

  public void setFileMetadataId(Long fileMetadataId) {
    this.fileMetadataId = fileMetadataId;
  }

  public FileMetadataDto fileName(String fileName) {
    this.fileName = fileName;
    return this;
  }

  /**
   * 文件名
   * @return fileName
  */
  @NotNull
  @Schema(name = "fileName", description = "文件名", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("fileName")
  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public FileMetadataDto fileType(String fileType) {
    this.fileType = fileType;
    return this;
  }

  /**
   * 文件类型
   * @return fileType
  */
  @NotNull
  @Schema(name = "fileType", description = "文件类型", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("fileType")
  public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileMetadataDto fileMetadata = (FileMetadataDto) o;
    return Objects.equals(this.fileMetadataId, fileMetadata.fileMetadataId) &&
        Objects.equals(this.fileName, fileMetadata.fileName) &&
        Objects.equals(this.fileType, fileMetadata.fileType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fileMetadataId, fileName, fileType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FileMetadataDto {\n");
    sb.append("    fileMetadataId: ").append(toIndentedString(fileMetadataId)).append("\n");
    sb.append("    fileName: ").append(toIndentedString(fileName)).append("\n");
    sb.append("    fileType: ").append(toIndentedString(fileType)).append("\n");
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

