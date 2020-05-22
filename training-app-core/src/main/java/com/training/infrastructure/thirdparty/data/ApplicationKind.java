
package com.training.infrastructure.thirdparty.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for applicationKind.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="applicationKind">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NEW_ISSUANCE"/>
 *     &lt;enumeration value="RENEWAL"/>
 *     &lt;enumeration value="LOST"/>
 *     &lt;enumeration value="DAMAGED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "applicationKind", namespace = "test")
@XmlEnum
public enum ApplicationKind {

    NEW_ISSUANCE,
    RENEWAL,
    LOST,
    DAMAGED;

    public String value() {
        return name();
    }

    public static ApplicationKind fromValue(String v) {
        return valueOf(v);
    }

}
