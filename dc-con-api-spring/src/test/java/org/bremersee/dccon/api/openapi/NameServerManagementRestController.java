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

import java.util.List;
import org.bremersee.dccon.api.NameServerManagementApi;
import org.bremersee.dccon.model.DhcpLeasePage;
import org.bremersee.dccon.model.DnsNode;
import org.bremersee.dccon.model.DnsNodePage;
import org.bremersee.dccon.model.DnsZone;
import org.bremersee.dccon.model.DnsZonePage;
import org.bremersee.dccon.model.UnknownFilter;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * The name server management rest controller.
 *
 * @author Christian Bremer
 */
@RestController
public class NameServerManagementRestController implements NameServerManagementApi {

  @Override
  public ResponseEntity<DnsNodePage> query(
      Pageable pageable,
      String query,
      UnknownFilter unknownFilter) {
    return null;
  }

  @Override
  public ResponseEntity<DhcpLeasePage> getDhcpLeases(
      Pageable pageable,
      Boolean all) {
    return null;
  }

  @Override
  public ResponseEntity<DnsZonePage> getDnsZones(Pageable pageable, Boolean reverseOnly) {
    return null;
  }

  @Override
  public ResponseEntity<DnsZone> addDnsZone(DnsZone request) {
    return null;
  }

  @Override
  public ResponseEntity<Boolean> deleteDnsZone(String zoneName) {
    return null;
  }

  @Override
  public ResponseEntity<DnsNodePage> getDnsNodes(
      String zoneName,
      Pageable pageable,
      String query,
      UnknownFilter unknownFilter) {
    return null;
  }

  @Override
  public ResponseEntity<DnsNode> saveDnsNode(String zoneName, DnsNode dnsNode) {
    return null;
  }

  @Override
  public ResponseEntity<DnsNode> getDnsNode(String zoneName, String nodeName,
      UnknownFilter unknownFilter) {
    return null;
  }

  @Override
  public ResponseEntity<Boolean> deleteDnsNode(String zoneName, String nodeName) {
    return null;
  }

  @Override
  public ResponseEntity<Void> deleteAllDnsNodes(String zoneName, List<String> nodeNames) {
    return null;
  }
}
