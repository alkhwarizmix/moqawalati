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

package dz.alkhwarizmix.framework.java.model;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.security.model.vo.User;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٧ محرم ١٤٣٥ (November 21, 2013)
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AlKhwarizmixSessionData implements Serializable {

	// --------------------------------------------------------------------------
	//
	// Constants
	//
	// --------------------------------------------------------------------------

	private static final long serialVersionUID = -6782022452977817665L;

	public static final String CURRENTUSER = "CURRENTUSER";

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public AlKhwarizmixSessionData() {
		// resetCustomizer();
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	private AlKhwarizmixDomainObject sessionOwner = null;

	private User connectedUser = null;

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// sessionOwner
	// ----------------------------------

	public AlKhwarizmixDomainObject getSessionOwner() {
		if (sessionOwner == null)
			sessionOwner = new AlKhwarizmixDomainObject();
		return sessionOwner;
	}

	public void setSessionOwner(AlKhwarizmixDomainObject value) {
		sessionOwner = value;
	}

	// ----------------------------------
	// connectedUser
	// ----------------------------------

	public User getConnectedUser() {
		return connectedUser;
	}

	public void setConnectedUser(User value) {
		connectedUser = value;
	}

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	public void resetSessionOwner() {
		setSessionOwner(null);
	}

} // Class
