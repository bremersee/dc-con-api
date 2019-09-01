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
import java.time.OffsetDateTime;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

/**
 * Domain group item.
 */
@ApiModel(description = "Domain group item.")
@Validated
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@SuppressWarnings("WeakerAccess")
public class DomainGroupItem extends CommonAttributes implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * The constant DEFAULT_SORT_ORDER.
   */
  public static final String DEFAULT_SORT_ORDER = "name";

  @JsonProperty("name")
  private String name = null;

  /**
   * Instantiates a new Domain group item.
   *
   * @param distinguishedName the distinguished name
   * @param created           the created
   * @param modified          the modified
   * @param name              the name
   */
  public DomainGroupItem(
      String distinguishedName,
      OffsetDateTime created,
      OffsetDateTime modified,
      String name) {

    super(distinguishedName, created, modified);
    this.name = name;
  }

  /**
   * The name of the domain group.
   *
   * @return name name
   */
  @ApiModelProperty(value = "The name of the domain group.")
  public String getName() {
    return name;
  }

  /**
   * Sets name.
   *
   * @param name the name
   */
  public void setName(String name) {
    this.name = name;
  }

}

