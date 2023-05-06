package com.stcos.server.pojo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * TaskDto
 */

@JsonTypeName("Task")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-06T16:58:45.971001+08:00[Asia/Shanghai]")
public class TaskDto {

  private String processId;

  private String taskId;

  private String taskName;

  private String description;

  private String startUserId;

  /**
   * Default constructor
   * @deprecated Use {@link TaskDto#TaskDto(String, String, String, String, String)}
   */
  @Deprecated
  public TaskDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public TaskDto(String processId, String taskId, String taskName, String description, String startUserId) {
    this.processId = processId;
    this.taskId = taskId;
    this.taskName = taskName;
    this.description = description;
    this.startUserId = startUserId;
  }

  public TaskDto processId(String processId) {
    this.processId = processId;
    return this;
  }

  /**
   * 当前任务对应的流程实例 id
   * @return processId
  */
  @NotNull 
  @Schema(name = "processId", description = "当前任务对应的流程实例 id", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("processId")
  public String getProcessId() {
    return processId;
  }

  public void setProcessId(String processId) {
    this.processId = processId;
  }

  public TaskDto taskId(String taskId) {
    this.taskId = taskId;
    return this;
  }

  /**
   * 当前任务实例的 id
   * @return taskId
  */
  @NotNull 
  @Schema(name = "taskId", description = "当前任务实例的 id", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("taskId")
  public String getTaskId() {
    return taskId;
  }

  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }

  public TaskDto taskName(String taskName) {
    this.taskName = taskName;
    return this;
  }

  /**
   * 当前任务的名称
   * @return taskName
  */
  @NotNull 
  @Schema(name = "taskName", description = "当前任务的名称", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("taskName")
  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public TaskDto description(String description) {
    this.description = description;
    return this;
  }

  /**
   * 当前任务的描述、状态等
   * @return description
  */
  @NotNull 
  @Schema(name = "description", description = "当前任务的描述、状态等", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public TaskDto startUserId(String startUserId) {
    this.startUserId = startUserId;
    return this;
  }

  /**
   * 当前流程的发起人
   * @return startUserId
  */
  @NotNull 
  @Schema(name = "startUserId", description = "当前流程的发起人", requiredMode = Schema.RequiredMode.REQUIRED)
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
    TaskDto task = (TaskDto) o;
    return Objects.equals(this.processId, task.processId) &&
        Objects.equals(this.taskId, task.taskId) &&
        Objects.equals(this.taskName, task.taskName) &&
        Objects.equals(this.description, task.description) &&
        Objects.equals(this.startUserId, task.startUserId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(processId, taskId, taskName, description, startUserId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TaskDto {\n");
    sb.append("    processId: ").append(toIndentedString(processId)).append("\n");
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

