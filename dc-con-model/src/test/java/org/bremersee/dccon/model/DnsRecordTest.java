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

import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;

/**
 * The dns record test.
 *
 * @author Christian Bremer
 */
class DnsRecordTest {

  /**
   * Gets record type.
   */
  @Test
  void getRecordType() {
    String value = UUID.randomUUID().toString();
    DnsRecord model = new DnsRecord();
    model.setRecordType(value);
    assertEquals(value, model.getRecordType());

    model = DnsRecord.builder().recordType(value).build();
    assertEquals(value, model.getRecordType());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().recordType(value).build());

    assertTrue(model.toString().contains(value));
  }

  /**
   * Gets record value.
   */
  @Test
  void getRecordValue() {
    String value = UUID.randomUUID().toString();
    DnsRecord model = new DnsRecord();
    model.setRecordValue(value);
    assertEquals(value, model.getRecordValue());

    model = DnsRecord.builder().recordValue(value).build();
    assertEquals(value, model.getRecordValue());

    assertEquals(model, model);
    assertEquals(model, model.toBuilder().recordValue(value).build());

    assertTrue(model.toString().contains(value));
  }

  /**
   * Gets record raw value.
   */
  @Test
  void getRecordRawValue() {
    byte[] value = UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8);
    DnsRecord model = new DnsRecord();
    assertFalse(model.hasRecordRawValue());
    model.setRecordRawValue(value);
    assertArrayEquals(value, model.getRecordRawValue());
    assertTrue(model.hasRecordRawValue());

    model = DnsRecord.builder().recordRawValue(value).build();
    assertArrayEquals(value, model.getRecordRawValue());

    assertEquals(model, model);
    assertEquals(model, model.toBuilder().recordRawValue(value).build());
  }

  /**
   * Gets correlated record value.
   */
  @Test
  void getCorrelatedRecordValue() {
    String value = UUID.randomUUID().toString();
    DnsRecord model = new DnsRecord();
    model.setCorrelatedRecordValue(value);
    assertEquals(value, model.getCorrelatedRecordValue());

    model = DnsRecord.builder().correlatedRecordValue(value).build();
    assertEquals(value, model.getCorrelatedRecordValue());

    assertEquals(model, model);
    assertEquals(model, model.toBuilder().correlatedRecordValue(value).build());

    assertTrue(model.toString().contains(value));
  }

  /**
   * Gets version.
   */
  @Test
  void getVersion() {
    Integer value = 1234;
    DnsRecord model = new DnsRecord();
    model.setVersion(value);
    assertEquals(value, model.getVersion());

    model = DnsRecord.builder().version(value).build();
    assertEquals(value, model.getVersion());

    assertEquals(model, model);
    assertEquals(model, model.toBuilder().version(value).build());

    assertTrue(model.toString().contains(value.toString()));
  }

  /**
   * Gets serial.
   */
  @Test
  void getSerial() {
    Integer value = 5678;
    DnsRecord model = new DnsRecord();
    model.setSerial(value);
    assertEquals(value, model.getSerial());

    model = DnsRecord.builder().serial(value).build();
    assertEquals(value, model.getSerial());

    assertEquals(model, model);
    assertEquals(model, model.toBuilder().serial(value).build());

    assertTrue(model.toString().contains(value.toString()));
  }

  /**
   * Gets ttl seconds.
   */
  @Test
  void getTtlSeconds() {
    Integer value = 9012;
    DnsRecord model = new DnsRecord();
    model.setTtlSeconds(value);
    assertEquals(value, model.getTtlSeconds());

    model = DnsRecord.builder().ttlSeconds(value).build();
    assertEquals(value, model.getTtlSeconds());

    assertEquals(model, model);
    assertEquals(model, model.toBuilder().ttlSeconds(value).build());

    assertTrue(model.toString().contains(value.toString()));
  }

  /**
   * Gets time stamp.
   */
  @Test
  void getTimeStamp() {
    OffsetDateTime value = OffsetDateTime.now();
    DnsRecord model = new DnsRecord();
    model.setTimeStamp(value);
    assertEquals(value, model.getTimeStamp());

    model = DnsRecord.builder().timeStamp(value).build();
    assertEquals(value, model.getTimeStamp());

    assertEquals(model, model);
    assertEquals(model, model.toBuilder().timeStamp(value).build());

    assertTrue(model.toString().contains(value.toString()));
  }

  /**
   * Gets dhcp lease.
   */
  @Test
  void getDhcpLease() {
    DhcpLease value = DhcpLease.builder()
        .end(OffsetDateTime.now())
        .begin(OffsetDateTime.now())
        .hostname("example")
        .ip("129.168.1.34")
        .build();
    DnsRecord model = new DnsRecord();
    model.setDhcpLease(value);
    assertEquals(value, model.getDhcpLease());

    model = DnsRecord.builder().dhcpLease(value).build();
    assertEquals(value, model.getDhcpLease());

    assertEquals(model, model);
    assertEquals(model, model.toBuilder().dhcpLease(value).build());

    assertTrue(model.toString().contains(value.toString()));
  }

  /**
   * Compare to.
   */
  @Test
  void compareTo() {
    assertTrue(new DnsRecord().compareTo(null) < 0);
    assertEquals(0, new DnsRecord().compareTo(new DnsRecord()));
    assertTrue(DnsRecord.builder().recordType("A").build()
        .compareTo(DnsRecord.builder().recordType("B").build()) < 0);
    assertTrue(DnsRecord.builder().recordType("A").recordValue("A").build()
        .compareTo(DnsRecord.builder().recordType("A").recordValue("B").build()) < 0);
  }

}