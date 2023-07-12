package com.stcos.server.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * OperatorDetailsDto
 */

@JsonTypeName("OperatorDetails")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-07-10T16:14:39.919912100+08:00[Asia/Shanghai]")
public class OperatorDetailsDto {

  private String uid;

  private String jobNumber;

  private String email;

  private String phone;

  private String realName;

  private String department;

  private String position;

  private Boolean isNonLocked;

  /**
   * Default constructor
   * @deprecated Use {@link OperatorDetailsDto#OperatorDetailsDto(String, String, String, String, String, String, String, Boolean)}
   */
  @Deprecated
  public OperatorDetailsDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public OperatorDetailsDto(String uid, String jobNumber, String email, String phone, String realName, String department, String position, Boolean isNonLocked) {
    this.uid = uid;
    this.jobNumber = jobNumber;
    this.email = email;
    this.phone = phone;
    this.realName = realName;
    this.department = department;
    this.position = position;
    this.isNonLocked = isNonLocked;
  }

  public OperatorDetailsDto uid(String uid) {
    this.uid = uid;
    return this;
  }

  /**
   * 员工 ID
   * @return uid
  */
  @NotNull 
  @Schema(name = "uid", description = "员工 ID", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("uid")
  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public OperatorDetailsDto jobNumber(String jobNumber) {
    this.jobNumber = jobNumber;
    return this;
  }

  /**
   * 员工工号
   * @return jobNumber
  */
  @NotNull 
  @Schema(name = "jobNumber", description = "员工工号", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("jobNumber")
  public String getJobNumber() {
    return jobNumber;
  }

  public void setJobNumber(String jobNumber) {
    this.jobNumber = jobNumber;
  }

  public OperatorDetailsDto email(String email) {
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

  public OperatorDetailsDto phone(String phone) {
    this.phone = phone;
    return this;
  }

  /**
   * 联系电话
   * @return phone
  */
  @NotNull 
  @Schema(name = "phone", description = "联系电话", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("phone")
  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public OperatorDetailsDto realName(String realName) {
    this.realName = realName;
    return this;
  }

  /**
   * 姓名
   * @return realName
  */
  @NotNull 
  @Schema(name = "realName", description = "姓名", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("realName")
  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  public OperatorDetailsDto department(String department) {
    this.department = department;
    return this;
  }

  /**
   * 部门
   * @return department
  */
  @NotNull 
  @Schema(name = "department", description = "部门", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("department")
  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public OperatorDetailsDto position(String position) {
    this.position = position;
    return this;
  }

  /**
   * 职位
   * @return position
  */
  @NotNull 
  @Schema(name = "position", description = "职位", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("position")
  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public OperatorDetailsDto isNonLocked(Boolean isNonLocked) {
    this.isNonLocked = isNonLocked;
    return this;
  }

  /**
   * 账号是否被封禁
   * @return isNonLocked
  */
  @NotNull 
  @Schema(name = "isNonLocked", description = "账号是否被封禁", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("isNonLocked")
  public Boolean getIsNonLocked() {
    return isNonLocked;
  }

  public void setIsNonLocked(Boolean isNonLocked) {
    this.isNonLocked = isNonLocked;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OperatorDetailsDto operatorDetails = (OperatorDetailsDto) o;
    return Objects.equals(this.uid, operatorDetails.uid) &&
        Objects.equals(this.jobNumber, operatorDetails.jobNumber) &&
        Objects.equals(this.email, operatorDetails.email) &&
        Objects.equals(this.phone, operatorDetails.phone) &&
        Objects.equals(this.realName, operatorDetails.realName) &&
        Objects.equals(this.department, operatorDetails.department) &&
        Objects.equals(this.position, operatorDetails.position) &&
        Objects.equals(this.isNonLocked, operatorDetails.isNonLocked);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uid, jobNumber, email, phone, realName, department, position, isNonLocked);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OperatorDetailsDto {\n");
    sb.append("    uid: ").append(toIndentedString(uid)).append("\n");
    sb.append("    jobNumber: ").append(toIndentedString(jobNumber)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
    sb.append("    realName: ").append(toIndentedString(realName)).append("\n");
    sb.append("    department: ").append(toIndentedString(department)).append("\n");
    sb.append("    position: ").append(toIndentedString(position)).append("\n");
    sb.append("    isNonLocked: ").append(toIndentedString(isNonLocked)).append("\n");
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

