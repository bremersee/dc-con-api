/*
 * Copyright 2022 the original author or authors.
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

import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.bremersee.comparator.model.SortOrder;
import org.bremersee.comparator.model.SortOrders;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

/**
 * The dns node page test.
 *
 * @author Christian Bremer
 */
@ExtendWith(SoftAssertionsExtension.class)
class DnsNodePageTest {

  /**
   * Test constructors.
   *
   * @param softly the soft assertions
   */
  @Test
  void testConstructors(SoftAssertions softly) {
    softly.assertThat(new DnsNodePage(
            List.of(), 0, 0, 0))
        .isNotNull();
    softly.assertThat(new DnsNodePage(
            List.of(), 0, 0, 0, SortOrders.by(SortOrder.by(DnsNode.NAME))))
        .isNotNull();
    softly.assertThat(new DnsNodePage(
            List.of(), 0, 0, 0, Sort.by(DnsNode.NAME)))
        .isNotNull();
    softly.assertThat(new DnsNodePage(Page.empty()))
        .isNotNull();
  }
}