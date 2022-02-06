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

import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * The dns node test.
 *
 * @author Christian Bremer
 */
@ExtendWith(SoftAssertionsExtension.class)
class DnsNodeTest {

  /**
   * Gets distinguished name.
   */
  @Test
  void getDistinguishedName(SoftAssertions softly) {
    String value = UUID.randomUUID().toString();
    DnsNode model = new DnsNode();
    model.setDistinguishedName(value);
    softly.assertThat(model.getDistinguishedName()).isEqualTo(value);

    model = DnsNode.builder().distinguishedName(value).build();
    softly.assertThat(model.getDistinguishedName()).isEqualTo(value);

    softly.assertThat(model).isNotEqualTo(null);
    softly.assertThat(model).isNotEqualTo(new Object());
    softly.assertThat(model).isEqualTo(model);
    softly.assertThat(model).isEqualTo(model.toBuilder().distinguishedName(value).build());

    softly.assertThat(model.toString()).contains(value);
  }

  /**
   * Gets created.
   */
  @Test
  void getCreated(SoftAssertions softly) {
    OffsetDateTime value = OffsetDateTime.now();
    DnsNode model = new DnsNode();
    model.setCreated(value);
    softly.assertThat(model.getCreated()).isEqualTo(value);

    model = DnsNode.builder().created(value).build();
    softly.assertThat(model.getCreated()).isEqualTo(value);

    softly.assertThat(model).isEqualTo(model);
    softly.assertThat(model).isEqualTo(model.toBuilder().created(value).build());

    softly.assertThat(model.toString()).contains(value.toString());
  }

  /**
   * Gets modified.
   */
  @Test
  void getModified(SoftAssertions softly) {
    OffsetDateTime value = OffsetDateTime.now();
    DnsNode model = new DnsNode();
    model.setModified(value);
    softly.assertThat(model.getModified()).isEqualTo(value);

    model = DnsNode.builder().modified(value).build();
    softly.assertThat(model.getModified()).isEqualTo(value);

    softly.assertThat(model).isEqualTo(model);
    softly.assertThat(model).isEqualTo(model.toBuilder().modified(value).build());

    softly.assertThat(model.toString()).contains(value.toString());
  }

  /**
   * Gets name.
   */
  @Test
  void getName(SoftAssertions softly) {
    String value = UUID.randomUUID().toString();
    DnsNode model = new DnsNode();
    model.setName(value);
    softly.assertThat(model.getName()).isEqualTo(value);

    model = DnsNode.builder().name(value).build();
    softly.assertThat(model.getName()).isEqualTo(value);

    softly.assertThat(model).isEqualTo(model);
    softly.assertThat(model).isEqualTo(model.toBuilder().name(value).build());

    softly.assertThat(model.toString()).contains(value);
  }

  /**
   * Gets records.
   */
  @Test
  void getRecords(SoftAssertions softly) {
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
    softly.assertThat(model.getRecords()).isEqualTo(values);

    model = DnsNode.builder().records(values).build();
    softly.assertThat(model.getRecords()).isEqualTo(values);

    softly.assertThat(model).isEqualTo(model);
    softly.assertThat(model).isEqualTo(model.toBuilder().records(values).build());

    softly.assertThat(model.toString()).contains(values.toString());
  }
}