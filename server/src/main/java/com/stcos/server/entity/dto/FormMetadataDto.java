package com.stcos.server.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * FormIndexDto
 */

@JsonTypeName("FormIndex")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-21T21:36:55.308582200+08:00[Asia/Shanghai]")
public class FormMetadataDto {

  private Long formIndexId;

  private String formName;

  /**
   * Default constructor
   * @deprecated Use {@link FormMetadataDto#FormMetadataDto(Long, String)}
   */
  @Deprecated
  public FormMetadataDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public FormMetadataDto(Long formIndexId, String formName) {
    this.formIndexId = formIndexId;
    this.formName = formName;
  }

  public FormMetadataDto formIndexId(Long formIndexId) {
    this.formIndexId = formIndexId;
    return this;
  }

  /**
   * 表单索引 ID
   * @return formIndexId
  */
  @NotNull 
  @Schema(name = "formIndexId", description = "表单索引 ID", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("formIndexId")
  public Long getFormIndexId() {
    return formIndexId;
  }

  public void setFormIndexId(Long formIndexId) {
    this.formIndexId = formIndexId;
  }

  public FormMetadataDto formName(String formName) {
    this.formName = formName;
    return this;
  }

  /**
   * 表单名称
   * @return formName
  */
  @NotNull 
  @Schema(name = "formName", description = "表单名称", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("formName")
  public String getFormName() {
    return formName;
  }

  public void setFormName(String formName) {
    this.formName = formName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FormMetadataDto formIndex = (FormMetadataDto) o;
    return Objects.equals(this.formIndexId, formIndex.formIndexId) &&
        Objects.equals(this.formName, formIndex.formName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(formIndexId, formName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FormIndexDto {\n");
    sb.append("    formIndexId: ").append(toIndentedString(formIndexId)).append("\n");
    sb.append("    formName: ").append(toIndentedString(formName)).append("\n");
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

