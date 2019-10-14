/*
 * Copyright 2019 the original author or authors.
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
import org.bremersee.dccon.model.DomainGroup;
import org.bremersee.exception.model.RestApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The domain group management api.
 *
 * @author Christian Bremer
 */
@Api(value = "DomainGroupManagement")
public interface DomainGroupManagementApi {

  /**
   * Get domain groups.
   *
   * @param sort  the sort order
   * @param query the query
   * @return the groups
   */
  @ApiOperation(
      value = "Get all domain groups.",
      nickname = "getGroups",
      notes = "Get all domain groups.",
      response = DomainGroup.class,
      responseContainer = "List",
      tags = {"domain-group-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "A list of domain groups.",
          response = DomainGroup.class, responseContainer = "List"),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = RestApiException.class)
  })
  @RequestMapping(
      value = "/api/groups",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<List<DomainGroup>> getGroups(
      @ApiParam(value = "The sort order.", defaultValue = DomainGroup.DEFAULT_SORT_ORDER)
      @RequestParam(value = "sort",
          defaultValue = DomainGroup.DEFAULT_SORT_ORDER) String sort,

      @ApiParam(value = "A query.")
      @RequestParam(name = "q", required = false) String query);

  /**
   * Add domain group.
   *
   * @param group the domain group to add
   * @return the added domain group
   */
  @ApiOperation(
      value = "Add domain group.",
      nickname = "addGroup",
      response = DomainGroup.class,
      tags = {"domain-group-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "The added domain group.", response = DomainGroup.class),
      @ApiResponse(code = 400, message = "Bad request.",
          response = RestApiException.class),
      @ApiResponse(code = 406, message = "Already exists",
          response = RestApiException.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = RestApiException.class)
  })
  @RequestMapping(
      value = "/api/groups",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  ResponseEntity<DomainGroup> addGroup(
      @ApiParam(value = "The domain group to add.", required = true)
      @Valid @RequestBody DomainGroup group);

  /**
   * Get domain group by name.
   *
   * @param groupName the group name
   * @return the domain group
   */
  @ApiOperation(
      value = "Get a domain group by name.",
      nickname = "getGroupByName",
      response = DomainGroup.class,
      tags = {"domain-group-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "The domain group with the specified name.",
          response = DomainGroup.class),
      @ApiResponse(code = 400, message = "Bad request.",
          response = RestApiException.class),
      @ApiResponse(code = 404, message = "Not found",
          response = RestApiException.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = RestApiException.class)
  })
  @RequestMapping(
      value = "/api/groups/{groupName}",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<DomainGroup> getGroup(
      @ApiParam(value = "The domain group name.", required = true)
      @PathVariable("groupName") String groupName);

  /**
   * Update domain group.
   *
   * @param groupName   the group name
   * @param domainGroup the domain group
   * @return the updated domain group
   */
  @ApiOperation(
      value = "Updates a domain group.",
      nickname = "updateGroup",
      response = DomainGroup.class,
      tags = {"domain-group-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "The updated domain group.", response = DomainGroup.class),
      @ApiResponse(code = 400, message = "Bad request.",
          response = RestApiException.class),
      @ApiResponse(code = 404, message = "Group not found.",
          response = RestApiException.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = RestApiException.class)
  })
  @RequestMapping(
      value = "/api/groups/{groupName}",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.PUT)
  ResponseEntity<DomainGroup> updateGroup(
      @ApiParam(value = "The name of the domain group.", required = true)
      @PathVariable("groupName") String groupName,

      @ApiParam(value = "The domain group.", required = true)
      @Valid @RequestBody DomainGroup domainGroup);

  /**
   * Domain group exists.
   *
   * @param groupName the group name
   * @return true if the group exists otherwise false
   */
  @ApiOperation(
      value = "Checks whether a domain group exists.",
      nickname = "groupExists",
      response = Boolean.class,
      tags = {"domain-group-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "True if the group exists otherwise false.",
          response = Boolean.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = RestApiException.class)
  })
  @RequestMapping(value = "/api/groups/{groupName}/exists",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<Boolean> groupExists(
      @ApiParam(value = "The name of the domain group.", required = true)
      @PathVariable("groupName") String groupName);

  /**
   * Delete domain group.
   *
   * @param groupName the group name
   * @return {@code true} if the domain group was deleted, otherwise {@code false}
   */
  @ApiOperation(
      value = "Delete domain group.",
      nickname = "deleteGroup",
      tags = {"domain-group-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(code = 200,
          message = "True if the domain group was successfully deleted, otherwise false.",
          response = Boolean.class),
      @ApiResponse(code = 400, message = "Bad request.",
          response = RestApiException.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = RestApiException.class)
  })
  @RequestMapping(
      value = "/api/groups/{groupName}",
      produces = {"application/json"},
      method = RequestMethod.DELETE)
  ResponseEntity<Boolean> deleteGroup(
      @ApiParam(value = "The domain group name.", required = true)
      @PathVariable("groupName") String groupName);

}
