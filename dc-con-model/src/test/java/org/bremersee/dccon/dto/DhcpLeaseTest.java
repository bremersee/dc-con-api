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

package org.bremersee.dccon.dto;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * The dhcp lease test.
 *
 * @author Christian Bremer
 */
@ExtendWith(SoftAssertionsExtension.class)
class DhcpLeaseTest {

  private Set<Integer> db = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

  @Test
  void stream() {
    Set<Integer> all = new HashSet<>(db);
    System.out.println("1) " + all);
    Stream<Integer> result = consumeA(all);
    System.out.println("2) " + all);
    if (!all.isEmpty()) {
      result = Stream.concat(result, consumeB(all));
      System.out.println("3) " + all);
    }
    System.out.println("4) " +  result.toList());
    System.out.println("5) " +  all);
  }

  private Stream<Integer> consumeA(Set<Integer> set) {
    return db.stream()
        .peek(v -> {
          if (v % 2 == 0) {
            System.out.println("A) " + set.remove(v));
          }
          //return v;
        })
        .filter(v -> v % 2 == 0);

  }

  private Stream<Integer> consumeB(Set<Integer> set) {
    return db.stream()
        .peek(v -> {
          if (v % 2 != 0) {
            System.out.println("B) " + set.remove(v));
          }
          //return v;
        })
        .filter(v -> v % 2 != 0);

  }

  @Test
  void set() {
    Set<Integer> all = new HashSet<>(db);
    System.out.println("1) " + all);
    Set<Integer> result = new LinkedHashSet<>(consumeAA(all));
    System.out.println("2) " + all);
    if (!all.isEmpty()) {
      result.addAll(consumeBB(all));
      System.out.println("3) " + all);
    }
    System.out.println("4) " +  result);
  }

  private Set<Integer> consumeAA(Set<Integer> set) {
    return db.stream()
        .map(v -> {
          if (v % 2 == 0) {
            System.out.println("A) " + set.remove(v));
          }
          return v;
        })
        .filter(v -> v % 2 == 0)
        .collect(Collectors.toSet());

  }

  private Set<Integer> consumeBB(Set<Integer> set) {
    return db.stream()
        .map(v -> {
          if (v % 2 != 0) {
            System.out.println("B) " + set.remove(v));
          }
          return v;
        })
        .filter(v -> v % 2 != 0)
        .collect(Collectors.toSet());

  }

  @Test
  void combine() {
    Set<Integer> all = new HashSet<>(db);
    System.out.println("1) " + all);
    Result result = consumeAAA(all);
    System.out.println("2) " + result.result);
    if (!result.result().isEmpty()) {
      result = consumeBBB(result.result());
      System.out.println("3) " + result.result);
    }
    System.out.println("4) " +  result.result);
    System.out.println("5) " +  result.s.collect(Collectors.toSet()));
  }

  private Result consumeAAA(Set<Integer> set) {
    Stream<Integer> s = db.stream()
        .map(v -> {
          if (v % 2 == 0) {
            System.out.println("A) " + set.remove(v));
          }
          return v;
        })
        .filter(v -> v % 2 == 0);
    return new Result(s, set);
  }

  private Result consumeBBB(Set<Integer> set) {
    Stream<Integer> s = db.stream()
        .map(v -> {
          if (v % 2 != 0) {
            System.out.println("B) " + set.remove(v));
          }
          return v;
        })
        .filter(v -> v % 2 != 0);
    return new Result(s, set);
  }

  record Result(Stream<Integer> s, Set<Integer> result) {}

  /**
   * Gets mac.
   *
   * @param softly the soft assertions
   */
  @Test
  void getMac(SoftAssertions softly) {
    String value = UUID.randomUUID().toString();
    DhcpLease model = new DhcpLease();
    model.setMac(value);
    softly.assertThat(model.getMac()).isEqualTo(value);

    model = DhcpLease.builder().mac(value).build();
    softly.assertThat(model.getMac()).isEqualTo(value);

    softly.assertThat(model).isNotEqualTo(null);
    softly.assertThat(model).isNotEqualTo(new Object());
    softly.assertThat(model).isEqualTo(model);
    softly.assertThat(model).isEqualTo(model.toBuilder().mac(value).build());

    softly.assertThat(model.toString()).contains(value);
  }

  /**
   * Gets ip.
   *
   * @param softly the soft assertions
   */
  @Test
  void getIp(SoftAssertions softly) {
    String value = UUID.randomUUID().toString();
    DhcpLease model = new DhcpLease();
    model.setIp(value);
    softly.assertThat(model.getIp()).isEqualTo(value);

    model = DhcpLease.builder().ip(value).build();
    softly.assertThat(model.getIp()).isEqualTo(value);

    softly.assertThat(model).isEqualTo(model);
    softly.assertThat(model).isEqualTo(model.toBuilder().ip(value).build());

    softly.assertThat(model.toString()).contains(value);
  }

  /**
   * Gets hostname.
   *
   * @param softly the soft assertions
   */
  @Test
  void getHostname(SoftAssertions softly) {
    String value = UUID.randomUUID().toString();
    DhcpLease model = new DhcpLease();
    model.setHostname(value);
    softly.assertThat(model.getHostname()).isEqualTo(value);

    model = DhcpLease.builder().hostname(value).build();
    softly.assertThat(model.getHostname()).isEqualTo(value);

    softly.assertThat(model).isEqualTo(model);
    softly.assertThat(model).isEqualTo(model.toBuilder().hostname(value).build());

    softly.assertThat(model.toString()).contains(value);
  }

  /**
   * Gets begin.
   *
   * @param softly the soft assertions
   */
  @Test
  void getBegin(SoftAssertions softly) {
    OffsetDateTime value = OffsetDateTime.now();
    DhcpLease model = new DhcpLease();
    model.setBegin(value);
    softly.assertThat(model.getBegin()).isEqualTo(value);

    model = DhcpLease.builder().begin(value).build();
    softly.assertThat(model.getBegin()).isEqualTo(value);

    softly.assertThat(model).isEqualTo(model);
    softly.assertThat(model).isEqualTo(model.toBuilder().begin(value).build());

    softly.assertThat(model.toString()).contains(value.toString());
  }

  /**
   * Gets end.
   *
   * @param softly the soft assertions
   */
  @Test
  void getEnd(SoftAssertions softly) {
    OffsetDateTime value = OffsetDateTime.now();
    DhcpLease model = new DhcpLease();
    model.setEnd(value);
    softly.assertThat(model.getEnd()).isEqualTo(value);

    model = DhcpLease.builder().end(value).build();
    softly.assertThat(model.getEnd()).isEqualTo(value);

    softly.assertThat(model).isEqualTo(model);
    softly.assertThat(model).isEqualTo(model.toBuilder().end(value).build());

    softly.assertThat(model.toString()).contains(value.toString());
  }

  /**
   * Gets manufacturer.
   *
   * @param softly the soft assertions
   */
  @Test
  void getManufacturer(SoftAssertions softly) {
    String value = UUID.randomUUID().toString();
    DhcpLease model = new DhcpLease();
    model.setManufacturer(value);
    softly.assertThat(model.getManufacturer()).isEqualTo(value);

    model = DhcpLease.builder().manufacturer(value).build();
    softly.assertThat(model.getManufacturer()).isEqualTo(value);

    softly.assertThat(model).isEqualTo(model);
    softly.assertThat(model).isEqualTo(model.toBuilder().manufacturer(value).build());

    softly.assertThat(model.toString()).contains(value);
  }
}