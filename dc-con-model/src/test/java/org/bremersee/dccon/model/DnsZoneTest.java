/*
 * Copyright 2019 the original author or authors.
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

import java.util.UUID;
import org.junit.jupiter.api.Test;

/**
 * The dns zone test.
 *
 * @author Christian Bremer
 */
class DnsZoneTest {

  /**
   * Gets name.
   */
  @Test
  void getName() {
    String value = UUID.randomUUID().toString();
    DnsZone model = new DnsZone();
    model.setName(value);
    assertEquals(value, model.getName());

    model = DnsZone.builder().name(value).build();
    assertEquals(value, model.getName());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().name(value).build());

    assertTrue(model.toString().contains(value));
  }

  /**
   * Gets default zone.
   */
  @Test
  void getDefaultZone() {
    DnsZone model = new DnsZone();
    model.setDefaultZone(true);
    assertEquals(true, model.getDefaultZone());

    model = DnsZone.builder().defaultZone(true).build();
    assertEquals(true, model.getDefaultZone());

    assertNotEquals(model, true);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().defaultZone(true).build());

    assertTrue(model.toString().contains("true"));
  }

  /**
   * Gets reverse zone.
   */
  @Test
  void getReverseZone() {
    DnsZone model = new DnsZone();
    model.setReverseZone(true);
    assertEquals(true, model.getReverseZone());

    model = DnsZone.builder().reverseZone(true).build();
    assertEquals(true, model.getReverseZone());

    assertNotEquals(model, true);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().reverseZone(true).build());

    assertTrue(model.toString().contains("true"));
  }
}