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

package org.bremersee.dccon.dto.mapper;

import static java.util.Objects.isNull;

import org.bremersee.dccon.dto.DomainGroupMember;
import org.mapstruct.Mapper;

/**
 * The interface DomainGroupMemberMapper.
 *
 * @author Christian Bremer
 */
@Mapper
public interface DomainGroupMemberMapper {

  default String mapDomainGroupMemberToDn(DomainGroupMember groupMember) {
    if (isNull(groupMember)) {
      return null;
    }
    return groupMember.getDistinguishedName();
  }

  default DomainGroupMember mapDnToDomainGroupMember(String distinguishedName) {
    if (isNull(distinguishedName)) {
      return null;
    }
    return DomainGroupMember.builder()
        .distinguishedName(distinguishedName)
        .build();
  }

}
