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
import java.io.Serializable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

/**
 * The password information of the domain controller.
 *
 * @author Christian Bremer
 */
@Schema(description = "The password information of the domain controller.")
@Validated
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class PasswordInformation implements Serializable {

  private static final long serialVersionUID = 1L;

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

  /**
   * Instantiates a new Password information.
   *
   * @param passwordComplexity the password complexity
   * @param storePlaintextPasswords the store plaintext passwords
   * @param passwordHistoryLength the password history length
   * @param minimumPasswordLength the minimum password length
   * @param minimumPasswordAgeInDays the minimum password age in days
   * @param maximumPasswordAgeInDays the maximum password age in days
   * @param accountLockoutDurationInMinutes the account lockout duration in minutes
   * @param accountLockoutThreshold the account lockout threshold
   * @param resetAccountLockoutAfter the reset account lockout after
   */
  @SuppressWarnings("unused")
  @Builder(toBuilder = true)
  public PasswordInformation(
      PasswordComplexity passwordComplexity,
      Boolean storePlaintextPasswords,
      Integer passwordHistoryLength,
      Integer minimumPasswordLength,
      Integer minimumPasswordAgeInDays,
      Integer maximumPasswordAgeInDays,
      Integer accountLockoutDurationInMinutes,
      Integer accountLockoutThreshold,
      Integer resetAccountLockoutAfter) {
    setPasswordComplexity(passwordComplexity);
    setStorePlaintextPasswords(storePlaintextPasswords);
    setPasswordHistoryLength(passwordHistoryLength);
    setMinimumPasswordLength(minimumPasswordLength);
    setMinimumPasswordAgeInDays(minimumPasswordAgeInDays);
    setMaximumPasswordAgeInDays(maximumPasswordAgeInDays);
    setAccountLockoutDurationInMinutes(accountLockoutDurationInMinutes);
    setAccountLockoutThreshold(accountLockoutThreshold);
    setResetAccountLockoutAfter(resetAccountLockoutAfter);
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
}
