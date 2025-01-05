/*
 * Copyright 2024 the original author or authors.
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
 * A domain (Active Directory) user may represent physical entities, such as people or may be used
 * as service accounts for applications. User accounts are also referred to as security principals
 * and are assigned a security identifier (SID).
 *
 * <p>A user account enables a user to logon to a computer and domain with an identity that can be
 * authenticated. To maximize security, each user should have their own unique user account and
 * password. A user's access to domain resources is based on permissions assigned to the user
 * account.
 *
 * <p>This generic representation has a generic membership attribute.
 *
 * @param <O> the type parameter of membership
 * @author Christian Bremer
 */
@Schema(description = "Generic domain user.",
    discriminatorProperty = "_type",
    discriminatorMapping = {
        @DiscriminatorMapping(
            value = "org.bremersee.dccon.model.GenericDomainUser",
            schema = GenericDomainUser.class),
        @DiscriminatorMapping(
            value = "org.bremersee.dccon.model.MainDomainUser",
            schema = MainDomainUser.class),
        @DiscriminatorMapping(
            value = "org.bremersee.dccon.model.DomainUser",
            schema = DomainUser.class)
    })
@JsonTypeInfo(use = Id.CLASS, property = "_type", visible = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class GenericDomainUser<O extends Serializable> extends PlainDomainUser {


  @Serial
  private static final long serialVersionUID = 2L;

  /**
   * The constant MEMBERSHIP.
   */
  public static final String MEMBERSHIP = "membership";

  /**
   * User's group membership.
   */
  @Schema(description = "User's group membership.", accessMode = AccessMode.READ_ONLY)
  @JsonProperty(MEMBERSHIP)
  List<O> membership;

  /**
   * User's group membership.
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
