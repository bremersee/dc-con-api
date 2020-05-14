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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;

/**
 * The domain user test.
 *
 * @author Christian Bremer
 */
class DomainUserTest {

  /**
   * Gets enabled.
   */
  @Test
  void getEnabled() {
    DomainUser model = new DomainUser();
    model.setEnabled(true);
    assertEquals(true, model.getEnabled());

    model = DomainUser.builder().enabled(true).build();
    assertEquals(true, model.getEnabled());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().enabled(true).build());

    assertTrue(model.toString().contains("true"));
  }

  /**
   * Gets groups.
   */
  @Test
  void getGroups() {
    String value = UUID.randomUUID().toString();
    List<String> values = Collections.singletonList(value);
    DomainUser model = new DomainUser();
    model.setGroups(values);
    assertEquals(values, model.getGroups());

    model = DomainUser.builder().groups(values).build();
    assertEquals(values, model.getGroups());

    assertEquals(model, model);
    assertEquals(model, model.toBuilder().groups(values).build());

    assertTrue(model.toString().contains(value));
  }

  /**
   * Gets sid.
   */
  @Test
  void getSid() {
    Sid value = Sid.builder()
        .value(UUID.randomUUID().toString())
        .systemEntity(true)
        .build();
    DomainUser model = new DomainUser();
    model.setSid(value);
    assertEquals(value, model.getSid());

    model = DomainUser.builder().sid(value).build();
    assertEquals(value, model.getSid());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().sid(value).build());

    assertTrue(model.toString().contains(value.toString()));
  }

  /**
   * Gets user name.
   */
  @Test
  void getUserName() {
    String value = UUID.randomUUID().toString();
    DomainUser model = new DomainUser();
    model.setUserName(value);
    assertEquals(value, model.getUserName());

    model = DomainUser.builder().userName(value).build();
    assertEquals(value, model.getUserName());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().userName(value).build());

    assertTrue(model.toString().contains(value));
  }

  /**
   * Gets first name.
   */
  @Test
  void getFirstName() {
    String value = UUID.randomUUID().toString();
    DomainUser model = new DomainUser();
    model.setFirstName(value);
    assertEquals(value, model.getFirstName());

    model = DomainUser.builder().firstName(value).build();
    assertEquals(value, model.getFirstName());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().firstName(value).build());

    assertTrue(model.toString().contains(value));
  }

  /**
   * Gets last name.
   */
  @Test
  void getLastName() {
    String value = UUID.randomUUID().toString();
    DomainUser model = new DomainUser();
    model.setLastName(value);
    assertEquals(value, model.getLastName());

    model = DomainUser.builder().lastName(value).build();
    assertEquals(value, model.getLastName());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().lastName(value).build());

    assertTrue(model.toString().contains(value));
  }

  /**
   * Gets display name.
   */
  @Test
  void getDisplayName() {
    String value = UUID.randomUUID().toString();
    DomainUser model = new DomainUser();
    model.setDisplayName(value);
    assertEquals(value, model.getDisplayName());

    model = DomainUser.builder().displayName(value).build();
    assertEquals(value, model.getDisplayName());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().displayName(value).build());

    assertTrue(model.toString().contains(value));
  }

  /**
   * Gets email.
   */
  @Test
  void getEmail() {
    String value = UUID.randomUUID().toString();
    DomainUser model = new DomainUser();
    model.setEmail(value);
    assertEquals(value, model.getEmail());

    model = DomainUser.builder().email(value).build();
    assertEquals(value, model.getEmail());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().email(value).build());

    assertTrue(model.toString().contains(value));
  }

  /**
   * Gets telephone number.
   */
  @Test
  void getTelephoneNumber() {
    String value = UUID.randomUUID().toString();
    DomainUser model = new DomainUser();
    model.setTelephoneNumber(value);
    assertEquals(value, model.getTelephoneNumber());

    model = DomainUser.builder().telephoneNumber(value).build();
    assertEquals(value, model.getTelephoneNumber());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().telephoneNumber(value).build());

    assertTrue(model.toString().contains(value));
  }

  /**
   * Gets mobile.
   */
  @Test
  void getMobile() {
    String value = UUID.randomUUID().toString();
    DomainUser model = new DomainUser();
    model.setMobile(value);
    assertEquals(value, model.getMobile());

    model = DomainUser.builder().mobile(value).build();
    assertEquals(value, model.getMobile());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().mobile(value).build());

    assertTrue(model.toString().contains(value));
  }

  /**
   * Gets description.
   */
  @Test
  void getDescription() {
    String value = UUID.randomUUID().toString();
    DomainUser model = new DomainUser();
    model.setDescription(value);
    assertEquals(value, model.getDescription());

    model = DomainUser.builder().description(value).build();
    assertEquals(value, model.getDescription());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().description(value).build());

    assertTrue(model.toString().contains(value));
  }

  /**
   * Gets home directory.
   */
  @Test
  void getHomeDirectory() {
    String value = UUID.randomUUID().toString();
    DomainUser model = new DomainUser();
    model.setHomeDirectory(value);
    assertEquals(value, model.getHomeDirectory());

    model = DomainUser.builder().homeDirectory(value).build();
    assertEquals(value, model.getHomeDirectory());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().homeDirectory(value).build());

    assertTrue(model.toString().contains(value));
  }

  /**
   * Gets unix home directory.
   */
  @Test
  void getUnixHomeDirectory() {
    String value = UUID.randomUUID().toString();
    DomainUser model = new DomainUser();
    model.setUnixHomeDirectory(value);
    assertEquals(value, model.getUnixHomeDirectory());

    model = DomainUser.builder().unixHomeDirectory(value).build();
    assertEquals(value, model.getUnixHomeDirectory());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().unixHomeDirectory(value).build());

    assertTrue(model.toString().contains(value));
  }

  /**
   * Gets login shell.
   */
  @Test
  void getLoginShell() {
    String value = UUID.randomUUID().toString();
    DomainUser model = new DomainUser();
    model.setLoginShell(value);
    assertEquals(value, model.getLoginShell());

    model = DomainUser.builder().loginShell(value).build();
    assertEquals(value, model.getLoginShell());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().loginShell(value).build());

    assertTrue(model.toString().contains(value));
  }

  /**
   * Gets last logon.
   */
  @Test
  void getLastLogon() {
    String value = UUID.randomUUID().toString();
    DomainUser model = new DomainUser();
    model.setLoginShell(value);
    assertEquals(value, model.getLoginShell());

    model = DomainUser.builder().loginShell(value).build();
    assertEquals(value, model.getLoginShell());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().loginShell(value).build());

    assertTrue(model.toString().contains(value));
  }

  /**
   * Gets logon count.
   */
  @Test
  void getLogonCount() {
    Integer value = 1234;
    DomainUser model = new DomainUser();
    model.setLogonCount(value);
    assertEquals(value, model.getLogonCount());

    model = DomainUser.builder().logonCount(value).build();
    assertEquals(value, model.getLogonCount());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().logonCount(value).build());

    assertTrue(model.toString().contains(value.toString()));
  }

  /**
   * Gets password last set.
   */
  @Test
  void getPasswordLastSet() {
    OffsetDateTime value = OffsetDateTime.now();
    DomainUser model = new DomainUser();
    model.setPasswordLastSet(value);
    assertEquals(value, model.getPasswordLastSet());

    model = DomainUser.builder().passwordLastSet(value).build();
    assertEquals(value, model.getPasswordLastSet());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().passwordLastSet(value).build());

    assertTrue(model.toString().contains(value.toString()));
  }

  /**
   * Gets password.
   */
  @Test
  void getPassword() {
    String value = UUID.randomUUID().toString();
    DomainUser model = new DomainUser();
    model.setPassword(value);
    assertEquals(value, model.getPassword());

    model = DomainUser.builder().password(value).build();
    assertEquals(value, model.getPassword());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().password(value).build());

    assertFalse(model.toString().contains(value));
  }
}