
package com.training.infrastructure.thirdparty.data;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.training.web.soap.application package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PersonalizationR_QNAME = new QName("http://application.soap.web.training.com/", "personalizationR");
    private final static QName _PersonalizationRResponse_QNAME = new QName("http://application.soap.web.training.com/", "personalizationRResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.training.web.soap.application
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PersonalizationR }
     * 
     */
    public PersonalizationR createPersonalizationR() {
        return new PersonalizationR();
    }

    /**
     * Create an instance of {@link PersonalizationRResponse }
     * 
     */
    public PersonalizationRResponse createPersonalizationRResponse() {
        return new PersonalizationRResponse();
    }

    /**
     * Create an instance of {@link Citizen }
     * 
     */
    public Citizen createCitizen() {
        return new Citizen();
    }

    /**
     * Create an instance of {@link Address }
     * 
     */
    public Address createAddress() {
        return new Address();
    }

    /**
     * Create an instance of {@link ApplicationReq }
     * 
     */
    public ApplicationReq createApplication() {
        return new ApplicationReq();
    }

    /**
     * Create an instance of {@link PersonalizationResponse }
     * 
     */
    public PersonalizationResponse createPersonalizationResponse() {
        return new PersonalizationResponse();
    }

    /**
     * Create an instance of {@link Parents }
     * 
     */
    public Parents createParents() {
        return new Parents();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersonalizationR }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://application.soap.web.training.com/", name = "personalizationR")
    public JAXBElement<PersonalizationR> createPersonalizationR(PersonalizationR value) {
        return new JAXBElement<PersonalizationR>(_PersonalizationR_QNAME, PersonalizationR.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersonalizationRResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://application.soap.web.training.com/", name = "personalizationRResponse")
    public JAXBElement<PersonalizationRResponse> createPersonalizationRResponse(PersonalizationRResponse value) {
        return new JAXBElement<PersonalizationRResponse>(_PersonalizationRResponse_QNAME, PersonalizationRResponse.class, null, value);
    }

}
