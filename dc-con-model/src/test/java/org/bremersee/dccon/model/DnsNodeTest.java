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
import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;

/**
 * The dns node test.
 *
 * @author Christian Bremer
 */
class DnsNodeTest {

  /**
   * Gets distinguished name.
   */
  @Test
  void getDistinguishedName() {
    String value = UUID.randomUUID().toString();
    DnsNode model = new DnsNode();
    model.setDistinguishedName(value);
    assertEquals(value, model.getDistinguishedName());

    model = DnsNode.builder().distinguishedName(value).build();
    assertEquals(value, model.getDistinguishedName());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().distinguishedName(value).build());

    assertTrue(model.toString().contains(value));
  }

  /**
   * Gets created.
   */
  @Test
  void getCreated() {
    OffsetDateTime value = OffsetDateTime.now();
    DnsNode model = new DnsNode();
    model.setCreated(value);
    assertEquals(value, model.getCreated());

    model = DnsNode.builder().created(value).build();
    assertEquals(value, model.getCreated());

    assertEquals(model, model);
    assertEquals(model, model.toBuilder().created(value).build());

    assertTrue(model.toString().contains(value.toString()));
  }

  /**
   * Gets modified.
   */
  @Test
  void getModified() {
    OffsetDateTime value = OffsetDateTime.now();
    DnsNode model = new DnsNode();
    model.setModified(value);
    assertEquals(value, model.getModified());

    model = DnsNode.builder().modified(value).build();
    assertEquals(value, model.getModified());

    assertEquals(model, model);
    assertEquals(model, model.toBuilder().modified(value).build());

    assertTrue(model.toString().contains(value.toString()));
  }

  /**
   * Gets name.
   */
  @Test
  void getName() {
    String value = UUID.randomUUID().toString();
    DnsNode model = new DnsNode();
    model.setName(value);
    assertEquals(value, model.getName());

    model = DnsNode.builder().name(value).build();
    assertEquals(value, model.getName());

    assertEquals(model, model);
    assertEquals(model, model.toBuilder().name(value).build());

    assertTrue(model.toString().contains(value));
  }

  /**
   * Gets records.
   */
  @Test
  void getRecords() {
    DnsRecord value = DnsRecord.builder()
        .correlatedRecordValue(UUID.randomUUID().toString())
        .recordRawValue(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8))
        .recordType("A")
        .recordValue(UUID.randomUUID().toString())
        .serial(1234)
        .timeStamp(OffsetDateTime.now())
        .ttlSeconds(5678)
        .version(1)
        .build();
    Set<DnsRecord> values = Collections.singleton(value);
    DnsNode model = new DnsNode();
    model.setRecords(values);
    assertEquals(values, model.getRecords());

    model = DnsNode.builder().records(values).build();
    assertEquals(values, model.getRecords());

    assertEquals(model, model);
    assertEquals(model, model.toBuilder().records(values).build());

    assertTrue(model.toString().contains(value.toString()));
  }
}