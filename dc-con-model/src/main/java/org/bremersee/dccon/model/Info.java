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
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

/**
 * A description of the domain controller.
 */
@ApiModel(description = "A description of the domain controller.")
@Validated
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@SuppressWarnings("unused")
public class Info implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("nameServerHosts")
  private List<String> nameServerHosts = new ArrayList<>();

  /**
   * Instantiates a new info object.
   *
   * @param nameServerHosts the host names of the name servers
   */
  @Builder
  public Info(List<String> nameServerHosts) {
    if (nameServerHosts != null) {
      this.nameServerHosts = nameServerHosts;
    }
  }

  /**
   * Gets the host names of the name servers.
   *
   * @return nameServerHosts the host names of the name servers
   */
  @ApiModelProperty(value = "The host names of the name servers.")
  public List<String> getNameServerHosts() {
    if (nameServerHosts == null) {
      nameServerHosts = new ArrayList<>();
    }
    return nameServerHosts;
  }

  /**
   * Sets the host names of the name servers.
   *
   * @param nameServerHosts the host names of the name servers
   */
  public void setNameServerHosts(List<String> nameServerHosts) {
    this.nameServerHosts = nameServerHosts;
  }

}

