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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * The select option.
 *
 * @author Christian Bremer
 */
@Schema(description = "A select option contains a value, a display value, the information "
    + "whether it is selected or not and the information whether it is disabled or not.")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SelectOption extends GenericSelectOption<String> {

  @Serial
  private static final long serialVersionUID = 1;

  /**
   * Instantiates a new select option.
   *
   * @param value the value
   * @param displayValue the display value
   * @param sortValue the sort value
   * @param selected the selected
   * @param disabled the disabled
   * @param hidden the hidden
   */
  @Builder(toBuilder = true)
  public SelectOption(
      String value,
      String displayValue,
      String sortValue,
      boolean selected,
      boolean disabled,
      boolean hidden) {
    super(value, displayValue, sortValue, selected, disabled, hidden);
  }
}
