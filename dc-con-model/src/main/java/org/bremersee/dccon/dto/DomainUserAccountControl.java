/*
 * Copyright 2024 the original author or authors.
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

package org.bremersee.dccon.dto;

import static java.util.Objects.isNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * The domain user's account control.
 *
 * @author Christian Bremer
 */
@Schema(description = "Domain user's account control.", type = "integer", format = "int32")
public class DomainUserAccountControl {

  /**
   * The bit map value of a disabled account.
   */
  static final int DISABLED_ACCOUNT = 1 << 1;

  /**
   * The bit map value of a normal account.
   */
  static final int NORMAL_ACCOUNT = 1 << 9;

  /**
   * The bit map value of a password that doesn't expire.
   */
  static final int DONT_EXPIRE_PASSWORD = 1 << 16;

  private int value;

  public DomainUserAccountControl() {
    this(null);
  }

  @JsonCreator
  public DomainUserAccountControl(Integer value) {
    if (isNull(value) || value <= 0) {
      this.value = NORMAL_ACCOUNT;
      setEnabled(true);
      setPasswordExpirationEnabled(false);
    } else {
      this.value = value;
    }
  }

  @Hidden
  @JsonValue
  public int getValue() {
    return value;
  }

  @Hidden
  @JsonIgnore
  public boolean isNormalAccount() {
    return (value & NORMAL_ACCOUNT) == NORMAL_ACCOUNT;
  }

  @Hidden
  @JsonIgnore
  public boolean isEnabled() {
    return (value & DISABLED_ACCOUNT) != DISABLED_ACCOUNT;
  }

  @Hidden
  @JsonIgnore
  public void setEnabled(boolean enabled) {
    if (enabled && !isEnabled()) {
      value = value - DISABLED_ACCOUNT;
    } else if (!enabled && isEnabled()) {
      value = value + DISABLED_ACCOUNT;
    }
  }

  @Hidden
  @JsonIgnore
  public boolean isPasswordExpirationEnabled() {
    return (value & DONT_EXPIRE_PASSWORD) != DONT_EXPIRE_PASSWORD;
  }

  @Hidden
  @JsonIgnore
  public void setPasswordExpirationEnabled(boolean enabled) {
    if (enabled && !isPasswordExpirationEnabled()) {
      value = value - DONT_EXPIRE_PASSWORD;
    } else if (!enabled && isPasswordExpirationEnabled()) {
      value = value + DONT_EXPIRE_PASSWORD;
    }
  }

}
