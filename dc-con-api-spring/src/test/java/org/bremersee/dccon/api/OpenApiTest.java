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

package org.bremersee.dccon.api;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * The type Open api test.
 *
 * @author Christian Bremer
 */
@SpringBootTest(
    classes = {OpenApiTestConfiguration.class},
    webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(SoftAssertionsExtension.class)
class OpenApiTest {

  /**
   * The rest template builder.
   */
  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  @Autowired
  RestTemplateBuilder restTemplateBuilder;

  /**
   * The local port.
   */
  @LocalServerPort
  int port;

  /**
   * Test open api.
   *
   * @param softly the softly
   */
  @Test
  void testOpenApi(SoftAssertions softly) {
    RestTemplate restTemplate = restTemplateBuilder.build();
    ResponseEntity<String> response = restTemplate.getForEntity(
        "http://localhost:" + port + "/v3/api-docs.yaml",
        String.class);
    String actual = response.getBody();
    softly.assertThat(actual).isNotNull();
    System.out.println(actual);
  }

}
