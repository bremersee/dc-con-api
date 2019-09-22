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
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

/**
 * Domain user.
 */
@ApiModel(description = "Domain user.")
@Validated
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@SuppressWarnings("unused")
public class DomainUser extends CommonAttributes {

  /**
   * The constant DEFAULT_SORT_ORDER.
   */
  public static final String DEFAULT_SORT_ORDER = "userName";

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(value = "The user name of the domain user.", required = true)
  @JsonProperty(value = "userName", required = true)
  @NotNull
  private String userName;

  @ApiModelProperty(value = "Specifies whether the user is enabled or not.")
  @JsonProperty("enabled")
  private Boolean enabled = Boolean.FALSE;

  @ApiModelProperty(value = "The first name of the domain user.")
  @JsonProperty("firstName")
  private String firstName;

  @ApiModelProperty(value = "The last name of the domain user.")
  @JsonProperty("lastName")
  private String lastName;

  @ApiModelProperty(value = "The display name.")
  @JsonProperty("displayName")
  private String displayName;

  @ApiModelProperty(value = "The email address of the domain user.")
  @JsonProperty("email")
  private String email;

  @ApiModelProperty(value = "The telephone number of the domain user.")
  @JsonProperty("telephoneNumber")
  private String telephoneNumber;

  @ApiModelProperty(value = "The mobile number of the domain user.")
  @JsonProperty("mobile")
  private String mobile;

  @ApiModelProperty(value = "The groups of the domain user.")
  @JsonProperty("groups")
  private List<String> groups;

  @ApiModelProperty(value = "The home directory of the domain user.")
  @JsonProperty("homeDirectory")
  private String homeDirectory;

  @ApiModelProperty(value = "The unix home directory of the domain user.")
  @JsonProperty("unixHomeDirectory")
  private String unixHomeDirectory;

  @ApiModelProperty(value = "The login shell of the domain user.")
  @JsonProperty("loginShell")
  private String loginShell;

  @ApiModelProperty(value = "The last logon time of the domain user.")
  @JsonProperty("lastLogon")
  private OffsetDateTime lastLogon;

  @ApiModelProperty(value = "The logon count of the domain user.")
  @JsonProperty("logonCount")
  private Integer logonCount;

  @ApiModelProperty(value = "Date of the last password change.")
  @JsonProperty("passwordLastSet")
  private OffsetDateTime passwordLastSet;

  @ApiModelProperty(value = "The password of the domain user.")
  @JsonProperty("password")
  private String password;

  @ApiModelProperty(value = "The avatar of the domain user.")
  @JsonProperty("avatar")
  private byte[] avatar;

  @Builder
  public DomainUser(String distinguishedName, OffsetDateTime created,
      OffsetDateTime modified, String userName, Boolean enabled, String firstName,
      String lastName, String displayName, String email, String telephoneNumber,
      String mobile, List<String> groups, String homeDirectory, String unixHomeDirectory,
      String loginShell, OffsetDateTime lastLogon, Integer logonCount,
      OffsetDateTime passwordLastSet, String password, byte[] avatar) {
    super(distinguishedName, created, modified);
    this.userName = userName;
    this.enabled = enabled;
    this.firstName = firstName;
    this.lastName = lastName;
    this.displayName = displayName;
    this.email = email;
    this.telephoneNumber = telephoneNumber;
    this.mobile = mobile;
    this.groups = groups;
    this.homeDirectory = homeDirectory;
    this.unixHomeDirectory = unixHomeDirectory;
    this.loginShell = loginShell;
    this.lastLogon = lastLogon;
    this.logonCount = logonCount;
    this.passwordLastSet = passwordLastSet;
    this.password = password;
    this.avatar = avatar;
  }

  /**
   * Specifies whether the user is enabled or not.
   *
   * @return {@code true} is the user is enabled, otherwise {@code false}
   */
  public Boolean getEnabled() {
    return Boolean.TRUE.equals(enabled);
  }

  /**
   * Sets whether the user is enabled or not.
   *
   * @param enabled {@code true} if the user should be enabled, otherwise {@code false}
   */
  public void setEnabled(Boolean enabled) {
    this.enabled = Boolean.TRUE.equals(enabled);
  }

  /**
   * The groups of the domain user.
   *
   * @return groups groups
   */
  public List<String> getGroups() {
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
  public void setGroups(List<String> groups) {
    this.groups = groups;
  }

}

