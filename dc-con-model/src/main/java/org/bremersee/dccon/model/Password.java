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
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import java.io.Serial;
import java.io.Serializable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Request body to change a password. Administrators can just set the new password. Normal users
 * must also set the previous password.
 *
 * @author Christian Bremer
 */
@Schema(description = "Request body to change a password. Administrators can just set the new "
    + "password. Normal users must also set the previous password.")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = {"value", "previousValue"})
@NoArgsConstructor
public class Password implements Serializable {

  @Serial
  private static final long serialVersionUID = 2L;

  /**
   * The new password.
   */
  @Schema(requiredMode = RequiredMode.REQUIRED, description = "The new password.")
  @JsonProperty(value = "value", required = true)
  private String value;

  /**
   * The previous password.
   */
  @Schema(requiredMode = RequiredMode.REQUIRED, description = "The previous password.")
  @JsonProperty(value = "previousValue")
  private String previousValue;

  /**
   * Instantiates a new password request body.
   *
   * @param value the new password
   */
  public Password(String value) {
    this.value = value;
  }

  /**
   * Instantiates a new password request body.
   *
   * @param value the new password
   * @param previousValue the previous password
   */
  @Builder(toBuilder = true)
  public Password(String value, String previousValue) {
    this.value = value;
    this.previousValue = previousValue;
  }

}

