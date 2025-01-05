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
import static java.util.Objects.nonNull;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Stream;
import org.bremersee.dccon.dto.DomainGroup;
import org.bremersee.dccon.dto.DomainGroupMember;
import org.bremersee.dccon.dto.GenericDomainGroup;
import org.bremersee.dccon.dto.MainDomainGroup;
import org.bremersee.dccon.dto.MemberOf;
import org.bremersee.dccon.dto.PlainDomainGroup;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * The interface DomainGroup.
 *
 * @author Christian Bremer
 */
@Mapper(uses = {MemberOfMapper.class, DomainGroupMemberMapper.class})
public interface DomainGroupMapper {

  DomainGroupMapper INSTANCE = Mappers.getMapper(DomainGroupMapper.class);

  PlainDomainGroup mapGenericToPlain(GenericDomainGroup<?, ?> source);

  MainDomainGroup mapCompleteToMain(DomainGroup source);

  DomainGroup mapMainToComplete(MainDomainGroup source);

  default DomainGroup mapMainToComplete(
      MainDomainGroup source,
      Function<String, DomainGroupMember> dnToGroupMemberFn) {
    return mapMainToComplete(source, null, dnToGroupMemberFn);
  }

  default DomainGroup mapMainToComplete(
      MainDomainGroup source,
      Function<String, MemberOf> dnToMemberOfFn,
      Function<String, DomainGroupMember> dnToGroupMemberFn) {

    DomainGroup destination = mapMainToComplete(source);
    if (isNull(source) || isNull(destination)) {
      return destination;
    }
    if (nonNull(dnToMemberOfFn)) {
      destination.setMembership(Stream.ofNullable(source.getMembership())
          .flatMap(Collection::stream)
          .map(dnToMemberOfFn)
          .sorted()
          .toList());
    }
    if (nonNull(dnToGroupMemberFn)) {
      destination.setMembers(Stream.ofNullable(source.getMembership())
          .flatMap(Collection::stream)
          .map(dnToGroupMemberFn)
          .sorted()
          .toList());
    }
    return destination;
  }

  void mapPlainToToGeneric(
      PlainDomainGroup source,
      @SuppressWarnings("rawtypes") @MappingTarget GenericDomainGroup destination);

  default <M extends Serializable, O extends Serializable> void mapMainToGeneric(
      MainDomainGroup source,
      GenericDomainGroup<M, O> destination,
      Comparator<O> memberOfComparator,
      Function<String, O> dnToMemberOfFn,
      Comparator<M> groupMemberComparator,
      Function<String, M> dnToGroupMemberFn) {

    if (isNull(source) || isNull(destination)) {
      return;
    }
    if (isNull(dnToGroupMemberFn)) {
      throw new IllegalArgumentException("DN to group member function is required.");
    }
    if (isNull(dnToMemberOfFn)) {
      throw new IllegalArgumentException("DN to member-of function is required.");
    }
    mapPlainToToGeneric(mapGenericToPlain(source), destination);
    if (isNull(groupMemberComparator)) {
      destination.setMembers(Stream.ofNullable(source.getMembership())
          .flatMap(Collection::stream)
          .map(dnToGroupMemberFn)
          .toList());
    } else {
      destination.setMembers(Stream.ofNullable(source.getMembership())
          .flatMap(Collection::stream)
          .map(dnToGroupMemberFn)
          .sorted(groupMemberComparator)
          .toList());
    }
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
