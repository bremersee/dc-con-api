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
 * Request body to change a password. Administrators can just set the new password. Normal users
 * must also set the previous password.
 */
@ApiModel(description = "Request body to change a password. Administrators can just set the new "
    + "password. Normal users must also set the previous password.")
@Validated
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@SuppressWarnings("unused")
public class Password implements Serializable {

  private static final long serialVersionUID = 2L;

  @JsonProperty(value = "value", required = true)
  private String value;

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
   * @param value         the new password
   * @param previousValue the previous password
   */
  @Builder(toBuilder = true)
  public Password(String value, String previousValue) {
    this.value = value;
    this.previousValue = previousValue;
  }

  /**
   * The new password.
   *
   * @return the new password
   */
  @ApiModelProperty(required = true, value = "The new password.")
  @NotNull
  public String getValue() {
    return value;
  }

  /**
   * Sets new password.
   *
   * @param value the new password
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * Gets previous password.
   *
   * @return the previous password
   */
  @ApiModelProperty(required = true, value = "The previous password.")
  public String getPreviousValue() {
    return previousValue;
  }

  /**
   * Sets previous password.
   *
   * @param previousValue the previous password
   */
  public void setPreviousValue(String previousValue) {
    this.previousValue = previousValue;
  }
}

