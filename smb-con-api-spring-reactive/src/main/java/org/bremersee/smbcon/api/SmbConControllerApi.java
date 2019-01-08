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

package org.bremersee.smbcon.api;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.bremersee.smbcon.model.BooleanWrapper;
import org.bremersee.smbcon.model.DnsEntry;
import org.bremersee.smbcon.model.DnsRecordRequest;
import org.bremersee.smbcon.model.DnsRecordUpdateRequest;
import org.bremersee.smbcon.model.DnsZone;
import org.bremersee.smbcon.model.DnsZoneCreateRequest;
import org.bremersee.smbcon.model.Info;
import org.bremersee.smbcon.model.Names;
import org.bremersee.smbcon.model.Password;
import org.bremersee.smbcon.model.SambaGroup;
import org.bremersee.smbcon.model.SambaGroupItem;
import org.bremersee.smbcon.model.SambaUser;
import org.bremersee.smbcon.model.SambaUserAddRequest;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Reactive samba connector interface.
 *
 * @author Christian Bremer
 */
@Validated
@SuppressWarnings("unused")
public interface SmbConControllerApi {

  /**
   * Gets samba server info.
   *
   * @return the info
   */
  Mono<Info> getInfo();


  /**
   * Create dns zone.
   *
   * @param request the request
   * @return void
   */
  Mono<Void> createDnsZone(@Valid DnsZoneCreateRequest request);

  /**
   * Create or delete dns record.
   *
   * @param action  the action, must be {@code CREATE} or {@code DELETE}
   * @param request the request
   * @return void
   */
  Mono<Void> createOrDeleteDnsRecord(@NotNull String action, @Valid DnsRecordRequest request);

  /**
   * Delete dns zone.
   *
   * @param zoneName the zone name
   * @return void
   */
  Mono<Void> deleteDnsZone(@NotNull String zoneName);

  /**
   * Gets dns records.
   *
   * @param zoneName the zone name
   * @return the dns records
   */
  Flux<DnsEntry> getDnsRecords(@NotNull String zoneName);

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
   * @return void
   */
  Mono<Void> updateDnsRecord(@Valid DnsRecordUpdateRequest request);


  /**
   * Add group.
   *
   * @param group the group
   * @return the added group
   */
  Mono<SambaGroup> addGroup(@Valid SambaGroup group);

  /**
   * Delete group.
   *
   * @param groupName the group name
   * @return void
   */
  Mono<Void> deleteGroup(@NotNull String groupName);

  /**
   * Gets group by name.
   *
   * @param groupName the group name
   * @return the group by name
   */
  Mono<SambaGroup> getGroupByName(@NotNull String groupName);

  /**
   * Gets groups.
   *
   * @return the groups
   */
  Flux<SambaGroupItem> getGroups();

  /**
   * Update group members.
   *
   * @param groupName the group name
   * @param members   the members
   * @return the updated group
   */
  Mono<SambaGroup> updateGroupMembers(@NotNull String groupName, @Valid Names members);


  /**
   * Add user.
   *
   * @param sambaUser the samba user
   * @return the added user
   */
  Mono<SambaUser> addUser(@Valid SambaUserAddRequest sambaUser);

  /**
   * Delete user.
   *
   * @param userName the user name
   * @return void
   */
  Mono<Void> deleteUser(@NotNull String userName);

  /**
   * Gets user.
   *
   * @param userName the user name
   * @return the user
   */
  Mono<SambaUser> getUser(@NotNull String userName);

  /**
   * Update user.
   *
   * @param userName  the user name
   * @param sambaUser the samba user
   * @return the updated user
   */
  Mono<SambaUser> updateUser(@NotNull String userName, @Valid SambaUser sambaUser);

  /**
   * Update user groups.
   *
   * @param userName the user name
   * @param groups   the groups
   * @return the updated user
   */
  Mono<SambaUser> updateUserGroups(@NotNull String userName, @Valid Names groups);

  /**
   * Update user password.
   *
   * @param userName    the user name
   * @param newPassword the new password
   * @return void
   */
  Mono<Void> updateUserPassword(@NotNull String userName, @Valid Password newPassword);

  /**
   * Determines whether the user exists or not.
   *
   * @param userName the user name
   * @return wrapped {@code true}, if the user exists, otherwise wrapped {@code false}
   */
  Mono<BooleanWrapper> userExists(@NotNull String userName);

}
