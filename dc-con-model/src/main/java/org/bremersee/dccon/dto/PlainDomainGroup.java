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

package org.bremersee.dccon.dto;

import static java.util.Objects.requireNonNullElse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import io.swagger.v3.oas.annotations.media.DiscriminatorMapping;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import java.io.Serial;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * A domain (Active Directory) group may contain user and computer accounts as well as other
 * groups.
 *
 * <p>Groups may also be used to establish email distribution lists, using group type
 * {@link DomainGroupType#DISTRIBUTION}.
 *
 * <p>This plain representation has no members attribute and no membership attribute.
 *
 * @author Christian Bremer
 */
@Schema(
    description = "Plain domain group.", discriminatorProperty = "_type",
    discriminatorMapping = {
        @DiscriminatorMapping(
            value = "org.bremersee.dccon.model.PlainDomainGroup",
            schema = PlainDomainGroup.class),
        @DiscriminatorMapping(
            value = "org.bremersee.dccon.model.GenericDomainGroup",
            schema = GenericDomainGroup.class)
    }
)
@JsonTypeInfo(use = Id.CLASS, property = "_type", visible = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class PlainDomainGroup extends CommonAttributes {

  @Serial
  private static final long serialVersionUID = 2L;

  /**
   * The constant DESCRIPTION.
   */
  public static final String DESCRIPTION = "description";

  /**
   * A description of the domain group.
   */
  @Schema(description = "A description of the domain group.")
  @JsonProperty(DESCRIPTION)
  String description;

  /**
   * The constant EMAIL.
   */
  public static final String EMAIL = "email";

  /**
   * The email address of the domain user.
   */
  @Schema(description = "The email address of the domain user.")
  @JsonProperty(EMAIL)
  String email;

  /**
   * The constant GID_NUMBER.
   */
  public static final String GID_NUMBER = "gidNumber";

  /**
   * Group's Unix/RFC2307 GID number.
   */
  @Schema(description = "Group's Unix/RFC2307 GID number.")
  @JsonProperty(GID_NUMBER)
  Integer gidNumber;

  /**
   * The constant GROUP_TYPE.
   */
  public static final String GROUP_TYPE = "groupType";

  /**
   * The type of the domain group.
   */
  @Schema(description = "The type of the domain group.")
  @JsonProperty(value = GROUP_TYPE, defaultValue = "security")
  DomainGroupType groupType;

  /**
   * The constant NAME.
   */
  public static final String NAME = "name";

  /**
   * The group's name.
   */
  @Schema(description = "The group's name.", requiredMode = RequiredMode.REQUIRED)
  @JsonProperty(value = NAME, required = true)
  String name;

  /**
   * The constant NIS_DOMAIN.
   */
  public static final String NIS_DOMAIN = "nisDomain";

  /**
   * Group's Unix/RFC2307 NIS domain.
   */
  @Schema(description = "Group's Unix/RFC2307 NIS domain.")
  @JsonProperty(NIS_DOMAIN)
  String nisDomain;

  /**
   * The constant SID.
   */
  public static final String SID = "sid";

  /**
   * Group's windows/samba SID.
   */
  @Schema(description = "Group's windows/samba SID.", accessMode = AccessMode.READ_ONLY)
  @JsonProperty(SID)
  Sid sid;

  /**
   * Returns the type of the domain group.
   *
   * @return the domain group type.
   */
  public DomainGroupType getGroupType() {
    return requireNonNullElse(groupType, DomainGroupType.SECURITY);
  }
}

