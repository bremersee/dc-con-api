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

import org.bremersee.common.model.TwoLetterLanguageCode;
import org.bremersee.dccon.api.DomainUserManagementApi;
import org.bremersee.dccon.model.AvatarDefault;
import org.bremersee.dccon.model.DomainUser;
import org.bremersee.dccon.model.DomainUserPage;
import org.bremersee.dccon.model.Password;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * The domain user management rest controller.
 *
 * @author Christian Bremer
 */
@RestController
public class DomainUserManagementRestController implements DomainUserManagementApi {

  @Override
  public ResponseEntity<DomainUserPage> getUsers(Pageable pageable, String query) {
    return null;
  }

  @Override
  public ResponseEntity<DomainUser> addUser(Boolean email, TwoLetterLanguageCode language,
      DomainUser domainUser) {
    return null;
  }

  @Override
  public ResponseEntity<DomainUser> getUser(String userName) {
    return null;
  }

  @Override
  public ResponseEntity<byte[]> getUserAvatar(String userName, AvatarDefault avatarDefault,
      Integer size) {
    return null;
  }

  @Override
  public ResponseEntity<DomainUser> updateUser(String userName, Boolean updateGroups,
      DomainUser domainUser) {
    return null;
  }

  @Override
  public ResponseEntity<Void> updateUserPassword(String userName, Boolean email,
      TwoLetterLanguageCode language, Password newPassword) {
    return null;
  }

  @Override
  public ResponseEntity<Void> updateUserAvatar(String userName, MultipartFile avatar) {
    return null;
  }

  @Override
  public ResponseEntity<Void> removeUserAvatar(String userName) {
    return null;
  }

  @Override
  public ResponseEntity<Boolean> userExists(String userName) {
    return null;
  }

  @Override
  public ResponseEntity<Boolean> isUserNameInUse(String userName) {
    return null;
  }

  @Override
  public ResponseEntity<Boolean> deleteUser(String userName) {
    return null;
  }
}
