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
import java.time.OffsetDateTime;
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
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@SuppressWarnings("unused")
public class DnsZone extends CommonAttributes {

  private static final long serialVersionUID = 2L;

  @JsonProperty(value = "name", required = true)
  private String name = null;

  @JsonProperty(value = "defaultZone")
  private Boolean defaultZone;

  /**
   * Instantiates a new dns zone.
   *
   * @param distinguishedName the distinguished name
   * @param created           the created
   * @param modified          the modified
   * @param name              the zone name
   * @param defaultZone       the default zone
   */
  @Builder(toBuilder = true)
  public DnsZone(
      String distinguishedName,
      OffsetDateTime created,
      OffsetDateTime modified,
      String name,
      Boolean defaultZone) {

    super(distinguishedName, created, modified);
    this.name = name;
    this.defaultZone = Boolean.TRUE.equals(defaultZone);
  }

  /**
   * The zone name.
   *
   * @return zone name
   */
  @ApiModelProperty(value = "The zone name.", required = true)
  public String getName() {
    return name;
  }

  /**
   * Sets zone name.
   *
   * @param name the zone name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets default zone.
   *
   * @return the default zone
   */
  @ApiModelProperty(
      value = "Specifies whether this zone is the default zone or not.",
      accessMode = AccessMode.READ_ONLY)
  public Boolean getDefaultZone() {
    return Boolean.TRUE.equals(defaultZone);
  }

  /**
   * Sets default zone.
   *
   * @param defaultZone the default zone
   */
  public void setDefaultZone(Boolean defaultZone) {
    this.defaultZone = defaultZone;
  }
}

