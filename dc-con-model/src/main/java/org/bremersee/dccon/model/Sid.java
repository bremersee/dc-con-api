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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The windows/samba SID. Well known (system) SIDs are listed <a
 * href="https://support.microsoft.com/en-us/help/243330/well-known-security-identifiers-in-windows-operating-systems">here</a>.
 *
 * @author Christian Bremer
 */
@Schema(description = "The SID of the entity.")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class Sid implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  /**
   * The constant VALUE.
   */
  public static final String VALUE = "value";

  /**
   * The SID of the entity.
   */
  @Schema(
      description = "The SID of the entity.",
      accessMode = AccessMode.READ_ONLY)
  @JsonProperty(value = VALUE, required = true)
  private String value = null;

  /**
   * The constant SYSTEM_ENTITY.
   */
  public static final String SYSTEM_ENTITY = "systemEntity";

  @JsonProperty(value = SYSTEM_ENTITY)
  private Boolean systemEntity;

  /**
   * Instantiates a new SID.
   *
   * @param value the value
   * @param systemEntity the system entity
   */
  @Builder(toBuilder = true)
  public Sid(String value, Boolean systemEntity) {
    setValue(value);
    setSystemEntity(systemEntity);
  }

  /**
   * Gets system entity.
   *
   * @return the system entity
   */
  @Schema(
      description = "Tells whether the entity is a system entity or not.",
      accessMode = AccessMode.READ_ONLY)
  public Boolean getSystemEntity() {
    return Boolean.TRUE.equals(systemEntity);
  }

}
