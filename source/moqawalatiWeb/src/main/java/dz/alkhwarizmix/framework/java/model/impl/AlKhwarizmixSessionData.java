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

package dz.alkhwarizmix.framework.java.model.impl;

import java.io.Serializable;

import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.security.model.vo.User;
import dz.alkhwarizmix.framework.java.model.IAlKhwarizmixSessionData;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٧ محرم ١٤٣٥ (November 21, 2013)
 */
public class AlKhwarizmixSessionData implements IAlKhwarizmixSessionData,
		Serializable {

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
		// NOOP
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

	@Override
	public final User getConnectedUser() {
		return connectedUser;
	}

	@Override
	public final void setConnectedUser(final User value) {
		connectedUser = value;
	}

	// ----------------------------------
	// loggedUser
	// ----------------------------------

	@Override
	public final User getLoggedUser() {
		return loggedUser;
	}

	@Override
	public final void setLoggedUser(final User value) {
		loggedUser = value;
	}

	// ----------------------------------
	// sessionOwner
	// ----------------------------------

	@Override
	public final AlKhwarizmixDomainObject getSessionOwner() {
		if (sessionOwner == null)
			sessionOwner = new AlKhwarizmixDomainObject();
		return sessionOwner;
	}

	@Override
	public final void setSessionOwner(final AlKhwarizmixDomainObject value) {
		sessionOwner = value;
	}

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	@Override
	public final void resetConnectedUser() {
		setConnectedUser(null);
	}

	@Override
	public final void resetLoggedUser() {
		setLoggedUser(null);
	}

	@Override
	public final void resetSessionOwner() {
		setSessionOwner(null);
	}

} // Class
