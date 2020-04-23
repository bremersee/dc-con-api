/*
 * Copyright 2020 the original author or authors.
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

import java.time.OffsetDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;

/**
 * The dhcp lease test.
 *
 * @author Christian Bremer
 */
class DhcpLeaseTest {

  /**
   * Gets mac.
   */
  @Test
  void getMac() {
    String value = UUID.randomUUID().toString();
    DhcpLease model = new DhcpLease();
    model.setMac(value);
    assertEquals(value, model.getMac());

    model = DhcpLease.builder().mac(value).build();
    assertEquals(value, model.getMac());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().mac(value).build());

    assertTrue(model.toString().contains(value));
  }

  /**
   * Gets ip.
   */
  @Test
  void getIp() {
    String value = UUID.randomUUID().toString();
    DhcpLease model = new DhcpLease();
    model.setIp(value);
    assertEquals(value, model.getIp());

    model = DhcpLease.builder().ip(value).build();
    assertEquals(value, model.getIp());

    assertEquals(model, model);
    assertEquals(model, model.toBuilder().ip(value).build());

    assertTrue(model.toString().contains(value));
  }

  /**
   * Gets hostname.
   */
  @Test
  void getHostname() {
    String value = UUID.randomUUID().toString();
    DhcpLease model = new DhcpLease();
    model.setHostname(value);
    assertEquals(value, model.getHostname());

    model = DhcpLease.builder().hostname(value).build();
    assertEquals(value, model.getHostname());

    assertEquals(model, model);
    assertEquals(model, model.toBuilder().hostname(value).build());

    assertTrue(model.toString().contains(value));
  }

  /**
   * Gets begin.
   */
  @Test
  void getBegin() {
    OffsetDateTime value = OffsetDateTime.now();
    DhcpLease model = new DhcpLease();
    model.setBegin(value);
    assertEquals(value, model.getBegin());

    model = DhcpLease.builder().begin(value).build();
    assertEquals(value, model.getBegin());

    assertEquals(model, model);
    assertEquals(model, model.toBuilder().begin(value).build());

    assertTrue(model.toString().contains(value.toString()));
  }

  /**
   * Gets end.
   */
  @Test
  void getEnd() {
    OffsetDateTime value = OffsetDateTime.now();
    DhcpLease model = new DhcpLease();
    model.setEnd(value);
    assertEquals(value, model.getEnd());

    model = DhcpLease.builder().end(value).build();
    assertEquals(value, model.getEnd());

    assertEquals(model, model);
    assertEquals(model, model.toBuilder().end(value).build());

    assertTrue(model.toString().contains(value.toString()));
  }

  /**
   * Gets manufacturer.
   */
  @Test
  void getManufacturer() {
    String value = UUID.randomUUID().toString();
    DhcpLease model = new DhcpLease();
    model.setManufacturer(value);
    assertEquals(value, model.getManufacturer());

    model = DhcpLease.builder().manufacturer(value).build();
    assertEquals(value, model.getManufacturer());

    assertEquals(model, model);
    assertEquals(model, model.toBuilder().manufacturer(value).build());

    assertTrue(model.toString().contains(value));
  }
}