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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * The membership of a domain user or group.
 *
 * @author Christian Bremer
 */
@Schema(description = "A membership of a domain user or group.")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@ToString
@EqualsAndHashCode
public class MemberOf implements Serializable, Comparable<MemberOf> {

  @Serial
  private static final long serialVersionUID = 1;

  @Schema(description = "The distinguished name of the domain group.")
  @JsonProperty(value = "distinguishedName", required = true)
  private String distinguishedName;

  @Schema(description = "The name of the domain group.")
  @JsonProperty(value = "name", required = true)
  private String name;

  @Builder(toBuilder = true)
  @JsonCreator
  public MemberOf(
      @JsonProperty(value = "distinguishedName", required = true) String distinguishedName,
      @JsonProperty(value = "name", required = true) String name) {
    this.distinguishedName = requireNonNull(distinguishedName, "Distinguished name is required.");
    this.name = requireNonNull(name, "Name is required.");
  }

  @Override
  public int compareTo(MemberOf o) {
    String s0 = requireNonNullElse(getName(), "");
    String s1 = requireNonNullElse(o.getName(), "");
    return s0.compareToIgnoreCase(s1);
  }
}
