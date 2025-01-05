/*
 * Copyright 2024 the original author or authors.
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

package org.bremersee.dccon.dto;

import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNullElse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.DiscriminatorMapping;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.NonNull;

/**
 * The select option.
 *
 * @param <D> the type parameter
 * @author Christian Bremer
 */
@Schema(
    description = "A generic select option contains a value, a display value, the information "
        + "whether it is selected or not and the information whether it is disabled or not.",
    discriminatorProperty = "_type",
    discriminatorMapping = {
        @DiscriminatorMapping(
            value = "org.bremersee.dccon.model.SelectOption", schema = SelectOption.class)
    })
@JsonTypeInfo(use = Id.CLASS, property = "_type", visible = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter(AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GenericSelectOption<D extends Serializable>
    implements Serializable, Comparable<GenericSelectOption<D>> {

  @Serial
  private static final long serialVersionUID = 1;

  /**
   * The value.
   */
  @Schema(description = "The value.")
  @JsonProperty(value = "value", required = true)
  String value;

  /**
   * The display value.
   */
  @Schema(description = "The display value.")
  @JsonProperty(value = "displayValue")
  D displayValue;

  @Hidden
  @JsonIgnore
  String sortValue;

  /**
   * The information whether it is selected or not.
   */
  @Schema(description = "The information whether it is selected or not.")
  @JsonProperty(value = "selected", defaultValue = "false")
  boolean selected;

  /**
   * The information whether it is disabled or not.
   */
  @Schema(description = "The information whether it is disabled or not.")
  @JsonProperty(value = "disabled", defaultValue = "false")
  boolean disabled;

  /**
   * The information whether it is hidden or not.
   */
  @Schema(description = "The information whether it is hidden or not.")
  @JsonProperty(value = "hidden", defaultValue = "false")
  boolean hidden;

  /**
   * Instantiates a new Select option.
   *
   * @param value the value
   * @param displayValue the display value
   * @param sortValue the sort value
   * @param selected the selected
   * @param disabled the disabled
   * @param hidden the hidden
   */
  public GenericSelectOption(
      String value,
      D displayValue,
      String sortValue,
      boolean selected,
      boolean disabled,
      boolean hidden) {
    this.value = requireNonNull(value, "Value is required.");
    this.displayValue = displayValue;
    this.sortValue = requireNonNullElse(sortValue, this.value);
    this.selected = selected;
    this.disabled = disabled;
    this.hidden = hidden;
  }

  @Override
  public int compareTo(@NonNull GenericSelectOption<D> selectOption) {
    String s0 = requireNonNullElse(getSortValue(), "");
    String s1 = requireNonNullElse(selectOption.getSortValue(), "");
    int c = s0.compareTo(s1);
    if (c != 0) {
      return c;
    }
    s0 = requireNonNullElse(getValue(), "");
    s1 = requireNonNullElse(selectOption.getValue(), "");
    return s0.compareTo(s1);
  }

}
