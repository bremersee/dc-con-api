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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Objects;
import lombok.Getter;

/**
 * The enum DomainGroupType.
 *
 * @author Christian Bremer
 */
@Getter
public enum DomainGroupType {

  UNKNOWN(0),

  SECURITY(-2147483646),

  DISTRIBUTION(2); // for sending mails

  @JsonIgnore
  private final int value;

  DomainGroupType(int value) {
    this.value = value;
  }

  @JsonValue
  @Override
  public String toString() {
    return name().toLowerCase();
  }

  @JsonCreator
  public static DomainGroupType fromString(String type) {
    if (Objects.isNull(type)) {
      return null;
    }
    try {
      return DomainGroupType.valueOf(type.toUpperCase());
    } catch (IllegalArgumentException e) {
      return null;
    }
  }

  public static DomainGroupType fromValue(Integer value) {
    if (Objects.isNull(value)) {
      return UNKNOWN;
    }
    for (DomainGroupType type : DomainGroupType.values()) {
      if (type.value == value) {
        return type;
      }
    }
    return UNKNOWN;
  }

}
