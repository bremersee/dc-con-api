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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

/**
 * A list with names.
 */
@ApiModel(description = "A list with names.")
@Validated
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@SuppressWarnings("unused")
public class Names implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("values")
  private List<Name> values = null;

  /**
   * Instantiates a new list of names.
   *
   * @param values the values
   */
  public Names(List<? extends Name> values) {
    if (values != null) {
      this.values = new ArrayList<>(values);
    }
  }

  /**
   * The names.
   *
   * @return values values
   */
  @ApiModelProperty(value = "The names.")
  public List<Name> getValues() {
    if (values == null) {
      values = new ArrayList<>();
    }
    return values;
  }

  /**
   * Sets values.
   *
   * @param values the values
   */
  public void setValues(List<Name> values) {
    this.values = values;
  }

}

