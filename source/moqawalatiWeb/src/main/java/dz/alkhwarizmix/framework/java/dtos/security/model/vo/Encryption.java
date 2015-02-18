////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٦ هجري، فارس بلحواس (Copyright 2014 Fares Belhaouas)  
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.framework.java.dtos.security.model.vo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang.ObjectUtils;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٣ ربيع الأول ١٤٣٦ (December 24, 2014)
 */
@Entity
@Table(name = "TEncryption")
@XmlRootElement(name = "Encryption")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Encryption extends AbstractAlKhwarizmixDomainObject implements
		Serializable {

	// --------------------------------------------------------------------------
	//
	// Constants
	//
	// --------------------------------------------------------------------------

	private static final long serialVersionUID = -3820045814524228203L;

	public static final String ENCRYPTIONID = "encryptionId";
	public static final String NAME = "name";

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public Encryption() {
		super();
	}

	/**
	 * constructor
	 */
	public Encryption(String theEncryptionId) {
		this();
		setEncryptionId(theEncryptionId);
	}

	/**
	 * constructor
	 */
	protected Encryption(Encryption other) {
		super(other);
		if (other != null) {
			this.encryptionId = other.encryptionId;
			this.user = (User) ObjectUtils.clone(other.user);
			this.domainObject = (AlKhwarizmixDomainObject) ObjectUtils
					.clone(other.domainObject);
		}
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@Column(name = "fEncryptionId", unique = true, nullable = false, length = 63)
	private String encryptionId;

	@ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "fUser", nullable = false)
	private User user;

	@ManyToOne(targetEntity = AlKhwarizmixDomainObject.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "fDomainObject", nullable = false)
	private AlKhwarizmixDomainObject domainObject;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	public String toString() {
		return super.toStringBuilder(this).append("encryptionId", encryptionId)
				.toString();
	}

	/**
	 */
	public void updateFrom(Object sourceObject) throws AlKhwarizmixException {
		Encryption sourceEncryption = (Encryption) sourceObject;
		if (sourceEncryption != null
				&& this.getEncryptionId().equals(
						sourceEncryption.getEncryptionId())) {
			// NOOP
		} else {
			throw new AlKhwarizmixException(
					AlKhwarizmixErrorCode.UPDATE_DATA_ERROR);
		}
	}

	/**
	 */
	@Override
	public void beforeDaoSaveOrUpdate(AbstractAlKhwarizmixDomainObject object) {
		if (domainObject == null)
			domainObject = new AlKhwarizmixDomainObject();
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	static {
		ignoreBlazeDSProperty(Encryption.class, "id");
	}

	// ----------------------------------
	// encryptionId
	// ----------------------------------
	//
	@XmlAttribute(name = "id")
	public String getEncryptionId() {
		return encryptionId;
	}

	public void setEncryptionId(String value) {
		this.encryptionId = value;
	}

	// ----------------------------------
	// user
	// ----------------------------------

	@XmlElement(name = "User")
	public User getUser() {
		return user;
	}

	public void setUser(User value) {
		this.user = value;
	}

	// ----------------------------------
	// domainObject
	// ----------------------------------

	static {
		ignoreBlazeDSProperty(User.class, "domainObject");
	}

	@XmlTransient
	public AlKhwarizmixDomainObject getDomainObject() {
		return domainObject;
	}

	public void setDomainObject(AlKhwarizmixDomainObject value) {
		this.domainObject = value;
	}

} // Class
