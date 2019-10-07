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
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

/**
 * The sub sets can be used to submit members of a group and available members or the memberships of
 * a domain user and the available groups.
 *
 * @author Christian Bremer
 */
@Validated
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode
@ToString
@SuppressWarnings({"unused", "WeakerAccess"})
public class SubSets implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty(value = "currentSet")
  private Set<String> currentSet;

  @JsonProperty(value = "availableSet")
  private Set<String> availableSet;

  /**
   * Instantiates a new Sub sets.
   */
  public SubSets() {
    this.currentSet = new TreeSet<>();
    this.availableSet = new TreeSet<>();
  }

  /**
   * Instantiates a new Sub sets.
   *
   * @param currentSet   the current set
   * @param availableSet the available set
   */
  @Builder
  public SubSets(Set<String> currentSet, Set<String> availableSet) {
    this();
    setCurrentSet(currentSet);
    setAvailableSet(availableSet);
  }

  /**
   * Gets current set.
   *
   * @return the current set
   */
  @ApiModelProperty(required = true, value = "The current set.")
  @NotNull
  public Set<String> getCurrentSet() {
    if (currentSet == null) {
      currentSet = new TreeSet<>();
    }
    return currentSet;
  }

  /**
   * Sets current set.
   *
   * @param currentSet the current set
   */
  public void setCurrentSet(Set<String> currentSet) {
    getCurrentSet().clear();
    if (currentSet != null) {
      getCurrentSet().addAll(currentSet);
    }
  }

  /**
   * Gets available set.
   *
   * @return the available set
   */
  @ApiModelProperty(required = true, value = "The available set.")
  @NotNull
  public Set<String> getAvailableSet() {
    if (availableSet == null) {
      availableSet = new TreeSet<>();
    }
    return availableSet;
  }

  /**
   * Sets available set.
   *
   * @param availableSet the available set
   */
  public void setAvailableSet(Set<String> availableSet) {
    getAvailableSet().clear();
    if (availableSet != null) {
      getAvailableSet().addAll(availableSet);
    }
  }
}
