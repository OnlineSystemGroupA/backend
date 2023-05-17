package com.stcos.server.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * SamplePathDto
 */

@JsonTypeName("SamplePath")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-09T00:09:21.323897900+08:00[Asia/Shanghai]")
public class SamplePathDto {

  private String path;

  private String taskId;

  private String taskName;

  private String description;

  private String startUserId;

  /**
   * Default constructor
   * @deprecated Use {@link SamplePathDto#SamplePathDto(String)}
   */
  @Deprecated
  public SamplePathDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SamplePathDto(String path) {
    this.path = path;
  }

  public SamplePathDto path(String path) {
    this.path = path;
    return this;
  }

  /**
   * 当前任务对应的流程实例 id
   * @return path
  */
  @NotNull 
  @Schema(name = "path", description = "当前任务对应的流程实例 id", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("path")
  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public SamplePathDto taskId(String taskId) {
    this.taskId = taskId;
    return this;
  }

  /**
   * 当前任务实例的 id
   * @return taskId
  */
  
  @Schema(name = "taskId", description = "当前任务实例的 id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("taskId")
  public String getTaskId() {
    return taskId;
  }

  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }

  public SamplePathDto taskName(String taskName) {
    this.taskName = taskName;
    return this;
  }

  /**
   * 当前任务的名称
   * @return taskName
  */
  
  @Schema(name = "taskName", description = "当前任务的名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("taskName")
  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public SamplePathDto description(String description) {
    this.description = description;
    return this;
  }

  /**
   * 当前任务的描述、状态等
   * @return description
  */
  
  @Schema(name = "description", description = "当前任务的描述、状态等", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public SamplePathDto startUserId(String startUserId) {
    this.startUserId = startUserId;
    return this;
  }

  /**
   * 当前流程的发起人
   * @return startUserId
  */
  
  @Schema(name = "startUserId", description = "当前流程的发起人", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("startUserId")
  public String getStartUserId() {
    return startUserId;
  }

  public void setStartUserId(String startUserId) {
    this.startUserId = startUserId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SamplePathDto samplePath = (SamplePathDto) o;
    return Objects.equals(this.path, samplePath.path) &&
        Objects.equals(this.taskId, samplePath.taskId) &&
        Objects.equals(this.taskName, samplePath.taskName) &&
        Objects.equals(this.description, samplePath.description) &&
        Objects.equals(this.startUserId, samplePath.startUserId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(path, taskId, taskName, description, startUserId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SamplePathDto {\n");
    sb.append("    path: ").append(toIndentedString(path)).append("\n");
    sb.append("    taskId: ").append(toIndentedString(taskId)).append("\n");
    sb.append("    taskName: ").append(toIndentedString(taskName)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    startUserId: ").append(toIndentedString(startUserId)).append("\n");
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

