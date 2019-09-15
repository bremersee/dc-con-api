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
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

/**
 * The pair of zone name and dns node.
 *
 * @author Christian Bremer
 */
@Validated
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@SuppressWarnings("unused")
public class DnsPair implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(value = "The zone name of the dns node.", required = true)
  @JsonProperty(value = "zoneName", required = true)
  @NotNull
  private String zoneName;

  @ApiModelProperty(value = "A dns node.", required = true)
  @JsonProperty(value = "node", required = true)
  @NotNull
  private DnsNode node;

  /**
   * Instantiates a new Dns pair.
   *
   * @param zoneName the zone name
   * @param node     the node
   */
  @Builder
  public DnsPair(String zoneName, DnsNode node) {
    this.zoneName = zoneName;
    this.node = node;
  }
}
