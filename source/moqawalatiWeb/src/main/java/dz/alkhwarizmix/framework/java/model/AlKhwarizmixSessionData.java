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

	private User connectedUser = null;

	private User loggedUser = null;

	private AlKhwarizmixDomainObject sessionOwner = null;

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// connectedUser
	// ----------------------------------

	public final User getConnectedUser() {
		return connectedUser;
	}

	public final void setConnectedUser(User value) {
		connectedUser = value;
	}

	// ----------------------------------
	// loggedUser
	// ----------------------------------

	public final User getLoggedUser() {
		return loggedUser;
	}

	public final void setLoggedUser(User value) {
		loggedUser = value;
	}

	// ----------------------------------
	// sessionOwner
	// ----------------------------------

	public final AlKhwarizmixDomainObject getSessionOwner() {
		if (sessionOwner == null)
			sessionOwner = new AlKhwarizmixDomainObject();
		return sessionOwner;
	}

	public final void setSessionOwner(AlKhwarizmixDomainObject value) {
		sessionOwner = value;
	}

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	public final void resetConnectedUser() {
		setConnectedUser(null);
	}

	public final void resetLoggedUser() {
		setLoggedUser(null);
	}

	public final void resetSessionOwner() {
		setSessionOwner(null);
	}

} // Class
