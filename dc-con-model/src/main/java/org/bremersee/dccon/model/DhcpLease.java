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
import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The dhcp lease.
 *
 * @author Christian Bremer
 */
@Schema(description = "A dhcp lease of the dhcp server.")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class DhcpLease implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  /**
   * The constant MAC.
   */
  public static final String MAC = "mac";

  /**
   * Gets mac.
   */
  @Schema(description = "The mac of the client.", accessMode = AccessMode.READ_ONLY)
  @JsonProperty(MAC)
  private String mac;

  /**
   * The constant IP.
   */
  public static final String IP = "ip";

  /**
   * Gets ip.
   */
  @Schema(description = "The ip of the client.", accessMode = AccessMode.READ_ONLY)
  @JsonProperty(IP)
  private String ip;

  /**
   * The constant HOSTNAME.
   */
  public static final String HOSTNAME = "hostname";

  /**
   * Gets hostname.
   */
  @Schema(description = "The host name of the client.", accessMode = AccessMode.READ_ONLY)
  @JsonProperty(HOSTNAME)
  private String hostname;

  /**
   * The constant BEGIN.
   */
  public static final String BEGIN = "begin";

  /**
   * Gets begin.
   */
  @Schema(description = "The start time of the lease.", accessMode = AccessMode.READ_ONLY)
  @JsonProperty(BEGIN)
  private OffsetDateTime begin;

  /**
   * The constant END.
   */
  public static final String END = "end";

  /**
   * Gets end.
   */
  @Schema(description = "The end time of the lease.", accessMode = AccessMode.READ_ONLY)
  @JsonProperty(END)
  private OffsetDateTime end;

  /**
   * The constant MANUFACTURER.
   */
  public static final String MANUFACTURER = "manufacturer";

  /**
   * Gets manufacturer.
   */
  @Schema(description = "The manufacturer of the client.", accessMode = AccessMode.READ_ONLY)
  @JsonProperty(MANUFACTURER)
  private String manufacturer;

  /**
   * Instantiates a new dhcp lease.
   *
   * @param mac the mac
   * @param ip the ip
   * @param hostname the hostname
   * @param begin the begin
   * @param end the end
   * @param manufacturer the manufacturer
   */
  @Builder(toBuilder = true)
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

}
