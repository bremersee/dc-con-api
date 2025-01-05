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

import java.util.Objects;
import org.bremersee.dccon.dto.MemberOf;
import org.mapstruct.Mapper;

/**
 * The interface MemberOfMapper.
 *
 * @author Christian Bremer
 */
@Mapper
public interface MemberOfMapper {

  default String mapMemberOfToDn(MemberOf memberOf) {
    if (Objects.isNull(memberOf)) {
      return null;
    }
    return memberOf.getDistinguishedName();
  }

  default MemberOf mapDnToMemberOf(String distinguishedName) {
    if (Objects.isNull(distinguishedName)) {
      return null;
    }
    MemberOf.MemberOfBuilder builder = MemberOf.builder()
        .distinguishedName(distinguishedName);
    int start = distinguishedName.indexOf('=');
    if (start == -1) {
      return builder
          .name(distinguishedName)
          .build();
    }
    int end = distinguishedName.indexOf(',', start);
    if (end == -1) {
      return builder
          .name(distinguishedName.substring(start + 1))
          .build();
    }
    return builder
        .name(distinguishedName.substring(start + 1, end))
        .build();
  }

}
