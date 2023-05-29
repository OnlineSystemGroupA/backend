package com.stcos.server.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * FormMetadataDto
 */

@JsonTypeName("FormMetadata")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-21T21:36:55.308582200+08:00[Asia/Shanghai]")
public class FormMetadataDto {

  private Long formMetadataId;

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
  public FormMetadataDto(Long formMetadataId, String formName) {
    this.formMetadataId = formMetadataId;
    this.formName = formName;
  }

  public FormMetadataDto formMetadataId(Long formMetadataId) {
    this.formMetadataId = formMetadataId;
    return this;
  }

  /**
   * 表单元数据 ID
   * @return formMetadataId
  */
  @NotNull 
  @Schema(name = "formMetadataId", description = "表单元数据 ID", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("formMetadataId")
  public Long getFormMetadataId() {
    return formMetadataId;
  }

  public void setFormMetadataId(Long formMetadataId) {
    this.formMetadataId = formMetadataId;
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
    FormMetadataDto formMetadata = (FormMetadataDto) o;
    return Objects.equals(this.formMetadataId, formMetadata.formMetadataId) &&
        Objects.equals(this.formName, formMetadata.formName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(formMetadataId, formName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FormMetadataDto {\n");
    sb.append("    formMetadataId: ").append(toIndentedString(formMetadataId)).append("\n");
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

