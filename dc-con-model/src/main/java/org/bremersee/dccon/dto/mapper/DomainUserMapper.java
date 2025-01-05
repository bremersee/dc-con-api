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

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Stream;
import org.bremersee.dccon.dto.DomainUser;
import org.bremersee.dccon.dto.GenericDomainUser;
import org.bremersee.dccon.dto.MainDomainUser;
import org.bremersee.dccon.dto.MemberOf;
import org.bremersee.dccon.dto.PlainDomainUser;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * The interface DomainUserMapper.
 *
 * @author Christian Bremer
 */
@Mapper(uses = {MemberOfMapper.class})
public interface DomainUserMapper {

  DomainUserMapper INSTANCE = Mappers.getMapper(DomainUserMapper.class);

  PlainDomainUser mapGenericToPlain(GenericDomainUser<?> source);

  MainDomainUser mapCompleteToMain(DomainUser source);

  DomainUser mapMainToComplete(MainDomainUser source);

  default DomainUser mapMainToComplete(
      MainDomainUser source,
      Function<String, MemberOf> dnToMemberOfFn) {

    DomainUser destination = mapMainToComplete(source);
    if (isNull(source) || isNull(destination) || isNull(dnToMemberOfFn)) {
      return destination;
    }
    destination.setMembership(Stream.ofNullable(source.getMembership())
        .flatMap(Collection::stream)
        .map(dnToMemberOfFn)
        .sorted()
        .toList());
    return destination;
  }

  void mapPlainToToGeneric(
      PlainDomainUser source,
      @SuppressWarnings("rawtypes") @MappingTarget GenericDomainUser destination);

  default <O extends Serializable> void mapMainToGeneric(
      MainDomainUser source,
      GenericDomainUser<O> destination,
      Comparator<O> memberOfComparator,
      Function<String, O> dnToMemberOfFn) {

    if (isNull(source) || isNull(destination)) {
      return;
    }
    if (isNull(dnToMemberOfFn)) {
      throw new IllegalArgumentException("DN to member-of function is required.");
    }
    mapPlainToToGeneric(mapGenericToPlain(source), destination);
    if (isNull(memberOfComparator)) {
      destination.setMembership(Stream.ofNullable(source.getMembership())
          .flatMap(Collection::stream)
          .map(dnToMemberOfFn)
          .toList());
    } else {
      destination.setMembership(Stream.ofNullable(source.getMembership())
          .flatMap(Collection::stream)
          .map(dnToMemberOfFn)
          .sorted(memberOfComparator)
          .toList());
    }
  }

}
