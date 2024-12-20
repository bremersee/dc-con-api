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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import java.io.Serial;
import java.io.Serializable;
import java.util.regex.Pattern;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * The password information of the domain controller.
 *
 * @author Christian Bremer
 */
@Schema(description = "The password information of the domain controller.")
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class PasswordInformation implements Serializable {

  @Serial
  private static final long serialVersionUID = 2L;

  private static final String SIMPLE_PASSWORD_REGEX = "^(?=.{%d,%d}$).*";

  private static final String COMPLEX_PASSWORD_REGEX = "(?=^.{%d,%d}$)"
      + "((?=.*\\d)(?=.*[A-Z])(?=.*[a-z])|(?=.*\\d)(?=.*[^A-Za-z0-9])(?=.*[a-z])"
      + "|(?=.*[^A-Za-z0-9])(?=.*[A-Z])(?=.*[a-z])|(?=.*\\d)(?=.*[A-Z])(?=.*[^A-Za-z0-9]))^.*";

  @Schema(description = "The password complexity.")
  @JsonProperty("passwordComplexity")
  private PasswordComplexity passwordComplexity = PasswordComplexity.ON;

  @Schema(description = "Store plaintext passwords where account have 'store passwords with "
      + "reversible encryption' set (on | off | default). Default is 'off'.")
  @JsonProperty("storePlaintextPasswords")
  private Boolean storePlaintextPasswords = Boolean.FALSE;

  @Schema(description = "The password history length. Default is 24.")
  @JsonProperty("passwordHistoryLength")
  private Integer passwordHistoryLength = 24;

  @Schema(description = "The minimum password length. Default is 7.")
  @JsonProperty("minimumPasswordLength")
  private Integer minimumPasswordLength = 7;

  @Schema(description = "The maximum password length. Default is 75.")
  @JsonProperty("maximumPasswordLength")
  private Integer maximumPasswordLength = 75;

  @Schema(description = "The minimum password age in days. Default is 1.")
  @JsonProperty("minimumPasswordAgeInDays")
  private Integer minimumPasswordAgeInDays = 1;

  @Schema(description = "The maximum password age in days. Default is 43.")
  @JsonProperty("maximumPasswordAgeInDays")
  private Integer maximumPasswordAgeInDays = 43;

  @Schema(description = "The the length of time an account is locked out after exceeding the "
      + "limit on bad password attempts. Default is 30.")
  @JsonProperty("accountLockoutDurationInMinutes")
  private Integer accountLockoutDurationInMinutes = 30;

  @Schema(description = "The number of bad password attempts allowed before locking out the "
      + "account. Default is 0 (never lock out).")
  @JsonProperty("accountLockoutThreshold")
  private Integer accountLockoutThreshold = 0;

  @Schema(description = "After this time is elapsed, the recorded number of attempts restarts "
      + "from zero. Default is 30.")
  @JsonProperty("resetAccountLockoutAfter")
  private Integer resetAccountLockoutAfter = 30;

  @Hidden
  @JsonIgnore
  private String simplePasswordRegexTemplate = SIMPLE_PASSWORD_REGEX;

  @Hidden
  @JsonIgnore
  private String complexPasswordRegexTemplate = COMPLEX_PASSWORD_REGEX;

  /**
   * Instantiates a new password information.
   *
   * @param passwordComplexity the password complexity
   * @param storePlaintextPasswords the store plaintext passwords
   * @param passwordHistoryLength the password history length
   * @param minimumPasswordLength the minimum password length
   * @param maximumPasswordLength the maximum password length
   * @param minimumPasswordAgeInDays the minimum password age in days
   * @param maximumPasswordAgeInDays the maximum password age in days
   * @param accountLockoutDurationInMinutes the account lockout duration in minutes
   * @param accountLockoutThreshold the account lockout threshold
   * @param resetAccountLockoutAfter the reset account lockout after
   * @param simplePasswordRegexTemplate the simple password regex template
   * @param complexPasswordRegexTemplate the complex password regex template
   */
  @Builder(toBuilder = true)
  public PasswordInformation(
      PasswordComplexity passwordComplexity,
      Boolean storePlaintextPasswords,
      Integer passwordHistoryLength,
      Integer minimumPasswordLength,
      Integer maximumPasswordLength,
      Integer minimumPasswordAgeInDays,
      Integer maximumPasswordAgeInDays,
      Integer accountLockoutDurationInMinutes,
      Integer accountLockoutThreshold,
      Integer resetAccountLockoutAfter,
      String simplePasswordRegexTemplate,
      String complexPasswordRegexTemplate) {
    setPasswordComplexity(passwordComplexity);
    setStorePlaintextPasswords(storePlaintextPasswords);
    setPasswordHistoryLength(passwordHistoryLength);
    setMinimumPasswordLength(minimumPasswordLength);
    setMaximumPasswordLength(maximumPasswordLength);
    setMinimumPasswordAgeInDays(minimumPasswordAgeInDays);
    setMaximumPasswordAgeInDays(maximumPasswordAgeInDays);
    setAccountLockoutDurationInMinutes(accountLockoutDurationInMinutes);
    setAccountLockoutThreshold(accountLockoutThreshold);
    setResetAccountLockoutAfter(resetAccountLockoutAfter);
    setSimplePasswordRegexTemplate(simplePasswordRegexTemplate);
    setComplexPasswordRegexTemplate(complexPasswordRegexTemplate);
  }

  /**
   * Sets password complexity.
   *
   * @param passwordComplexity the password complexity
   */
  public void setPasswordComplexity(PasswordComplexity passwordComplexity) {
    if (passwordComplexity != null) {
      this.passwordComplexity = passwordComplexity;
    }
  }

  /**
   * Gets password regex.
   *
   * @return the password regex
   */
  @Schema(description = "The password regex.", accessMode = AccessMode.READ_ONLY)
  @JsonProperty(value = "passwordRegex", access = Access.READ_ONLY)
  public String getPasswordRegex() {
    int minLength = getMinimumPasswordLength() != null
        ? getMinimumPasswordLength()
        : 7;
    int maxLength = getMaximumPasswordLength() != null
        ? getMaximumPasswordLength()
        : 75;
    String template;
    if (PasswordComplexity.OFF == getPasswordComplexity()) {
      template = getSimplePasswordRegexTemplate();
    } else {
      template = getComplexPasswordRegexTemplate();
    }
    if (containsPlaceholderForMinAndMaxLength(template)) {
      return String.format(template, minLength, maxLength);
    }
    return template;
  }

  /**
   * Gets password pattern.
   *
   * @return the password pattern
   */
  @Hidden
  @JsonIgnore
  public Pattern getPasswordPattern() {
    return Pattern.compile(getPasswordRegex());
  }

  /**
   * Sets store plaintext passwords.
   *
   * @param storePlaintextPasswords the store plaintext passwords
   */
  public void setStorePlaintextPasswords(Boolean storePlaintextPasswords) {
    if (storePlaintextPasswords != null) {
      this.storePlaintextPasswords = storePlaintextPasswords;
    }
  }

  /**
   * Sets password history length.
   *
   * @param passwordHistoryLength the password history length
   */
  public void setPasswordHistoryLength(Integer passwordHistoryLength) {
    if (passwordHistoryLength != null) {
      this.passwordHistoryLength = passwordHistoryLength;
    }
  }

  /**
   * Sets minimum password length.
   *
   * @param minimumPasswordLength the minimum password length
   */
  public void setMinimumPasswordLength(Integer minimumPasswordLength) {
    if (minimumPasswordLength != null) {
      this.minimumPasswordLength = minimumPasswordLength;
    }
  }

  /**
   * Sets maximum password length.
   *
   * @param maximumPasswordLength the minimum password length
   */
  public void setMaximumPasswordLength(Integer maximumPasswordLength) {
    if (maximumPasswordLength != null) {
      this.maximumPasswordLength = maximumPasswordLength;
    }
  }

  /**
   * Sets minimum password age in days.
   *
   * @param minimumPasswordAgeInDays the minimum password age in days
   */
  public void setMinimumPasswordAgeInDays(Integer minimumPasswordAgeInDays) {
    if (minimumPasswordAgeInDays != null) {
      this.minimumPasswordAgeInDays = minimumPasswordAgeInDays;
    }
  }

  /**
   * Sets maximum password age in days.
   *
   * @param maximumPasswordAgeInDays the maximum password age in days
   */
  public void setMaximumPasswordAgeInDays(Integer maximumPasswordAgeInDays) {
    if (maximumPasswordAgeInDays != null) {
      this.maximumPasswordAgeInDays = maximumPasswordAgeInDays;
    }
  }

  /**
   * Sets account lockout duration in minutes.
   *
   * @param accountLockoutDurationInMinutes the account lockout duration in minutes
   */
  public void setAccountLockoutDurationInMinutes(Integer accountLockoutDurationInMinutes) {
    if (accountLockoutDurationInMinutes != null) {
      this.accountLockoutDurationInMinutes = accountLockoutDurationInMinutes;
    }
  }

  /**
   * Sets account lockout threshold.
   *
   * @param accountLockoutThreshold the account lockout threshold
   */
  public void setAccountLockoutThreshold(Integer accountLockoutThreshold) {
    if (accountLockoutThreshold != null) {
      this.accountLockoutThreshold = accountLockoutThreshold;
    }
  }

  /**
   * Sets reset account lockout after.
   *
   * @param resetAccountLockoutAfter the reset account lockout after
   */
  public void setResetAccountLockoutAfter(Integer resetAccountLockoutAfter) {
    if (resetAccountLockoutAfter != null) {
      this.resetAccountLockoutAfter = resetAccountLockoutAfter;
    }
  }

  /**
   * Gets simple password regex template.
   *
   * @return the simple password regex template
   */
  @Hidden
  @JsonIgnore
  public String getSimplePasswordRegexTemplate() {
    return simplePasswordRegexTemplate;
  }

  /**
   * Sets simple password regex template.
   *
   * @param simplePasswordRegexTemplate the simple password regex template
   */
  @Hidden
  @JsonIgnore
  public void setSimplePasswordRegexTemplate(String simplePasswordRegexTemplate) {
    if (simplePasswordRegexTemplate != null) {
      this.simplePasswordRegexTemplate = simplePasswordRegexTemplate;
    }
  }

  /**
   * Gets complex password regex template.
   *
   * @return the complex password regex template
   */
  @Hidden
  @JsonIgnore
  public String getComplexPasswordRegexTemplate() {
    return complexPasswordRegexTemplate;
  }

  /**
   * Sets complex password regex template.
   *
   * @param complexPasswordRegexTemplate the complex password regex template
   */
  @Hidden
  @JsonIgnore
  public void setComplexPasswordRegexTemplate(String complexPasswordRegexTemplate) {
    if (complexPasswordRegexTemplate != null) {
      this.complexPasswordRegexTemplate = complexPasswordRegexTemplate;
    }
  }

  private static boolean containsPlaceholderForMinAndMaxLength(String passwordRegexTemplate) {
    return containsPlaceholderForMinAndMaxLength(passwordRegexTemplate, "%d")
        || containsPlaceholderForMinAndMaxLength(passwordRegexTemplate, "%s");
  }

  private static boolean containsPlaceholderForMinAndMaxLength(
      String passwordRegexTemplate, String placeholder) {
    int index = passwordRegexTemplate.indexOf(placeholder);
    if (index < 0) {
      return false;
    }
    int from = index + placeholder.length();
    index = passwordRegexTemplate.indexOf(placeholder, from);
    return index > 0;
  }
}
