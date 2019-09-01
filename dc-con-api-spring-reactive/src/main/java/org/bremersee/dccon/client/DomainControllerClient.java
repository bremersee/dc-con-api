/*
 * Copyright 2018 the original author or authors.
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

package org.bremersee.dccon.client;

import java.util.List;
import org.bremersee.dccon.api.DomainControllerApi;
import org.bremersee.dccon.model.AddDhcpLeaseParameter;
import org.bremersee.dccon.model.DhcpLease;
import org.bremersee.dccon.model.DnsEntry;
import org.bremersee.dccon.model.DnsRecordRequest;
import org.bremersee.dccon.model.DnsRecordUpdateRequest;
import org.bremersee.dccon.model.DnsZone;
import org.bremersee.dccon.model.DnsZoneCreateRequest;
import org.bremersee.dccon.model.DomainGroup;
import org.bremersee.dccon.model.DomainGroupItem;
import org.bremersee.dccon.model.DomainUser;
import org.bremersee.dccon.model.Password;
import org.bremersee.web.ErrorDetectors;
import org.bremersee.web.reactive.function.client.DefaultWebClientErrorDecoder;
import org.bremersee.web.reactive.function.client.WebClientErrorDecoder;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Reactive web client of the domain connector rest service.
 *
 * @author Christian Bremer
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class DomainControllerClient implements DomainControllerApi {

  private final WebClient webClient;

  private final WebClientErrorDecoder<? extends Throwable> webClientErrorDecoder;

  /**
   * Instantiates a new domain connector client.
   *
   * @param webClient the web client
   */
  public DomainControllerClient(final WebClient webClient) {
    this(webClient, null);
  }

  /**
   * Instantiates a new domain connector client.
   *
   * @param webClient             the web client
   * @param webClientErrorDecoder the web client error decoder
   */
  public DomainControllerClient(
      final WebClient webClient,
      final WebClientErrorDecoder<? extends Throwable> webClientErrorDecoder) {
    this.webClient = webClient;
    this.webClientErrorDecoder = webClientErrorDecoder != null
        ? webClientErrorDecoder
        : new DefaultWebClientErrorDecoder();
  }

  @Override
  public Mono<Void> createDnsZone(final DnsZoneCreateRequest request) {
    return webClient
        .post()
        .uri("/api/dns/zones")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromObject(request))
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, webClientErrorDecoder)
        .bodyToMono(Void.class);
  }

  @Override
  public Mono<Void> createOrDeleteDnsRecord(
      final String action,
      final Boolean reverse,
      final DnsRecordRequest request) {
    final String validatedAction = "DELETE".equalsIgnoreCase(action) ? "DELETE" : "CREATE";
    return webClient
        .post()
        .uri("/api/dns/zones/records?action={action}&reverse={reverse}",
            validatedAction, !Boolean.FALSE.equals(reverse))
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromObject(request))
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, webClientErrorDecoder)
        .bodyToMono(Void.class);
  }

  @Override
  public Mono<Void> deleteDnsZone(final String zoneName) {
    return webClient
        .delete()
        .uri("/api/dns/zones?zoneName={zoneName}", zoneName)
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, webClientErrorDecoder)
        .bodyToMono(Void.class);
  }

  @Override
  public Flux<DhcpLease> getDhcpLeases(final Boolean all, final String sort) {
    final String sortOrder = StringUtils.hasText(sort) ? sort : DhcpLease.SORT_ORDER_BEGIN_HOSTNAME;
    return webClient
        .get()
        .uri("/api/dhcp-leases?all={all}&sort={sort}",
            Boolean.TRUE.equals(all),
            sortOrder)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, webClientErrorDecoder)
        .bodyToFlux(DhcpLease.class);
  }

  @Override
  public Flux<DnsEntry> getDnsRecords(
      final String zoneName,
      final Boolean correlations,
      final String leases) {
    return webClient
        .get()
        .uri("/api/dns/zones/records"
                + "?zoneName={zoneName}"
                + "&correlations={correlations}"
                + "&leases={leases}",
            zoneName,
            !Boolean.FALSE.equals(correlations),
            AddDhcpLeaseParameter.fromValue(leases, AddDhcpLeaseParameter.ACTIVE))
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, webClientErrorDecoder)
        .bodyToFlux(DnsEntry.class);
  }

  @Override
  public Flux<DnsZone> getDnsZones() {
    return webClient
        .get()
        .uri("/api/dns/zones")
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, webClientErrorDecoder)
        .bodyToFlux(DnsZone.class);
  }

  @Override
  public Mono<Void> updateDnsRecord(final DnsRecordUpdateRequest request) {
    return webClient
        .put()
        .uri("/api/dns/zones/records")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromObject(request))
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, webClientErrorDecoder)
        .bodyToMono(Void.class);
  }

  @Override
  public Mono<DomainGroup> addGroup(final DomainGroup group) {
    return webClient
        .put()
        .uri("/api/groups")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromObject(group))
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, webClientErrorDecoder)
        .bodyToMono(DomainGroup.class);
  }

  @Override
  public Mono<Void> deleteGroup(final String groupName) {
    return webClient
        .delete()
        .uri("/api/groups/{groupName}", groupName)
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, webClientErrorDecoder)
        .bodyToMono(Void.class);
  }

  @Override
  public Mono<DomainGroup> getGroupByName(final String groupName) {
    return webClient
        .get()
        .uri("/api/groups/{groupName}", groupName)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, webClientErrorDecoder)
        .bodyToMono(DomainGroup.class);
  }

  @Override
  public Flux<DomainGroupItem> getGroups() {
    return webClient
        .get()
        .uri("/api/groups")
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, webClientErrorDecoder)
        .bodyToFlux(DomainGroupItem.class);
  }

  @Override
  public Mono<DomainGroup> updateGroupMembers(final String groupName, final List<String> members) {
    return webClient
        .put()
        .uri("/api/groups/{groupName}/members", groupName)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromObject(members))
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, webClientErrorDecoder)
        .bodyToMono(DomainGroup.class);
  }

  @Override
  public Mono<DomainUser> addUser(final DomainUser domainUser) {
    return webClient
        .post()
        .uri("/api/users")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromObject(domainUser))
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, webClientErrorDecoder)
        .bodyToMono(DomainUser.class);
  }

  @Override
  public Mono<Void> deleteUser(final String userName) {
    return webClient
        .delete()
        .uri("/api/users/{userName}", userName)
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, webClientErrorDecoder)
        .bodyToMono(Void.class);
  }

  @Override
  public Flux<DomainUser> getUsers() {
    return webClient
        .get()
        .uri("/api/users")
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, webClientErrorDecoder)
        .bodyToFlux(DomainUser.class);
  }

  @Override
  public Mono<DomainUser> getUser(final String userName) {
    return webClient
        .get()
        .uri("/api/users/{userName}", userName)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, webClientErrorDecoder)
        .bodyToMono(DomainUser.class);
  }

  @Override
  public Mono<DomainUser> updateUser(
      final String userName,
      final Boolean updateGroups,
      final DomainUser domainUser) {
    return webClient
        .put()
        .uri("/api/users/{userName}?updateGroups={updateGroups}",
            userName,
            Boolean.TRUE.equals(updateGroups))
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromObject(domainUser))
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, webClientErrorDecoder)
        .bodyToMono(DomainUser.class);
  }

  @Override
  public Mono<DomainUser> updateUserGroups(final String userName, final List<String> groups) {
    return webClient
        .put()
        .uri("/api/users/{userName}/groups", userName)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromObject(groups))
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, webClientErrorDecoder)
        .bodyToMono(DomainUser.class);
  }

  @Override
  public Mono<Void> updateUserPassword(final String userName, final Password newPassword) {
    return webClient
        .put()
        .uri("/api/users/{userName}/password", userName)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromObject(newPassword))
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, webClientErrorDecoder)
        .bodyToMono(Void.class);
  }

  @Override
  public Mono<Boolean> userExists(final String userName) {
    return webClient
        .get()
        .uri("/api/users/{userName}/f/exists", userName)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, webClientErrorDecoder)
        .bodyToMono(Boolean.class);
  }

}
