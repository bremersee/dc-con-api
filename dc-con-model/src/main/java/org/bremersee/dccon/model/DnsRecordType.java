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

package org.bremersee.dccon.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * DNS Record Types.
 */
@SuppressWarnings("unused")
public enum DnsRecordType {

  /**
   * A dns record type.
   */
  A("A"),

  /**
   * Aaaa dns record type.
   */
  AAAA("AAAA"),

  /**
   * Ptr dns record type.
   */
  PTR("PTR"),

  /**
   * Cname dns record type.
   */
  CNAME("CNAME"),

  /**
   * Ns dns record type.
   */
  NS("NS"),

  /**
   * Mx dns record type.
   */
  MX("MX"),

  /**
   * Srv dns record type.
   */
  SRV("SRV"),

  /**
   * Txt dns record type.
   */
  TXT("TXT"),

  /**
   * Soa dns record type.
   */
  SOA("SOA"),

  /**
   * Afsdb dns record type.
   */
  AFSDB("AFSDB"),

  /**
   * Apl dns record type.
   */
  APL("APL"),

  /**
   * Caa dns record type.
   */
  CAA("CAA"),

  /**
   * Cdnskey dns record type.
   */
  CDNSKEY("CDNSKEY"),

  /**
   * Cds dns record type.
   */
  CDS("CDS"),

  /**
   * Cert dns record type.
   */
  CERT("CERT"),

  /**
   * Dhcid dns record type.
   */
  DHCID("DHCID"),

  /**
   * Dlv dns record type.
   */
  DLV("DLV"),

  /**
   * Dname dns record type.
   */
  DNAME("DNAME"),

  /**
   * Dnskey dns record type.
   */
  DNSKEY("DNSKEY"),

  /**
   * Ds dns record type.
   */
  DS("DS"),

  /**
   * Hip dns record type.
   */
  HIP("HIP"),

  /**
   * Ipseckey dns record type.
   */
  IPSECKEY("IPSECKEY"),

  /**
   * Key dns record type.
   */
  KEY("KEY"),

  /**
   * Kx dns record type.
   */
  KX("KX"),

  /**
   * Loc dns record type.
   */
  LOC("LOC"),

  /**
   * Naptr dns record type.
   */
  NAPTR("NAPTR"),

  /**
   * Nsec dns record type.
   */
  NSEC("NSEC"),

  /**
   * Nsec 3 dns record type.
   */
  NSEC3("NSEC3"),

  /**
   * Nsec 3 param dns record type.
   */
  NSEC3PARAM("NSEC3PARAM"),

  /**
   * Openpgpkey dns record type.
   */
  OPENPGPKEY("OPENPGPKEY"),

  /**
   * Rrsig dns record type.
   */
  RRSIG("RRSIG"),

  /**
   * Rp dns record type.
   */
  RP("RP"),

  /**
   * Sig dns record type.
   */
  SIG("SIG"),

  /**
   * Sshfp dns record type.
   */
  SSHFP("SSHFP"),

  /**
   * Ta dns record type.
   */
  TA("TA"),

  /**
   * Tkey dns record type.
   */
  TKEY("TKEY"),

  /**
   * Tlsa dns record type.
   */
  TLSA("TLSA"),

  /**
   * Tsig dns record type.
   */
  TSIG("TSIG"),

  /**
   * Uri dns record type.
   */
  URI("URI");

  private String value;

  DnsRecordType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  /**
   * From dns record type.
   *
   * @param text the text
   * @return the dns record type
   */
  @JsonCreator
  public static DnsRecordType fromValue(String text) {
    for (DnsRecordType b : DnsRecordType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

