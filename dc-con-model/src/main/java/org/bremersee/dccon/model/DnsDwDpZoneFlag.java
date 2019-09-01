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
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

/**
 * DNS Zone Flag.
 */
@ApiModel(description = "DNS Zone Flag")
@Validated
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@SuppressWarnings("unused")
public class DnsDwDpZoneFlag implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty(value = "name", required = true)
  private String name = null;

  /**
   * Instantiates a new dns zone flag.
   *
   * @param name the name
   */
  @Builder
  public DnsDwDpZoneFlag(String name) {
    this.name = name;
  }

  /**
   * The zone flag name.
   *
   * @return zone flag name
   */
  @ApiModelProperty(required = true, value = "The zone flag name.")
  @NotNull
  public String getName() {
    return name;
  }

  /**
   * Sets zone flag name.
   *
   * @param name the zone flag name
   */
  public void setName(String name) {
    this.name = name;
  }

}

