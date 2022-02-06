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
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import io.swagger.v3.oas.annotations.media.DiscriminatorMapping;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import java.io.Serializable;
import java.time.OffsetDateTime;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

/**
 * Common attributes.
 *
 * @author Christian Bremer
 */
@Schema(
    description = "Common attributes",
    discriminatorProperty = "_type",
    discriminatorMapping = {
        @DiscriminatorMapping(
            value = "org.bremersee.dccon.model.DnsNode", schema = DnsNode.class),
        @DiscriminatorMapping(
            value = "org.bremersee.dccon.model.DnsZone", schema = DnsZone.class),
        @DiscriminatorMapping(
            value = "org.bremersee.dccon.model.DomainGroup", schema = DomainGroup.class),
        @DiscriminatorMapping(
            value = "org.bremersee.dccon.model.DomainUser", schema = DomainUser.class)
    })
@Validated
@JsonTypeInfo(use = Id.CLASS, property = "_type", visible = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public abstract class CommonAttributes implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * The constant DISTINGUISHED_NAME.
   */
  public static final String DISTINGUISHED_NAME = "distinguishedName";

  /**
   * The Distinguished name.
   */
  @JsonProperty(value = DISTINGUISHED_NAME)
  String distinguishedName = null;

  /**
   * The constant CREATED.
   */
  public static final String CREATED = "created";

  /**
   * The Created.
   */
  @JsonProperty(CREATED)
  OffsetDateTime created = null;

  /**
   * The constant MODIFIED.
   */
  public static final String MODIFIED = "modified";

  /**
   * The Modified.
   */
  @JsonProperty(MODIFIED)
  OffsetDateTime modified = null;

  /**
   * Instantiates a new common attributes.
   *
   * @param distinguishedName the distinguished name
   * @param created the created
   * @param modified the modified
   */
  public CommonAttributes(
      String distinguishedName,
      OffsetDateTime created,
      OffsetDateTime modified) {

    this.distinguishedName = distinguishedName;
    this.created = created;
    this.modified = modified;
  }

  /**
   * The distinguished name in the active directory.
   *
   * @return distinguishedName distinguished name
   */
  @Schema(
      description = "The distinguished name in the active directory.",
      accessMode = AccessMode.READ_ONLY)
  public String getDistinguishedName() {
    return distinguishedName;
  }

  /**
   * Sets distinguished name.
   *
   * @param distinguishedName the distinguished name
   */
  public void setDistinguishedName(String distinguishedName) {
    this.distinguishedName = distinguishedName;
  }

  /**
   * The creation date.
   *
   * @return created created
   */
  @Schema(description = "The creation date.", accessMode = AccessMode.READ_ONLY)
  public OffsetDateTime getCreated() {
    return created;
  }

  /**
   * Sets creation date.
   *
   * @param created the creation date
   */
  public void setCreated(OffsetDateTime created) {
    this.created = created;
  }

  /**
   * The last modification date.
   *
   * @return modified modified
   */
  @Schema(description = "The last modification date.", accessMode = AccessMode.READ_ONLY)
  public OffsetDateTime getModified() {
    return modified;
  }

  /**
   * Sets last modification date.
   *
   * @param modified the last modification date
   */
  public void setModified(OffsetDateTime modified) {
    this.modified = modified;
  }

}

