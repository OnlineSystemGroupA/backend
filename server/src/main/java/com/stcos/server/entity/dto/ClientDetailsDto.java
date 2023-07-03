package com.stcos.server.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * ClientDetailsDto
 */

@JsonTypeName("ClientDetails")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-07-02T14:20:28.737283600+08:00[Asia/Shanghai]")
public class ClientDetailsDto {

  private String username;

  private String createdDate;

  private String realName;

  private String email;

  private String phone;

  private String gender;

  private String company;

  private String companyTelephone;

  private String companyFax;

  private String companyAddress;

  private String companyPostcode;

  private String companyWebsite;

  private String companyEmail;

  private String companyPhone;

  /**
   * Default constructor
   * @deprecated Use {@link ClientDetailsDto#ClientDetailsDto(String, String, String, String, String, String, String, String, String, String, String, String, String, String)}
   */
  @Deprecated
  public ClientDetailsDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ClientDetailsDto(String username, String createdDate, String realName, String email, String phone, String gender, String company, String companyTelephone, String companyFax, String companyAddress, String companyPostcode, String companyWebsite, String companyEmail, String companyPhone) {
    this.username = username;
    this.createdDate = createdDate;
    this.realName = realName;
    this.email = email;
    this.phone = phone;
    this.gender = gender;
    this.company = company;
    this.companyTelephone = companyTelephone;
    this.companyFax = companyFax;
    this.companyAddress = companyAddress;
    this.companyPostcode = companyPostcode;
    this.companyWebsite = companyWebsite;
    this.companyEmail = companyEmail;
    this.companyPhone = companyPhone;
  }

  public ClientDetailsDto username(String username) {
    this.username = username;
    return this;
  }

  /**
   * 用户名
   * @return username
  */
  @NotNull 
  @Schema(name = "username", description = "用户名", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("username")
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public ClientDetailsDto createdDate(String createdDate) {
    this.createdDate = createdDate;
    return this;
  }

  /**
   * 账号创建时间
   * @return createdDate
  */
  @NotNull 
  @Schema(name = "createdDate", description = "账号创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("createdDate")
  public String getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }

  public ClientDetailsDto realName(String realName) {
    this.realName = realName;
    return this;
  }

  /**
   * 用户的真实姓名
   * @return realName
  */
  @NotNull 
  @Schema(name = "realName", description = "用户的真实姓名", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("realName")
  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  public ClientDetailsDto email(String email) {
    this.email = email;
    return this;
  }

  /**
   * 邮箱
   * @return email
  */
  @NotNull 
  @Schema(name = "email", description = "邮箱", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public ClientDetailsDto phone(String phone) {
    this.phone = phone;
    return this;
  }

  /**
   * 联系电话
   * @return phone
  */
  @NotNull 
  @Schema(name = "phone", description = "联系电话", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("phone")
  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public ClientDetailsDto gender(String gender) {
    this.gender = gender;
    return this;
  }

  /**
   * 性别
   * @return gender
  */
  @NotNull 
  @Schema(name = "gender", description = "性别", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("gender")
  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public ClientDetailsDto company(String company) {
    this.company = company;
    return this;
  }

  /**
   * 公司名称
   * @return company
  */
  @NotNull 
  @Schema(name = "company", description = "公司名称", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("company")
  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public ClientDetailsDto companyTelephone(String companyTelephone) {
    this.companyTelephone = companyTelephone;
    return this;
  }

  /**
   * 公司电话号
   * @return companyTelephone
  */
  @NotNull 
  @Schema(name = "companyTelephone", description = "公司电话号", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("companyTelephone")
  public String getCompanyTelephone() {
    return companyTelephone;
  }

  public void setCompanyTelephone(String companyTelephone) {
    this.companyTelephone = companyTelephone;
  }

  public ClientDetailsDto companyFax(String companyFax) {
    this.companyFax = companyFax;
    return this;
  }

  /**
   * 公司传真
   * @return companyFax
  */
  @NotNull 
  @Schema(name = "companyFax", description = "公司传真", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("companyFax")
  public String getCompanyFax() {
    return companyFax;
  }

  public void setCompanyFax(String companyFax) {
    this.companyFax = companyFax;
  }

  public ClientDetailsDto companyAddress(String companyAddress) {
    this.companyAddress = companyAddress;
    return this;
  }

  /**
   * 公司地址
   * @return companyAddress
  */
  @NotNull 
  @Schema(name = "companyAddress", description = "公司地址", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("companyAddress")
  public String getCompanyAddress() {
    return companyAddress;
  }

  public void setCompanyAddress(String companyAddress) {
    this.companyAddress = companyAddress;
  }

  public ClientDetailsDto companyPostcode(String companyPostcode) {
    this.companyPostcode = companyPostcode;
    return this;
  }

  /**
   * 公司邮编
   * @return companyPostcode
  */
  @NotNull 
  @Schema(name = "companyPostcode", description = "公司邮编", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("companyPostcode")
  public String getCompanyPostcode() {
    return companyPostcode;
  }

  public void setCompanyPostcode(String companyPostcode) {
    this.companyPostcode = companyPostcode;
  }

  public ClientDetailsDto companyWebsite(String companyWebsite) {
    this.companyWebsite = companyWebsite;
    return this;
  }

  /**
   * 公司网址
   * @return companyWebsite
  */
  @NotNull 
  @Schema(name = "companyWebsite", description = "公司网址", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("companyWebsite")
  public String getCompanyWebsite() {
    return companyWebsite;
  }

  public void setCompanyWebsite(String companyWebsite) {
    this.companyWebsite = companyWebsite;
  }

  public ClientDetailsDto companyEmail(String companyEmail) {
    this.companyEmail = companyEmail;
    return this;
  }

  /**
   * 公司邮箱
   * @return companyEmail
  */
  @NotNull 
  @Schema(name = "companyEmail", description = "公司邮箱", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("companyEmail")
  public String getCompanyEmail() {
    return companyEmail;
  }

  public void setCompanyEmail(String companyEmail) {
    this.companyEmail = companyEmail;
  }

  public ClientDetailsDto companyPhone(String companyPhone) {
    this.companyPhone = companyPhone;
    return this;
  }

  /**
   * 公司电话
   * @return companyPhone
  */
  @NotNull 
  @Schema(name = "companyPhone", description = "公司电话", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("companyPhone")
  public String getCompanyPhone() {
    return companyPhone;
  }

  public void setCompanyPhone(String companyPhone) {
    this.companyPhone = companyPhone;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClientDetailsDto clientDetails = (ClientDetailsDto) o;
    return Objects.equals(this.username, clientDetails.username) &&
        Objects.equals(this.createdDate, clientDetails.createdDate) &&
        Objects.equals(this.realName, clientDetails.realName) &&
        Objects.equals(this.email, clientDetails.email) &&
        Objects.equals(this.phone, clientDetails.phone) &&
        Objects.equals(this.gender, clientDetails.gender) &&
        Objects.equals(this.company, clientDetails.company) &&
        Objects.equals(this.companyTelephone, clientDetails.companyTelephone) &&
        Objects.equals(this.companyFax, clientDetails.companyFax) &&
        Objects.equals(this.companyAddress, clientDetails.companyAddress) &&
        Objects.equals(this.companyPostcode, clientDetails.companyPostcode) &&
        Objects.equals(this.companyWebsite, clientDetails.companyWebsite) &&
        Objects.equals(this.companyEmail, clientDetails.companyEmail) &&
        Objects.equals(this.companyPhone, clientDetails.companyPhone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, createdDate, realName, email, phone, gender, company, companyTelephone, companyFax, companyAddress, companyPostcode, companyWebsite, companyEmail, companyPhone);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClientDetailsDto {\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    createdDate: ").append(toIndentedString(createdDate)).append("\n");
    sb.append("    realName: ").append(toIndentedString(realName)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
    sb.append("    gender: ").append(toIndentedString(gender)).append("\n");
    sb.append("    company: ").append(toIndentedString(company)).append("\n");
    sb.append("    companyTelephone: ").append(toIndentedString(companyTelephone)).append("\n");
    sb.append("    companyFax: ").append(toIndentedString(companyFax)).append("\n");
    sb.append("    companyAddress: ").append(toIndentedString(companyAddress)).append("\n");
    sb.append("    companyPostcode: ").append(toIndentedString(companyPostcode)).append("\n");
    sb.append("    companyWebsite: ").append(toIndentedString(companyWebsite)).append("\n");
    sb.append("    companyEmail: ").append(toIndentedString(companyEmail)).append("\n");
    sb.append("    companyPhone: ").append(toIndentedString(companyPhone)).append("\n");
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

