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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.Valid;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bremersee.comparator.model.SortOrders;
import org.bremersee.pagebuilder.model.JsonPageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

/**
 * The dns zone page.
 *
 * @author Christian Bremer
 */
@SuppressWarnings("SameNameButDifferent")
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "A page of dns zones.")
@Valid
public class DnsZonePage extends JsonPageDto<DnsZone> {

  /**
   * Instantiates a new dns zone page.
   *
   * @param content the content
   * @param number the number
   * @param size the size
   * @param totalElements the total elements
   */
  public DnsZonePage(List<? extends DnsZone> content, int number, int size,
      long totalElements) {
    super(content, number, size, totalElements);
  }

  /**
   * Instantiates a new dns zone page.
   *
   * @param content the content
   * @param number the number
   * @param size the size
   * @param totalElements the total elements
   * @param sort the sort
   */
  public DnsZonePage(List<? extends DnsZone> content, int number, int size, long totalElements,
      SortOrders sort) {
    super(content, number, size, totalElements, sort);
  }

  /**
   * Instantiates a new dns zone page.
   *
   * @param content the content
   * @param number the number
   * @param size the size
   * @param totalElements the total elements
   * @param sort the sort
   */
  public DnsZonePage(List<? extends DnsZone> content, int number, int size, long totalElements,
      Sort sort) {
    super(content, number, size, totalElements, sort);
  }

  /**
   * Instantiates a new dns zone page.
   *
   * @param page the page
   */
  public DnsZonePage(Page<? extends DnsZone> page) {
    super(page);
  }
}
