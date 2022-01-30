/*
 * Copyright 2019-2020 the original author or authors.
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import org.bremersee.dccon.model.DomainGroup;
import org.bremersee.dccon.model.DomainGroupPage;
import org.bremersee.exception.model.RestApiException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;

/**
 * The domain group management api.
 *
 * @author Christian Bremer
 */
@Tag(name = "domain-group-management-controller", description = "The domain group API.")
@Validated
public interface DomainGroupWebfluxManagementApi {

  /**
   * Get domain groups.
   *
   * @param pageable the pageable
   * @param query the query
   * @return the groups
   */
  @Operation(
      summary = "Get all domain groups.",
      operationId = "getGroups",
      tags = {"domain-group-management-controller"},
      parameters = {
          @Parameter(name = "page",
              description = "The page number starting with 0.",
              in = ParameterIn.QUERY,
              schema = @Schema(type = "integer")),
          @Parameter(name = "size",
              description = "The page size.",
              in = ParameterIn.QUERY,
              schema = @Schema(type = "integer")),
          @Parameter(name = "sort",
              description = "The sort order.",
              example = "name,asc",
              in = ParameterIn.QUERY,
              array = @ArraySchema(schema = @Schema(type = "string")))
      }
  )
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "A list of domain groups.",
          content = @Content(
              schema = @Schema(implementation = DomainGroupPage.class))),
      @ApiResponse(
          responseCode = "500",
          description = "Fatal server error.",
          content = @Content(
              schema = @Schema(
                  implementation = RestApiException.class)))
  })
  @RequestMapping(
      value = "/api/groups",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Mono<DomainGroupPage> getGroups(
      @Parameter(hidden = true)
      @PageableDefault(size = Integer.MAX_VALUE, sort = "name,asc") Pageable pageable,

      @Parameter(description = "A query.")
      @RequestParam(name = "q", required = false) String query);

  /**
   * Add domain group.
   *
   * @param group the domain group to add
   * @return the added domain group
   */
  @Operation(
      summary = "Add domain group.",
      operationId = "addGroup",
      tags = {"domain-group-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "The added domain group.",
          content = @Content(
              schema = @Schema(
                  implementation = DomainGroup.class))),
      @ApiResponse(
          responseCode = "400",
          description = "Bad request.",
          content = @Content(
              schema = @Schema(
                  implementation = RestApiException.class))),
      @ApiResponse(
          responseCode = "406",
          description = "Already exists.",
          content = @Content(
              schema = @Schema(
                  implementation = RestApiException.class))),
      @ApiResponse(
          responseCode = "500",
          description = "Fatal server error.",
          content = @Content(
              schema = @Schema(
                  implementation = RestApiException.class)))
  })
  @RequestMapping(
      value = "/api/groups",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  Mono<DomainGroup> addGroup(
      @Parameter(description = "The domain group to add.", required = true)
      @Valid @RequestBody DomainGroup group);

  /**
   * Get domain group by name.
   *
   * @param groupName the group name
   * @return the domain group
   */
  @Operation(
      summary = "Get a domain group by name.",
      operationId = "getGroupByName",
      tags = {"domain-group-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "The domain group with the specified name.",
          content = @Content(
              schema = @Schema(
                  implementation = DomainGroup.class))),
      @ApiResponse(
          responseCode = "400",
          description = "Bad request.",
          content = @Content(
              schema = @Schema(
                  implementation = RestApiException.class))),
      @ApiResponse(
          responseCode = "404",
          description = "Not found.",
          content = @Content(
              schema = @Schema(
                  implementation = RestApiException.class))),
      @ApiResponse(
          responseCode = "500",
          description = "Fatal server error.",
          content = @Content(
              schema = @Schema(
                  implementation = RestApiException.class)))
  })
  @RequestMapping(
      value = "/api/groups/{groupName}",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Mono<DomainGroup> getGroup(
      @Parameter(description = "The domain group name.", required = true)
      @PathVariable("groupName") String groupName);

  /**
   * Update domain group.
   *
   * @param groupName the group name
   * @param domainGroup the domain group
   * @return the updated domain group
   */
  @Operation(
      summary = "Updates a domain group.",
      operationId = "updateGroup",
      tags = {"domain-group-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "The updated domain group.",
          content = @Content(
              schema = @Schema(
                  implementation = DomainGroup.class))),
      @ApiResponse(
          responseCode = "400",
          description = "Bad request.",
          content = @Content(
              schema = @Schema(
                  implementation = RestApiException.class))),
      @ApiResponse(
          responseCode = "404",
          description = "Not found.",
          content = @Content(
              schema = @Schema(
                  implementation = RestApiException.class))),
      @ApiResponse(
          responseCode = "500",
          description = "Fatal server error.",
          content = @Content(
              schema = @Schema(
                  implementation = RestApiException.class)))
  })
  @RequestMapping(
      value = "/api/groups/{groupName}",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.PUT)
  Mono<DomainGroup> updateGroup(
      @Parameter(description = "The name of the domain group.", required = true)
      @PathVariable("groupName") String groupName,

      @Parameter(description = "The domain group.", required = true)
      @Valid @RequestBody DomainGroup domainGroup);

  /**
   * Domain group exists.
   *
   * @param groupName the group name
   * @return true if the group exists otherwise false
   */
  @Operation(
      summary = "Checks whether a domain group exists.",
      operationId = "groupExists",
      tags = {"domain-group-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "True if the group exists otherwise false.",
          content = @Content(
              schema = @Schema(
                  implementation = Boolean.class))),
      @ApiResponse(
          responseCode = "500",
          description = "Fatal server error.",
          content = @Content(
              schema = @Schema(
                  implementation = RestApiException.class)))
  })
  @RequestMapping(value = "/api/groups/{groupName}/exists",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Mono<Boolean> groupExists(
      @Parameter(description = "The name of the domain group.", required = true)
      @PathVariable("groupName") String groupName);

  /**
   * Checks whether a group is in use or not.
   *
   * @param groupName the group name
   * @return true if the group name is in use otherwise false
   */
  @Operation(
      summary = "Checks whether a group is in use or not.",
      operationId = "isGroupNameInUse",
      tags = {"domain-group-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "True if the group name is in use otherwise false.",
          content = @Content(
              schema = @Schema(
                  implementation = Boolean.class))),
      @ApiResponse(
          responseCode = "500",
          description = "Fatal server error.",
          content = @Content(
              schema = @Schema(
                  implementation = RestApiException.class)))
  })
  @RequestMapping(value = "/api/groups/{groupName}/in-use",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Mono<Boolean> isGroupNameInUse(
      @Parameter(description = "The name of the domain group.", required = true)
      @PathVariable("groupName") String groupName);

  /**
   * Delete domain group.
   *
   * @param groupName the group name
   * @return {@code true} if the domain group was deleted, otherwise {@code false}
   */
  @Operation(
      summary = "Delete domain group.",
      operationId = "deleteGroup",
      tags = {"domain-group-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "True if the domain group was successfully deleted, otherwise false.",
          content = @Content(
              schema = @Schema(
                  implementation = Boolean.class))),
      @ApiResponse(
          responseCode = "400",
          description = "Bad request.",
          content = @Content(
              schema = @Schema(
                  implementation = RestApiException.class))),
      @ApiResponse(
          responseCode = "500",
          description = "Fatal server error.",
          content = @Content(
              schema = @Schema(
                  implementation = RestApiException.class)))
  })
  @RequestMapping(
      value = "/api/groups/{groupName}",
      produces = {"application/json"},
      method = RequestMethod.DELETE)
  Mono<Boolean> deleteGroup(
      @Parameter(description = "The domain group name.", required = true)
      @PathVariable("groupName") String groupName);

}
