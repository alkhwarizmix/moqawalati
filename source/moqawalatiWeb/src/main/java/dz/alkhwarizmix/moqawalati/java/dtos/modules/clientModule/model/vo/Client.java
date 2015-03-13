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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.ObjectUtils;
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
 * @since ٢٥ ذو القعدة ١٤٣٤ (October 01, 2013)
 */
@Entity
@Table(name = "TClient")
@XmlRootElement(name = "Client")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Client extends AbstractMoqawalatiDomainObject implements
		Serializable, Cloneable {

	// --------------------------------------------------------------------------
	//
	// Constants
	//
	// --------------------------------------------------------------------------

	private static final long serialVersionUID = 5055979898938206174L;

	public static final String CLIENTID = "clientId";
	public static final String NAME = "name";

	// --------------------------------------------------------------------------
	//
	// Constructors
	//
	// --------------------------------------------------------------------------

	public Client() {
		super();
	}

	public Client(final String theClientId) {
		super();
		setClientId(theClientId);
	}

	public Client(final String theClientId, final String theName) {
		super();
		setClientId(theClientId);
		setName(theName);
	}

	protected Client(final Client other) {
		// super(other);
		if (other != null) {
			clientId = other.clientId;
			name = other.name;
			address = (Address) ObjectUtils.clone(other.address);
		}
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@Column(name = "clientId", unique = true, nullable = false, length = 63)
	private String clientId;

	@Column(name = "name", nullable = false, length = 127)
	private String name;

	@Transient
	private Address address;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	@Override
	public Object clone() {
		return new Client(this);
	}

	/**
	 */
	@Override
	protected ToStringBuilder toStringBuilder() {
		return super.toStringBuilder().append("clientId", clientId);
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
		final Client sourceClient = (Client) sourceObject;
		if ((sourceClient != null)
				&& getClientId().equals(sourceClient.getClientId())) {
			if (sourceClient.name != null) {
				name = sourceClient.name;
			}
		} else {
			throw new MoqawalatiException(
					AlKhwarizmixErrorCode.UPDATE_DATA_ERROR);
		}
	}

	/**
	 */
	public void setupAddress() {
		createFakeAddress();
	}

	/**
	 */
	private void createFakeAddress() {
		final Address newAddress = new Address();
		// newAddress.setCreatorId(getCreatorId());
		newAddress.setAddressId(String.valueOf(Math.random()));
		newAddress.setStreet(new Date().toString());
		setAddress(newAddress);
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// clientId
	// ----------------------------------

	@XmlAttribute(name = "id")
	public String getClientId() {
		return clientId;
	}

	public void setClientId(final String value) {
		clientId = value;
	}

	// ----------------------------------
	// name
	// ----------------------------------

	@XmlElement(name = "Name")
	public String getName() {
		return name;
	}

	public void setName(final String value) {
		name = value;
	}

	// ----------------------------------
	// address
	// ----------------------------------

	@XmlElement(name = "Address")
	public Address getAddress() {
		return address;
	}

	public void setAddress(final Address value) {
		address = value;
	}

} // Class
