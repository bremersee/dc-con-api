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
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.NonNull;

/**
 * The domain group member.
 *
 * @author Christian Bremer
 */
@Schema(description = "A member of a domain group.")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter(AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DomainGroupMember implements Serializable, Comparable<DomainGroupMember> {

  @Serial
  private static final long serialVersionUID = 1;

  @Schema(description = "The distinguished name of the member.")
  @JsonProperty(value = "distinguishedName", required = true)
  private String distinguishedName;

  @Schema(description = "The object class of the member.")
  @JsonProperty(value = "objectClass", defaultValue = "unknown")
  private DomainGroupMemberType objectClass;

  @Schema(description = "The name of the member.")
  @JsonProperty("name")
  private String name;

  @Schema(description = "The display name of the member.")
  @JsonProperty("displayName")
  private String displayName;

  @Hidden
  @JsonIgnore
  private String sortValue;

  @Builder(toBuilder = true)
  public DomainGroupMember(
      String distinguishedName,
      DomainGroupMemberType objectClass,
      String name,
      String displayName,
      String sortValue) {
    this.distinguishedName = requireNonNull(distinguishedName, "Distinguished name is required.");
    this.objectClass = requireNonNullElse(objectClass, DomainGroupMemberType.UNKNOWN);
    this.name = requireNonNullElse(name, this.distinguishedName);
    this.displayName = requireNonNullElse(displayName, this.name);
    this.sortValue = requireNonNullElse(sortValue, this.displayName);
  }

  @Override
  public int compareTo(@NonNull DomainGroupMember selectOption) {
    String s0 = requireNonNullElse(getSortValue(), "");
    String s1 = requireNonNullElse(selectOption.getSortValue(), "");
    int c = s0.compareTo(s1);
    if (c != 0) {
      return c;
    }
    s0 = requireNonNullElse(getDisplayName(), "");
    s1 = requireNonNullElse(selectOption.getDisplayName(), "");
    c = s0.compareTo(s1);
    if (c != 0) {
      return c;
    }
    s0 = requireNonNullElse(getName(), "");
    s1 = requireNonNullElse(selectOption.getName(), "");
    c = s0.compareTo(s1);
    if (c != 0) {
      return c;
    }
    s0 = requireNonNullElse(getName(), "");
    s1 = requireNonNullElse(selectOption.getName(), "");
    return s0.compareTo(s1);
  }
}
