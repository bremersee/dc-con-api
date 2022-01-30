/*
 * Copyright 2020 the original author or authors.
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

package org.bremersee.dccon.api.openapi;

import org.bremersee.dccon.api.DomainManagementApi;
import org.bremersee.dccon.model.Password;
import org.bremersee.dccon.model.PasswordInformation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * The domain management rest controller.
 *
 * @author Christian Bremer
 */
@RestController
public class DomainManagementRestController implements DomainManagementApi {

  @Override
  public ResponseEntity<PasswordInformation> getPasswordInformation() {
    return null;
  }

  @Override
  public ResponseEntity<Password> getRandomPassword() {
    return null;
  }
}
