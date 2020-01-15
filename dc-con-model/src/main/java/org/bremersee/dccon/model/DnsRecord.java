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
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import java.io.Serializable;
import java.time.OffsetDateTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

/**
 * DNS Record.
 *
 * @author Christian Bremer
 */
@ApiModel(description = "DNS Record")
@Validated
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@EqualsAndHashCode(of = {"recordType", "recordValue"})
@ToString(exclude = {"recordRawValue"})
@NoArgsConstructor
public class DnsRecord implements Serializable, Comparable<DnsRecord> {

  private static final long serialVersionUID = 1L;

  /**
   * The constant SORT_ORDER_TIME_STAMP_DESC.
   */
  public static final String SORT_ORDER_TIME_STAMP_DESC = "timeStamp,desc";

  @ApiModelProperty(value = "The record type.", required = true)
  @JsonProperty(value = "recordType", required = true)
  private String recordType;

  @ApiModelProperty(value = "The record value.")
  @JsonProperty(value = "recordValue")
  private String recordValue;

  @ApiModelProperty(
      value = "The record raw active directory value.",
      accessMode = AccessMode.READ_ONLY)
  @JsonProperty("recordRawValue")
  private byte[] recordRawValue;

  @ApiModelProperty(value = "The correlated record value.", accessMode = AccessMode.READ_ONLY)
  @JsonProperty("correlatedRecordValue")
  private String correlatedRecordValue;

  @ApiModelProperty(value = "The version.", accessMode = AccessMode.READ_ONLY)
  @JsonProperty("version")
  private Integer version;

  @ApiModelProperty(value = "The serial.", accessMode = AccessMode.READ_ONLY)
  @JsonProperty("serial")
  private Integer serial;

  @ApiModelProperty(value = "TTL in seconds.", accessMode = AccessMode.READ_ONLY)
  @JsonProperty("ttlSeconds")
  private Integer ttlSeconds;

  @ApiModelProperty(value = "The time stamp.", accessMode = AccessMode.READ_ONLY)
  @JsonProperty("timeStamp")
  private OffsetDateTime timeStamp;

  @ApiModelProperty(value = "The dhcp lease.", accessMode = AccessMode.READ_ONLY)
  @JsonProperty("dhcpLease")
  private DhcpLease dhcpLease;

  /**
   * Instantiates a new Dns record.
   *
   * @param recordType the record type
   * @param recordValue the record value
   * @param recordRawValue the record raw active directory value
   * @param correlatedRecordValue the correlated record value
   * @param version the version
   * @param serial the serial
   * @param ttlSeconds the ttl seconds
   * @param timeStamp the time stamp
   * @param dhcpLease the dhcp lease
   */
  @SuppressWarnings("unused")
  @Builder(toBuilder = true)
  public DnsRecord(String recordType, String recordValue, byte[] recordRawValue,
      String correlatedRecordValue, Integer version, Integer serial, Integer ttlSeconds,
      OffsetDateTime timeStamp, DhcpLease dhcpLease) {
    this.recordType = recordType;
    this.recordValue = recordValue;
    this.recordRawValue = recordRawValue;
    this.correlatedRecordValue = correlatedRecordValue;
    this.version = version;
    this.serial = serial;
    this.ttlSeconds = ttlSeconds;
    this.timeStamp = timeStamp;
    this.dhcpLease = dhcpLease;
  }

  public boolean hasRecordRawValue() {
    return recordRawValue != null && recordRawValue.length > 0;
  }

  @Override
  public int compareTo(DnsRecord o) {
    if (o == null) {
      return -1;
    }
    String s1 = getRecordType() != null ? getRecordType() : "";
    String s2 = o.getRecordType() != null ? o.getRecordType() : "";
    int result = s1.compareToIgnoreCase(s2);
    if (result != 0) {
      return result;
    }
    s1 = getRecordValue() != null ? getRecordValue() : "";
    s2 = o.getRecordValue() != null ? o.getRecordValue() : "";
    return s1.compareToIgnoreCase(s2);
  }
}

