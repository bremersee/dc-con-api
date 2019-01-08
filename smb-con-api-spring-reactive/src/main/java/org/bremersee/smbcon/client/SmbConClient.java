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

package org.bremersee.smbcon.client;

import org.bremersee.smbcon.api.SmbConControllerApi;
import org.bremersee.smbcon.model.BooleanWrapper;
import org.bremersee.smbcon.model.DnsEntry;
import org.bremersee.smbcon.model.DnsRecordRequest;
import org.bremersee.smbcon.model.DnsRecordUpdateRequest;
import org.bremersee.smbcon.model.DnsZone;
import org.bremersee.smbcon.model.DnsZoneCreateRequest;
import org.bremersee.smbcon.model.Info;
import org.bremersee.smbcon.model.Names;
import org.bremersee.smbcon.model.Password;
import org.bremersee.smbcon.model.SambaGroup;
import org.bremersee.smbcon.model.SambaGroupItem;
import org.bremersee.smbcon.model.SambaUser;
import org.bremersee.smbcon.model.SambaUserAddRequest;
import org.bremersee.web.ErrorDetectors;
import org.bremersee.web.reactive.function.client.DefaultWebClientErrorDecoder;
import org.bremersee.web.reactive.function.client.WebClientErrorDecoder;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Reactive web client of the samba connector rest service.
 *
 * @author Christian Bremer
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class SmbConClient implements SmbConControllerApi {

  private final WebClient webClient;

  private final WebClientErrorDecoder<? extends Throwable> webClientErrorDecoder;

  /**
   * Instantiates a new samba connector client.
   *
   * @param webClient the web client
   */
  public SmbConClient(final WebClient webClient) {
    this(webClient, null);
  }

  /**
   * Instantiates a new samba connector client.
   *
   * @param webClient             the web client
   * @param webClientErrorDecoder the web client error decoder
   */
  public SmbConClient(
      final WebClient webClient,
      final WebClientErrorDecoder<? extends Throwable> webClientErrorDecoder) {
    this.webClient = webClient;
    this.webClientErrorDecoder = webClientErrorDecoder != null
        ? webClientErrorDecoder
        : new DefaultWebClientErrorDecoder();
  }

  @Override
  public Mono<Info> getInfo() {
    return webClient
        .get()
        .uri("/api/info")
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, webClientErrorDecoder)
        .bodyToMono(Info.class);
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
  public Mono<Void> createOrDeleteDnsRecord(final String action, final DnsRecordRequest request) {
    final String validatedAction = "DELETE".equalsIgnoreCase(action) ? "DELETE" : "CREATE";
    return webClient
        .post()
        .uri("/api/dns/zones/records?action={action}", validatedAction)
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
  public Flux<DnsEntry> getDnsRecords(final String zoneName) {
    return webClient
        .get()
        .uri("/api/dns/zones/records?zoneName={zoneName}", zoneName)
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
  public Mono<SambaGroup> addGroup(final SambaGroup group) {
    return webClient
        .put()
        .uri("/api/groups")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromObject(group))
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, webClientErrorDecoder)
        .bodyToMono(SambaGroup.class);
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
  public Mono<SambaGroup> getGroupByName(final String groupName) {
    return webClient
        .get()
        .uri("/api/groups/{groupName}", groupName)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, webClientErrorDecoder)
        .bodyToMono(SambaGroup.class);
  }

  @Override
  public Flux<SambaGroupItem> getGroups() {
    return webClient
        .get()
        .uri("/api/groups")
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, webClientErrorDecoder)
        .bodyToFlux(SambaGroupItem.class);
  }

  @Override
  public Mono<SambaGroup> updateGroupMembers(final String groupName, final Names members) {
    return webClient
        .put()
        .uri("/api/groups/{groupName}/members", groupName)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromObject(members))
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, webClientErrorDecoder)
        .bodyToMono(SambaGroup.class);
  }

  @Override
  public Mono<SambaUser> addUser(final SambaUserAddRequest sambaUser) {
    return webClient
        .post()
        .uri("/api/users")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromObject(sambaUser))
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, webClientErrorDecoder)
        .bodyToMono(SambaUser.class);
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
  public Mono<SambaUser> getUser(final String userName) {
    return webClient
        .get()
        .uri("/api/users/{userName}", userName)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, webClientErrorDecoder)
        .bodyToMono(SambaUser.class);
  }

  @Override
  public Mono<SambaUser> updateUser(final String userName, final SambaUser sambaUser) {
    return webClient
        .put()
        .uri("/api/users/{userName}", userName)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromObject(sambaUser))
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, webClientErrorDecoder)
        .bodyToMono(SambaUser.class);
  }

  @Override
  public Mono<SambaUser> updateUserGroups(final String userName, final Names groups) {
    return webClient
        .put()
        .uri("/api/users/{userName}/groups", userName)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromObject(groups))
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, webClientErrorDecoder)
        .bodyToMono(SambaUser.class);
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
  public Mono<BooleanWrapper> userExists(final String userName) {
    return webClient
        .get()
        .uri("/api/users/{userName}/f/exists", userName)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, webClientErrorDecoder)
        .bodyToMono(BooleanWrapper.class);
  }
}