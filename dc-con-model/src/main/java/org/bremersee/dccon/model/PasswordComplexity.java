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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The password complexity.
 *
 * @author Christian Bremer
 */
public enum PasswordComplexity {

  /**
   * On password complexity.
   */
  ON("on"),

  /**
   * Off password complexity.
   */
  OFF("off"),

  /**
   * Default password complexity.
   */
  DEFAULT("default");

  private final String value;

  PasswordComplexity(String value) {
    this.value = value;
  }

  @JsonValue
  @Override
  public String toString() {
    return value;
  }

  /**
   * From value password complexity.
   *
   * @param value the value
   * @return the password complexity
   */
  @SuppressWarnings("unused")
  @JsonCreator
  public static PasswordComplexity fromValue(String value) {
    for (PasswordComplexity pc : PasswordComplexity.values()) {
      if (pc.value.equalsIgnoreCase(value)) {
        return pc;
      }
    }
    return ON;
  }
}
