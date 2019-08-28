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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

/**
 * The correlated dns record.
 *
 * @author Christian Bremer
 */
@ApiModel(description = "Correlated DNS Record")
@Validated
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@SuppressWarnings("unused")
public class CorrelatedDnsRecord extends AbstractDnsRecord {

  private static final long serialVersionUID = 1L;

  @JsonProperty("zoneName")
  private String zoneName;

  @JsonProperty("entryName")
  private String entryName;

  /**
   * Instantiates a new Correlated dns record.
   *
   * @param zoneName    the zone name
   * @param entryName   the entry name
   * @param recordType  the record type
   * @param recordValue the record value
   * @param flags       the flags
   * @param serial      the serial
   * @param ttl         the ttl
   */
  @Builder
  public CorrelatedDnsRecord(
      String zoneName,
      String entryName,
      String recordType,
      String recordValue,
      String flags,
      String serial,
      String ttl) {
    super(recordType, recordValue, flags, serial, ttl);
    this.zoneName = zoneName;
    this.entryName = entryName;
  }

  /**
   * Gets zone name.
   *
   * @return the zone name
   */
  public String getZoneName() {
    return zoneName;
  }

  /**
   * Sets zone name.
   *
   * @param zoneName the zone name
   */
  public void setZoneName(String zoneName) {
    this.zoneName = zoneName;
  }

  /**
   * Gets entry name.
   *
   * @return the entry name
   */
  public String getEntryName() {
    return entryName;
  }

  /**
   * Sets entry name.
   *
   * @param entryName the entry name
   */
  public void setEntryName(String entryName) {
    this.entryName = entryName;
  }
}
