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
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

/**
 * The dhcp lease.
 *
 * @author Christian Bremer
 */
@ApiModel(description = "A dhcp lease of the dhcp server.")
@Validated
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@SuppressWarnings("unused")
public class DhcpLease implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * The constant SORT_ORDER_BEGIN_HOSTNAME.
   */
  public static final String SORT_ORDER_BEGIN_HOSTNAME = "begin,desc|hostname";

  /**
   * The constant SORT_ORDER_IP_BEGIN_HOSTNAME.
   */
  public static final String SORT_ORDER_IP_BEGIN_HOSTNAME = "ip|begin,desc";

  @JsonProperty("mac")
  private String mac;

  @JsonProperty("ip")
  private String ip;

  @JsonProperty("hostname")
  private String hostname;

  @JsonProperty("begin")
  private OffsetDateTime begin;

  @JsonProperty("end")
  private OffsetDateTime end;

  @JsonProperty("manufacturer")
  private String manufacturer;

  /**
   * Instantiates a new dhcp lease.
   *
   * @param mac          the mac
   * @param ip           the ip
   * @param hostname     the hostname
   * @param begin        the begin
   * @param end          the end
   * @param manufacturer the manufacturer
   */
  @Builder
  public DhcpLease(
      String mac,
      String ip,
      String hostname,
      OffsetDateTime begin,
      OffsetDateTime end,
      String manufacturer) {
    this.mac = mac;
    this.ip = ip;
    this.hostname = hostname;
    this.begin = begin;
    this.end = end;
    this.manufacturer = manufacturer;
  }

  /**
   * Gets mac.
   *
   * @return the mac
   */
  @ApiModelProperty(value = "The mac of the client.", accessMode = AccessMode.READ_ONLY)
  public String getMac() {
    return mac;
  }

  /**
   * Sets mac.
   *
   * @param mac the mac
   */
  public void setMac(String mac) {
    this.mac = mac;
  }

  /**
   * Gets ip.
   *
   * @return the ip
   */
  @ApiModelProperty(value = "The ip of the client.", accessMode = AccessMode.READ_ONLY)
  public String getIp() {
    return ip;
  }

  /**
   * Sets ip.
   *
   * @param ip the ip
   */
  public void setIp(String ip) {
    this.ip = ip;
  }

  /**
   * Gets hostname.
   *
   * @return the hostname
   */
  @ApiModelProperty(value = "The host name of the client.", accessMode = AccessMode.READ_ONLY)
  public String getHostname() {
    return hostname;
  }

  /**
   * Sets hostname.
   *
   * @param hostname the hostname
   */
  public void setHostname(String hostname) {
    this.hostname = hostname;
  }

  /**
   * Gets begin.
   *
   * @return the begin
   */
  @ApiModelProperty(value = "The start time of the lease.", accessMode = AccessMode.READ_ONLY)
  public OffsetDateTime getBegin() {
    return begin;
  }

  /**
   * Sets begin.
   *
   * @param begin the begin
   */
  public void setBegin(OffsetDateTime begin) {
    this.begin = begin;
  }

  /**
   * Gets end.
   *
   * @return the end
   */
  @ApiModelProperty(value = "The end time of the lease.", accessMode = AccessMode.READ_ONLY)
  public OffsetDateTime getEnd() {
    return end;
  }

  /**
   * Sets end.
   *
   * @param end the end
   */
  public void setEnd(OffsetDateTime end) {
    this.end = end;
  }

  /**
   * Gets manufacturer.
   *
   * @return the manufacturer
   */
  @ApiModelProperty(value = "The manufacturer of the client.", accessMode = AccessMode.READ_ONLY)
  public String getManufacturer() {
    return manufacturer;
  }

  /**
   * Sets manufacturer.
   *
   * @param manufacturer the manufacturer
   */
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }
}
