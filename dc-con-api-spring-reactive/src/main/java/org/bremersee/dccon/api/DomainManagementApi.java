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

import org.bremersee.dccon.model.Password;
import org.bremersee.dccon.model.PasswordInformation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reactor.core.publisher.Mono;

/**
 * The domain management api.
 *
 * @author Christian Bremer
 */
public interface DomainManagementApi {

  /**
   * Get password information.
   *
   * @return the password information
   */
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
  @RequestMapping(
      value = "/api/domain/random-password",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Mono<Password> getRandomPassword();

}
