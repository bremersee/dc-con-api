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

/**
 * The dhcp lease add parameter.
 *
 * @author Christian Bremer
 */
public enum AddDhcpLeaseParameter {

  /**
   * None dhcp lease add parameter.
   */
  NONE,

  /**
   * Active dhcp lease add parameter.
   */
  ACTIVE,

  /**
   * All dhcp lease add parameter.
   */
  ALL;

  /**
   * From value dhcp lease add parameter.
   *
   * @param text             the text
   * @param defaultParameter the default parameter
   * @return the dhcp lease add parameter
   */
  public static AddDhcpLeaseParameter fromValue(
      String text,
      AddDhcpLeaseParameter defaultParameter) {

    for (AddDhcpLeaseParameter parameter : AddDhcpLeaseParameter.values()) {
      if (parameter.name().equalsIgnoreCase(text)) {
        return parameter;
      }
    }
    return defaultParameter;
  }

}
