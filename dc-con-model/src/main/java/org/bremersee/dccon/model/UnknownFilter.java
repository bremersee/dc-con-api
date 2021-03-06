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

import java.util.Optional;

/**
 * The unknown filter.
 *
 * @author Christian Bremer
 */
public enum UnknownFilter {

  /**
   * All unknown filter.
   */
  ALL,

  /**
   * No unknown filter.
   */
  NO_UNKNOWN,

  /**
   * Unknown filter.
   */
  UNKNOWN;

  /**
   * Matches record type filter.
   *
   * @param dnsRecord the dns record
   * @return the boolean
   */
  public boolean matches(DnsRecord dnsRecord) {
    return Optional.ofNullable(dnsRecord)
        .map(DnsRecord::getRecordType)
        .map(recordType -> {
          switch (this) {
            case NO_UNKNOWN:
              return !recordType.equalsIgnoreCase("UNKNOWN");
            case UNKNOWN:
              return recordType.equalsIgnoreCase("UNKNOWN");
            default:
              return true;
          }
        })
        .orElse(false);
  }

}
