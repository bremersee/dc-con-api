/*
 * Copyright 2024 the original author or authors.
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

package org.bremersee.dccon.dto;

import static java.util.Objects.nonNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import io.swagger.v3.oas.annotations.media.DiscriminatorMapping;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import java.io.Serial;
import java.time.OffsetDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * A domain (Active Directory) user may represent physical entities, such as people or may be used
 * as service accounts for applications. User accounts are also referred to as security principals
 * and are assigned a security identifier (SID).
 *
 * <p>A user account enables a user to logon to a computer and domain with an identity that can be
 * authenticated. To maximize security, each user should have their own unique user account and
 * password. A user's access to domain resources is based on permissions assigned to the user
 * account.
 *
 * <p>This plain representation has no membership attribute.
 *
 * @author Christian Bremer
 */
@Schema(description = "Plain domain user.",
    discriminatorProperty = "_type",
    discriminatorMapping = {
        @DiscriminatorMapping(
            value = "org.bremersee.dccon.model.PlainDomainUser",
            schema = PlainDomainUser.class),
        @DiscriminatorMapping(
            value = "org.bremersee.dccon.model.GenericDomainUser",
            schema = GenericDomainUser.class)
    })
@JsonTypeInfo(use = Id.CLASS, property = "_type", visible = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, exclude = {"password"})
@ToString(callSuper = true, exclude = {"password"})
@NoArgsConstructor
public class PlainDomainUser extends CommonAttributes {

  @Serial
  private static final long serialVersionUID = 2L;

  /**
   * The constant ACCOUNT_CONTROL.
   */
  public static final String ACCOUNT_CONTROL = "accountControl";

  /**
   * User's account control.
   */
  @Schema(description = "User's account control.")
  @JsonProperty(ACCOUNT_CONTROL)
  DomainUserAccountControl accountControl = new DomainUserAccountControl();

  /**
   * The constant LAST_LOGON.
   */
  public static final String ACCOUNT_EXPIRES = "accountExpires";

  /**
   * User's last logon time.
   */
  @Schema(
      description = "User's account expiration time.",
      accessMode = AccessMode.READ_ONLY)
  @JsonProperty(ACCOUNT_EXPIRES)
  OffsetDateTime accountExpires;

  /**
   * The constant COMPANY.
   */
  public static final String COMPANY = "company";

  /**
   * User's company.
   */
  @Schema(description = "User's company.")
  @JsonProperty(COMPANY)
  String company;

  /**
   * The constant DEPARTMENT.
   */
  public static final String DEPARTMENT = "department";

  /**
   * User's department.
   */
  @Schema(description = "User's department.")
  @JsonProperty(DEPARTMENT)
  String department;

  /**
   * The constant DESCRIPTION.
   */
  public static final String DESCRIPTION = "description";

  /**
   * A description of the user.
   */
  @Schema(description = "A description of the user.")
  @JsonProperty(DESCRIPTION)
  String description;

  /**
   * The constant DISPLAY_NAME.
   */
  public static final String DISPLAY_NAME = "displayName";

  /**
   * User's display name.
   */
  @Schema(description = "User's display name.")
  @JsonProperty(DISPLAY_NAME)
  String displayName;

  /**
   * The constant EMAIL.
   */
  public static final String EMAIL = "email";

  /**
   * User's email address.
   */
  @Schema(description = "User's email address.")
  @JsonProperty(EMAIL)
  String email;

  /**
   * The constant FIRST_NAME.
   */
  public static final String FIRST_NAME = "firstName";

  /**
   * User's first name.
   */
  @Schema(description = "User's first name.")
  @JsonProperty(FIRST_NAME)
  String firstName;

  /**
   * The constant GID_NUMBER.
   */
  public static final String GID_NUMBER = "gidNumber";

  /**
   * User's Unix/RFC2307 primary GID number.
   */
  @Schema(description = "User's Unix/RFC2307 primary GID number.")
  @JsonProperty(GID_NUMBER)
  Integer gidNumber;

  /**
   * The constant HOME_DIRECTORY.
   */
  public static final String HOME_DIRECTORY = "homeDirectory";

  /**
   * User's home directory path.
   */
  @Schema(description = "User's home directory path.")
  @JsonProperty(HOME_DIRECTORY)
  String homeDirectory;

  /**
   * The constant HOME_DRIVE.
   */
  public static final String HOME_DRIVE = "homeDrive";

  /**
   * User's home drive letter.
   */
  @Schema(description = "User's home drive letter.")
  @JsonProperty(HOME_DRIVE)
  String homeDrive;

  /**
   * The constant INITIALS.
   */
  public static final String INITIALS = "initials";

  /**
   * User's initials.
   */
  @Schema(description = "User's initials.")
  @JsonProperty(INITIALS)
  String initials;

  /**
   * The constant LAST_LOGON.
   */
  public static final String LAST_LOGON = "lastLogon";

  /**
   * User's last logon time.
   */
  @Schema(
      description = "User's last logon time.",
      accessMode = AccessMode.READ_ONLY)
  @JsonProperty(LAST_LOGON)
  OffsetDateTime lastLogon;

  /**
   * The constant LAST_NAME.
   */
  public static final String LAST_NAME = "lastName";

  /**
   * User's last name.
   */
  @Schema(description = "User's last name.")
  @JsonProperty(LAST_NAME)
  String lastName;

  /**
   * The constant LOGIN_SHELL.
   */
  public static final String LOGIN_SHELL = "loginShell";

  /**
   * User's Unix/RFC2307 login shell.
   */
  @Schema(description = "User's Unix/RFC2307 login shell.")
  @JsonProperty(LOGIN_SHELL)
  String loginShell;

  /**
   * The constant LOGON_COUNT.
   */
  public static final String LOGON_COUNT = "logonCount";

  /**
   * User's logon count.
   */
  @Schema(
      description = "User's logon count.",
      accessMode = AccessMode.READ_ONLY)
  @JsonProperty(LOGON_COUNT)
  Integer logonCount;

  /**
   * The constant MOBILE.
   */
  public static final String MOBILE = "mobile";

  /**
   * User's mobile phone number.
   */
  @Schema(description = "User's mobile phone number.")
  @JsonProperty(MOBILE)
  String mobile;

  /**
   * The constant NIS_DOMAIN.
   */
  public static final String NIS_DOMAIN = "nisDomain";

  /**
   * User's Unix/RFC2307 NIS domain.
   */
  @Schema(description = "User's Unix/RFC2307 NIS domain.")
  @JsonProperty(NIS_DOMAIN)
  String nisDomain;

  /**
   * The constant PASSWORD.
   */
  public static final String PASSWORD = "password";

  /**
   * User's password.
   */
  @Schema(description = "User's password.")
  @JsonProperty(PASSWORD)
  String password;

  /**
   * The constant PASSWORD_LAST_SET.
   */
  public static final String PASSWORD_LAST_SET = "passwordLastSet";

  /**
   * Timestamp of the last password change.
   */
  @Schema(
      description = "Timestamp of the last password change.",
      accessMode = AccessMode.READ_ONLY)
  @JsonProperty(PASSWORD_LAST_SET)
  OffsetDateTime passwordLastSet;

  /**
   * The constant NIS_DOMAIN.
   */
  public static final String PHYSICAL_DELIVERY_OFFICE_NAME = "physicalDeliveryOfficeName";

  /**
   * User's office location.
   */
  @Schema(description = "User's office location.")
  @JsonProperty(PHYSICAL_DELIVERY_OFFICE_NAME)
  String physicalDeliveryOfficeName;

  /**
   * The constant PREFERRED_LANGUAGE.
   */
  public static final String PREFERRED_LANGUAGE = "preferredLanguage";

  /**
   * User's preferred language. ISO 639-1 language codes. The combinations like de-DE and en-US with
   * ISO-639 and ISO-3166 also work.
   */
  @Schema(description = "User's preferred language. ISO 639-1 language codes. The combinations "
      + "like de-DE and en-US with ISO-639 and ISO-3166 also work.")
  @JsonProperty(PREFERRED_LANGUAGE)
  String preferredLanguage;

  /**
   * The constant PROFILE_PATH.
   */
  public static final String PROFILE_PATH = "profilePath";

  /**
   * User's profile path.
   */
  @Schema(description = "User's profile path.")
  @JsonProperty(PROFILE_PATH)
  String profilePath;

  /**
   * The constant SCRIPT_PATH.
   */
  public static final String SCRIPT_PATH = "scriptPath";

  /**
   * User's logon script path.
   */
  @Schema(description = "User's logon script path.")
  @JsonProperty(SCRIPT_PATH)
  String scriptPath;

  /**
   * The constant SID.
   */
  public static final String SID = "sid";

  /**
   * User's windows/samba SID.
   */
  @Schema(description = "User's windows/samba SID.", accessMode = AccessMode.READ_ONLY)
  @JsonProperty(SID)
  Sid sid;

  /**
   * The constant TELEPHONE_NUMBER.
   */
  public static final String TELEPHONE_NUMBER = "telephoneNumber";

  /**
   * User's telephone number.
   */
  @Schema(description = "User's telephone number.")
  @JsonProperty(TELEPHONE_NUMBER)
  String telephoneNumber;

  /**
   * The constant TITLE.
   */
  public static final String TITLE = "title";

  /**
   * User's job title.
   */
  @Schema(description = "User's job title.")
  @JsonProperty(TITLE)
  String title;

  /**
   * The constant UID_NUMBER.
   */
  public static final String UID_NUMBER = "uidNumber";

  /**
   * User's Unix/RFC2307 numeric UID.
   */
  @Schema(description = "User's Unix/RFC2307 numeric UID.")
  @JsonProperty(UID_NUMBER)
  Integer uidNumber;

  /**
   * The constant UNIX_HOME_DIRECTORY.
   */
  public static final String UNIX_HOME_DIRECTORY = "unixHomeDirectory";

  /**
   * User's Unix/RFC2307 home directory.
   */
  @Schema(description = "User's Unix/RFC2307 home directory.")
  @JsonProperty(UNIX_HOME_DIRECTORY)
  String unixHomeDirectory;

  /**
   * The constant USER_NAME.
   */
  public static final String USER_NAME = "userName";

  /**
   * User's username.
   */
  @Schema(description = "The user name of the domain user.", requiredMode = RequiredMode.REQUIRED)
  @JsonProperty(value = USER_NAME, required = true)
  String userName;

  /**
   * Sets user's account control.
   *
   * @param accountControl user's account control
   */
  public void setAccountControl(DomainUserAccountControl accountControl) {
    if (nonNull(accountControl)) {
      this.accountControl = accountControl;
    }
  }
}
