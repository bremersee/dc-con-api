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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import java.io.Serializable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * The windows/samba SID. Well known (system) SIDs are listed
 * <a href="https://support.microsoft.com/en-us/help/243330/well-known-security-identifiers-in-windows-operating-systems">here</a>.
 *
 * @author Christian Bremer
 */
@ApiModel(description = "The SID of the entity.")
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Sid implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty(value = "value", required = true)
  private String value = null;

  @JsonProperty(value = "value")
  private Boolean systemAccount;

  /**
   * Instantiates a new SID.
   *
   * @param value         the value
   * @param systemAccount the system account
   */
  @Builder(toBuilder = true)
  public Sid(String value, Boolean systemAccount) {
    setValue(value);
    setSystemAccount(systemAccount);
  }

  /**
   * Gets value.
   *
   * @return the value
   */
  @ApiModelProperty(
      value = "The SID of the entity.",
      accessMode = AccessMode.READ_ONLY)
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
   * Gets system account.
   *
   * @return the system account
   */
  @ApiModelProperty(
      value = "Tells whether the entity is a system entity or not.",
      accessMode = AccessMode.READ_ONLY)
  public Boolean getSystemAccount() {
    return systemAccount;
  }

  /**
   * Sets system account.
   *
   * @param systemAccount the system account
   */
  public void setSystemAccount(Boolean systemAccount) {
    this.systemAccount = systemAccount;
  }

}
