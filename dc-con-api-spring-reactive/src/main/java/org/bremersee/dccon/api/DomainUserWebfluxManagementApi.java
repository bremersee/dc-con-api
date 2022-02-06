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
import org.bremersee.common.model.TwoLetterLanguageCode;
import org.bremersee.dccon.model.AvatarDefault;
import org.bremersee.dccon.model.DomainUser;
import org.bremersee.dccon.model.DomainUserPage;
import org.bremersee.dccon.model.Password;
import org.bremersee.exception.model.RestApiException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The domain user management api.
 *
 * @author Christian Bremer
 */
@Tag(name = "domain-user-management-controller", description = "The domain user API.")
@Validated
public interface DomainUserWebfluxManagementApi {

  /**
   * Get domain users.
   *
   * @param pageable the pageable
   * @param query the query
   * @return the domain users
   */
  @Operation(
      summary = "Get all domain users.",
      operationId = "getUsers",
      tags = {"domain-user-management-controller"},
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
              example = "userName,asc",
              in = ParameterIn.QUERY,
              array = @ArraySchema(schema = @Schema(type = "string")))
      }
  )
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "A list of domain users.",
          content = @Content(
              schema = @Schema(implementation = DomainUserPage.class))),
      @ApiResponse(
          responseCode = "500",
          description = "Fatal server error.",
          content = @Content(
              schema = @Schema(
                  implementation = RestApiException.class)))
  })
  @RequestMapping(
      value = "/api/users",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Mono<DomainUserPage> getUsers(
      @Parameter(hidden = true)
      @PageableDefault(size = Integer.MAX_VALUE, sort = "userName") Pageable pageable,

      @Parameter(description = "A query.")
      @RequestParam(name = "q", required = false) String query);

  /**
   * Add a domain user.
   *
   * @param email specifies whether to send an email or not (default is {@code false})
   * @param language the language of the email
   * @param domainUser the domain user to add
   * @return the added domain user
   */
  @Operation(
      summary = "Add domain user.",
      operationId = "addUser",
      tags = {"domain-user-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "The added domain user.",
          content = @Content(
              schema = @Schema(
                  implementation = DomainUser.class))),
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
      value = "/api/users",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  Mono<DomainUser> addUser(
      @Parameter(description = "Specifies whether to send an email or not.")
      @RequestParam(name = "email", defaultValue = "false") Boolean email,

      @Parameter(description = "The language of the email.")
      @RequestParam(name = "lang", defaultValue = "en") TwoLetterLanguageCode language,

      @Parameter(description = "The domain user to add.", required = true)
      @Valid @RequestBody DomainUser domainUser);

  /**
   * Get domain user.
   *
   * @param userName the user name
   * @return the domain user
   */
  @Operation(
      summary = "Get a domain user by name.",
      operationId = "getUser",
      tags = {"domain-user-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "The domain user with the specified name.",
          content = @Content(
              schema = @Schema(
                  implementation = DomainUser.class))),
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
      value = "/api/users/{userName}",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Mono<DomainUser> getUser(
      @Parameter(description = "The user name of the domain user.", required = true)
      @PathVariable("userName") String userName);

  /**
   * Get avatar of domain user.
   *
   * @param userName the user name
   * @param avatarDefault the avatar default
   * @param size the size
   * @return the avatar of the domain user
   */
  @Operation(
      summary = "Get avatar of domain user.",
      operationId = "getUserAvatar",
      tags = {"domain-user-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "The avatar of the domain user.",
          content = @Content(
              schema = @Schema(
                  implementation = byte[].class))),
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
      value = "/api/users/{userName}/avatar",
      produces = {MediaType.IMAGE_JPEG_VALUE},
      method = RequestMethod.GET)
  Mono<byte[]> getUserAvatar(
      @Parameter(description = "The user name of the domain user.", required = true)
      @PathVariable("userName") String userName,

      @Parameter(description = "Return a default avatar if no one exits.")
      @RequestParam(name = "d", defaultValue = "NOT_FOUND") AvatarDefault avatarDefault,

      @Parameter(description = "The size of the avatar.")
      @RequestParam(name = "s", defaultValue = "80") Integer size);

  /**
   * Update domain user.
   *
   * @param userName the user name
   * @param updateGroups the update groups flag (default is false)
   * @param domainUser the domain user
   * @return the updated domain user
   */
  @Operation(
      summary = "Updates a domain user.",
      operationId = "updateUser",
      tags = {"domain-user-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "The updated domain user.",
          content = @Content(
              schema = @Schema(
                  implementation = DomainUser.class))),
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
      value = "/api/users/{userName}",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.PUT)
  Mono<DomainUser> updateUser(
      @Parameter(description = "The user name of the domain user.", required = true)
      @PathVariable("userName") String userName,

      @Parameter(description = "Specifies whether the groups should also be updated or not.")
      @RequestParam(name = "updateGroups", defaultValue = "false") Boolean updateGroups,

      @Parameter(description = "The domain user.", required = true)
      @Valid @RequestBody DomainUser domainUser);

  /**
   * Update user password.
   *
   * @param userName the user name
   * @param email specifies whether to send an email or not (default is {@code false})
   * @param language the language of the email
   * @param newPassword the new password
   * @return void response entity
   */
  @Operation(
      summary = "Updates the password of the domain user.",
      operationId = "updateUserPassword",
      tags = {"domain-user-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "The password was successfully changed."),
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
      value = "/api/users/{userName}/password",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.PUT)
  Mono<Void> updateUserPassword(
      @Parameter(description = "The user name of the domain user.", required = true)
      @PathVariable("userName") String userName,

      @Parameter(description = "Specifies whether to send an email or not.")
      @RequestParam(name = "email", defaultValue = "false") Boolean email,

      @Parameter(description = "The language of the email.")
      @RequestParam(name = "lang", defaultValue = "en") TwoLetterLanguageCode language,

      @Parameter(description = "The password of the domain user.", required = true)
      @Valid @RequestBody Password newPassword);

  /**
   * Update user avatar response entity.
   *
   * @param userName the user name
   * @param avatar the avatar
   * @return the response entity
   */
  @Operation(
      summary = "Updates avatar of the domain user.",
      operationId = "updateUserAvatar",
      tags = {"domain-user-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "The avatar was successfully updated."),
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
      value = "/api/users/{userName}/avatar",
      consumes = {"multipart/form-data"},
      method = RequestMethod.POST)
  Mono<Void> updateUserAvatar(
      @Parameter(description = "The user name of the domain user.", required = true)
      @PathVariable("userName") String userName,

      @Parameter(description = "The avatar.", required = true)
      @RequestParam("avatar") Flux<FilePart> avatar);

  /**
   * Remove user avatar mono.
   *
   * @param userName the user name
   * @return void mono
   */
  @Operation(
      summary = "Removes avatar of the domain user.",
      operationId = "removeUserAvatar",
      tags = {"domain-user-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "The avatar was successfully removed."),
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
      value = "/api/users/{userName}/avatar",
      method = RequestMethod.DELETE)
  Mono<Void> removeUserAvatar(
      @Parameter(description = "The user name of the domain user.", required = true)
      @PathVariable("userName") String userName);

  /**
   * Domain user exists.
   *
   * @param userName the user name
   * @return true if the user exists otherwise false
   */
  @Operation(
      summary = "Checks whether a domain user exists.",
      operationId = "userExists",
      tags = {"domain-user-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "True if the user exists otherwise false.",
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
  @RequestMapping(value = "/api/users/{userName}/exists",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Mono<Boolean> userExists(
      @Parameter(description = "The user name of the domain user.",
          required = true) @PathVariable("userName") String userName);

  /**
   * Checks whether a user name is in use or not.
   *
   * @param userName the user name
   * @return true if the user name is in use otherwise false
   */
  @Operation(
      summary = "Checks whether a user name is in use or not.",
      operationId = "isUserNameInUse",
      tags = {"domain-user-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "True if the user name is in use otherwise false.",
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
  @RequestMapping(value = "/api/users/{userName}/in-use",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Mono<Boolean> isUserNameInUse(
      @Parameter(description = "The user name of the domain user.",
          required = true) @PathVariable("userName") String userName);

  /**
   * Delete domain user.
   *
   * @param userName the user name
   * @return {@code true} if the domain user was deleted, otherwise {@code false}
   */
  @Operation(
      summary = "Delete domain user.",
      operationId = "deleteUser",
      tags = {"domain-user-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "True if the domain user was successfully deleted, otherwise false.",
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
  @RequestMapping(value = "/api/users/{userName}",
      produces = {"application/json"},
      method = RequestMethod.DELETE)
  Mono<Boolean> deleteUser(
      @Parameter(description = "The user name of the domain user.", required = true)
      @PathVariable("userName") String userName);

}
