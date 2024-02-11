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
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.bremersee.dccon.model.Password;
import org.bremersee.dccon.model.PasswordInformation;
import org.bremersee.exception.model.RestApiException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reactor.core.publisher.Mono;

/**
 * The domain management api.
 *
 * @author Christian Bremer
 */
@Tag(name = "domain-management-controller", description = "The domain management API.")
public interface DomainWebfluxManagementApi {

  /**
   * Get password information.
   *
   * @return the password information
   */
  @Operation(
      summary = "Get password information.",
      operationId = "getPasswordInformation",
      tags = {"domain-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "The password information.",
          content = @Content(
              schema = @Schema(
                  implementation = PasswordInformation.class))),
      @ApiResponse(
          responseCode = "500",
          description = "Fatal server error.",
          content = @Content(
              schema = @Schema(
                  implementation = RestApiException.class)))
  })
  @RequestMapping(
      value = "/api/domain/password-information",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Mono<PasswordInformation> getPasswordInformation();

  /**
   * Gets random password.
   *
   * @return the random password
   */
  @Operation(
      summary = "Get a random password.",
      operationId = "getRandomPassword",
      tags = {"domain-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "The random password.",
          content = @Content(
              schema = @Schema(
                  implementation = Password.class))),
      @ApiResponse(
          responseCode = "500",
          description = "Fatal server error.",
          content = @Content(
              schema = @Schema(
                  implementation = RestApiException.class)))
  })
  @RequestMapping(
      value = "/api/domain/random-password",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Mono<Password> getRandomPassword();

}
