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
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

/**
 * DNS Zone.
 */
@ApiModel(description = "DNS Zone")
@Validated
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@SuppressWarnings("unused")
public class DnsZone implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("pszZoneName")
  private String pszZoneName = null;

  @JsonProperty("flags")
  private List<DnsZoneFlag> flags = null;

  @JsonProperty("zoneType")
  private String zoneType = null;

  @JsonProperty("version")
  private String version = null;

  @JsonProperty("dwDpFlags")
  private List<DnsDwDpZoneFlag> dwDpFlags = null;

  @JsonProperty("pszDpFqdn")
  private String pszDpFqdn = null;

  /**
   * Instantiates a new dns zone.
   *
   * @param pszZoneName the psz zone name
   * @param flags       the flags
   * @param zoneType    the zone type
   * @param version     the version
   * @param dwDpFlags   the dw dp flags
   * @param pszDpFqdn   the psz dp fqdn
   */
  @Builder
  public DnsZone(
      String pszZoneName,
      List<DnsZoneFlag> flags,
      String zoneType,
      String version,
      List<DnsDwDpZoneFlag> dwDpFlags,
      String pszDpFqdn) {

    this.pszZoneName = pszZoneName;
    this.flags = flags;
    this.zoneType = zoneType;
    this.version = version;
    this.dwDpFlags = dwDpFlags;
    this.pszDpFqdn = pszDpFqdn;
  }

  /**
   * The zone name.
   *
   * @return zone name
   */
  @ApiModelProperty(value = "The zone name.")
  public String getPszZoneName() {
    return pszZoneName;
  }

  /**
   * Sets zone name.
   *
   * @param pszZoneName the zone name
   */
  public void setPszZoneName(String pszZoneName) {
    this.pszZoneName = pszZoneName;
  }

  /**
   * The zone flags.
   *
   * @return flags
   */
  @ApiModelProperty(value = "The zone flags.")
  public List<DnsZoneFlag> getFlags() {
    if (flags == null) {
      flags = new ArrayList<>();
    }
    return flags;
  }

  /**
   * Sets flags.
   *
   * @param flags the flags
   */
  public void setFlags(List<DnsZoneFlag> flags) {
    this.flags = flags;
  }

  /**
   * The zone type.
   *
   * @return zone type
   */
  @ApiModelProperty(value = "The zone type.")
  public String getZoneType() {
    return zoneType;
  }

  /**
   * Sets zone type.
   *
   * @param zoneType the zone type
   */
  public void setZoneType(String zoneType) {
    this.zoneType = zoneType;
  }

  /**
   * The version.
   *
   * @return version
   */
  @ApiModelProperty(value = "The version.")
  public String getVersion() {
    return version;
  }

  /**
   * Sets version.
   *
   * @param version the version
   */
  public void setVersion(String version) {
    this.version = version;
  }

  /**
   * The dw dp zone flags.
   *
   * @return dw dp flags
   */
  @ApiModelProperty(value = "The dwDp zone flags.")
  public List<DnsDwDpZoneFlag> getDwDpFlags() {
    if (dwDpFlags == null) {
      dwDpFlags = new ArrayList<>();
    }
    return dwDpFlags;
  }

  /**
   * Sets dw dp flags.
   *
   * @param dwDpFlags the dw dp flags
   */
  public void setDwDpFlags(List<DnsDwDpZoneFlag> dwDpFlags) {
    this.dwDpFlags = dwDpFlags;
  }

  /**
   * The psz dp fqdn.
   *
   * @return psz dp fqdn
   */
  @ApiModelProperty(value = "The pszDpFqdn.")
  public String getPszDpFqdn() {
    return pszDpFqdn;
  }

  /**
   * Sets psz dp fqdn.
   *
   * @param pszDpFqdn the psz dp fqdn
   */
  public void setPszDpFqdn(String pszDpFqdn) {
    this.pszDpFqdn = pszDpFqdn;
  }

}

