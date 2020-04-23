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

/**
 * The avatar default.
 *
 * @author Christian Bremer
 */
public enum AvatarDefault {

  /**
   * Do not load any image if none is associated with the email hash, instead return an HTTP 404
   * (File Not Found) response.
   */
  NOT_FOUND("404"),

  /**
   * Mystery-Person. A simple, cartoon-style silhouetted outline of a person (does not vary by email
   * hash).
   */
  MP("mp"),

  /**
   * A geometric pattern based on an email hash.
   */
  IDENTICON("identicon"),

  /**
   * A generated 'monster' with different colors, faces, etc.
   */
  MONSTERID("monsterid"),

  /**
   * Generated faces with differing features and backgrounds.
   */
  WAVATAR("wavatar"),

  /**
   * Awesome generated, 8-bit arcade-style pixelated faces.
   */
  RETRO("retro"),

  /**
   * A generated robot with different colors, faces, etc.
   */
  ROBOHASH("robohash"),

  /**
   * A transparent PNG image (border added to HTML below for demonstration purposes).
   */
  BLANK("blank");

  private String value;

  AvatarDefault(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }

  /**
   * From value avatar default.
   *
   * @param value the value
   * @param defaultAvatarDefault the default avatar default
   * @return the avatar default
   */
  public static AvatarDefault fromValue(String value, AvatarDefault defaultAvatarDefault) {
    for (AvatarDefault avatarDefault : AvatarDefault.values()) {
      if (avatarDefault.value.equalsIgnoreCase(value)
          || avatarDefault.name().equalsIgnoreCase(value)) {
        return avatarDefault;
      }
    }
    return defaultAvatarDefault;
  }

}
