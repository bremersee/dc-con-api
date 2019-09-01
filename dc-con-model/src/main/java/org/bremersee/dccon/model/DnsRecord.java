/*
 * Copyright 2018 the original author or authors.
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
 * DNS Record.
 */
@ApiModel(description = "DNS Record")
@Validated
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@SuppressWarnings("unused")
public class DnsRecord extends AbstractDnsRecord {

  private static final long serialVersionUID = 1L;

  @JsonProperty("dhcpLease")
  private DhcpLease dhcpLease;

  @JsonProperty("correlatedDnsRecord")
  private CorrelatedDnsRecord correlatedDnsRecord;

  /**
   * Instantiates a new dns record.
   *
   * @param recordType          the record type
   * @param recordValue         the record value
   * @param flags               the flags
   * @param serial              the serial
   * @param ttl                 the ttl
   * @param dhcpLease           the dhcp lease
   * @param correlatedDnsRecord the correlated dns record
   */
  @Builder
  public DnsRecord(
      String recordType,
      String recordValue,
      String flags,
      String serial,
      String ttl,
      DhcpLease dhcpLease,
      CorrelatedDnsRecord correlatedDnsRecord) {
    super(recordType, recordValue, flags, serial, ttl);
    this.dhcpLease = dhcpLease;
    this.correlatedDnsRecord = correlatedDnsRecord;
  }

  /**
   * Gets dhcp lease.
   *
   * @return the dhcp lease
   */
  public DhcpLease getDhcpLease() {
    return dhcpLease;
  }

  /**
   * Sets dhcp lease.
   *
   * @param dhcpLease the dhcp lease
   */
  public void setDhcpLease(DhcpLease dhcpLease) {
    this.dhcpLease = dhcpLease;
  }

  /**
   * Gets correlated dns record.
   *
   * @return the correlated dns record
   */
  public CorrelatedDnsRecord getCorrelatedDnsRecord() {
    return correlatedDnsRecord;
  }

  /**
   * Sets correlated dns record.
   *
   * @param correlatedDnsRecord the correlated dns record
   */
  public void setCorrelatedDnsRecord(CorrelatedDnsRecord correlatedDnsRecord) {
    this.correlatedDnsRecord = correlatedDnsRecord;
  }
}

