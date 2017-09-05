
package elead.service.ts;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the elead.service.ts package. 
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

    private final static QName _PromoteTS_QNAME = new QName("http://ts.service.elead/", "promoteTS");
    private final static QName _PromoteTSResponse_QNAME = new QName("http://ts.service.elead/", "promoteTSResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: elead.service.ts
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Timesheet }
     * 
     */
    public Timesheet createTimesheet() {
        return new Timesheet();
    }

    /**
     * Create an instance of {@link PromoteTS }
     * 
     */
    public PromoteTS createPromoteTS() {
        return new PromoteTS();
    }

    /**
     * Create an instance of {@link PromoteTSResponse }
     * 
     */
    public PromoteTSResponse createPromoteTSResponse() {
        return new PromoteTSResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PromoteTS }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ts.service.elead/", name = "promoteTS")
    public JAXBElement<PromoteTS> createPromoteTS(PromoteTS value) {
        return new JAXBElement<PromoteTS>(_PromoteTS_QNAME, PromoteTS.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PromoteTSResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ts.service.elead/", name = "promoteTSResponse")
    public JAXBElement<PromoteTSResponse> createPromoteTSResponse(PromoteTSResponse value) {
        return new JAXBElement<PromoteTSResponse>(_PromoteTSResponse_QNAME, PromoteTSResponse.class, null, value);
    }

}
