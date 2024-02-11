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
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import java.io.Serial;
import java.time.OffsetDateTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DNS Zone.
 *
 * @author Christian Bremer
 */
@Schema(description = "DNS Zone")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class DnsZone extends CommonAttributes {

  @Serial
  private static final long serialVersionUID = 2L;

  /**
   * The constant NAME.
   */
  public static final String NAME = "name";

  /**
   * The zone name.
   */
  @Schema(description = "The zone name.", requiredMode = RequiredMode.REQUIRED)
  @JsonProperty(value = NAME, required = true)
  private String name;

  /**
   * The constant DEFAULT_ZONE.
   */
  public static final String DEFAULT_ZONE = "defaultZone";

  @Schema(
      description = "Specifies whether this zone is the default zone or not.",
      accessMode = AccessMode.READ_ONLY)
  @JsonProperty(value = DEFAULT_ZONE)
  private Boolean defaultZone;

  /**
   * The constant REVERSE_ZONE.
   */
  public static final String REVERSE_ZONE = "reverseZone";

  @Schema(
      description = "Specifies whether this zone is a reverse zone or not.",
      accessMode = AccessMode.READ_ONLY)
  @JsonProperty(value = REVERSE_ZONE)
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
   * Gets default zone.
   *
   * @return the default zone
   */
  public Boolean getDefaultZone() {
    return Boolean.TRUE.equals(defaultZone);
  }

  /**
   * Gets reverse zone.
   *
   * @return the reverse zone
   */
  public Boolean getReverseZone() {
    return Boolean.TRUE.equals(reverseZone);
  }

}

