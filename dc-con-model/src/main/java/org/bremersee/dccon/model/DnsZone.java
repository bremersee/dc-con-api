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
import java.time.OffsetDateTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

/**
 * DNS Zone.
 *
 * @author Christian Bremer
 */
@Schema(description = "DNS Zone")
@Validated
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class DnsZone extends CommonAttributes {

  private static final long serialVersionUID = 2L;

  @JsonProperty(value = "name", required = true)
  private String name = null;

  @JsonProperty(value = "defaultZone")
  private Boolean defaultZone;

  @JsonProperty(value = "reverseZone")
  private Boolean reverseZone;

  /**
   * Instantiates a new dns zone.
   *
   * @param distinguishedName the distinguished name
   * @param created the created
   * @param modified the modified
   * @param name the zone name
   * @param defaultZone the default zone
   * @param reverseZone the reverse zone
   */
  @SuppressWarnings("unused")
  @Builder(toBuilder = true)
  public DnsZone(
      String distinguishedName,
      OffsetDateTime created,
      OffsetDateTime modified,
      String name,
      Boolean defaultZone,
      Boolean reverseZone) {

    super(distinguishedName, created, modified);
    this.name = name;
    this.defaultZone = Boolean.TRUE.equals(defaultZone);
    this.reverseZone = Boolean.TRUE.equals(reverseZone);
  }

  /**
   * The zone name.
   *
   * @return zone name
   */
  @Schema(description = "The zone name.", required = true)
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
  @Schema(
      description = "Specifies whether this zone is the default zone or not.",
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

  /**
   * Gets reverse zone.
   *
   * @return the reverse zone
   */
  @Schema(
      description = "Specifies whether this zone is a reverse zone or not.",
      accessMode = AccessMode.READ_ONLY)
  public Boolean getReverseZone() {
    return Boolean.TRUE.equals(reverseZone);
  }

  /**
   * Sets reverse zone.
   *
   * @param reverseZone the reverse zone
   */
  public void setReverseZone(Boolean reverseZone) {
    this.reverseZone = reverseZone;
  }

}

