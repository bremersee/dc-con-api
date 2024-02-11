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
import jakarta.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The pair of zone name and dns node.
 *
 * @author Christian Bremer
 */
@Schema(description = "A dns node and t's zone.")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class DnsPair implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  /**
   * The constant ZONE_NAME.
   */
  public static final String ZONE_NAME = "zoneName";

  @Schema(
      description = "The zone name of the dns node.",
      requiredMode = RequiredMode.REQUIRED,
      accessMode = AccessMode.READ_ONLY)
  @JsonProperty(value = ZONE_NAME, required = true)
  @NotNull
  private String zoneName;

  /**
   * The constant NODE.
   */
  public static final String NODE = "node";

  @Schema(
      description = "A dns node.",
      requiredMode = RequiredMode.REQUIRED,
      accessMode = AccessMode.READ_ONLY)
  @JsonProperty(value = NODE, required = true)
  @NotNull
  private DnsNode node;

  /**
   * The constant NODE_EXISTS.
   */
  public static final String NODE_EXISTS = "nodeExists";

  @Schema(
      description = "Information about the existence of the dns node.",
      accessMode = AccessMode.READ_ONLY)
  @JsonProperty(value = NODE_EXISTS)
  private Boolean nodeExists;

  /**
   * Instantiates a new dns pair.
   *
   * @param zoneName the zone name
   * @param node the node
   * @param nodeExists {@code true} if the dns node exists, otherwise {@code false}
   */
  @Builder(toBuilder = true)
  public DnsPair(String zoneName, DnsNode node, Boolean nodeExists) {
    this.zoneName = zoneName;
    this.node = node;
    this.nodeExists = nodeExists;
  }
}
