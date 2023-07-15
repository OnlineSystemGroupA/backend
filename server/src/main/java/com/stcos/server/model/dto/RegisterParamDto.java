package com.stcos.server.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * RegisterParamDto
 */

@JsonTypeName("RegisterParam")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-21T21:36:55.308582200+08:00[Asia/Shanghai]")
public class RegisterParamDto {

  private String username;

  private String email;

  private String password;

  /**
   * Default constructor
   * @deprecated Use {@link RegisterParamDto#RegisterParamDto(String, String, String)}
   */
  @Deprecated
  public RegisterParamDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public RegisterParamDto(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public RegisterParamDto username(String username) {
    this.username = username;
    return this;
  }

  /**
   * 用户名，唯一
   * @return username
  */
  @NotNull 
  @Schema(name = "username", description = "用户名，唯一", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("username")
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public RegisterParamDto email(String email) {
    this.email = email;
    return this;
  }

  /**
   * 用户邮箱
   * @return email
  */
  @NotNull 
  @Schema(name = "email", description = "用户邮箱", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public RegisterParamDto password(String password) {
    this.password = password;
    return this;
  }

  /**
   * 用户密码
   * @return password
  */
  @NotNull 
  @Schema(name = "password", description = "用户密码", requiredMode = Schema.RequiredMode.REQUIRED)
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
    RegisterParamDto registerParam = (RegisterParamDto) o;
    return Objects.equals(this.username, registerParam.username) &&
        Objects.equals(this.email, registerParam.email) &&
        Objects.equals(this.password, registerParam.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, email, password);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RegisterParamDto {\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
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

