package com.stcos.server.entity.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * LockDto
 */

@JsonTypeName("Lock")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-07-10T14:31:57.470003700+08:00[Asia/Shanghai]")
public class LockDto {

  private Boolean isLock;

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
  public LockDto(Boolean isLock) {
    this.isLock = isLock;
  }

  public LockDto isLock(Boolean isLock) {
    this.isLock = isLock;
    return this;
  }

  /**
   * 流程所在任务名称
   * @return isLock
  */
  @NotNull 
  @Schema(name = "isLock", description = "流程所在任务名称", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("isLock")
  public Boolean getIsLock() {
    return isLock;
  }

  public void setIsLock(Boolean isLock) {
    this.isLock = isLock;
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
    return Objects.equals(this.isLock, lock.isLock);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isLock);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LockDto {\n");
    sb.append("    isLock: ").append(toIndentedString(isLock)).append("\n");
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

