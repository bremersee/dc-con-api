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
 * The sid test.
 *
 * @author Christian Bremer
 */
class SidTest {

  /**
   * Gets value.
   */
  @Test
  void getValue() {
    String value = UUID.randomUUID().toString();
    Sid model = new Sid();
    model.setValue(value);
    assertEquals(value, model.getValue());

    model = Sid.builder().value(value).build();
    assertEquals(value, model.getValue());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().value(value).build());

    assertTrue(model.toString().contains(value));
  }

  /**
   * Gets system entity.
   */
  @Test
  void getSystemEntity() {
    Sid model = new Sid();
    model.setSystemEntity(true);
    assertEquals(true, model.getSystemEntity());

    model = Sid.builder().systemEntity(true).build();
    assertEquals(true, model.getSystemEntity());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().systemEntity(true).build());

    assertTrue(model.toString().contains("true"));
  }
}