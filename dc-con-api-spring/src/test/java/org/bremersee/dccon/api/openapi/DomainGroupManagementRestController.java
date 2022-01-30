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

import org.bremersee.dccon.api.DomainGroupManagementApi;
import org.bremersee.dccon.model.DomainGroup;
import org.bremersee.dccon.model.DomainGroupPage;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * The domain group management rest controller.
 *
 * @author Christian Bremer
 */
@RestController
public class DomainGroupManagementRestController implements DomainGroupManagementApi {

  @Override
  public ResponseEntity<DomainGroupPage> getGroups(Pageable pageable, String query) {
    return null;
  }

  @Override
  public ResponseEntity<DomainGroup> addGroup(DomainGroup group) {
    return null;
  }

  @Override
  public ResponseEntity<DomainGroup> getGroup(String groupName) {
    return null;
  }

  @Override
  public ResponseEntity<DomainGroup> updateGroup(String groupName, DomainGroup domainGroup) {
    return null;
  }

  @Override
  public ResponseEntity<Boolean> groupExists(String groupName) {
    return null;
  }

  @Override
  public ResponseEntity<Boolean> isGroupNameInUse(String groupName) {
    return null;
  }

  @Override
  public ResponseEntity<Boolean> deleteGroup(String groupName) {
    return null;
  }
}
