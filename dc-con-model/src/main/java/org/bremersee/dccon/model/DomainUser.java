/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bremersee.dccon.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

/**
 * Domain user.
 */
@ApiModel(description = "Domain user.")
@Validated
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@SuppressWarnings("unused")
public class DomainUser extends CommonAttributes implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("userName")
  private String userName = null;

  @JsonProperty("enabled")
  private Boolean enabled = Boolean.FALSE;

  @JsonProperty("firstName")
  private String firstName = null;

  @JsonProperty("lastName")
  private String lastName = null;

  @JsonProperty("passwordLastSet")
  private OffsetDateTime passwordLastSet = null;

  @JsonProperty("displayName")
  private String displayName = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("telephoneNumber")
  private String telephoneNumber = null;

  @JsonProperty("mobile")
  private String mobile = null;

  @JsonProperty("groups")
  private List<Name> groups = null;

  @JsonProperty("password")
  private String password = null;

  /**
   * Instantiates a new domain user.
   *
   * @param distinguishedName the distinguished name
   * @param created           the created
   * @param modified          the modified
   * @param userName          the user name
   * @param enabled           the enabled
   * @param firstName         the first name
   * @param lastName          the last name
   * @param passwordLastSet   the password last set
   * @param displayName       the display name
   * @param email             the email
   * @param telephoneNumber   the telephone number
   * @param mobile            the mobile
   * @param groups            the groups
   * @param password          the password
   */
  @Builder
  public DomainUser(
      String distinguishedName,
      OffsetDateTime created,
      OffsetDateTime modified,
      String userName,
      Boolean enabled,
      String firstName,
      String lastName,
      OffsetDateTime passwordLastSet,
      String displayName,
      String email,
      String telephoneNumber,
      String mobile,
      List<Name> groups,
      String password) {

    super(distinguishedName, created, modified);
    this.userName = userName;
    this.enabled = Boolean.TRUE.equals(enabled);
    this.firstName = firstName;
    this.lastName = lastName;
    this.passwordLastSet = passwordLastSet;
    this.displayName = displayName;
    this.email = email;
    this.telephoneNumber = telephoneNumber;
    this.mobile = mobile;
    this.groups = groups;
    this.password = password;
  }

  /**
   * The user name of the domain user.
   *
   * @return userName user name
   */
  @ApiModelProperty(value = "The user name of the domain user.")
  public String getUserName() {
    return userName;
  }

  /**
   * Sets user name.
   *
   * @param userName the user name
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   * Is the domain user enabled.
   *
   * @return enabled enabled
   */
  @ApiModelProperty(value = "Is the domain user enabled?")
  public Boolean getEnabled() {
    return Boolean.TRUE.equals(enabled);
  }

  /**
   * Sets enabled.
   *
   * @param enabled the enabled
   */
  public void setEnabled(Boolean enabled) {
    this.enabled = Boolean.TRUE.equals(enabled);
  }

  /**
   * Gets first name.
   *
   * @return the first name
   */
  @ApiModelProperty(value = "The first name of the domain user.")
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets first name.
   *
   * @param firstName the first name
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Gets last name.
   *
   * @return the last name
   */
  @ApiModelProperty(value = "The last name of the domain user.")
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets last name.
   *
   * @param lastName the last name
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Get password last set.
   *
   * @return password last set
   */
  @ApiModelProperty(value = "Date of the last password change.")
  public OffsetDateTime getPasswordLastSet() {
    return passwordLastSet;
  }

  /**
   * Sets password last set.
   *
   * @param passwordLastSet the password last set
   */
  public void setPasswordLastSet(OffsetDateTime passwordLastSet) {
    this.passwordLastSet = passwordLastSet;
  }

  /**
   * Get display name.
   *
   * @return display name
   */
  @ApiModelProperty(value = "The display name.")
  public String getDisplayName() {
    return displayName;
  }

  /**
   * Sets display name.
   *
   * @param displayName the display name
   */
  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  /**
   * The mail address of the domain user.
   *
   * @return email email
   */
  @ApiModelProperty(value = "The mail address of the domain user.")
  public String getEmail() {
    return email;
  }

  /**
   * Sets email.
   *
   * @param email the email
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Gets telephone number.
   *
   * @return the telephone number
   */
  @ApiModelProperty(value = "The telephone number of the domain user.")
  public String getTelephoneNumber() {
    return telephoneNumber;
  }

  /**
   * Sets telephone number.
   *
   * @param telephoneNumber the telephone number
   */
  public void setTelephoneNumber(String telephoneNumber) {
    this.telephoneNumber = telephoneNumber;
  }

  /**
   * The mobile number of the domain user.
   *
   * @return mobile mobile
   */
  @ApiModelProperty(value = "The mobile number of the domain user.")
  public String getMobile() {
    return mobile;
  }

  /**
   * Sets mobile.
   *
   * @param mobile the mobile
   */
  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  /**
   * The groups of the domain user.
   *
   * @return groups groups
   */
  @ApiModelProperty(value = "The groups of the domain user.")
  public List<Name> getGroups() {
    if (groups == null) {
      groups = new ArrayList<>();
    }
    return groups;
  }

  /**
   * Sets groups.
   *
   * @param groups the groups
   */
  public void setGroups(List<Name> groups) {
    this.groups = groups;
  }

  /**
   * The password of the domain user.
   *
   * @return password password
   */
  @ApiModelProperty(value = "The password of the domain user.")
  public String getPassword() {
    return password;
  }

  /**
   * Sets password.
   *
   * @param password the password
   */
  public void setPassword(String password) {
    this.password = password;
  }

}

