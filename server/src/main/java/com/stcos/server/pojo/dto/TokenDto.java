package com.stcos.server.pojo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * TokenDto
 */

@JsonTypeName("Token")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-03T23:03:21.844871700+08:00[Asia/Shanghai]")
public class TokenDto {

  private String tokenHead;

  private String tokenStr;

  private String userType;

  /**
   * Default constructor
   * @deprecated Use {@link TokenDto#TokenDto(String, String, String)}
   */
  @Deprecated
  public TokenDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public TokenDto(String tokenHead, String tokenStr, String userType) {
    this.tokenHead = tokenHead;
    this.tokenStr = tokenStr;
    this.userType = userType;
  }

  public TokenDto tokenHead(String tokenHead) {
    this.tokenHead = tokenHead;
    return this;
  }

  /**
   * token 的头部，前端请求携带 token 时需要将其拼接在 token 之前
   * @return tokenHead
  */
  @NotNull 
  @Schema(name = "tokenHead", description = "token 的头部，前端请求携带 token 时需要将其拼接在 token 之前", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("tokenHead")
  public String getTokenHead() {
    return tokenHead;
  }

  public void setTokenHead(String tokenHead) {
    this.tokenHead = tokenHead;
  }

  public TokenDto tokenStr(String tokenStr) {
    this.tokenStr = tokenStr;
    return this;
  }

  /**
   * token 字符串
   * @return tokenStr
  */
  @NotNull 
  @Schema(name = "tokenStr", description = "token 字符串", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("tokenStr")
  public String getTokenStr() {
    return tokenStr;
  }

  public void setTokenStr(String tokenStr) {
    this.tokenStr = tokenStr;
  }

  public TokenDto userType(String userType) {
    this.userType = userType;
    return this;
  }

  /**
   * 成功登录的用户类型：admin、operator、client.
   * @return userType
  */
  @NotNull 
  @Schema(name = "userType", description = "成功登录的用户类型：admin、operator、client.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("userType")
  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TokenDto token = (TokenDto) o;
    return Objects.equals(this.tokenHead, token.tokenHead) &&
        Objects.equals(this.tokenStr, token.tokenStr) &&
        Objects.equals(this.userType, token.userType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tokenHead, tokenStr, userType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TokenDto {\n");
    sb.append("    tokenHead: ").append(toIndentedString(tokenHead)).append("\n");
    sb.append("    tokenStr: ").append(toIndentedString(tokenStr)).append("\n");
    sb.append("    userType: ").append(toIndentedString(userType)).append("\n");
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

