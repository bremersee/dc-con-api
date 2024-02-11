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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import java.io.Serial;
import java.time.OffsetDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DNS node.
 *
 * @author Christian Bremer
 */
@Schema(description = "DNS node")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class DnsNode extends CommonAttributes {

  @Serial
  private static final long serialVersionUID = 2L;

  /**
   * The constant NAME.
   */
  public static final String NAME = "name";

  /**
   * The node name. It can be the host name or a part of the ip address (e. g. 1.178 or 178).
   */
  @Schema(
      description = "The entry name (host name or part of the ip address).",
      requiredMode = RequiredMode.REQUIRED)
  @JsonProperty(value = NAME, required = true)
  private String name;

  /**
   * The constant RECORDS.
   */
  public static final String RECORDS = "records";

  /**
   * The name server records.
   */
  @Schema(description = "The name server records.")
  @JsonProperty(RECORDS)
  private Set<DnsRecord> records;

  /**
   * Instantiates a new dns node.
   *
   * @param distinguishedName the distinguished name
   * @param created the created
   * @param modified the modified
   * @param name the name
   * @param records the records
   */
  @Builder(toBuilder = true)
  public DnsNode(
      String distinguishedName,
      OffsetDateTime created,
      OffsetDateTime modified,
      String name,
      Set<DnsRecord> records) {
    super(distinguishedName, created, modified);
    this.name = name;
    this.records = records;
  }

  /**
   * The name server records.
   *
   * @return the name server records
   */
  @Schema(description = "The name server records.")
  public Set<DnsRecord> getRecords() {
    if (records == null) {
      records = new LinkedHashSet<>();
    }
    return records;
  }

}

