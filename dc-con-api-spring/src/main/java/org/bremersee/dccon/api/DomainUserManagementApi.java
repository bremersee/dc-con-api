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
import org.bremersee.dccon.model.AvatarDefault;
import org.bremersee.dccon.model.DomainUser;
import org.bremersee.dccon.model.Password;
import org.bremersee.exception.model.RestApiException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The domain user management api.
 *
 * @author Christian Bremer
 */
@Api(value = "DomainUserManagement")
public interface DomainUserManagementApi {

  /**
   * Get domain users.
   *
   * @param sort  the sort
   * @param query the query
   * @return the domain users
   */
  @ApiOperation(
      value = "Get all domain users.",
      nickname = "getUsers",
      notes = "Get all domain users.",
      response = DomainUser.class,
      responseContainer = "List",
      tags = {"domain-user-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "The domain users.",
          response = DomainUser.class, responseContainer = "List"),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = RestApiException.class)
  })
  @RequestMapping(
      value = "/api/users",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<List<DomainUser>> getUsers(
      @ApiParam(value = "The sort order.", defaultValue = DomainUser.DEFAULT_SORT_ORDER)
      @RequestParam(value = "sort",
          defaultValue = DomainUser.DEFAULT_SORT_ORDER) String sort,

      @ApiParam(value = "A query.")
      @RequestParam(name = "q", required = false) String query);

  /**
   * Add a domain user.
   *
   * @param domainUser the domain user to add
   * @return the added domain user
   */
  @ApiOperation(
      value = "Add domain user.",
      nickname = "addUser",
      response = DomainUser.class,
      tags = {"domain-user-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "The added domain user.", response = DomainUser.class),
      @ApiResponse(code = 400, message = "Bad request.",
          response = RestApiException.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = RestApiException.class)
  })
  @RequestMapping(
      value = "/api/users",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  ResponseEntity<DomainUser> addUser(
      @ApiParam(value = "The domain user to add.", required = true)
      @Valid @RequestBody DomainUser domainUser);

  /**
   * Get domain user.
   *
   * @param userName           the user name
   * @param addAvailableGroups the add available groups flag (default is {@code false})
   * @return the domain user
   */
  @ApiOperation(
      value = "Get a domain user by name.",
      nickname = "getUser",
      response = DomainUser.class,
      tags = {"domain-user-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "The domain user with the specified name.",
          response = DomainUser.class),
      @ApiResponse(code = 400, message = "Bad request.",
          response = RestApiException.class),
      @ApiResponse(code = 404, message = "User not found.",
          response = RestApiException.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = RestApiException.class)
  })
  @RequestMapping(
      value = "/api/users/{userName}",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<DomainUser> getUser(
      @ApiParam(value = "The user name of the domain user.", required = true)
      @PathVariable("userName") String userName,

      @ApiParam(value = "The add available groups flag.", defaultValue = "false")
      @RequestParam(name = "available", defaultValue = "false") Boolean addAvailableGroups);

  /**
   * Get avatar of domain user.
   *
   * @param userName      the user name
   * @param avatarDefault the default avatar
   * @param size          the size
   * @return the avatar of the domain user
   */
  @ApiOperation(
      value = "Get avatar of domain user.",
      nickname = "getUserAvatar",
      response = byte[].class,
      tags = {"domain-user-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "The avatar of the domain user.",
          response = byte[].class),
      @ApiResponse(code = 400, message = "Bad request.",
          response = RestApiException.class),
      @ApiResponse(code = 404, message = "Avatar not found.",
          response = RestApiException.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = RestApiException.class)
  })
  @RequestMapping(
      value = "/api/users/{userName}/avatar",
      produces = {MediaType.IMAGE_JPEG_VALUE},
      method = RequestMethod.GET)
  ResponseEntity<byte[]> getUserAvatar(
      @ApiParam(value = "The user name of the domain user.", required = true)
      @PathVariable("userName") String userName,

      @ApiParam(value = "Return a default avatar if no one exits.", defaultValue = "NOT_FOUND")
      @RequestParam(name = "d", defaultValue = "NOT_FOUND") AvatarDefault avatarDefault,

      @ApiParam(value = "The size of the avatar.", defaultValue = "80")
      @RequestParam(name = "s", defaultValue = "80") Integer size);

  /**
   * Update domain user.
   *
   * @param userName     the user name
   * @param updateGroups the update groups flag (default is false)
   * @param domainUser   the domain user
   * @return the updated domain user
   */
  @ApiOperation(
      value = "Updates a domain user.",
      nickname = "updateUser",
      response = DomainUser.class,
      tags = {"domain-user-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "The updated domain user.", response = DomainUser.class),
      @ApiResponse(code = 400, message = "Bad request.",
          response = RestApiException.class),
      @ApiResponse(code = 404, message = "User not found.",
          response = RestApiException.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = RestApiException.class)
  })
  @RequestMapping(
      value = "/api/users/{userName}",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.PUT)
  ResponseEntity<DomainUser> updateUser(
      @ApiParam(value = "The user name of the domain user.", required = true)
      @PathVariable("userName") String userName,

      @ApiParam(value = "Specifies whether the groups should also be updated or not.",
          defaultValue = "false")
      @RequestParam(name = "updateGroups", defaultValue = "false") Boolean updateGroups,

      @ApiParam(value = "The domain user.", required = true)
      @Valid @RequestBody DomainUser domainUser);

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
      tags = {"domain-user-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "The password was successfully changed."),
      @ApiResponse(code = 400, message = "Bad request.",
          response = RestApiException.class),
      @ApiResponse(code = 404, message = "User not found.",
          response = RestApiException.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = RestApiException.class)
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
   * Domain user exists.
   *
   * @param userName the user name
   * @return true if the user exists otherwise false
   */
  @ApiOperation(
      value = "Checks whether a domain user exists.",
      nickname = "userExists",
      response = Boolean.class,
      tags = {"domain-user-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "True if the user exists otherwise false.",
          response = Boolean.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = RestApiException.class)
  })
  @RequestMapping(value = "/api/users/{userName}/exists",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<Boolean> userExists(
      @ApiParam(value = "The user name of the domain user.",
          required = true) @PathVariable("userName") String userName);

  /**
   * Delete domain user.
   *
   * @param userName the user name
   * @return {@code true} if the domain user was deleted, otherwise {@code false}
   */
  @ApiOperation(
      value = "Delete domain user.",
      nickname = "deleteUser",
      tags = {"domain-user-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(code = 200,
          message = "True if the domain user was successfully deleted, otherwise false.",
          response = Boolean.class),
      @ApiResponse(code = 400, message = "Bad request.",
          response = RestApiException.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = RestApiException.class)
  })
  @RequestMapping(value = "/api/users/{userName}",
      produces = {"application/json"},
      method = RequestMethod.DELETE)
  ResponseEntity<Boolean> deleteUser(
      @ApiParam(value = "The user name of the domain user.", required = true)
      @PathVariable("userName") String userName);

}
