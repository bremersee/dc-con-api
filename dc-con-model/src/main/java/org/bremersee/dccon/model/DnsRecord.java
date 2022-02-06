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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import java.io.Serializable;
import java.time.OffsetDateTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

/**
 * DNS Record.
 *
 * @author Christian Bremer
 */
@Schema(description = "DNS Record")
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
   * The constant RECORD_TYPE.
   */
  public static final String RECORD_TYPE = "recordType";

  @Schema(description = "The record type.", required = true)
  @JsonProperty(value = RECORD_TYPE, required = true)
  private String recordType;

  /**
   * The constant RECORD_VALUE.
   */
  public static final String RECORD_VALUE = "recordValue";

  @Schema(description = "The record value.")
  @JsonProperty(value = RECORD_VALUE)
  private String recordValue;

  /**
   * The constant RECORD_RAW_VALUE.
   */
  public static final String RECORD_RAW_VALUE = "recordRawValue";

  @Schema(
      description = "The record raw active directory value.",
      accessMode = AccessMode.READ_ONLY)
  @JsonProperty(RECORD_RAW_VALUE)
  private byte[] recordRawValue;

  /**
   * The constant CORRELATED_RECORD_VALUE.
   */
  public static final String CORRELATED_RECORD_VALUE = "correlatedRecordValue";

  @Schema(description = "The correlated record value.", accessMode = AccessMode.READ_ONLY)
  @JsonProperty(CORRELATED_RECORD_VALUE)
  private String correlatedRecordValue;

  /**
   * The constant VERSION.
   */
  public static final String VERSION = "version";

  @Schema(description = "The version.", accessMode = AccessMode.READ_ONLY)
  @JsonProperty(VERSION)
  private Integer version;

  /**
   * The constant SERIAL.
   */
  public static final String SERIAL = "serial";

  @Schema(description = "The serial.", accessMode = AccessMode.READ_ONLY)
  @JsonProperty(SERIAL)
  private Integer serial;

  /**
   * The constant TTL_SECONDS.
   */
  public static final String TTL_SECONDS = "ttlSeconds";

  @Schema(description = "TTL in seconds.", accessMode = AccessMode.READ_ONLY)
  @JsonProperty(TTL_SECONDS)
  private Integer ttlSeconds;

  /**
   * The constant TIME_STAMP.
   */
  public static final String TIME_STAMP = "timeStamp";

  @Schema(description = "The time stamp.", accessMode = AccessMode.READ_ONLY)
  @JsonProperty(TIME_STAMP)
  private OffsetDateTime timeStamp;

  /**
   * The constant DHCP_LEASE.
   */
  public static final String DHCP_LEASE = "dhcpLease";

  @Schema(description = "The dhcp lease.", accessMode = AccessMode.READ_ONLY)
  @JsonProperty(DHCP_LEASE)
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

  /**
   * Has record raw value boolean.
   *
   * @return the boolean
   */
  public boolean hasRecordRawValue() {
    return recordRawValue != null && recordRawValue.length > 0;
  }

  @Override
  public int compareTo(@NonNull DnsRecord o) {
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

