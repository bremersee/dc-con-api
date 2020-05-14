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

package org.bremersee.dccon.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;
import org.junit.jupiter.api.Test;

/**
 * The dns pair test.
 *
 * @author Christian Bremer
 */
class DnsPairTest {

  /**
   * Gets zone name.
   */
  @Test
  void getZoneName() {
    String value = UUID.randomUUID().toString();
    DnsPair model = new DnsPair();
    model.setZoneName(value);
    assertEquals(value, model.getZoneName());

    model = DnsPair.builder().zoneName(value).build();
    assertEquals(value, model.getZoneName());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().zoneName(value).build());

    assertTrue(model.toString().contains(value));
  }

  /**
   * Gets node.
   */
  @Test
  void getNode() {
    DnsNode value = DnsNode.builder()
        .distinguishedName(UUID.randomUUID().toString())
        .name(UUID.randomUUID().toString())
        .build();
    DnsPair model = new DnsPair();
    model.setNode(value);
    assertEquals(value, model.getNode());

    model = DnsPair.builder().node(value).build();
    assertEquals(value, model.getNode());

    assertEquals(model, model);
    assertEquals(model, model.toBuilder().node(value).build());

    assertTrue(model.toString().contains(value.toString()));
  }

  /**
   * Gets node exists.
   */
  @Test
  void getNodeExists() {
    DnsPair model = new DnsPair();
    model.setNodeExists(false);
    assertEquals(false, model.getNodeExists());

    model = DnsPair.builder().nodeExists(false).build();
    assertEquals(false, model.getNodeExists());

    assertEquals(model, model);
    assertEquals(model, model.toBuilder().nodeExists(false).build());

    assertTrue(model.toString().contains("false"));

    model.setNodeExists(true);
    assertEquals(true, model.getNodeExists());

    model = DnsPair.builder().nodeExists(true).build();
    assertEquals(true, model.getNodeExists());

    assertEquals(model, model);
    assertEquals(model, model.toBuilder().nodeExists(true).build());

    assertTrue(model.toString().contains("true"));

  }
}