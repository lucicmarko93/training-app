
package com.training.infrastructure.thirdparty.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for application complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="application">
 *   &lt;complexContent>
 *     &lt;extension base="{http://application.soap.web.training.com/}abstractBaseEntity">
 *       &lt;sequence>
 *         &lt;element name="applicationKind" type="{http://application.soap.web.training.com/}applicationKind" minOccurs="0"/>
 *         &lt;element name="applicationNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="applicationState" type="{http://application.soap.web.training.com/}applicationState" minOccurs="0"/>
 *         &lt;element name="citizen" type="{http://application.soap.web.training.com/}citizen" minOccurs="0"/>
 *         &lt;element name="comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="documentType" type="{http://application.soap.web.training.com/}documentType" minOccurs="0"/>
 *         &lt;element name="priority" type="{http://application.soap.web.training.com/}priority" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "application",namespace = "test", propOrder = {
    "applicationKind",
    "applicationNumber",
    "applicationState",
    "citizen",
    "comment",
    "documentType",
    "priority"
})
public class ApplicationReq
    extends AbstractBaseEntity
{

    @XmlSchemaType(name = "string")
    protected ApplicationKind applicationKind;
    protected String applicationNumber;
    @XmlSchemaType(name = "string")
    protected ApplicationState applicationState;
    protected Citizen citizen;
    protected String comment;
    @XmlSchemaType(name = "string")
    protected DocumentType documentType;
    @XmlSchemaType(name = "string")
    protected Priority priority;

    /**
     * Gets the value of the applicationKind property.
     * 
     * @return
     *     possible object is
     *     {@link ApplicationKind }
     *     
     */
    public ApplicationKind getApplicationKind() {
        return applicationKind;
    }

    /**
     * Sets the value of the applicationKind property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplicationKind }
     *     
     */
    public void setApplicationKind(ApplicationKind value) {
        this.applicationKind = value;
    }

    /**
     * Gets the value of the applicationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationNumber() {
        return applicationNumber;
    }

    /**
     * Sets the value of the applicationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationNumber(String value) {
        this.applicationNumber = value;
    }

    /**
     * Gets the value of the applicationState property.
     * 
     * @return
     *     possible object is
     *     {@link ApplicationState }
     *     
     */
    public ApplicationState getApplicationState() {
        return applicationState;
    }

    /**
     * Sets the value of the applicationState property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplicationState }
     *     
     */
    public void setApplicationState(ApplicationState value) {
        this.applicationState = value;
    }

    /**
     * Gets the value of the citizen property.
     * 
     * @return
     *     possible object is
     *     {@link Citizen }
     *     
     */
    public Citizen getCitizen() {
        return citizen;
    }

    /**
     * Sets the value of the citizen property.
     * 
     * @param value
     *     allowed object is
     *     {@link Citizen }
     *     
     */
    public void setCitizen(Citizen value) {
        this.citizen = value;
    }

    /**
     * Gets the value of the comment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the value of the comment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComment(String value) {
        this.comment = value;
    }

    /**
     * Gets the value of the documentType property.
     * 
     * @return
     *     possible object is
     *     {@link DocumentType }
     *     
     */
    public DocumentType getDocumentType() {
        return documentType;
    }

    /**
     * Sets the value of the documentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentType }
     *     
     */
    public void setDocumentType(DocumentType value) {
        this.documentType = value;
    }

    /**
     * Gets the value of the priority property.
     * 
     * @return
     *     possible object is
     *     {@link Priority }
     *     
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     * @param value
     *     allowed object is
     *     {@link Priority }
     *     
     */
    public void setPriority(Priority value) {
        this.priority = value;
    }

}
