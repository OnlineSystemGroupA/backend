package com.stcos.server.pojo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * LoginParamDto
 */

@JsonTypeName("LoginParam")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-03T10:56:09.306354100+08:00[Asia/Shanghai]")
public class LoginParamDto {

  private String username;

  private String password;

  /**
   * Default constructor
   * @deprecated Use {@link LoginParamDto#LoginParamDto(String, String)}
   */
  @Deprecated
  public LoginParamDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public LoginParamDto(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public LoginParamDto username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Get username
   * @return username
  */
  @NotNull 
  @Schema(name = "username", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("username")
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public LoginParamDto password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Get password
   * @return password
  */
  @NotNull 
  @Schema(name = "password", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("password")
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoginParamDto loginParam = (LoginParamDto) o;
    return Objects.equals(this.username, loginParam.username) &&
        Objects.equals(this.password, loginParam.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoginParamDto {\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
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

