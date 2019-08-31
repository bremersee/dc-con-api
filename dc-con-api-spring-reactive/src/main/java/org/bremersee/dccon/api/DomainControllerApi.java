/*
 * Copyright 2018 the original author or authors.
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

package org.bremersee.dccon.api;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.bremersee.dccon.model.DhcpLease;
import org.bremersee.dccon.model.DnsEntry;
import org.bremersee.dccon.model.DnsRecordRequest;
import org.bremersee.dccon.model.DnsRecordUpdateRequest;
import org.bremersee.dccon.model.DnsZone;
import org.bremersee.dccon.model.DnsZoneCreateRequest;
import org.bremersee.dccon.model.DomainGroup;
import org.bremersee.dccon.model.DomainGroupItem;
import org.bremersee.dccon.model.DomainUser;
import org.bremersee.dccon.model.Password;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Reactive domain connector interface.
 *
 * @author Christian Bremer
 */
@Validated
@SuppressWarnings("unused")
public interface DomainControllerApi {

  /**
   * Create dns zone.
   *
   * @param request the request
   * @return void mono
   */
  Mono<Void> createDnsZone(@Valid DnsZoneCreateRequest request);

  /**
   * Create or delete dns record.
   *
   * @param action  the action, can be {@code CREATE} or {@code DELETE}, default is {@code CREATE}
   * @param reverse also handle reverse record, default is {@code true}
   * @param request the request
   * @return void mono
   */
  Mono<Void> createOrDeleteDnsRecord(
      @Nullable String action,
      @Nullable Boolean reverse,
      @NotNull @Valid DnsRecordRequest request);

  /**
   * Delete dns zone.
   *
   * @param zoneName the zone name
   * @return void mono
   */
  Mono<Void> deleteDnsZone(@NotNull String zoneName);

  /**
   * Gets dns records.
   *
   * @param zoneName     the zone name
   * @param correlations specifies whether correlated records should be added to the response
   *                     (default is {@code true})
   * @param leases       the enum value for adding dhcp leases (NONE|ACTIVE|ALL)
   * @return the dns records
   */
  Flux<DnsEntry> getDnsRecords(
      @NotNull String zoneName,
      @Nullable Boolean correlations,
      @Nullable String leases);

  /**
   * Gets dhcp leases.
   *
   * @param all  if {@code true}, expired leases will also be returned, otherwise only active ones
   *             (default is {@code false})
   * @param sort the sort order (default is {@link DhcpLease#SORT_ORDER_BEGIN_HOSTNAME})
   * @return the dhcp leases
   */
  Flux<DhcpLease> getDhcpLeases(Boolean all, String sort);

  /**
   * Gets dns zones.
   *
   * @return the dns zones
   */
  Flux<DnsZone> getDnsZones();

  /**
   * Update dns record.
   *
   * @param request the request
   * @return void mono
   */
  Mono<Void> updateDnsRecord(@Valid DnsRecordUpdateRequest request);


  /**
   * Add group.
   *
   * @param group the group
   * @return the added group
   */
  Mono<DomainGroup> addGroup(@Valid DomainGroup group);

  /**
   * Delete group.
   *
   * @param groupName the group name
   * @return void mono
   */
  Mono<Void> deleteGroup(@NotNull String groupName);

  /**
   * Gets group by name.
   *
   * @param groupName the group name
   * @return the group by name
   */
  Mono<DomainGroup> getGroupByName(@NotNull String groupName);

  /**
   * Gets groups.
   *
   * @return the groups
   */
  Flux<DomainGroupItem> getGroups();

  /**
   * Update group members.
   *
   * @param groupName the group name
   * @param members   the members
   * @return the updated group
   */
  Mono<DomainGroup> updateGroupMembers(@NotNull String groupName, @Valid List<String> members);


  /**
   * Add user.
   *
   * @param domainUser the domain user
   * @return the added user
   */
  Mono<DomainUser> addUser(@Valid DomainUser domainUser);

  /**
   * Delete user.
   *
   * @param userName the user name
   * @return void mono
   */
  Mono<Void> deleteUser(@NotNull String userName);

  /**
   * Gets user.
   *
   * @param userName the user name
   * @return the user
   */
  Mono<DomainUser> getUser(@NotNull String userName);

  /**
   * Update user.
   *
   * @param userName     the user name
   * @param updateGroups the update groups flag (default is false)
   * @param domainUser   the domain user
   * @return the updated user
   */
  Mono<DomainUser> updateUser(
      @NotNull String userName,
      @Nullable Boolean updateGroups,
      @Valid DomainUser domainUser);

  /**
   * Update user groups.
   *
   * @param userName the user name
   * @param groups   the groups
   * @return the updated user
   */
  Mono<DomainUser> updateUserGroups(@NotNull String userName, @Valid List<String> groups);

  /**
   * Update user password.
   *
   * @param userName    the user name
   * @param newPassword the new password
   * @return void mono
   */
  Mono<Void> updateUserPassword(@NotNull String userName, @Valid Password newPassword);

  /**
   * Determines whether the user exists or not.
   *
   * @param userName the user name
   * @return wrapped {@code true}, if the user exists, otherwise wrapped {@code false}
   */
  Mono<Boolean> userExists(@NotNull String userName);

}
