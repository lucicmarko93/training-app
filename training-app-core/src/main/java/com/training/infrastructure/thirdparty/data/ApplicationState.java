
package com.training.infrastructure.thirdparty.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for applicationState.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="applicationState">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="RECEIVED"/>
 *     &lt;enumeration value="CREATED"/>
 *     &lt;enumeration value="IN_PERSONALIZATION"/>
 *     &lt;enumeration value="REJECTED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "applicationState", namespace = "test")
@XmlEnum
public enum ApplicationState {

    RECEIVED,
    CREATED,
    IN_PERSONALIZATION,
    REJECTED;

    public String value() {
        return name();
    }

    public static ApplicationState fromValue(String v) {
        return valueOf(v);
    }

}
