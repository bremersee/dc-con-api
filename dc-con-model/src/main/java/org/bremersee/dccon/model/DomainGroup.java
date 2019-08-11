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
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

/**
 * Domain group.
 */
@ApiModel(description = "Domain group.")
@Validated
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@SuppressWarnings("unused")
public class DomainGroup extends DomainGroupItem implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("members")
  private List<String> members = null;

  /**
   * Instantiates a new Domain group.
   *
   * @param distinguishedName the distinguished name
   * @param created           the created
   * @param modified          the modified
   * @param name              the name
   * @param members           the members
   */
  @Builder
  public DomainGroup(
      String distinguishedName,
      OffsetDateTime created,
      OffsetDateTime modified,
      String name,
      List<String> members) {

    super(distinguishedName, created, modified, name);
    this.members = members;
  }

  /**
   * The members of the domain group.
   *
   * @return members members
   */
  @ApiModelProperty(value = "The members of the domain group.")
  public List<String> getMembers() {
    if (members == null) {
      members = new ArrayList<>();
    }
    return members;
  }

  /**
   * Sets members.
   *
   * @param members the members
   */
  public void setMembers(List<String> members) {
    this.members = members;
  }

}

