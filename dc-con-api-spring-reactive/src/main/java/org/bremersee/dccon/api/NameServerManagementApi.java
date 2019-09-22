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

import java.util.List;
import javax.validation.Valid;
import org.bremersee.dccon.model.DhcpLease;
import org.bremersee.dccon.model.DnsNode;
import org.bremersee.dccon.model.DnsZone;
import org.bremersee.dccon.model.UnknownFilter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The name server management api.
 *
 * @author Christian Bremer
 */
public interface NameServerManagementApi {

  /**
   * Gets dhcp leases.
   *
   * @param all  if {@code true}, expired leases will also be returned, otherwise only active ones
   *             (default is {@code false})
   * @param sort the sort order (default is {@link DhcpLease#SORT_ORDER_BEGIN_HOSTNAME})
   * @return the dhcp leases
   */
  @RequestMapping(value = "/api/dns/dhcp-leases",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Flux<DhcpLease> getDhcpLeases(
      @RequestParam(value = "all", defaultValue = "false") Boolean all,
      @RequestParam(value = "sort",
          defaultValue = DhcpLease.SORT_ORDER_BEGIN_HOSTNAME) String sort);


  /**
   * Get dns zones.
   *
   * @return the dns zones
   */
  @RequestMapping(
      value = "/api/dns/zones",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Flux<DnsZone> getDnsZones();

  /**
   * Add dns zone.
   *
   * @param request the request
   * @return the added dns zone
   */
  @RequestMapping(
      value = "/api/dns/zones",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  Mono<DnsZone> addDnsZone(
      @Valid @RequestBody DnsZone request);

  /**
   * Delete dns zone.
   *
   * @param zoneName the zone name
   * @return {@code true} if the dns zone was deleted, otherwise {@code false}
   */
  @RequestMapping(
      value = "/api/dns/zones/{zoneName}",
      produces = {"application/json"},
      method = RequestMethod.DELETE)
  Mono<Boolean> deleteDnsZone(
      @PathVariable(value = "zoneName") String zoneName);


  /**
   * Get dns nodes.
   *
   * @param zoneName      the zone name
   * @param unknownFilter the unknown filter
   * @return the dns nodes
   */
  @RequestMapping(value = "/api/dns/zones/{zoneName}",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Flux<DnsNode> getDnsNodes(
      @PathVariable(value = "zoneName") String zoneName,
      @RequestParam(name = "filter", defaultValue = "NO_UNKNOWN") UnknownFilter unknownFilter);

  /**
   * Save dns node.
   *
   * @param zoneName the dns zone name
   * @param dnsNode  the dns node
   * @return the saved dns node
   */
  @RequestMapping(
      value = "/api/dns/zones/{zoneName}",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  Mono<DnsNode> saveDnsNode(
      @PathVariable("zoneName") String zoneName,
      @Valid @RequestBody DnsNode dnsNode);

  /**
   * Get dns node.
   *
   * @param zoneName      the dns zone name
   * @param nodeName      the dns node name
   * @param unknownFilter the unknown filter
   * @return the dns node
   */
  @RequestMapping(
      value = "/api/dns/zones/{zoneName}/{nodeName}",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Mono<DnsNode> getDnsNode(
      @PathVariable("zoneName") String zoneName,
      @PathVariable("nodeName") String nodeName,
      @RequestParam(name = "filter", defaultValue = "NO_UNKNOWN") UnknownFilter unknownFilter);

  /**
   * Delete dns node.
   *
   * @param zoneName the dns zone name
   * @param nodeName the dns node name
   * @return {@code true} if the dns node was removed; {@code false} if dns node didn't exist
   */
  @RequestMapping(
      value = "/api/dns/zones/{zoneName}/{nodeName}",
      produces = {"application/json"},
      method = RequestMethod.DELETE)
  Mono<Boolean> deleteDnsNode(
      @PathVariable("zoneName") String zoneName,
      @PathVariable("nodeName") String nodeName);

  /**
   * Delete all dns nodes.
   *
   * @param zoneName  the zone name
   * @param nodeNames the node names (if the list is empty, all dns nodes will be deleted)
   * @return void response entity
   */
  @RequestMapping(
      value = "/api/dns/zones/{zoneName}/nodes/all",
      produces = {"application/json"},
      method = RequestMethod.DELETE)
  Mono<Void> deleteAllDnsNodes(
      @PathVariable("zoneName") String zoneName,
      @RequestParam("nodeNames") List<String> nodeNames);

}
