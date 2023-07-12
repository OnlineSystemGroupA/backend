package com.stcos.server.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * LockDto
 */

@JsonTypeName("Lock")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-07-10T16:14:39.919912100+08:00[Asia/Shanghai]")
public class LockDto {

  private Boolean doLock;

  /**
   * Default constructor
   * @deprecated Use {@link LockDto#LockDto(Boolean)}
   */
  @Deprecated
  public LockDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public LockDto(Boolean doLock) {
    this.doLock = doLock;
  }

  public LockDto doLock(Boolean doLock) {
    this.doLock = doLock;
    return this;
  }

  /**
   * 选择对其进行封禁/解封
   * @return doLock
  */
  @NotNull 
  @Schema(name = "doLock", description = "选择对其进行封禁/解封", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("doLock")
  public Boolean getDoLock() {
    return doLock;
  }

  public void setDoLock(Boolean doLock) {
    this.doLock = doLock;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LockDto lock = (LockDto) o;
    return Objects.equals(this.doLock, lock.doLock);
  }

  @Override
  public int hashCode() {
    return Objects.hash(doLock);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LockDto {\n");
    sb.append("    doLock: ").append(toIndentedString(doLock)).append("\n");
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

