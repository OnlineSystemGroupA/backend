package com.stcos.server.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * UserIdDto
 */

@JsonTypeName("UserId")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-07-03T15:58:43.666965300+08:00[Asia/Shanghai]")
public class UserIdDto {

  private String userId;

  /**
   * Default constructor
   * @deprecated Use {@link UserIdDto#UserIdDto(String)}
   */
  @Deprecated
  public UserIdDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public UserIdDto(String userId) {
    this.userId = userId;
  }

  public UserIdDto userId(String userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  */
  @NotNull 
  @Schema(name = "userId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("userId")
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserIdDto userId = (UserIdDto) o;
    return Objects.equals(this.userId, userId.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserIdDto {\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
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

