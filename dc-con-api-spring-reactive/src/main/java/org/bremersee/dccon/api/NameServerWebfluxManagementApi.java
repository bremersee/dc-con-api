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
import java.util.List;
import jakarta.validation.Valid;
import org.bremersee.dccon.model.DhcpLease;
import org.bremersee.dccon.model.DhcpLeasePage;
import org.bremersee.dccon.model.DnsNode;
import org.bremersee.dccon.model.DnsNodePage;
import org.bremersee.dccon.model.DnsZone;
import org.bremersee.dccon.model.DnsZonePage;
import org.bremersee.dccon.model.UnknownFilter;
import org.bremersee.exception.model.RestApiException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;

/**
 * The name server management api.
 *
 * @author Christian Bremer
 */
@Tag(name = "name-server-management-controller", description = "Name server management API.")
public interface NameServerWebfluxManagementApi {

  /**
   * Query dns nodes.
   *
   * @param pageable the pageable
   * @param query the query, can be a host name, an IP or a MAC address
   * @param unknownFilter the unknown filter
   * @return found dns nodes
   */
  @Operation(
      summary = "Simple dns query.",
      operationId = "query",
      tags = {"name-server-management-controller"},
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
              example = "name,asc",
              in = ParameterIn.QUERY,
              array = @ArraySchema(schema = @Schema(type = "string")))
      }
  )
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "The found dns nodes.",
          content = @Content(
              array = @ArraySchema(
                  schema = @Schema(implementation = DnsNode.class)))),
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
      value = "/api/dns",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Mono<DnsNodePage> query(

      @Parameter(hidden = true)
      @PageableDefault(size = Integer.MAX_VALUE, sort = "name") Pageable pageable,

      @Parameter(description = "The query, can be a host name, an IP or a MAC address.")
      @RequestParam(name = "q") String query,

      @Parameter(description = "The unknown filter.")
      @RequestParam(name = "filter", defaultValue = "NO_UNKNOWN") UnknownFilter unknownFilter);

  /**
   * Gets dhcp leases.
   *
   * @param pageable the pageable
   * @param all if {@code true}, expired leases will also be returned, otherwise only active
   *     ones (default is {@code false})
   * @return the dhcp leases
   */
  @Operation(
      summary = "Get dhcp leases.",
      operationId = "getDhcpLeases",
      tags = {"name-server-management-controller"},
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
              example = "name,asc",
              in = ParameterIn.QUERY,
              array = @ArraySchema(schema = @Schema(type = "string")))
      }
  )
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "A list with dhcp leases.",
          content = @Content(
              array = @ArraySchema(
                  schema = @Schema(implementation = DhcpLease.class)))),
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
  @RequestMapping(value = "/api/dns/dhcp-leases",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Mono<DhcpLeasePage> getDhcpLeases(
      @Parameter(hidden = true)
      @PageableDefault(size = Integer.MAX_VALUE, sort = "ip") Pageable pageable,

      @Parameter(description = "'true' returns also expired leases, 'false' only active ones.")
      @RequestParam(value = "all", defaultValue = "false") Boolean all);


  /**
   * Get dns zones.
   *
   * @param pageable the pageable
   * @param reverseOnly the reverse only
   * @return the dns zones
   */
  @Operation(
      summary = "Get all dns zones.",
      operationId = "getDnsZones",
      tags = {"name-server-management-controller"},
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
              example = "name,asc",
              in = ParameterIn.QUERY,
              array = @ArraySchema(schema = @Schema(type = "string")))
      }
  )
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "A list with dns zones.",
          content = @Content(
              array = @ArraySchema(
                  schema = @Schema(implementation = DnsZone.class)))),
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
      value = "/api/dns/zones",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Mono<DnsZonePage> getDnsZones(

      @Parameter(hidden = true)
      @PageableDefault(size = Integer.MAX_VALUE, sort = "name") Pageable pageable,

      @Parameter(description = "'true' returns only reverse zone, 'false' only normal zones, "
          + "absence of the parameter both zones.")
      @RequestParam(value = "reverse", required = false) Boolean reverseOnly);

  /**
   * Add dns zone.
   *
   * @param request the request
   * @return the added dns zone
   */
  @Operation(
      summary = "Add dns zone.",
      operationId = "addDnsZone",
      tags = {"name-server-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "The zone was successfully added.",
          content = @Content(
              schema = @Schema(
                  implementation = DnsZone.class))),
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
      value = "/api/dns/zones",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  Mono<DnsZone> addDnsZone(
      @Parameter(description = "The dns zone to add.", required = true)
      @Valid @RequestBody DnsZone request);

  /**
   * Delete dns zone.
   *
   * @param zoneName the zone name
   * @return {@code true} if the dns zone was deleted, otherwise {@code false}
   */
  @Operation(
      summary = "Delete dns zone.",
      operationId = "deleteDnsZone",
      tags = {"name-server-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "True if the dns zone was successfully deleted, otherwise false.",
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
  @RequestMapping(
      value = "/api/dns/zones/{zoneName}",
      produces = {"application/json"},
      method = RequestMethod.DELETE)
  Mono<Boolean> deleteDnsZone(
      @Parameter(description = "The dns zone name.", required = true)
      @PathVariable(value = "zoneName") String zoneName);


  /**
   * Get dns nodes.
   *
   * @param zoneName the zone name
   * @param pageable the pageable
   * @param query the query
   * @param unknownFilter the unknown filter
   * @return the dns nodes
   */
  @Operation(
      summary = "Get all dns nodes of a zone.",
      operationId = "getDnsNodes",
      tags = {"name-server-management-controller"},
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
              example = "name,asc",
              in = ParameterIn.QUERY,
              array = @ArraySchema(schema = @Schema(type = "string")))
      }
  )
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "A list with dns nodes.",
          content = @Content(
              array = @ArraySchema(
                  schema = @Schema(implementation = DnsNode.class)))),
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
  @RequestMapping(value = "/api/dns/zones/{zoneName}",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Mono<DnsNodePage> getDnsNodes(
      @Parameter(description = "The dns zone name.", required = true)
      @PathVariable(value = "zoneName") String zoneName,

      @Parameter(hidden = true)
      @PageableDefault(size = Integer.MAX_VALUE, sort = "name") Pageable pageable,

      @Parameter(description = "A query.")
      @RequestParam(name = "q", required = false) String query,

      @Parameter(description = "The unknown filter.")
      @RequestParam(name = "filter", defaultValue = "NO_UNKNOWN") UnknownFilter unknownFilter);

  /**
   * Save dns node.
   *
   * @param zoneName the dns zone name
   * @param dnsNode the dns node
   * @return the saved dns node
   */
  @Operation(
      summary = "Save dns node.",
      operationId = "saveDnsNode",
      tags = {"name-server-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "The dns node was successfully saved.",
          content = @Content(
              schema = @Schema(
                  implementation = DnsNode.class))),
      @ApiResponse(
          responseCode = "204",
          description = "The dns node was deleted due to no records."),
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
      value = "/api/dns/zones/{zoneName}",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  Mono<DnsNode> saveDnsNode(
      @Parameter(description = "The dns zone name.", required = true)
      @PathVariable("zoneName") String zoneName,

      @Parameter(description = "The dns node to save.", required = true)
      @Valid @RequestBody DnsNode dnsNode);

  /**
   * Get dns node.
   *
   * @param zoneName the dns zone name
   * @param nodeName the dns node name
   * @param unknownFilter the unknown filter
   * @return the dns node
   */
  @Operation(
      summary = "Get dns node.",
      operationId = "getDnsNode",
      tags = {"name-server-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "The dns node.",
          content = @Content(
              schema = @Schema(
                  implementation = DnsNode.class))),
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
      value = "/api/dns/zones/{zoneName}/{nodeName}",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Mono<DnsNode> getDnsNode(
      @Parameter(description = "The dns zone name.", required = true)
      @PathVariable("zoneName") String zoneName,

      @Parameter(description = "The dns node name.", required = true)
      @PathVariable("nodeName") String nodeName,

      @Parameter(description = "The unknown filter.")
      @RequestParam(name = "filter", defaultValue = "NO_UNKNOWN") UnknownFilter unknownFilter);

  /**
   * Delete dns node.
   *
   * @param zoneName the dns zone name
   * @param nodeName the dns node name
   * @return {@code true} if the dns node was removed; {@code false} if dns node didn't exist
   */
  @Operation(
      summary = "Delete dns node.",
      operationId = "deleteDnsNode",
      tags = {"name-server-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "True if the dns node was removed; false if dns node didn't exist.",
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
          responseCode = "404",
          description = "DNS zone not found.",
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
      value = "/api/dns/zones/{zoneName}/{nodeName}",
      produces = {"application/json"},
      method = RequestMethod.DELETE)
  Mono<Boolean> deleteDnsNode(
      @Parameter(description = "The dns zone name.", required = true)
      @PathVariable("zoneName") String zoneName,

      @Parameter(description = "The dns node name.", required = true)
      @PathVariable("nodeName") String nodeName);

  /**
   * Delete all dns nodes.
   *
   * @param zoneName the zone name
   * @param nodeNames the node names (if the list is empty, all dns nodes will be deleted)
   * @return void response entity
   */
  @Operation(
      summary = "Delete all dns nodes.",
      operationId = "deleteAllDnsNodes",
      tags = {"name-server-management-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "All dns nodes were deleted."),
      @ApiResponse(
          responseCode = "400",
          description = "Bad request.",
          content = @Content(
              schema = @Schema(
                  implementation = RestApiException.class))),
      @ApiResponse(
          responseCode = "404",
          description = "DNS zone not found.",
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
      value = "/api/dns/zones/{zoneName}/nodes/all",
      produces = {"application/json"},
      method = RequestMethod.DELETE)
  Mono<Void> deleteAllDnsNodes(
      @Parameter(description = "The dns zone name.", required = true)
      @PathVariable("zoneName") String zoneName,

      @Parameter(description = "The dns node names.")
      @RequestParam(value = "nodeNames", required = false) List<String> nodeNames);

}
