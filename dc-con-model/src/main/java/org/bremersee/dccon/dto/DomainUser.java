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
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
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
 * <p>This full representation has a membership attribute with detailed information of the groups
 * (group name and distinguished name, see {@link MemberOf}).
 *
 * @author Christian Bremer
 */
@Schema(description = "Domain user.")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class DomainUser extends GenericDomainUser<MemberOf> {

  @Serial
  private static final long serialVersionUID = 2L;

}
