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
public class DnsRecordUpdateRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty(value = "zoneName", required = true)
  private String zoneName = null;

  @JsonProperty(value = "name", required = true)
  private String name = null;

  @JsonProperty(value = "recordType", required = true)
  private DnsRecordType recordType = null;

  @JsonProperty(value = "oldData", required = true)
  private String oldData = null;

  @JsonProperty(value = "newData", required = true)
  private String newData = null;

  /**
   * Instantiates a new dns record update request.
   *
   * @param zoneName   the zone name
   * @param name       the name
   * @param recordType the record type
   * @param oldData    the old data
   * @param newData    the new data
   */
  @Builder
  public DnsRecordUpdateRequest(
      String zoneName,
      String name,
      DnsRecordType recordType,
      String oldData,
      String newData) {

    this.zoneName = zoneName;
    this.name = name;
    this.recordType = recordType;
    this.oldData = oldData;
    this.newData = newData;
  }

  /**
   * The zone name.
   *
   * @return zoneName zone name
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
   * The record name.
   *
   * @return name name
   */
  @ApiModelProperty(required = true, value = "The record name.")
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
   * Get record type.
   *
   * @return record type
   */
  @ApiModelProperty(required = true, value = "The record type.")
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
   * The old record data.
   *
   * @return oldData old data
   */
  @ApiModelProperty(required = true, value = "The old record data.")
  @NotNull
  public String getOldData() {
    return oldData;
  }

  /**
   * Sets old data.
   *
   * @param oldData the old data
   */
  public void setOldData(String oldData) {
    this.oldData = oldData;
  }

  /**
   * The new record data.
   *
   * @return newData new data
   */
  @ApiModelProperty(required = true, value = "The new record data.")
  @NotNull
  public String getNewData() {
    return newData;
  }

  /**
   * Sets new data.
   *
   * @param newData the new data
   */
  public void setNewData(String newData) {
    this.newData = newData;
  }

}

