
package com.training.infrastructure.thirdparty.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for parents complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="parents">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fatherFirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fatherLastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="motherFirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="motherLastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parents",namespace = "test", propOrder = {
    "fatherFirstName",
    "fatherLastName",
    "motherFirstName",
    "motherLastName"
})
public class Parents {

    protected String fatherFirstName;
    protected String fatherLastName;
    protected String motherFirstName;
    protected String motherLastName;

    /**
     * Gets the value of the fatherFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFatherFirstName() {
        return fatherFirstName;
    }

    /**
     * Sets the value of the fatherFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFatherFirstName(String value) {
        this.fatherFirstName = value;
    }

    /**
     * Gets the value of the fatherLastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFatherLastName() {
        return fatherLastName;
    }

    /**
     * Sets the value of the fatherLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFatherLastName(String value) {
        this.fatherLastName = value;
    }

    /**
     * Gets the value of the motherFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotherFirstName() {
        return motherFirstName;
    }

    /**
     * Sets the value of the motherFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotherFirstName(String value) {
        this.motherFirstName = value;
    }

    /**
     * Gets the value of the motherLastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotherLastName() {
        return motherLastName;
    }

    /**
     * Sets the value of the motherLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotherLastName(String value) {
        this.motherLastName = value;
    }

}
