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

import static java.util.Objects.nonNull;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import lombok.Getter;

/**
 * The type DomainGroupMemberSelectOptions.
 *
 * @author Christian Bremer
 */
@Getter
public class DomainGroupMemberSelectOptions implements Serializable {

  private final List<GenericSelectOption<DomainGroupMember>> selectOptions;

  private final boolean unknownPresent;

  private final boolean userPresent;

  private final boolean groupPresent;

  private final boolean computerPresent;

  public DomainGroupMemberSelectOptions(
      List<GenericSelectOption<DomainGroupMember>> selectOptions) {

    this.selectOptions = nonNull(selectOptions)
        ? Collections.unmodifiableList(selectOptions)
        : List.of();
    unknownPresent = this.selectOptions.stream()
        .filter(m -> nonNull(m.getDisplayValue()))
        .anyMatch(m -> DomainGroupMemberType.UNKNOWN
            .equals(m.getDisplayValue().getObjectClass()));
    userPresent = this.selectOptions.stream()
        .filter(m -> nonNull(m.getDisplayValue()))
        .anyMatch(m -> DomainGroupMemberType.USER
            .equals(m.getDisplayValue().getObjectClass()));
    groupPresent = this.selectOptions.stream()
        .filter(m -> nonNull(m.getDisplayValue()))
        .anyMatch(m -> DomainGroupMemberType.GROUP
            .equals(m.getDisplayValue().getObjectClass()));
    computerPresent = this.selectOptions.stream()
        .filter(m -> nonNull(m.getDisplayValue()))
        .anyMatch(m -> DomainGroupMemberType.COMPUTER
            .equals(m.getDisplayValue().getObjectClass()));
  }
}
