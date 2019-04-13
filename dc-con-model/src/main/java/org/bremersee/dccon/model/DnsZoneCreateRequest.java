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
 * DNS Zone Create Request.
 */
@ApiModel(description = "DNS Zone Create Request")
@Validated
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@SuppressWarnings("unused")
public class DnsZoneCreateRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty(value = "pszZoneName", required = true)
  private String pszZoneName = null;

  /**
   * Instantiates a new dns zone create request.
   *
   * @param pszZoneName the psz zone name
   */
  @Builder
  public DnsZoneCreateRequest(String pszZoneName) {
    this.pszZoneName = pszZoneName;
  }

  /**
   * The zone name.
   *
   * @return pszZoneName psz zone name
   */
  @ApiModelProperty(required = true, value = "The zone name.")
  @NotNull
  public String getPszZoneName() {
    return pszZoneName;
  }

  /**
   * Sets psz zone name.
   *
   * @param pszZoneName the psz zone name
   */
  public void setPszZoneName(String pszZoneName) {
    this.pszZoneName = pszZoneName;
  }

}

