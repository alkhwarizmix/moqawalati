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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.ObjectUtils;
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
		Serializable {

	// --------------------------------------------------------------------------
	//
	// Constants
	//
	// --------------------------------------------------------------------------

	private static final long serialVersionUID = 8137825263607988935L;

	public static final String PASSWORD = "password";

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public Password() {
		super();
	}

	/**
	 * constructor
	 */
	protected Password(Password other) {
		super(other);
		if (other != null) {
			this.encryption = (Encryption) ObjectUtils.clone(other.encryption);
			this.password = other.password;
			this.user = (User) ObjectUtils.clone(other.user);
		}
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@ManyToOne(targetEntity = Encryption.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "fEncryption", nullable = false)
	private Encryption encryption;

	@Column(name = "fPassword", nullable = false, length = 127)
	private String password;

	@ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "fUser", nullable = false)
	private User user;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	public String toString() {
		// return super.toStringBuilder(this).append("userId",
		// userId).toString();
		return "";
	}

	/**
	 */
	public void updateFrom(Object sourceObject) throws AlKhwarizmixException {
		Password sourceUser = (Password) sourceObject;
		if (sourceUser != null) {
			if (sourceUser.password != null) {
				this.password = sourceUser.password;
			}
		} else {
			throw new AlKhwarizmixException(
					AlKhwarizmixErrorCode.UPDATE_DATA_ERROR);
		}
	}

	/**
	 */
	@Override
	public void beforeDaoSaveOrUpdate(AbstractAlKhwarizmixDomainObject object) {
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

	public Encryption getEncryption() {
		return encryption;
	}

	public void setEncryption(Encryption value) {
		this.encryption = value;
	}

	// ----------------------------------
	// password
	// ----------------------------------

	static {
		ignoreBlazeDSProperty(Password.class, "password");
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String value) {
		this.password = value;
	}

	// ----------------------------------
	// user
	// ----------------------------------

	public User getUser() {
		return user;
	}

	public void setUser(User value) {
		this.user = value;
	}

} // Class
