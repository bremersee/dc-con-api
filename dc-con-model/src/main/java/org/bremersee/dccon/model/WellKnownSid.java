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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The well known SIDs. See <a href="https://support.microsoft.com/en-us/help/243330/well-known-security-identifiers-in-windows-operating-systems">well-known-security-identifiers-in-windows-operating-systems</a>.
 *
 * @author Christian Bremer
 */
public enum WellKnownSid {

  /**
   * An unknown sid with a sid suffix smaller than 1000.
   */
  UNKNOWN("Unknown", 0),

  /**
   * A user account for the system administrator. By default, it is the only user account that is
   * given full control over the system.
   */
  ADMINISTRATOR("Administrator", 500),

  /**
   * A user account for people who do not have individual accounts. This user account does not
   * require a password. By default, the Guest account is disabled.
   */
  GUEST("Guest", 501),

  /**
   * A service account that is used by the Key Distribution Center (KDC) service.
   */
  KRBTGT("KRBTGT", 502),

  /**
   * A global group whose members are authorized to administer the domain. By default, the Domain
   * Admins group is a member of the Administrators group on all computers that have joined a
   * domain, including the domain controllers. Domain Admins is the default owner of any object that
   * is created by any member of the group.
   */
  DOMAIN_ADMINS("Domain Admins", 512),

  /**
   * A global group that, by default, includes all user accounts in a domain. When you create a user
   * account in a domain, it is added to this group by default.
   */
  DOMAIN_USERS("Domain Users", 513),

  /**
   * A global group that, by default, has only one member, the domain's built-in Guest account.
   */
  DOMAIN_GUESTS("Domain Guests", 514),

  /**
   * A global group that includes all clients and servers that have joined the domain.
   */
  DOMAIN_COMPUTERS("Domain Computers", 515),

  /**
   * A global group that includes all domain controllers in the domain. New domain controllers are
   * added to this group by default.
   */
  DOMAIN_CONTROLLERS("Domain Controllers", 516),

  /**
   * A global group that includes all computers that are running an enterprise certification
   * authority. Cert Publishers are authorized to publish certificates for User objects in Active
   * Directory.
   */
  CERT_PUBLISHERS("Cert Publishers", 517),

  /**
   * A universal group in a native-mode domain; a global group in a mixed-mode domain. The group is
   * authorized to make schema changes in Active Directory. By default, the only member of the group
   * is the Administrator account for the forest root domain.
   */
  SCHEMA_ADMINS("Schema Admins", 518),

  /**
   * A universal group in a native-mode domain; a global group in a mixed-mode domain. The group is
   * authorized to make forest-wide changes in Active Directory, such as adding child domains. By
   * default, the only member of the group is the Administrator account for the forest root domain.
   */
  ENTERPRISE_ADMINS("Enterprise Admins", 519),

  /**
   * A global group that is authorized to create new Group Policy objects in Active Directory. By
   * default, the only member of the group is Administrator.
   */
  GROUP_POLICY_CREATOR_OWNERS("Group Policy Creator Owners", 520),

  /**
   * A security group. The intention for this group is to have delegated write access on the
   * msdsKeyCredentialLink attribute only. The group is intended for use in scenarios where trusted
   * external authorities (for example, Active Directory Federated Services) are responsible for
   * modifying this attribute. Only trusted administrators should be made a member of this group.
   */
  KEY_ADMINS("Key Admins", 526),

  /**
   * A security group. The intention for this group is to have delegated write access on the
   * msdsKeyCredentialLink attribute only. The group is intended for use in scenarios where trusted
   * external authorities (for example, Active Directory Federated Services) are responsible for
   * modifying this attribute. Only trusted administrators should be made a member of this group.
   */
  ENTERPRISE_KEY_ADMINS("Enterprise Key Admins", 527),

  /**
   * A domain local group. By default, this group has no members. Servers in this group have Read
   * Account Restrictions and Read Logon Information access to User objects in the Active Directory
   * domain local group.
   */
  RAS_AND_IAS_SERVERS("RAS and IAS Servers", 553),

  /**
   * A Universal group. Members of this group are Read-Only Domain Controllers in the enterprise.
   */
  ENTERPRISE_READ_ONLY_DOMAIN_CONTROLLERS("Enterprise Read-only Domain Controllers", 498),

  /**
   * A Global group. Members of this group are Read-Only Domain Controllers in the domain.
   */
  READ_ONLY_DOMAIN_CONTROLLERS("Read-only Domain Controllers", 521),

  /**
   * A Domain Local group. Members in this group can have their passwords replicated to all
   * read-only domain controllers in the domain.
   */
  ALLOWED_RODC_PASSWORD_REPLICATION_GROUP("Allowed RODC Password Replication Group", 571),

  /**
   * A Domain Local group. Members in this group cannot have their passwords replicated to any
   * read-only domain controllers in the domain.
   */
  DENIED_RODC_PASSWORD_REPLICATION_GROUP("Denied RODC Password Replication Group", 572);

  private String name;

  private Integer value;

  WellKnownSid(String name, Integer value) {
    this.name = name;
    this.value = value;
  }

  public String getName() {
    return name;
  }

  public Integer getValue() {
    return value;
  }

  @Override
  public String toString() {
    return name;
  }

  @JsonValue
  public String toValue() {
    return String.valueOf(value);
  }

  /**
   * From value sid.
   *
   * @param value the value
   * @return the well known sid
   */
  @SuppressWarnings("unused")
  @JsonCreator
  public static WellKnownSid fromValue(final String value) {
    if (value == null) {
      return null;
    }
    final String sidSuffix;
    int index = value.lastIndexOf('-');
    if (index > -1) {
      sidSuffix = value.substring(index + 1);
    } else {
      sidSuffix = value;
    }
    for (WellKnownSid sid : WellKnownSid.values()) {
      if (sidSuffix.equalsIgnoreCase(sid.toValue())
          || value.equalsIgnoreCase(sid.toString())
          || value.equalsIgnoreCase(sid.name())) {
        return sid;
      }
    }
    try {
      int sidValue = Integer.parseInt(sidSuffix);
      if (sidValue < 1000) {
        return UNKNOWN;
      }
    } catch (Exception ignored) {
      // ignored
    }
    return null;
  }
}
