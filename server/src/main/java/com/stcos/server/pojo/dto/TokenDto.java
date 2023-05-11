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
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-09T00:09:21.323897900+08:00[Asia/Shanghai]")
public class TokenDto {

  private String tokenHead;

  private String tokenStr;

  /**
   * Default constructor
   * @deprecated Use {@link TokenDto#TokenDto(String, String)}
   */
  @Deprecated
  public TokenDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public TokenDto(String tokenHead, String tokenStr) {
    this.tokenHead = tokenHead;
    this.tokenStr = tokenStr;
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
        Objects.equals(this.tokenStr, token.tokenStr);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tokenHead, tokenStr);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TokenDto {\n");
    sb.append("    tokenHead: ").append(toIndentedString(tokenHead)).append("\n");
    sb.append("    tokenStr: ").append(toIndentedString(tokenStr)).append("\n");
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

