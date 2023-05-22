package com.stcos.server.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * ProcessIdDto
 */

@JsonTypeName("ProcessId")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-21T21:36:55.308582200+08:00[Asia/Shanghai]")
public class ProcessIdDto {

  private String processId;

  /**
   * Default constructor
   * @deprecated Use {@link ProcessIdDto#ProcessIdDto(String)}
   */
  @Deprecated
  public ProcessIdDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ProcessIdDto(String processId) {
    this.processId = processId;
  }

  public ProcessIdDto processId(String processId) {
    this.processId = processId;
    return this;
  }

  /**
   * 新委托流程的 ID
   * @return processId
  */
  @NotNull 
  @Schema(name = "processId", description = "新委托流程的 ID", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("processId")
  public String getProcessId() {
    return processId;
  }

  public void setProcessId(String processId) {
    this.processId = processId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProcessIdDto processId = (ProcessIdDto) o;
    return Objects.equals(this.processId, processId.processId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(processId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProcessIdDto {\n");
    sb.append("    processId: ").append(toIndentedString(processId)).append("\n");
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

