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

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;

/**
 * The domain group test.
 *
 * @author Christian Bremer
 */
class DomainGroupTest {

  /**
   * Gets name.
   */
  @Test
  void getName() {
    String value = UUID.randomUUID().toString();
    DomainGroup model = new DomainGroup();
    model.setName(value);
    assertEquals(value, model.getName());

    model = DomainGroup.builder().name(value).build();
    assertEquals(value, model.getName());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().name(value).build());

    assertTrue(model.toString().contains(value));
  }

  /**
   * Gets description.
   */
  @Test
  void getDescription() {
    String value = UUID.randomUUID().toString();
    DomainGroup model = new DomainGroup();
    model.setDescription(value);
    assertEquals(value, model.getDescription());

    model = DomainGroup.builder().description(value).build();
    assertEquals(value, model.getDescription());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().description(value).build());

    assertTrue(model.toString().contains(value));
  }

  /**
   * Gets sid.
   */
  @Test
  void getSid() {
    Sid value = Sid.builder()
        .value(UUID.randomUUID().toString())
        .build();
    DomainGroup model = new DomainGroup();
    model.setSid(value);
    assertEquals(value, model.getSid());

    model = DomainGroup.builder().sid(value).build();
    assertEquals(value, model.getSid());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().sid(value).build());

    assertTrue(model.toString().contains(value.toString()));
  }

  /**
   * Gets members.
   */
  @Test
  void getMembers() {
    String value = UUID.randomUUID().toString();
    List<String> values = Collections.singletonList(value);
    DomainGroup model = new DomainGroup();
    model.setMembers(values);
    assertEquals(values, model.getMembers());

    model = DomainGroup.builder().members(values).build();
    assertEquals(values, model.getMembers());

    assertEquals(model, model);
    assertEquals(model, model.toBuilder().members(values).build());

    assertTrue(model.toString().contains(value));
  }
}