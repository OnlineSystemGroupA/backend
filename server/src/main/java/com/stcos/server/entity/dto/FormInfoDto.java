package com.stcos.server.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * FormInfoDto
 */

@JsonTypeName("FormInfo")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-07-11T14:50:24.301854500+08:00[Asia/Shanghai]")
public class FormInfoDto {

  private String formType;

  private String createDate;

  private String formState;

  /**
   * Default constructor
   * @deprecated Use {@link FormInfoDto#FormInfoDto(String, String, String)}
   */
  @Deprecated
  public FormInfoDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public FormInfoDto(String formType, String createDate, String formState) {
    this.formType = formType;
    this.createDate = createDate;
    this.formState = formState;
  }

  public FormInfoDto formType(String formType) {
    this.formType = formType;
    return this;
  }

  /**
   * 表单类型
   * @return formType
  */
  @NotNull 
  @Schema(name = "formType", description = "表单类型", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("formType")
  public String getFormType() {
    return formType;
  }

  public void setFormType(String formType) {
    this.formType = formType;
  }

  public FormInfoDto createDate(String createDate) {
    this.createDate = createDate;
    return this;
  }

  /**
   * 表单创建日期
   * @return createDate
  */
  @NotNull 
  @Schema(name = "createDate", description = "表单创建日期", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("createDate")
  public String getCreateDate() {
    return createDate;
  }

  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }

  public FormInfoDto formState(String formState) {
    this.formState = formState;
    return this;
  }

  /**
   * 表单当前状态
   * @return formState
  */
  @NotNull 
  @Schema(name = "formState", description = "表单当前状态", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("formState")
  public String getFormState() {
    return formState;
  }

  public void setFormState(String formState) {
    this.formState = formState;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FormInfoDto formInfo = (FormInfoDto) o;
    return Objects.equals(this.formType, formInfo.formType) &&
        Objects.equals(this.createDate, formInfo.createDate) &&
        Objects.equals(this.formState, formInfo.formState);
  }

  @Override
  public int hashCode() {
    return Objects.hash(formType, createDate, formState);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FormInfoDto {\n");
    sb.append("    formType: ").append(toIndentedString(formType)).append("\n");
    sb.append("    createDate: ").append(toIndentedString(createDate)).append("\n");
    sb.append("    formState: ").append(toIndentedString(formState)).append("\n");
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

