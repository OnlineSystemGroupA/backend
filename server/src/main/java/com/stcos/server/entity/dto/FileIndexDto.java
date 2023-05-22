package com.stcos.server.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * FileIndexDto
 */

@JsonTypeName("fileIndex")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-21T21:36:55.308582200+08:00[Asia/Shanghai]")
public class FileIndexDto {

  private Long fileIndexId;

  private String fileName;

  private String fileType;

  /**
   * Default constructor
   * @deprecated Use {@link FileIndexDto#FileIndexDto(Long, String, String)}
   */
  @Deprecated
  public FileIndexDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public FileIndexDto(Long fileIndexId, String fileName, String fileType) {
    this.fileIndexId = fileIndexId;
    this.fileName = fileName;
    this.fileType = fileType;
  }

  public FileIndexDto fileIndexId(Long fileIndexId) {
    this.fileIndexId = fileIndexId;
    return this;
  }

  /**
   * 文件索引 ID
   * @return fileIndexId
  */
  @NotNull 
  @Schema(name = "fileIndexId", description = "文件索引 ID", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("fileIndexId")
  public Long getFileIndexId() {
    return fileIndexId;
  }

  public void setFileIndexId(Long fileIndexId) {
    this.fileIndexId = fileIndexId;
  }

  public FileIndexDto fileName(String fileName) {
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

  public FileIndexDto fileType(String fileType) {
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
    FileIndexDto fileIndex = (FileIndexDto) o;
    return Objects.equals(this.fileIndexId, fileIndex.fileIndexId) &&
        Objects.equals(this.fileName, fileIndex.fileName) &&
        Objects.equals(this.fileType, fileIndex.fileType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fileIndexId, fileName, fileType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FileIndexDto {\n");
    sb.append("    fileIndexId: ").append(toIndentedString(fileIndexId)).append("\n");
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

