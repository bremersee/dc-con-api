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

package org.bremersee.dccon.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * The password information test.
 *
 * @author Christian Bremer
 */
class PasswordInformationTest {

  /**
   * Gets account lockout duration in minutes.
   */
  @Test
  void getAccountLockoutDurationInMinutes() {
    Integer value = 1234;
    PasswordInformation model = new PasswordInformation();
    model.setAccountLockoutDurationInMinutes(value);
    assertEquals(value, model.getAccountLockoutDurationInMinutes());

    model = PasswordInformation.builder().accountLockoutDurationInMinutes(value).build();
    assertEquals(value, model.getAccountLockoutDurationInMinutes());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().accountLockoutDurationInMinutes(value).build());

    assertTrue(model.toString().contains(value.toString()));
  }

  /**
   * Gets account lockout threshold.
   */
  @Test
  void getAccountLockoutThreshold() {
    Integer value = 5678;
    PasswordInformation model = new PasswordInformation();
    model.setAccountLockoutThreshold(value);
    assertEquals(value, model.getAccountLockoutThreshold());

    model = PasswordInformation.builder().accountLockoutThreshold(value).build();
    assertEquals(value, model.getAccountLockoutThreshold());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().accountLockoutThreshold(value).build());

    assertTrue(model.toString().contains(value.toString()));
  }

  /**
   * Gets maximum password age in days.
   */
  @Test
  void getMaximumPasswordAgeInDays() {
    Integer value = 9012;
    PasswordInformation model = new PasswordInformation();
    model.setMaximumPasswordAgeInDays(value);
    assertEquals(value, model.getMaximumPasswordAgeInDays());

    model = PasswordInformation.builder().maximumPasswordAgeInDays(value).build();
    assertEquals(value, model.getMaximumPasswordAgeInDays());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().maximumPasswordAgeInDays(value).build());

    assertTrue(model.toString().contains(value.toString()));
  }

  /**
   * Gets minimum password length.
   */
  @Test
  void getMinimumPasswordLength() {
    Integer value = 3456;
    PasswordInformation model = new PasswordInformation();
    model.setMinimumPasswordLength(value);
    assertEquals(value, model.getMinimumPasswordLength());

    model = PasswordInformation.builder().minimumPasswordLength(value).build();
    assertEquals(value, model.getMinimumPasswordLength());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().minimumPasswordLength(value).build());

    assertTrue(model.toString().contains(value.toString()));
  }

  /**
   * Gets minimum password age in days.
   */
  @Test
  void getMinimumPasswordAgeInDays() {
    Integer value = 7890;
    PasswordInformation model = new PasswordInformation();
    model.setMinimumPasswordAgeInDays(value);
    assertEquals(value, model.getMinimumPasswordAgeInDays());

    model = PasswordInformation.builder().minimumPasswordAgeInDays(value).build();
    assertEquals(value, model.getMinimumPasswordAgeInDays());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().minimumPasswordAgeInDays(value).build());

    assertTrue(model.toString().contains(value.toString()));
  }

  /**
   * Gets reset account lockout after.
   */
  @Test
  void getResetAccountLockoutAfter() {
    Integer value = 12345;
    PasswordInformation model = new PasswordInformation();
    model.setResetAccountLockoutAfter(value);
    assertEquals(value, model.getResetAccountLockoutAfter());

    model = PasswordInformation.builder().resetAccountLockoutAfter(value).build();
    assertEquals(value, model.getResetAccountLockoutAfter());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().resetAccountLockoutAfter(value).build());

    assertTrue(model.toString().contains(value.toString()));
  }

  /**
   * Gets password complexity.
   */
  @Test
  void getPasswordComplexity() {
    PasswordComplexity value = PasswordComplexity.OFF;
    PasswordInformation model = new PasswordInformation();
    model.setPasswordComplexity(value);
    assertEquals(value, model.getPasswordComplexity());

    model = PasswordInformation.builder().passwordComplexity(value).build();
    assertEquals(value, model.getPasswordComplexity());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().passwordComplexity(value).build());

    assertTrue(model.toString().contains(value.toString()));
  }

  /**
   * Gets password history length.
   */
  @Test
  void getPasswordHistoryLength() {
    Integer value = 67890;
    PasswordInformation model = new PasswordInformation();
    model.setPasswordHistoryLength(value);
    assertEquals(value, model.getPasswordHistoryLength());

    model = PasswordInformation.builder().passwordHistoryLength(value).build();
    assertEquals(value, model.getPasswordHistoryLength());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().passwordHistoryLength(value).build());

    assertTrue(model.toString().contains(value.toString()));
  }

  /**
   * Gets store plaintext passwords.
   */
  @Test
  void getStorePlaintextPasswords() {
    PasswordInformation model = new PasswordInformation();
    model.setStorePlaintextPasswords(true);
    assertEquals(true, model.getStorePlaintextPasswords());

    model = PasswordInformation.builder().storePlaintextPasswords(true).build();
    assertEquals(true, model.getStorePlaintextPasswords());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().storePlaintextPasswords(true).build());

    assertTrue(model.toString().contains("true"));
  }

  /**
   * Gets password regex with complexity is off.
   */
  @Test
  void getPasswordRegexWithComplexityIsOff() {
    String expected = "^(?=.{8,70}$).*";
    String template = expected;
    PasswordInformation target = PasswordInformation.builder()
        .passwordComplexity(PasswordComplexity.OFF)
        .simplePasswordRegexTemplate(template)
        .build();
    String actual = target.getPasswordRegex();
    assertEquals(expected, actual);
    assertNotNull(target.getPasswordPattern());

    template = "^(?=.{%s,%s}$).*";
    target = PasswordInformation.builder()
        .passwordComplexity(PasswordComplexity.OFF)
        .simplePasswordRegexTemplate(template)
        .minimumPasswordLength(8)
        .maximumPasswordLength(70)
        .build();
    actual = target.getPasswordRegex();
    assertEquals(expected, actual);

    target = PasswordInformation.builder()
        .passwordComplexity(PasswordComplexity.OFF)
        .simplePasswordRegexTemplate(null)
        .minimumPasswordLength(8)
        .maximumPasswordLength(70)
        .build();
    actual = target.getPasswordRegex();
    assertEquals(expected, actual);
  }

  /**
   * Gets password regex with complexity is on.
   */
  @Test
  void getPasswordRegexWithComplexityIsOn() {
    String expected = "(?=^.{12,80}$)"
        + "((?=.*\\d)(?=.*[A-Z])(?=.*[a-z])|(?=.*\\d)(?=.*[^A-Za-z0-9])(?=.*[a-z])"
        + "|(?=.*[^A-Za-z0-9])(?=.*[A-Z])(?=.*[a-z])|(?=.*\\d)(?=.*[A-Z])(?=.*[^A-Za-z0-9]))^.*";
    String template = expected;
    PasswordInformation target = PasswordInformation.builder()
        .passwordComplexity(PasswordComplexity.ON)
        .complexPasswordRegexTemplate(template)
        .build();
    String actual = target.getPasswordRegex();
    assertEquals(expected, actual);
    assertNotNull(target.getPasswordPattern());

    template = "(?=^.{%s,%s}$)"
        + "((?=.*\\d)(?=.*[A-Z])(?=.*[a-z])|(?=.*\\d)(?=.*[^A-Za-z0-9])(?=.*[a-z])"
        + "|(?=.*[^A-Za-z0-9])(?=.*[A-Z])(?=.*[a-z])|(?=.*\\d)(?=.*[A-Z])(?=.*[^A-Za-z0-9]))^.*";
    target = PasswordInformation.builder()
        .passwordComplexity(PasswordComplexity.ON)
        .complexPasswordRegexTemplate(template)
        .minimumPasswordLength(12)
        .maximumPasswordLength(80)
        .build();
    actual = target.getPasswordRegex();
    assertEquals(expected, actual);

    target = PasswordInformation.builder()
        .passwordComplexity(PasswordComplexity.ON)
        .complexPasswordRegexTemplate(null)
        .minimumPasswordLength(12)
        .maximumPasswordLength(80)
        .build();
    actual = target.getPasswordRegex();
    assertEquals(expected, actual);
  }

}