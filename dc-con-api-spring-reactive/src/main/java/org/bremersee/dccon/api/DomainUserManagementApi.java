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
import org.bremersee.common.model.TwoLetterLanguageCode;
import org.bremersee.dccon.model.AvatarDefault;
import org.bremersee.dccon.model.DomainUser;
import org.bremersee.dccon.model.Password;
import org.springframework.http.MediaType;
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
public interface DomainUserManagementApi {

  /**
   * Get domain users.
   *
   * @param sort  the sort
   * @param query the query
   * @return the domain users
   */
  @RequestMapping(
      value = "/api/users",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Flux<DomainUser> getUsers(
      @RequestParam(value = "sort",
          defaultValue = DomainUser.DEFAULT_SORT_ORDER) String sort,
      @RequestParam(name = "q", required = false) String query);

  /**
   * Add a domain user.
   *
   * @param email      specifies whether to send an email or not (default is {@code false})
   * @param language   the language of the email
   * @param domainUser the domain user to add
   * @return the added domain user
   */
  @RequestMapping(
      value = "/api/users",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  Mono<DomainUser> addUser(
      @RequestParam(name = "email", defaultValue = "false") Boolean email,
      @RequestParam(name = "lang", defaultValue = "en") TwoLetterLanguageCode language,
      @Valid @RequestBody DomainUser domainUser);

  /**
   * Get domain user.
   *
   * @param userName the user name
   * @return the domain user
   */
  @RequestMapping(
      value = "/api/users/{userName}",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Mono<DomainUser> getUser(
      @PathVariable("userName") String userName);

  /**
   * Get avatar of domain user.
   *
   * @param userName      the user name
   * @param avatarDefault the avatar default
   * @param size          the size
   * @return the avatar of the domain user
   */
  @RequestMapping(
      value = "/api/users/{userName}/avatar",
      produces = {MediaType.IMAGE_JPEG_VALUE},
      method = RequestMethod.GET)
  Mono<byte[]> getUserAvatar(
      @PathVariable("userName") String userName,
      @RequestParam(name = "d", defaultValue = "NOT_FOUND") AvatarDefault avatarDefault,
      @RequestParam(name = "s", defaultValue = "80") Integer size);

  /**
   * Update domain user.
   *
   * @param userName     the user name
   * @param updateGroups the update groups flag (default is false)
   * @param domainUser   the domain user
   * @return the updated domain user
   */
  @RequestMapping(
      value = "/api/users/{userName}",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.PUT)
  Mono<DomainUser> updateUser(
      @PathVariable("userName") String userName,
      @RequestParam(name = "updateGroups", defaultValue = "false") Boolean updateGroups,
      @Valid @RequestBody DomainUser domainUser);

  /**
   * Update user password.
   *
   * @param userName    the user name
   * @param email       specifies whether to send an email or not (default is {@code false})
   * @param language    the language of the email
   * @param newPassword the new password
   * @return void response entity
   */
  @RequestMapping(
      value = "/api/users/{userName}/password",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.PUT)
  Mono<Void> updateUserPassword(
      @PathVariable("userName") String userName,
      @RequestParam(name = "email", defaultValue = "false") Boolean email,
      @RequestParam(name = "lang", defaultValue = "en") TwoLetterLanguageCode language,
      @Valid @RequestBody Password newPassword);

  /**
   * Domain user exists.
   *
   * @param userName the user name
   * @return true if the user exists otherwise false
   */
  @RequestMapping(value = "/api/users/{userName}/exists",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Mono<Boolean> userExists(@PathVariable("userName") String userName);

  /**
   * Checks whether a user name is in use or not.
   *
   * @param userName the user name
   * @return true if the user name is in use otherwise false
   */
  @RequestMapping(value = "/api/users/{userName}/in-use",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Mono<Boolean> isUserNameInUse(
      @ApiParam(value = "The user name of the domain user.",
          required = true) @PathVariable("userName") String userName);

  /**
   * Delete domain user.
   *
   * @param userName the user name
   * @return {@code true} if the domain user was deleted, otherwise {@code false}
   */
  @RequestMapping(value = "/api/users/{userName}",
      produces = {"application/json"},
      method = RequestMethod.DELETE)
  Mono<Boolean> deleteUser(@PathVariable("userName") String userName);

}
