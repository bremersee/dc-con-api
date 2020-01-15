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

import static org.bremersee.dccon.model.UnknownFilter.ALL;
import static org.bremersee.dccon.model.UnknownFilter.NO_UNKNOWN;
import static org.bremersee.dccon.model.UnknownFilter.UNKNOWN;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * The unknown filter test.
 *
 * @author Christian Bremer
 */
class UnknownFilterTest {

  /**
   * Matches.
   */
  @Test
  void matches() {
    for (UnknownFilter filter : UnknownFilter.values()) {
      assertFalse(filter.matches(null));
      assertFalse(filter.matches(DnsRecord.builder().build()));
    }
    assertTrue(ALL.matches(DnsRecord.builder().recordType("UNKNOWN").build()));
    assertTrue(UNKNOWN.matches(DnsRecord.builder().recordType("UNKNOWN").build()));
    assertFalse(NO_UNKNOWN.matches(DnsRecord.builder().recordType("UNKNOWN").build()));
  }
}