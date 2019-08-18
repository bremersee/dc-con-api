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
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
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
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@SuppressWarnings("unused")
public class DnsRecord implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("recordType")
  private String recordType = null;

  @JsonProperty("recordValue")
  private String recordValue = null;

  @JsonProperty("flags")
  private String flags = null;

  @JsonProperty("serial")
  private String serial = null;

  @JsonProperty("ttl")
  private String ttl = null;

  @JsonProperty("dhcpLease")
  private DhcpLease dhcpLease;

  /**
   * Instantiates a new dns record.
   *
   * @param recordType  the record type
   * @param recordValue the record value
   * @param flags       the flags
   * @param serial      the serial
   * @param ttl         the ttl
   * @param dhcpLease   the dhcp lease
   */
  @Builder
  public DnsRecord(
      String recordType,
      String recordValue,
      String flags,
      String serial,
      String ttl,
      DhcpLease dhcpLease) {
    this.recordType = recordType;
    this.recordValue = recordValue;
    this.flags = flags;
    this.serial = serial;
    this.ttl = ttl;
    this.dhcpLease = dhcpLease;
  }

  /**
   * The record type (A|CNAME|PTR etc).
   *
   * @return the record type
   */
  @ApiModelProperty(value = "The record type (A|CNAME|PTR etc).")
  public String getRecordType() {
    return recordType;
  }

  /**
   * Sets record type.
   *
   * @param recordType the record type
   */
  public void setRecordType(String recordType) {
    this.recordType = recordType;
  }

  /**
   * The record value. It can be the ip address or the full qualified host name.
   *
   * @return record value
   */
  @ApiModelProperty(value = "The record value (IP address or full qualified host name).")
  public String getRecordValue() {
    return recordValue;
  }

  /**
   * Sets record value.
   *
   * @param recordValue the record value
   */
  public void setRecordValue(String recordValue) {
    this.recordValue = recordValue;
  }

  /**
   * The name server record flags.
   *
   * @return flags flags
   */
  @ApiModelProperty(value = "The name server record flags.")
  public String getFlags() {
    return flags;
  }

  /**
   * Sets flags.
   *
   * @param flags the flags
   */
  public void setFlags(String flags) {
    this.flags = flags;
  }

  /**
   * The name server record serial.
   *
   * @return serial serial
   */
  @ApiModelProperty(value = "The name server record serial.")
  public String getSerial() {
    return serial;
  }

  /**
   * Sets serial.
   *
   * @param serial the serial
   */
  public void setSerial(String serial) {
    this.serial = serial;
  }

  /**
   * The name server record ttl.
   *
   * @return ttl ttl
   */
  @ApiModelProperty(value = "The name server record ttl.")
  public String getTtl() {
    return ttl;
  }

  /**
   * Sets ttl.
   *
   * @param ttl the ttl
   */
  public void setTtl(String ttl) {
    this.ttl = ttl;
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
}

