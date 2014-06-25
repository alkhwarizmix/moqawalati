////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٤ هجري، فارس بلحواس (Copyright 2013 Fares Belhaouas)  
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.moqawalati.java.dtos.modules.clientModule.model.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.moqawalati.java.MoqawalatiException;
import dz.alkhwarizmix.moqawalati.java.model.vo.MoqawalatiDomainObject;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٦ ذو الحجة ١٤٣٤ (October 10, 2013)
 */
@XmlRootElement(name = "Address")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Address extends MoqawalatiDomainObject implements Serializable {

	// --------------------------------------------------------------------------
	//
	// Constants
	//
	// --------------------------------------------------------------------------

	private static final long serialVersionUID = 5522269212409294225L;

	public static final String ADDRESSID = "addressId";
	public static final String STREET = "street";

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public Address() {
		super();
	}

	/**
	 * constructor
	 */
	public Address(String theAddressId) {
		super();
		setAddressId(theAddressId);
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	private String addressId;

	private String street;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	public String toString() {
		return super.toStringBuilder(this).append("addressId", addressId)
				.toString();
	}

	/**
	 */
	public void updateFrom(Object sourceObject) throws AlKhwarizmixException {
		Address sourceAddress = (Address) sourceObject;
		if ((sourceAddress != null)
		// && (this.getCreatorId().equals(sourceAddress.getCreatorId()))
				&& (this.getAddressId().equals(sourceAddress.getAddressId()))) {
			if (sourceAddress.street != null) {
				this.street = sourceAddress.street;
			}
		} else {
			throw new MoqawalatiException(
					AlKhwarizmixErrorCode.UPDATE_DATA_ERROR);
		}
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// addressId
	// ----------------------------------

	@XmlAttribute(name = "id")
	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String value) {
		this.addressId = value;
	}

	// ----------------------------------
	// name
	// ----------------------------------

	@XmlElement(name = "Street")
	public String getStreet() {
		return street;
	}

	public void setStreet(String value) {
		this.street = value;
	}

} // Class