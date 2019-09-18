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
import org.bremersee.dccon.model.DhcpLease;
import org.bremersee.dccon.model.DnsNode;
import org.bremersee.dccon.model.DnsZone;
import org.bremersee.dccon.model.UnknownFilter;
import org.bremersee.exception.model.RestApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The ame server management api.
 *
 * @author Christian Bremer
 */
@Api(value = "NameServerManagement")
public interface NameServerManagementApi {

  /**
   * Gets dhcp leases.
   *
   * @param all  if {@code true}, expired leases will also be returned, otherwise only active ones
   *             (default is {@code false})
   * @param sort the sort order (default is {@link DhcpLease#SORT_ORDER_BEGIN_HOSTNAME})
   * @return the dhcp leases
   */
  @ApiOperation(
      value = "Get dhcp leases.",
      nickname = "getDhcpLeases", notes = "Get dhcp leases.",
      response = DhcpLease.class,
      responseContainer = "List",
      tags = {"name-server-management"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "A list with dhcp leases.",
          response = DhcpLease.class, responseContainer = "List"),
      @ApiResponse(code = 400, message = "Bad request.",
          response = RestApiException.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = RestApiException.class)})
  @RequestMapping(value = "/api/dns/dhcp-leases",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<List<DhcpLease>> getDhcpLeases(
      @ApiParam(value = "'true' returns also expired leases, 'false' only active ones.",
          defaultValue = "false")
      @RequestParam(value = "all", defaultValue = "false") Boolean all,
      @ApiParam(value = "The sort order.", defaultValue = DhcpLease.SORT_ORDER_BEGIN_HOSTNAME)
      @RequestParam(value = "sort",
          defaultValue = DhcpLease.SORT_ORDER_BEGIN_HOSTNAME) String sort);


  /**
   * Get dns zones.
   *
   * @return the dns zones
   */
  @ApiOperation(
      value = "Get all dns zones.",
      nickname = "getDnsZones",
      notes = "Get all dns zones.",
      response = DnsZone.class,
      responseContainer = "List",
      tags = {"name-server-management"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "A list with dns zones.",
          response = DnsZone.class, responseContainer = "List"),
      @ApiResponse(code = 400, message = "Bad request.",
          response = RestApiException.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = RestApiException.class)
  })
  @RequestMapping(
      value = "/api/dns/zones",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<List<DnsZone>> getDnsZones();

  /**
   * Add dns zone.
   *
   * @param request the request
   * @return the added dns zone
   */
  @ApiOperation(
      value = "Add dns zone.",
      nickname = "addDnsZone",
      notes = "Add dns zone.",
      tags = {"name-server-management"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "The zone was successfully added.",
          response = DnsZone.class),
      @ApiResponse(code = 400, message = "Bad request.",
          response = RestApiException.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = RestApiException.class)
  })
  @RequestMapping(
      value = "/api/dns/zones",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  ResponseEntity<DnsZone> addDnsZone(
      @ApiParam(value = "The dns zone create request")
      @Valid @RequestBody DnsZone request);

  /**
   * Delete dns zone.
   *
   * @param zoneName the zone name
   * @return {@code true} if the dns zone was deleted, otherwise {@code false}
   */
  @ApiOperation(
      value = "Delete dns zone.",
      nickname = "deleteDnsZone",
      notes = "Delete dns zone.",
      tags = {"name-server-management"})
  @ApiResponses(value = {
      @ApiResponse(code = 200,
          message = "True if the dns zone was successfully deleted, otherwise false.",
          response = Boolean.class),
      @ApiResponse(code = 400, message = "Bad request.",
          response = RestApiException.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = RestApiException.class)
  })
  @RequestMapping(
      value = "/api/dns/zones/{zoneName}",
      produces = {"application/json"},
      method = RequestMethod.DELETE)
  ResponseEntity<Boolean> deleteDnsZone(
      @ApiParam(value = "The dns zone name.", required = true)
      @PathVariable(value = "zoneName") String zoneName);


  /**
   * Get dns nodes.
   *
   * @param zoneName      the zone name
   * @param unknownFilter the unknown filter
   * @return the dns nodes
   */
  @ApiOperation(
      value = "Get all dns nodes of a zone.",
      nickname = "getDnsNodes", notes = "Get all dns nodes of a zone.",
      response = DnsNode.class,
      responseContainer = "List",
      tags = {"name-server-management"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "A list with dns nodes.",
          response = DnsNode.class, responseContainer = "List"),
      @ApiResponse(code = 400, message = "Bad request.",
          response = RestApiException.class),
      @ApiResponse(code = 404, message = "DNS zone not found.",
          response = RestApiException.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = RestApiException.class)})
  @RequestMapping(value = "/api/dns/zones/{zoneName}",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<List<DnsNode>> getDnsNodes(
      @ApiParam(value = "The dns zone name.", required = true)
      @PathVariable(value = "zoneName") String zoneName,

      @ApiParam(value = "The unknown filter.", defaultValue = "NO_UNKNOWN")
      @RequestParam(name = "filter", defaultValue = "NO_UNKNOWN") UnknownFilter unknownFilter);

  /**
   * Save dns node.
   *
   * @param zoneName the dns zone name
   * @param dnsNode  the dns node
   * @return the saved dns node
   */
  @ApiOperation(
      value = "Save dns node.",
      nickname = "saveDnsNode",
      notes = "Save dns node.",
      tags = {"name-server-management"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "The dns node was successfully saved.",
          response = DnsNode.class),
      @ApiResponse(code = 204, message = "The dns node was deleted due to no records."),
      @ApiResponse(code = 400, message = "Bad request.",
          response = RestApiException.class),
      @ApiResponse(code = 404, message = "DNS zone not found.",
          response = RestApiException.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = RestApiException.class)
  })
  @RequestMapping(
      value = "/api/dns/zones/{zoneName}",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  ResponseEntity<DnsNode> saveDnsNode(
      @ApiParam(value = "The dns zone name.", required = true)
      @PathVariable("zoneName") String zoneName,

      @ApiParam(value = "The dns node.")
      @Valid @RequestBody DnsNode dnsNode);

  /**
   * Get dns node.
   *
   * @param zoneName      the dns zone name
   * @param nodeName      the dns node name
   * @param unknownFilter the unknown filter
   * @return the dns node
   */
  @ApiOperation(
      value = "Get dns node.",
      nickname = "getDnsNode",
      notes = "Get dns node.",
      tags = {"name-server-management"})
  @ApiResponses(value = {
      @ApiResponse(code = 200,
          message = "The dns node.",
          response = DnsNode.class),
      @ApiResponse(code = 400, message = "Bad request.",
          response = RestApiException.class),
      @ApiResponse(code = 404, message = "DNS node not found.",
          response = RestApiException.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = RestApiException.class)
  })
  @RequestMapping(
      value = "/api/dns/zones/{zoneName}/{nodeName}",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<DnsNode> getDnsNode(
      @ApiParam(value = "The dns zone name.", required = true)
      @PathVariable("zoneName") String zoneName,

      @ApiParam(value = "The dns node name.", required = true)
      @PathVariable("nodeName") String nodeName,

      @ApiParam(value = "The unknown filter.", defaultValue = "NO_UNKNOWN")
      @RequestParam(name = "filter", defaultValue = "NO_UNKNOWN") UnknownFilter unknownFilter);

  /**
   * Delete dns node.
   *
   * @param zoneName the dns zone name
   * @param nodeName the dns node name
   * @return {@code true} if the dns node was removed; {@code false} if dns node didn't exist
   */
  @ApiOperation(
      value = "Delete dns node.",
      nickname = "deleteDnsNode",
      notes = "Delete dns node.",
      tags = {"name-server-management"})
  @ApiResponses(value = {
      @ApiResponse(code = 200,
          message = "True if the dns node was removed; false if dns node didn't exist.",
          response = Boolean.class),
      @ApiResponse(code = 400, message = "Bad request.",
          response = RestApiException.class),
      @ApiResponse(code = 404, message = "DNS zone not found.",
          response = RestApiException.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = RestApiException.class)
  })
  @RequestMapping(
      value = "/api/dns/zones/{zoneName}/{nodeName}",
      produces = {"application/json"},
      method = RequestMethod.DELETE)
  ResponseEntity<Boolean> deleteDnsNode(
      @ApiParam(value = "The dns zone name.", required = true)
      @PathVariable("zoneName") String zoneName,

      @ApiParam(value = "The dns node name.", required = true)
      @PathVariable("nodeName") String nodeName);

  /**
   * Delete all dns nodes.
   *
   * @param zoneName  the zone name
   * @param nodeNames the node names (if the list is empty, all dns nodes will be deleted)
   * @return void response entity
   */
  @ApiOperation(
      value = "Delete all dns nodes.",
      nickname = "deleteAllDnsNodes",
      notes = "Delete all dns nodes.",
      tags = {"name-server-management"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "All dns nodes were deleted."),
      @ApiResponse(code = 400, message = "Bad request.",
          response = RestApiException.class),
      @ApiResponse(code = 404, message = "DNS zone not found.",
          response = RestApiException.class),
      @ApiResponse(code = 500, message = "Fatal server error.",
          response = RestApiException.class)
  })
  @RequestMapping(
      value = "/api/dns/zones/{zoneName}/nodes/all",
      produces = {"application/json"},
      method = RequestMethod.DELETE)
  ResponseEntity<Void> deleteAllDnsNodes(
      @ApiParam(value = "The dns zone name.", required = true)
      @PathVariable("zoneName") String zoneName,

      @ApiParam(value = "The dns node names.")
      @RequestParam("nodeNames") List<String> nodeNames);

}
