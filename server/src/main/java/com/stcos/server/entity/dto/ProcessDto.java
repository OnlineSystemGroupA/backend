package com.stcos.server.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * ProcessDto
 */

@JsonTypeName("Process")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-06-27T16:30:13.830100300+08:00[Asia/Shanghai]")
public class ProcessDto {

  private String projectId;

  private String processId;

  private String taskId;

  private String title;

  private String taskName;

  private String assignee;

  private String startUser;

  private String startDate;

  /**
   * Default constructor
   * @deprecated Use {@link ProcessDto#ProcessDto(String, String, String, String, String, String, String, String)}
   */
  @Deprecated
  public ProcessDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ProcessDto(String projectId, String processId, String taskId, String title, String taskName, String assignee, String startUser, String startDate) {
    this.projectId = projectId;
    this.processId = processId;
    this.taskId = taskId;
    this.title = title;
    this.taskName = taskName;
    this.assignee = assignee;
    this.startUser = startUser;
    this.startDate = startDate;
  }

  public ProcessDto projectId(String projectId) {
    this.projectId = projectId;
    return this;
  }

  /**
   * 软件测试项目的 ID
   * @return projectId
  */
  @NotNull 
  @Schema(name = "projectId", description = "软件测试项目的 ID", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("projectId")
  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  public ProcessDto processId(String processId) {
    this.processId = processId;
    return this;
  }

  /**
   * 流程实例 ID
   * @return processId
  */
  @NotNull 
  @Schema(name = "processId", description = "流程实例 ID", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("processId")
  public String getProcessId() {
    return processId;
  }

  public void setProcessId(String processId) {
    this.processId = processId;
  }

  public ProcessDto taskId(String taskId) {
    this.taskId = taskId;
    return this;
  }

  /**
   * 当前任务的实例 ID
   * @return taskId
  */
  @NotNull 
  @Schema(name = "taskId", description = "当前任务的实例 ID", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("taskId")
  public String getTaskId() {
    return taskId;
  }

  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }

  public ProcessDto title(String title) {
    this.title = title;
    return this;
  }

  /**
   * 待测试软件名称
   * @return title
  */
  @NotNull 
  @Schema(name = "title", description = "待测试软件名称", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public ProcessDto taskName(String taskName) {
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

  public ProcessDto assignee(String assignee) {
    this.assignee = assignee;
    return this;
  }

  /**
   * 当前任务被分配人姓名
   * @return assignee
  */
  @NotNull 
  @Schema(name = "assignee", description = "当前任务被分配人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("assignee")
  public String getAssignee() {
    return assignee;
  }

  public void setAssignee(String assignee) {
    this.assignee = assignee;
  }

  public ProcessDto startUser(String startUser) {
    this.startUser = startUser;
    return this;
  }

  /**
   * 流程发起人姓名
   * @return startUser
  */
  @NotNull 
  @Schema(name = "startUser", description = "流程发起人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("startUser")
  public String getStartUser() {
    return startUser;
  }

  public void setStartUser(String startUser) {
    this.startUser = startUser;
  }

  public ProcessDto startDate(String startDate) {
    this.startDate = startDate;
    return this;
  }

  /**
   * 流程发起时间
   * @return startDate
  */
  @NotNull 
  @Schema(name = "startDate", description = "流程发起时间", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("startDate")
  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProcessDto process = (ProcessDto) o;
    return Objects.equals(this.projectId, process.projectId) &&
        Objects.equals(this.processId, process.processId) &&
        Objects.equals(this.taskId, process.taskId) &&
        Objects.equals(this.title, process.title) &&
        Objects.equals(this.taskName, process.taskName) &&
        Objects.equals(this.assignee, process.assignee) &&
        Objects.equals(this.startUser, process.startUser) &&
        Objects.equals(this.startDate, process.startDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(projectId, processId, taskId, title, taskName, assignee, startUser, startDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProcessDto {\n");
    sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
    sb.append("    processId: ").append(toIndentedString(processId)).append("\n");
    sb.append("    taskId: ").append(toIndentedString(taskId)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    taskName: ").append(toIndentedString(taskName)).append("\n");
    sb.append("    assignee: ").append(toIndentedString(assignee)).append("\n");
    sb.append("    startUser: ").append(toIndentedString(startUser)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
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

