
package elead.service.ts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for timesheet complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="timesheet">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="company_code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="staffCardNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="staffHWNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="staffIdNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="staffName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="staffNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statffHWCardNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="swipeDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="swipeTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="week" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "timesheet", propOrder = {
    "companyCode",
    "staffCardNo",
    "staffHWNo",
    "staffIdNo",
    "staffName",
    "staffNo",
    "statffHWCardNo",
    "swipeDate",
    "swipeTime",
    "week"
})
public class Timesheet {

    @XmlElement(name = "company_code")
    protected String companyCode;
    protected String staffCardNo;
    protected String staffHWNo;
    protected String staffIdNo;
    protected String staffName;
    protected String staffNo;
    protected String statffHWCardNo;
    protected String swipeDate;
    protected String swipeTime;
    protected String week;

    /**
     * Gets the value of the companyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyCode() {
        return companyCode;
    }

    /**
     * Sets the value of the companyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyCode(String value) {
        this.companyCode = value;
    }

    /**
     * Gets the value of the staffCardNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStaffCardNo() {
        return staffCardNo;
    }

    /**
     * Sets the value of the staffCardNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStaffCardNo(String value) {
        this.staffCardNo = value;
    }

    /**
     * Gets the value of the staffHWNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStaffHWNo() {
        return staffHWNo;
    }

    /**
     * Sets the value of the staffHWNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStaffHWNo(String value) {
        this.staffHWNo = value;
    }

    /**
     * Gets the value of the staffIdNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStaffIdNo() {
        return staffIdNo;
    }

    /**
     * Sets the value of the staffIdNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStaffIdNo(String value) {
        this.staffIdNo = value;
    }

    /**
     * Gets the value of the staffName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStaffName() {
        return staffName;
    }

    /**
     * Sets the value of the staffName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStaffName(String value) {
        this.staffName = value;
    }

    /**
     * Gets the value of the staffNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStaffNo() {
        return staffNo;
    }

    /**
     * Sets the value of the staffNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStaffNo(String value) {
        this.staffNo = value;
    }

    /**
     * Gets the value of the statffHWCardNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatffHWCardNo() {
        return statffHWCardNo;
    }

    /**
     * Sets the value of the statffHWCardNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatffHWCardNo(String value) {
        this.statffHWCardNo = value;
    }

    /**
     * Gets the value of the swipeDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSwipeDate() {
        return swipeDate;
    }

    /**
     * Sets the value of the swipeDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSwipeDate(String value) {
        this.swipeDate = value;
    }

    /**
     * Gets the value of the swipeTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSwipeTime() {
        return swipeTime;
    }

    /**
     * Sets the value of the swipeTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSwipeTime(String value) {
        this.swipeTime = value;
    }

    /**
     * Gets the value of the week property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWeek() {
        return week;
    }

    /**
     * Sets the value of the week property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWeek(String value) {
        this.week = value;
    }

}
