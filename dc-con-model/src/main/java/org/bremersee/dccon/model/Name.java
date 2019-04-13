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
 * A simple name or a distinguished name.
 */
@ApiModel(description = "A simple name or a distinguished name.")
@Validated
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@SuppressWarnings({"unused", "WeakerAccess"})
public class Name implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty(value = "value", required = true)
  private String value = null;

  @JsonProperty(value = "distinguishedName", required = true)
  private Boolean distinguishedName = Boolean.FALSE;

  /**
   * Instantiates a new Name.
   *
   * @param value             the value
   * @param distinguishedName the distinguished name
   */
  @Builder
  public Name(String value, Boolean distinguishedName) {
    setValue(value);
    setDistinguishedName(distinguishedName);
  }

  /**
   * The value.
   *
   * @return value
   */
  @ApiModelProperty(required = true, value = "The name.")
  @NotNull
  public String getValue() {
    return value;
  }

  /**
   * Sets value.
   *
   * @param value the value
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * Is the name a distinguished name.
   *
   * @return distinguishedName distinguished name
   */
  @ApiModelProperty(required = true, value = "Is the name a distinguished name?")
  @NotNull
  public Boolean getDistinguishedName() {
    if (distinguishedName == null) {
      distinguishedName = Boolean.FALSE;
    }
    return distinguishedName;
  }

  /**
   * Sets distinguished name.
   *
   * @param distinguishedName the distinguished name
   */
  public void setDistinguishedName(Boolean distinguishedName) {
    this.distinguishedName = Boolean.TRUE.equals(distinguishedName);
  }

}

