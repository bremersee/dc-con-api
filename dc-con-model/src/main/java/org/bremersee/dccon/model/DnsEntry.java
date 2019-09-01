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
 * DNS Entry.
 */
@ApiModel(description = "DNS Entry")
@Validated
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@SuppressWarnings("unused")
public class DnsEntry implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("records")
  private List<DnsRecord> records = null;

  /**
   * Instantiates a new dns entry.
   *
   * @param name    the name
   * @param records the records
   */
  @Builder
  public DnsEntry(String name, List<DnsRecord> records) {
    this.name = name;
    this.records = records;
  }

  /**
   * The entry name. It can be the host name or a part of the ip address (e. g. 1.178 or 178).
   *
   * @return the entry name
   */
  @ApiModelProperty(value = "The entry name (host name or part of the ip address).")
  public String getName() {
    return name;
  }

  /**
   * Sets entry name.
   *
   * @param name the entry name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * The name server records.
   *
   * @return the name server records
   */
  @ApiModelProperty(value = "The name server records.")
  public List<DnsRecord> getRecords() {
    if (records == null) {
      records = new ArrayList<>();
    }
    return records;
  }

  /**
   * Sets name server records.
   *
   * @param records the name server records
   */
  public void setRecords(List<DnsRecord> records) {
    this.records = records;
  }

}

