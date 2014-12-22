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
		Serializable {

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
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public Client() {
		super();
	}

	/**
	 * constructor
	 */
	public Client(String theClientId) {
		super();
		setClientId(theClientId);
	}

	/**
	 * constructor
	 */
	public Client(String theClientId, String theName) {
		super();
		setClientId(theClientId);
		setName(theName);
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
	public void beforeDaoSaveOrUpdate(AbstractAlKhwarizmixDomainObject object) {
		// NOOP
	}

	/**
	 */
	public String toString() {
		return super.toStringBuilder(this).append("clientId", clientId)
				.toString();
	}

	/**
	 */
	public void updateFrom(Object sourceObject) throws AlKhwarizmixException {
		final Client sourceClient = (Client) sourceObject;
		if (sourceClient != null
				&& getClientId().equals(sourceClient.getClientId())) {
			if (sourceClient.name != null) {
				this.name = sourceClient.name;
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
		Address newAddress = new Address();
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

	public void setClientId(String value) {
		this.clientId = value;
	}

	// ----------------------------------
	// name
	// ----------------------------------

	@XmlElement(name = "Name")
	public String getName() {
		return name;
	}

	public void setName(String value) {
		this.name = value;
	}

	// ----------------------------------
	// address
	// ----------------------------------

	@XmlElement(name = "Address")
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address value) {
		this.address = value;
	}

} // Class
