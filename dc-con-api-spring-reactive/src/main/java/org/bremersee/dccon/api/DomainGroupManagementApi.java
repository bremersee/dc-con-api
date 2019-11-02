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

import io.swagger.annotations.ApiParam;
import javax.validation.Valid;
import org.bremersee.dccon.model.DomainGroup;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The domain group management api.
 *
 * @author Christian Bremer
 */
public interface DomainGroupManagementApi {

  /**
   * Get domain groups.
   *
   * @param sort  the sort order
   * @param query the query
   * @return the groups
   */
  @RequestMapping(
      value = "/api/groups",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Flux<DomainGroup> getGroups(
      @RequestParam(value = "sort",
          defaultValue = DomainGroup.DEFAULT_SORT_ORDER) String sort,
      @RequestParam(name = "q", required = false) String query);

  /**
   * Add domain group.
   *
   * @param group the domain group to add
   * @return the added domain group
   */
  @RequestMapping(
      value = "/api/groups",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  Mono<DomainGroup> addGroup(@Valid @RequestBody DomainGroup group);

  /**
   * Get domain group by name.
   *
   * @param groupName the group name
   * @return the domain group
   */
  @RequestMapping(
      value = "/api/groups/{groupName}",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Mono<DomainGroup> getGroup(
      @PathVariable("groupName") String groupName);

  /**
   * Update domain group.
   *
   * @param groupName   the group name
   * @param domainGroup the domain group
   * @return the updated domain group
   */
  @RequestMapping(
      value = "/api/groups/{groupName}",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.PUT)
  Mono<DomainGroup> updateGroup(
      @PathVariable("groupName") String groupName,
      @Valid @RequestBody DomainGroup domainGroup);

  /**
   * Domain group exists.
   *
   * @param groupName the group name
   * @return true if the group exists otherwise false
   */
  @RequestMapping(value = "/api/groups/{groupName}/exists",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Mono<Boolean> groupExists(
      @PathVariable("groupName") String groupName);

  /**
   * Checks whether a group is in use or not.
   *
   * @param groupName the group name
   * @return true if the group name is in use otherwise false
   */
  @RequestMapping(value = "/api/groups/{groupName}/in-use",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Mono<Boolean> isGroupNameInUse(
      @ApiParam(value = "The name of the domain group.", required = true)
      @PathVariable("groupName") String groupName);

  /**
   * Delete domain group.
   *
   * @param groupName the group name
   * @return {@code true} if the domain group was deleted, otherwise {@code false}
   */
  @RequestMapping(
      value = "/api/groups/{groupName}",
      produces = {"application/json"},
      method = RequestMethod.DELETE)
  Mono<Boolean> deleteGroup(
      @PathVariable("groupName") String groupName);

}
