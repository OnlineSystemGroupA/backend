package com.stcos.server.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * ProcessDetailsDto
 */

@JsonTypeName("ProcessDetails")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-07-11T17:11:10.573960700+08:00[Asia/Shanghai]")
public class ProcessDetailsDto {

  private Long projectId;

  private String title;

  private String version;

  private String testType;

  private String applicationDate;

  private String applicant;

  private String company;

  private String telephone;

  private String email;

  private String address;

  private String startDate;

  private String dueDate;

  private Integer index;

  private String currentTaskName;

  private String assignee;

  /**
   * Default constructor
   * @deprecated Use {@link ProcessDetailsDto#ProcessDetailsDto(Long, String, String, String, String, String, String, String, String, String, String, String, Integer, String, String)}
   */
  @Deprecated
  public ProcessDetailsDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ProcessDetailsDto(Long projectId, String title, String version, String testType, String applicationDate, String applicant, String company, String telephone, String email, String address, String startDate, String dueDate, Integer index, String currentTaskName, String assignee) {
    this.projectId = projectId;
    this.title = title;
    this.version = version;
    this.testType = testType;
    this.applicationDate = applicationDate;
    this.applicant = applicant;
    this.company = company;
    this.telephone = telephone;
    this.email = email;
    this.address = address;
    this.startDate = startDate;
    this.dueDate = dueDate;
    this.index = index;
    this.currentTaskName = currentTaskName;
    this.assignee = assignee;
  }

  public ProcessDetailsDto projectId(Long projectId) {
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

  public ProcessDetailsDto title(String title) {
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

  public ProcessDetailsDto version(String version) {
    this.version = version;
    return this;
  }

  /**
   * 软件版本
   * @return version
  */
  @NotNull 
  @Schema(name = "version", description = "软件版本", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("version")
  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public ProcessDetailsDto testType(String testType) {
    this.testType = testType;
    return this;
  }

  /**
   * 测试类型
   * @return testType
  */
  @NotNull 
  @Schema(name = "testType", description = "测试类型", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("testType")
  public String getTestType() {
    return testType;
  }

  public void setTestType(String testType) {
    this.testType = testType;
  }

  public ProcessDetailsDto applicationDate(String applicationDate) {
    this.applicationDate = applicationDate;
    return this;
  }

  /**
   * 申请日期
   * @return applicationDate
  */
  @NotNull 
  @Schema(name = "applicationDate", description = "申请日期", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("applicationDate")
  public String getApplicationDate() {
    return applicationDate;
  }

  public void setApplicationDate(String applicationDate) {
    this.applicationDate = applicationDate;
  }

  public ProcessDetailsDto applicant(String applicant) {
    this.applicant = applicant;
    return this;
  }

  /**
   * 申请人
   * @return applicant
  */
  @NotNull 
  @Schema(name = "applicant", description = "申请人", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("applicant")
  public String getApplicant() {
    return applicant;
  }

  public void setApplicant(String applicant) {
    this.applicant = applicant;
  }

  public ProcessDetailsDto company(String company) {
    this.company = company;
    return this;
  }

  /**
   * 公司名称
   * @return company
  */
  @NotNull 
  @Schema(name = "company", description = "公司名称", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("company")
  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public ProcessDetailsDto telephone(String telephone) {
    this.telephone = telephone;
    return this;
  }

  /**
   * 电话
   * @return telephone
  */
  @NotNull 
  @Schema(name = "telephone", description = "电话", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("telephone")
  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public ProcessDetailsDto email(String email) {
    this.email = email;
    return this;
  }

  /**
   * 邮箱
   * @return email
  */
  @NotNull 
  @Schema(name = "email", description = "邮箱", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public ProcessDetailsDto address(String address) {
    this.address = address;
    return this;
  }

  /**
   * 地址
   * @return address
  */
  @NotNull 
  @Schema(name = "address", description = "地址", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("address")
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public ProcessDetailsDto startDate(String startDate) {
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

  public ProcessDetailsDto dueDate(String dueDate) {
    this.dueDate = dueDate;
    return this;
  }

  /**
   * 预计结束时间
   * @return dueDate
  */
  @NotNull 
  @Schema(name = "dueDate", description = "预计结束时间", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("dueDate")
  public String getDueDate() {
    return dueDate;
  }

  public void setDueDate(String dueDate) {
    this.dueDate = dueDate;
  }

  public ProcessDetailsDto index(Integer index) {
    this.index = index;
    return this;
  }

  /**
   * 当前任务所在组的编号
   * @return index
  */
  @NotNull 
  @Schema(name = "index", description = "当前任务所在组的编号", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("index")
  public Integer getIndex() {
    return index;
  }

  public void setIndex(Integer index) {
    this.index = index;
  }

  public ProcessDetailsDto currentTaskName(String currentTaskName) {
    this.currentTaskName = currentTaskName;
    return this;
  }

  /**
   * 当前任务名称
   * @return currentTaskName
  */
  @NotNull 
  @Schema(name = "currentTaskName", description = "当前任务名称", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("currentTaskName")
  public String getCurrentTaskName() {
    return currentTaskName;
  }

  public void setCurrentTaskName(String currentTaskName) {
    this.currentTaskName = currentTaskName;
  }

  public ProcessDetailsDto assignee(String assignee) {
    this.assignee = assignee;
    return this;
  }

  /**
   * 当前被分配人姓名
   * @return assignee
  */
  @NotNull 
  @Schema(name = "assignee", description = "当前被分配人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("assignee")
  public String getAssignee() {
    return assignee;
  }

  public void setAssignee(String assignee) {
    this.assignee = assignee;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProcessDetailsDto processDetails = (ProcessDetailsDto) o;
    return Objects.equals(this.projectId, processDetails.projectId) &&
        Objects.equals(this.title, processDetails.title) &&
        Objects.equals(this.version, processDetails.version) &&
        Objects.equals(this.testType, processDetails.testType) &&
        Objects.equals(this.applicationDate, processDetails.applicationDate) &&
        Objects.equals(this.applicant, processDetails.applicant) &&
        Objects.equals(this.company, processDetails.company) &&
        Objects.equals(this.telephone, processDetails.telephone) &&
        Objects.equals(this.email, processDetails.email) &&
        Objects.equals(this.address, processDetails.address) &&
        Objects.equals(this.startDate, processDetails.startDate) &&
        Objects.equals(this.dueDate, processDetails.dueDate) &&
        Objects.equals(this.index, processDetails.index) &&
        Objects.equals(this.currentTaskName, processDetails.currentTaskName) &&
        Objects.equals(this.assignee, processDetails.assignee);
  }

  @Override
  public int hashCode() {
    return Objects.hash(projectId, title, version, testType, applicationDate, applicant, company, telephone, email, address, startDate, dueDate, index, currentTaskName, assignee);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProcessDetailsDto {\n");
    sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    testType: ").append(toIndentedString(testType)).append("\n");
    sb.append("    applicationDate: ").append(toIndentedString(applicationDate)).append("\n");
    sb.append("    applicant: ").append(toIndentedString(applicant)).append("\n");
    sb.append("    company: ").append(toIndentedString(company)).append("\n");
    sb.append("    telephone: ").append(toIndentedString(telephone)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    dueDate: ").append(toIndentedString(dueDate)).append("\n");
    sb.append("    index: ").append(toIndentedString(index)).append("\n");
    sb.append("    currentTaskName: ").append(toIndentedString(currentTaskName)).append("\n");
    sb.append("    assignee: ").append(toIndentedString(assignee)).append("\n");
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

