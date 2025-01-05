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
import io.swagger.v3.oas.annotations.media.Schema;
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
 * <p>This main representation has a members attribute and a membership attribute with the
 * distinguished names of the referenced entities.
 *
 * @author Christian Bremer
 */
@Schema(description = "Main domain group.")
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class MainDomainGroup extends GenericDomainGroup<String, String> {

  @Serial
  private static final long serialVersionUID = 2L;

}
