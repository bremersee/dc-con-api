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

import java.util.UUID;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * The avatar default test.
 *
 * @author Christian Bremer
 */
@ExtendWith(SoftAssertionsExtension.class)
class AvatarDefaultTest {

  /**
   * From value.
   *
   * @param softly the softly
   */
  @Test
  void fromValue(SoftAssertions softly) {
    for (AvatarDefault expected : AvatarDefault.values()) {
      AvatarDefault actual = AvatarDefault.fromValue(expected.toString(), null);
      assertEquals(expected, actual);
    }
    softly.assertThat(AvatarDefault.fromValue(UUID.randomUUID().toString(), null))
        .isNull();
    softly.assertThat(AvatarDefault.fromValue(UUID.randomUUID().toString(), AvatarDefault.MP))
        .isEqualTo(AvatarDefault.MP);
  }
}