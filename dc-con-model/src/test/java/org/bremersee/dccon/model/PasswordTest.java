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

package org.bremersee.dccon.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.Test;

/**
 * The password test.
 *
 * @author Christian Bremer
 */
class PasswordTest {

  /**
   * Gets value.
   */
  @Test
  void getValue() {
    String value = UUID.randomUUID().toString();
    Password model = new Password(value);
    assertEquals(value, model.getValue());
    model.setValue(value);
    assertEquals(value, model.getValue());

    model = Password.builder().value(value).build();
    assertEquals(value, model.getValue());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().value(value).build());

    assertFalse(model.toString().contains(value));
  }

  /**
   * Gets previous value.
   */
  @Test
  void getPreviousValue() {
    String value = UUID.randomUUID().toString();
    Password model = new Password();
    model.setPreviousValue(value);
    assertEquals(value, model.getPreviousValue());

    model = Password.builder().previousValue(value).build();
    assertEquals(value, model.getPreviousValue());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().previousValue(value).build());

    assertFalse(model.toString().contains(value));
  }
}