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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import io.swagger.v3.oas.annotations.media.DiscriminatorMapping;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
 * <p>This generic representation has a generic members attribute and a generic membership
 * attribute.
 *
 * @param <M> the type parameter of members
 * @param <O> the type parameter of membership
 * @author Christian Bremer
 */
@Schema(description = "Generic domain group.",
    discriminatorProperty = "_type",
    discriminatorMapping = {
        @DiscriminatorMapping(
            value = "org.bremersee.dccon.model.GenericDomainGroup",
            schema = GenericDomainGroup.class),
        @DiscriminatorMapping(
            value = "org.bremersee.dccon.model.MainDomainGroup",
            schema = MainDomainGroup.class),
        @DiscriminatorMapping(
            value = "org.bremersee.dccon.model.DomainGroup",
            schema = DomainGroup.class)
    })
@JsonTypeInfo(use = Id.CLASS, property = "_type", visible = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class GenericDomainGroup<M extends Serializable, O extends Serializable>
    extends PlainDomainGroup {

  @Serial
  private static final long serialVersionUID = 2L;

  /**
   * The constant MEMBERS.
   */
  public static final String MEMBERS = "members";

  /**
   * The members of the domain group.
   */
  @Schema(description = "The members of the domain group.")
  @JsonProperty(MEMBERS)
  List<M> members;

  /**
   * The constant MEMBERSHIP.
   */
  public static final String MEMBERSHIP = "membership";

  /**
   * The group's group membership.
   */
  @Schema(description = "The group's group membership.", accessMode = AccessMode.READ_ONLY)
  @JsonProperty(MEMBERSHIP)
  List<O> membership;

  /**
   * The members of the domain group.
   *
   * @return the members
   */
  public List<M> getMembers() {
    if (members == null) {
      members = new ArrayList<>();
    }
    return members;
  }

  /**
   * The group's group membership.
   *
   * @return the group membership
   */
  public List<O> getMembership() {
    if (membership == null) {
      membership = new ArrayList<>();
    }
    return membership;
  }

}

