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

import org.apache.commons.lang.builder.ToStringBuilder;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.moqawalati.java.MoqawalatiException;
import dz.alkhwarizmix.moqawalati.java.model.vo.AbstractMoqawalatiDomainObject;

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
public class Address extends AbstractMoqawalatiDomainObject implements
		Cloneable, Serializable {

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
	public Address(final String theAddressId) {
		super();
		setAddressId(theAddressId);
	}

	protected Address(final Address other) {
		// super(other);
		if (other != null) {
			addressId = other.addressId;
			street = other.street;
			// address = (Address) ObjectUtils.clone(other.address);
		}
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
	@Override
	public Object clone() {
		return new Address(this);
	}

	/**
	 */
	@Override
	protected ToStringBuilder toStringBuilder() {
		return super.toStringBuilder().append("addressId", addressId);
	}

	/**
	 */
	@Override
	public void beforeDaoSaveOrUpdate(
			final AbstractAlKhwarizmixDomainObject object) {
		// NOOP
	}

	/**
	 */
	@Override
	public void updateFrom(final Object sourceObject)
			throws AlKhwarizmixException {
		final Address sourceAddress = (Address) sourceObject;
		if ((sourceAddress != null)
				&& getAddressId().equals(sourceAddress.getAddressId())) {
			if (sourceAddress.street != null) {
				street = sourceAddress.street;
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

	public void setAddressId(final String value) {
		addressId = value;
	}

	// ----------------------------------
	// name
	// ----------------------------------

	@XmlElement(name = "Street")
	public String getStreet() {
		return street;
	}

	public void setStreet(final String value) {
		street = value;
	}

} // Class
