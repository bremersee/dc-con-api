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
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

/**
 * DNS Record Create Request.
 */
@ApiModel(description = "DNS Record Create Request")
@Validated
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@SuppressWarnings("unused")
public class DnsRecordRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty(value = "zoneName", required = true)
  private String zoneName = null;

  @JsonProperty(value = "name", required = true)
  private String name = null;

  @JsonProperty(value = "recordType", required = true)
  private DnsRecordType recordType = null;

  @JsonProperty(value = "data", required = true)
  private String data = null;

  /**
   * Instantiates a new dns record request.
   *
   * @param zoneName   the zone name
   * @param name       the name
   * @param recordType the record type
   * @param data       the data
   */
  @Builder
  public DnsRecordRequest(
      String zoneName,
      String name,
      DnsRecordType recordType,
      String data) {

    this.zoneName = zoneName;
    this.name = name;
    this.recordType = recordType;
    this.data = data;
  }

  /**
   * The zone name.
   *
   * @return zone name
   */
  @ApiModelProperty(required = true, value = "The zone name.")
  @NotNull
  public String getZoneName() {
    return zoneName;
  }

  /**
   * Sets zone name.
   *
   * @param zoneName the zone name
   */
  public void setZoneName(String zoneName) {
    this.zoneName = zoneName;
  }

  /**
   * The record name. It can be the host name or a part of the ip address (e. g. 1.178 or 178).
   *
   * @return name
   */
  @ApiModelProperty(required = true, value = "The record name (host name or part of the ip address).")
  @NotNull
  public String getName() {
    return name;
  }

  /**
   * Sets name.
   *
   * @param name the name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get record type (A|CNAME|PTR etc).
   *
   * @return the record type
   */
  @ApiModelProperty(required = true, value = "The record type (A|CNAME|PTR etc).")
  @NotNull
  public DnsRecordType getRecordType() {
    return recordType;
  }

  /**
   * Sets record type.
   *
   * @param recordType the record type
   */
  public void setRecordType(DnsRecordType recordType) {
    this.recordType = recordType;
  }

  /**
   * The record data. It can be the ip address or the full qualified host name.
   *
   * @return data data
   */
  @ApiModelProperty(
      required = true,
      value = "The record data (ip address or the full qualified host name).")
  @NotNull
  public String getData() {
    return data;
  }

  /**
   * Sets data.
   *
   * @param data the data
   */
  public void setData(String data) {
    this.data = data;
  }

}

