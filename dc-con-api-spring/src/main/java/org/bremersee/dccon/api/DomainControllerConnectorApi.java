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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
import org.bremersee.dccon.model.Info;
import org.bremersee.dccon.model.Password;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The domain controller connector api.
 */
@Api(value = "DomainControllerConnector")
public interface DomainControllerConnectorApi {

  /**
   * Add group.
   *
   * @param group the group
   * @return the group
   */
  @ApiOperation(
      value = "Add a domain group.",
      nickname = "addGroup",
      response = DomainGroup.class,
      tags = {"domain-controller-connector"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "The added domain group.", response = DomainGroup.class),
      @ApiResponse(code = 400, message = "Bad request.",
          response = org.bremersee.exception.model.RestApiException.class),
      @ApiResponse(code = 406, message = "Already exists",
          response = org.bremersee.exception.model.RestApiException.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = org.bremersee.exception.model.RestApiException.class)
  })
  @RequestMapping(
      value = "/api/groups",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  ResponseEntity<DomainGroup> addGroup(
      @ApiParam(value = "The domain group to add.") @Valid @RequestBody DomainGroup group);


  /**
   * Add user.
   *
   * @param domainUser the domain user
   * @return the user
   */
  @ApiOperation(
      value = "Add a domain user.",
      nickname = "addUser",
      response = DomainUser.class,
      tags = {"domain-controller-connector"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "The added domain user.", response = DomainUser.class),
      @ApiResponse(code = 400, message = "Bad request.",
          response = org.bremersee.exception.model.RestApiException.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = org.bremersee.exception.model.RestApiException.class)
  })
  @RequestMapping(
      value = "/api/users",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  ResponseEntity<DomainUser> addUser(
      @ApiParam(value = "The domain user to add.",
          required = true) @Valid @RequestBody DomainUser domainUser);


  /**
   * Create dns zone.
   *
   * @param request the request
   * @return void response entity
   */
  @ApiOperation(
      value = "Create new name server zone.",
      nickname = "createDnsZone",
      notes = "Create new name server zone.",
      tags = {"domain-controller-connector"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "The zone was successfully created."),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = org.bremersee.exception.model.RestApiException.class)
  })
  @RequestMapping(
      value = "/api/dns/zones",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  ResponseEntity<Void> createDnsZone(
      @ApiParam(
          value = "The dns zone create request") @Valid @RequestBody DnsZoneCreateRequest request);


  /**
   * Create or delete dns record.
   *
   * @param action  the action
   * @param request the request
   * @return void response entity
   */
  @ApiOperation(
      value = "Create or delete name server record.",
      nickname = "createOrDeleteDnsRecord",
      notes = "Create or delete name server record.",
      tags = {"domain-controller-connector"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "The name server record was successfully created."),
      @ApiResponse(code = 400, message = "Bad request.",
          response = org.bremersee.exception.model.RestApiException.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = org.bremersee.exception.model.RestApiException.class)
  })
  @RequestMapping(
      value = "/api/dns/zones/records",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  ResponseEntity<Void> createOrDeleteDnsRecord(
      @ApiParam(value = "The action.", allowableValues = "CREATE, DELETE", defaultValue = "CREATE")
      @RequestParam(value = "action", defaultValue = "CREATE") String action,
      @ApiParam(value = "Also handle reverse record.", defaultValue = "true")
      @RequestParam(value = "reverse", defaultValue = "true") Boolean reverse,
      @ApiParam(value = "The dns record request.") @Valid @RequestBody DnsRecordRequest request);


  /**
   * Delete dns zone.
   *
   * @param zoneName the zone name
   * @return void response entity
   */
  @ApiOperation(
      value = "Delete names server zone.",
      nickname = "deleteDnsZone",
      notes = "Delete name server zone.",
      tags = {"domain-controller-connector"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "The zone was successfully created."),
      @ApiResponse(code = 400, message = "Bad request.",
          response = org.bremersee.exception.model.RestApiException.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = org.bremersee.exception.model.RestApiException.class)
  })
  @RequestMapping(
      value = "/api/dns/zones",
      produces = {"application/json"},
      method = RequestMethod.DELETE)
  ResponseEntity<Void> deleteDnsZone(
      @NotNull @ApiParam(value = "The zone name.", required = true) @Valid @RequestParam(
          value = "zoneName") String zoneName);


  /**
   * Delete group.
   *
   * @param groupName the group name
   * @return void response entity
   */
  @ApiOperation(
      value = "Delete a domain group.",
      nickname = "deleteGroup",
      tags = {"domain-controller-connector"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Group does not exist or was successfully deleted."),
      @ApiResponse(code = 400, message = "Bad request.",
          response = org.bremersee.exception.model.RestApiException.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = org.bremersee.exception.model.RestApiException.class)
  })
  @RequestMapping(
      value = "/api/groups/{groupName}",
      produces = {"application/json"},
      method = RequestMethod.DELETE)
  ResponseEntity<Void> deleteGroup(
      @ApiParam(value = "The domain group name.",
          required = true) @PathVariable("groupName") String groupName);


  /**
   * Delete user.
   *
   * @param userName the user name
   * @return void response entity
   */
  @ApiOperation(
      value = "Deletes a domain user.",
      nickname = "deleteUser",
      tags = {"domain-controller-connector"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "User does not exist or was successfully deleted."),
      @ApiResponse(code = 400, message = "Bad request.",
          response = org.bremersee.exception.model.RestApiException.class),
      @ApiResponse(code = 404, message = "User not found.",
          response = org.bremersee.exception.model.RestApiException.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = org.bremersee.exception.model.RestApiException.class)
  })
  @RequestMapping(value = "/api/users/{userName}",
      produces = {"application/json"},
      method = RequestMethod.DELETE)
  ResponseEntity<Void> deleteUser(
      @ApiParam(value = "The user name of the domain user.",
          required = true) @PathVariable("userName") String userName);

  /**
   * Gets dhcp leases.
   *
   * @param all  if {@code true}, expired leases will also be returned, otherwise only active ones
   * @param sort the sort order
   * @return the dhcp leases
   */
  @ApiOperation(
      value = "Get dhcp leases.",
      nickname = "getDhcpLeases", notes = "Get dhcp leases.",
      response = DhcpLease.class,
      responseContainer = "List",
      tags = {"domain-controller-connector"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "A list with dhcp leases.",
          response = DhcpLease.class, responseContainer = "List"),
      @ApiResponse(code = 400, message = "Bad request.",
          response = org.bremersee.exception.model.RestApiException.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = org.bremersee.exception.model.RestApiException.class)})
  @RequestMapping(value = "/api/dhcp-leases",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<List<DhcpLease>> getDhcpLeases(
      @ApiParam(value = "'true' returns also expired leases, 'false' only active ones.",
          defaultValue = "false")
      @RequestParam(value = "all", defaultValue = "false") Boolean all,
      @ApiParam(value = "The sort order.", defaultValue = DhcpLease.SORT_ORDER_BEGIN_HOSTNAME)
      @RequestParam(value = "sort", defaultValue = DhcpLease.SORT_ORDER_BEGIN_HOSTNAME) String sort);


  /**
   * Gets dns records.
   *
   * @param zoneName     the zone name
   * @param addDhcpLease the add dhcp lease
   * @return the dns records
   */
  @ApiOperation(
      value = "Get all name server records of a zone.",
      nickname = "getDnsRecords", notes = "Get all name server records of a zone.",
      response = DnsEntry.class,
      responseContainer = "List",
      tags = {"domain-controller-connector"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "A list with name server records.",
          response = DnsEntry.class, responseContainer = "List"),
      @ApiResponse(code = 400, message = "Bad request.",
          response = org.bremersee.exception.model.RestApiException.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = org.bremersee.exception.model.RestApiException.class)})
  @RequestMapping(value = "/api/dns/zones/records",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<List<DnsEntry>> getDnsRecords(
      @ApiParam(value = "The name server zone name.", required = true)
      @RequestParam(value = "zoneName") String zoneName,
      @ApiParam(value = "Specifies which dhcp lease information should be added (NONE|ACTIVE|ALL).",
          defaultValue = "ACTIVE")
      @RequestParam(value = "addDhcpLease", defaultValue = "ACTIVE") String addDhcpLease);


  /**
   * Gets dns zones.
   *
   * @return the dns zones
   */
  @ApiOperation(
      value = "Get all name server zones.",
      nickname = "getDnsZones",
      notes = "Get all name server zones.",
      response = DnsZone.class,
      responseContainer = "List",
      tags = {"domain-controller-connector"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "A list with name server zones.",
          response = DnsZone.class, responseContainer = "List"),
      @ApiResponse(code = 400, message = "Bad request.",
          response = org.bremersee.exception.model.RestApiException.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = org.bremersee.exception.model.RestApiException.class)
  })
  @RequestMapping(
      value = "/api/dns/zones",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<List<DnsZone>> getDnsZones();


  /**
   * Gets group by name.
   *
   * @param groupName the group name
   * @return the group by name
   */
  @ApiOperation(
      value = "Get a domain group by name.",
      nickname = "getGroupByName",
      response = DomainGroup.class,
      tags = {"domain-controller-connector"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "The domain group with the specified name.",
          response = DomainGroup.class),
      @ApiResponse(code = 400, message = "Bad request.",
          response = org.bremersee.exception.model.RestApiException.class),
      @ApiResponse(code = 404, message = "Not found",
          response = org.bremersee.exception.model.RestApiException.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = org.bremersee.exception.model.RestApiException.class)
  })
  @RequestMapping(
      value = "/api/groups/{groupName}",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<DomainGroup> getGroupByName(
      @ApiParam(value = "The domain group name.",
          required = true) @PathVariable("groupName") String groupName);


  /**
   * Gets groups.
   *
   * @return the groups
   */
  @ApiOperation(
      value = "Get all domain groups.",
      nickname = "getGroups",
      notes = "Get all domain groups.",
      response = DomainGroupItem.class,
      responseContainer = "List",
      tags = {"domain-controller-connector"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "A list with domain group items.",
          response = DomainGroupItem.class, responseContainer = "List"),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = org.bremersee.exception.model.RestApiException.class)
  })
  @RequestMapping(
      value = "/api/groups",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<List<DomainGroupItem>> getGroups();


  /**
   * Gets info.
   *
   * @return the info
   */
  @ApiOperation(
      value = "Get domain controller server info.",
      nickname = "getInfo",
      notes = "Get domain controller server info.",
      response = Info.class,
      tags = {"domain-controller-connector"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "The domain controller server info.",
          response = Info.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = org.bremersee.exception.model.RestApiException.class)
  })
  @RequestMapping(
      value = "/api/info",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<Info> getInfo();


  /**
   * Gets user.
   *
   * @param userName the user name
   * @return the user
   */
  @ApiOperation(
      value = "Get a domain user by name.",
      nickname = "getUser",
      response = DomainUser.class,
      tags = {"domain-controller-connector"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "The domain user with the specified name.",
          response = DomainUser.class),
      @ApiResponse(code = 400, message = "Bad request.",
          response = org.bremersee.exception.model.RestApiException.class),
      @ApiResponse(code = 404, message = "User not found.",
          response = org.bremersee.exception.model.RestApiException.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = org.bremersee.exception.model.RestApiException.class)
  })
  @RequestMapping(
      value = "/api/users/{userName}",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<DomainUser> getUser(
      @ApiParam(value = "The user name of the domain user.",
          required = true) @PathVariable("userName") String userName);


  /**
   * Update dns record.
   *
   * @param request the request
   * @return void response entity
   */
  @ApiOperation(
      value = "Update name server record.",
      nickname = "updateDnsRecord",
      notes = "Update name server record.",
      tags = {"domain-controller-connector"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "The name server record was successfully created."),
      @ApiResponse(code = 400, message = "Bad request.",
          response = org.bremersee.exception.model.RestApiException.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = org.bremersee.exception.model.RestApiException.class)
  })
  @RequestMapping(
      value = "/api/dns/zones/records",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.PUT)
  ResponseEntity<Void> updateDnsRecord(
      @ApiParam(value = "The dns record.") @Valid @RequestBody DnsRecordUpdateRequest request);


  /**
   * Update group members.
   *
   * @param groupName the group name
   * @param members   the members
   * @return the group
   */
  @ApiOperation(
      value = "Update domain group members.",
      nickname = "updateGroupMembers",
      response = DomainGroup.class,
      tags = {"domain-controller-connector"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "The updated domain group.", response = DomainGroup.class),
      @ApiResponse(code = 400, message = "Bad request.",
          response = org.bremersee.exception.model.RestApiException.class),
      @ApiResponse(code = 404, message = "Group not found.",
          response = org.bremersee.exception.model.RestApiException.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = org.bremersee.exception.model.RestApiException.class)
  })
  @RequestMapping(
      value = "/api/groups/{groupName}/members",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.PUT)
  ResponseEntity<DomainGroup> updateGroupMembers(
      @ApiParam(value = "The domain group name.",
          required = true) @PathVariable("groupName") String groupName,
      @ApiParam(value = "The domain group members.",
          required = true) @Valid @RequestBody List<String> members);


  /**
   * Update user.
   *
   * @param userName     the user name
   * @param updateGroups the update groups flag (default is false)
   * @param domainUser   the domain user
   * @return the user
   */
  @ApiOperation(
      value = "Updates a domain user.",
      nickname = "updateUser",
      response = DomainUser.class,
      tags = {"domain-controller-connector"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "The updated domain user.", response = DomainUser.class),
      @ApiResponse(code = 400, message = "Bad request.",
          response = org.bremersee.exception.model.RestApiException.class),
      @ApiResponse(code = 404, message = "User not found.",
          response = org.bremersee.exception.model.RestApiException.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = org.bremersee.exception.model.RestApiException.class)
  })
  @RequestMapping(
      value = "/api/users/{userName}",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.PATCH)
  ResponseEntity<DomainUser> updateUser(
      @ApiParam(value = "The user name of the domain user.",
          required = true) @PathVariable("userName") String userName,
      @RequestParam(name = "updateGroups", defaultValue = "false") Boolean updateGroups,
      @ApiParam(value = "The update data of the domain user.",
          required = true) @Valid @RequestBody DomainUser domainUser);


  /**
   * Update user groups.
   *
   * @param userName the user name
   * @param groups   the groups
   * @return the user
   */
  @ApiOperation(
      value = "Updates domain user groups.",
      nickname = "updateUserGroups",
      response = DomainUser.class,
      tags = {"domain-controller-connector"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "The updated domain user.", response = DomainUser.class),
      @ApiResponse(code = 400, message = "Bad request.",
          response = org.bremersee.exception.model.RestApiException.class),
      @ApiResponse(code = 404, message = "User not found.",
          response = org.bremersee.exception.model.RestApiException.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = org.bremersee.exception.model.RestApiException.class)
  })
  @RequestMapping(
      value = "/api/users/{userName}/groups",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.PUT)
  ResponseEntity<DomainUser> updateUserGroups(
      @ApiParam(value = "The user name of the domain user.",
          required = true) @PathVariable("userName") String userName,
      @ApiParam(value = "The groups of the domain user.",
          required = true) @Valid @RequestBody List<String> groups);


  /**
   * Update user password.
   *
   * @param userName    the user name
   * @param newPassword the new password
   * @return void response entity
   */
  @ApiOperation(
      value = "Updates the password of the domain user.",
      nickname = "updateUserPassword",
      tags = {"domain-controller-connector"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "The password was successfully changed."),
      @ApiResponse(code = 400, message = "Bad request.",
          response = org.bremersee.exception.model.RestApiException.class),
      @ApiResponse(code = 404, message = "User not found.",
          response = org.bremersee.exception.model.RestApiException.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = org.bremersee.exception.model.RestApiException.class)
  })
  @RequestMapping(
      value = "/api/users/{userName}/password",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.PUT)
  ResponseEntity<Void> updateUserPassword(
      @ApiParam(value = "The user name of the domain user.",
          required = true) @PathVariable("userName") String userName,
      @ApiParam(value = "The password of the domain user.",
          required = true) @Valid @RequestBody Password newPassword);


  /**
   * User exists.
   *
   * @param userName the user name
   * @return true if the user exists otherwise false
   */
  @ApiOperation(
      value = "Checks whether a domain user exists.",
      nickname = "userExists",
      response = Boolean.class,
      tags = {"domain-controller-connector"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "True if the user exists otherwise false.",
          response = Boolean.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = org.bremersee.exception.model.RestApiException.class)
  })
  @RequestMapping(value = "/api/users/{userName}/f/exists",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<Boolean> userExists(
      @ApiParam(value = "The user name of the domain user.",
          required = true) @PathVariable("userName") String userName);

}
