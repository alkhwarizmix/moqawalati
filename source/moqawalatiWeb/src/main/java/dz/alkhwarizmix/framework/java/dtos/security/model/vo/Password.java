////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٦ هجري، فارس بلحواس (Copyright 2015 Fares Belhaouas)
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.framework.java.dtos.security.model.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٥ ربيع الثاني ١٤٣٦ (February 04, 2015)
 */
@Entity
@Table(name = "TPassword")
public class Password extends AbstractAlKhwarizmixDomainObject implements
		Serializable, Cloneable {

	// --------------------------------------------------------------------------
	//
	// Constants
	//
	// --------------------------------------------------------------------------

	private static final long serialVersionUID = 8137825263607988935L;

	public static final String PASSWORD = "password";
	public static final String USERID = "user.id";

	// --------------------------------------------------------------------------
	//
	// Constructors
	//
	// --------------------------------------------------------------------------

	public Password() {
		super();
	}

	public Password(final User user) {
		this();
		setUser(user);
	}

	protected Password(final Password other) {
		super(other);
		if (other != null) {
			encryption = (Encryption) ObjectUtils.clone(other.encryption);
			password = other.password;
			user = (User) ObjectUtils.clone(other.user);
			lastUse = (Date) ObjectUtils.clone(other.lastUse);
		}
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@ManyToOne(targetEntity = Encryption.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "fEncryption", nullable = true, updatable = false)
	private Encryption encryption;

	@Column(name = "fPassword", nullable = false, length = 127, updatable = false)
	private String password;

	@ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "fUser", nullable = false, updatable = false)
	private User user;

	@Column(name = "fLastUse", nullable = true, updatable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUse;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	@Override
	public Object clone() {
		return new Password(this);
	}

	/**
	 */
	@Override
	protected ToStringBuilder toStringBuilder() {
		return super.toStringBuilder();
	}

	/**
	 */
	@Override
	public void updateFrom(final Object sourceObject)
			throws AlKhwarizmixException {
		final Password sourceUser = (Password) sourceObject;
		if (sourceUser != null) {
			if (sourceUser.password != null) {
				password = sourceUser.password;
			}
		} else {
			throw new AlKhwarizmixException(
					AlKhwarizmixErrorCode.UPDATE_DATA_ERROR);
		}
	}

	/**
	 */
	@Override
	public void beforeDaoSaveOrUpdate(
			final AbstractAlKhwarizmixDomainObject object) {
		// NOOP
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	static {
		ignoreBlazeDSProperty(Password.class, "id");
	}

	// ----------------------------------
	// encryption
	// ----------------------------------

	static {
		ignoreBlazeDSProperty(Password.class, "encryption");
	}

	public Encryption getEncryption() {
		return encryption;
	}

	public void setEncryption(final Encryption value) {
		encryption = value;
	}

	// ----------------------------------
	// password
	// ----------------------------------

	static {
		ignoreBlazeDSProperty(Password.class, "password");
	}

	@XmlTransient
	public String getPassword() {
		return password;
	}

	public void setPassword(final String value) {
		password = value;
	}

	// ----------------------------------
	// user
	// ----------------------------------

	public User getUser() {
		return user;
	}

	public void setUser(final User value) {
		user = value;
	}

	// ----------------------------------
	// lastUse
	// ----------------------------------

	public Date getLastUse() {
		return lastUse;
	}

	public void setLastUse(final Date value) {
		lastUse = value;
	}

} // Class
