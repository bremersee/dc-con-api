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
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Domain group.
 *
 * @author Christian Bremer
 */
@Schema(description = "Domain group.")
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class DomainGroup extends CommonAttributes implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  /**
   * The constant NAME.
   */
  public static final String NAME = "name";

  /**
   * The name of the domain group.
   */
  @Schema(description = "The name of the domain group.", requiredMode = RequiredMode.REQUIRED)
  @JsonProperty(value = NAME, required = true)
  @NotNull
  private String name = null;

  /**
   * The constant DESCRIPTION.
   */
  public static final String DESCRIPTION = "description";

  /**
   * Gets description.
   */
  @Schema(description = "A description of the domain group.")
  @JsonProperty(DESCRIPTION)
  private String description;

  /**
   * The constant SID.
   */
  public static final String SID = "sid";

  /**
   * Gets sid.
   */
  @Schema(description = "The windows/samba SID.", accessMode = AccessMode.READ_ONLY)
  @JsonProperty(SID)
  private Sid sid;

  /**
   * The constant MEMBERS.
   */
  public static final String MEMBERS = "members";

  /**
   * The members of the domain group.
   */
  @Schema(description = "The members of the domain group.")
  @JsonProperty(MEMBERS)
  private List<String> members = null;

  /**
   * Instantiates a new Domain group.
   *
   * @param distinguishedName the distinguished name
   * @param created the created
   * @param modified the modified
   * @param sid the windows/samba SID
   * @param name the name
   * @param description the description
   * @param members the members
   */
  @Builder(toBuilder = true)
  public DomainGroup(
      String distinguishedName,
      OffsetDateTime created,
      OffsetDateTime modified,
      Sid sid,
      String name,
      String description,
      List<String> members) {

    super(distinguishedName, created, modified);
    setSid(sid);
    setName(name);
    setDescription(description);
    setMembers(members);
  }

  /**
   * The members of the domain group.
   *
   * @return the members
   */
  public List<String> getMembers() {
    if (members == null) {
      members = new ArrayList<>();
    }
    return members;
  }

}

