/*
 * Copyright 2019-2020 the original author or authors.
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
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
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
 *
 * @author Christian Bremer
 */
@Schema(description = "Domain user.")
@Validated
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, exclude = {"password"})
@ToString(callSuper = true, exclude = {"password"})
@NoArgsConstructor
public class DomainUser extends CommonAttributes {

  /**
   * The constant DEFAULT_SORT_ORDER.
   */
  public static final String DEFAULT_SORT_ORDER = "userName";

  private static final long serialVersionUID = 1L;

  /**
   * The constant SID.
   */
  public static final String SID = "sid";

  @Schema(description = "The windows/samba SID.", accessMode = AccessMode.READ_ONLY)
  @JsonProperty(SID)
  private Sid sid;

  /**
   * The constant USER_NAME.
   */
  public static final String USER_NAME = "userName";

  @Schema(description = "The user name of the domain user.", required = true)
  @JsonProperty(value = USER_NAME, required = true)
  @NotNull
  private String userName;

  /**
   * The constant ENABLED.
   */
  public static final String ENABLED = "enabled";

  @Schema(description = "Specifies whether the user is enabled or not.")
  @JsonProperty(ENABLED)
  private Boolean enabled = Boolean.FALSE;

  /**
   * The constant FIRST_NAME.
   */
  public static final String FIRST_NAME = "firstName";

  @Schema(description = "The first name of the domain user.")
  @JsonProperty(FIRST_NAME)
  private String firstName;

  /**
   * The constant LAST_NAME.
   */
  public static final String LAST_NAME = "lastName";

  @Schema(description = "The last name of the domain user.")
  @JsonProperty(LAST_NAME)
  private String lastName;

  /**
   * The constant DISPLAY_NAME.
   */
  public static final String DISPLAY_NAME = "displayName";

  @Schema(description = "The display name.")
  @JsonProperty(DISPLAY_NAME)
  private String displayName;

  /**
   * The constant EMAIL.
   */
  public static final String EMAIL = "email";

  @Schema(description = "The email address of the domain user.")
  @JsonProperty(EMAIL)
  private String email;

  /**
   * The constant TELEPHONE_NUMBER.
   */
  public static final String TELEPHONE_NUMBER = "telephoneNumber";

  @Schema(description = "The telephone number of the domain user.")
  @JsonProperty(TELEPHONE_NUMBER)
  private String telephoneNumber;

  /**
   * The constant MOBILE.
   */
  public static final String MOBILE = "mobile";

  @Schema(description = "The mobile number of the domain user.")
  @JsonProperty(MOBILE)
  private String mobile;

  /**
   * The constant DESCRIPTION.
   */
  public static final String DESCRIPTION = "description";

  @Schema(description = "A description of the domain user.")
  @JsonProperty(DESCRIPTION)
  private String description;

  /**
   * The constant HOME_DIRECTORY.
   */
  public static final String HOME_DIRECTORY = "homeDirectory";

  @Schema(description = "The home directory of the domain user.")
  @JsonProperty(HOME_DIRECTORY)
  private String homeDirectory;

  /**
   * The constant UNIX_HOME_DIRECTORY.
   */
  public static final String UNIX_HOME_DIRECTORY = "unixHomeDirectory";

  @Schema(description = "The unix home directory of the domain user.")
  @JsonProperty(UNIX_HOME_DIRECTORY)
  private String unixHomeDirectory;

  /**
   * The constant LOGIN_SHELL.
   */
  public static final String LOGIN_SHELL = "loginShell";

  @Schema(description = "The login shell of the domain user.")
  @JsonProperty(LOGIN_SHELL)
  private String loginShell;

  /**
   * The constant LAST_LOGON.
   */
  public static final String LAST_LOGON = "lastLogon";

  @Schema(
      description = "The last logon time of the domain user.",
      accessMode = AccessMode.READ_ONLY)
  @JsonProperty(LAST_LOGON)
  private OffsetDateTime lastLogon;

  /**
   * The constant LOGON_COUNT.
   */
  public static final String LOGON_COUNT = "logonCount";

  @Schema(
      description = "The logon count of the domain user.",
      accessMode = AccessMode.READ_ONLY)
  @JsonProperty(LOGON_COUNT)
  private Integer logonCount;

  /**
   * The constant PASSWORD_LAST_SET.
   */
  public static final String PASSWORD_LAST_SET = "passwordLastSet";

  @Schema(
      description = "Date of the last password change.",
      accessMode = AccessMode.READ_ONLY)
  @JsonProperty(PASSWORD_LAST_SET)
  private OffsetDateTime passwordLastSet;

  /**
   * The constant PASSWORD.
   */
  public static final String PASSWORD = "password";

  @Schema(description = "The password of the domain user.")
  @JsonProperty(PASSWORD)
  private String password;

  /**
   * The constant GROUPS.
   */
  public static final String GROUPS = "groups";

  @Schema(description = "The groups of the domain user.")
  @JsonProperty(GROUPS)
  private List<String> groups;

  /**
   * Instantiates a new domain user.
   *
   * @param distinguishedName the distinguished name
   * @param created the created
   * @param modified the modified
   * @param sid the windows/samba SID
   * @param userName the user name
   * @param enabled the enabled
   * @param firstName the first name
   * @param lastName the last name
   * @param displayName the display name
   * @param email the email
   * @param telephoneNumber the telephone number
   * @param mobile the mobile
   * @param description the description
   * @param homeDirectory the home directory
   * @param unixHomeDirectory the unix home directory
   * @param loginShell the login shell
   * @param lastLogon the last logon
   * @param logonCount the logon count
   * @param passwordLastSet the password last set
   * @param password the password
   * @param groups the groups
   */
  @SuppressWarnings("unused")
  @Builder(toBuilder = true)
  public DomainUser(String distinguishedName, OffsetDateTime created, OffsetDateTime modified,
      Sid sid, String userName, Boolean enabled, String firstName, String lastName,
      String displayName, String email, String telephoneNumber, String mobile, String description,
      String homeDirectory, String unixHomeDirectory, String loginShell, OffsetDateTime lastLogon,
      Integer logonCount, OffsetDateTime passwordLastSet, String password, List<String> groups) {
    super(distinguishedName, created, modified);
    this.sid = sid;
    this.userName = userName;
    this.enabled = enabled;
    this.firstName = firstName;
    this.lastName = lastName;
    this.displayName = displayName;
    this.email = email;
    this.telephoneNumber = telephoneNumber;
    this.mobile = mobile;
    this.description = description;
    this.homeDirectory = homeDirectory;
    this.unixHomeDirectory = unixHomeDirectory;
    this.loginShell = loginShell;
    this.lastLogon = lastLogon;
    this.logonCount = logonCount;
    this.passwordLastSet = passwordLastSet;
    this.password = password;
    this.groups = groups;
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

