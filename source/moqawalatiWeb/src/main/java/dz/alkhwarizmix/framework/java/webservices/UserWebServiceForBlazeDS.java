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

package dz.alkhwarizmix.framework.java.webservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import dz.alkhwarizmix.framework.java.AlKhwarizmixBlazeDSException;
import dz.alkhwarizmix.framework.java.dtos.security.model.vo.User;
import dz.alkhwarizmix.framework.java.interfaces.IUserWebServiceForBlazeDS;
import dz.alkhwarizmix.framework.java.services.IAlKhwarizmixService;
import dz.alkhwarizmix.framework.java.services.IUserService;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٤ ربيع الثاني ١٤٣٦ (February 03, 2015)
 */
public class UserWebServiceForBlazeDS extends AlKhwarizmixWebServiceForBlazeDS
		implements IUserWebServiceForBlazeDS {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public UserWebServiceForBlazeDS() {
		super();
	}

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	private static Logger logger = null;

	@Override
	protected Logger getLogger() {
		if (logger == null)
			logger = LoggerFactory.getLogger(UserWebServiceForBlazeDS.class);
		return logger;
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@Autowired
	private IUserService userService;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	@Override
	public void addUser(final User user) throws AlKhwarizmixBlazeDSException {
		getLogger().debug("addUser({})", user);
		try {
			getUserService().addUser(user, true);
		} catch (final Exception e) {
			throw getAlKhwarizmixBlazeDSException(e, "addUser");
		}
	}

	/**
	 */
	@Override
	public User getUser(final User user) throws AlKhwarizmixBlazeDSException {
		getLogger().debug("getUser({})", user);
		try {
			return getUserService().getUser(user, true);
		} catch (final Exception e) {
			throw getAlKhwarizmixBlazeDSException(e, "getUser");
		}
	}

	/**
	 */
	@Override
	public User updateUser(final User user) throws AlKhwarizmixBlazeDSException {
		getLogger().debug("updateUser({})", user);
		try {
			return getUserService().updateUser(user, true);
		} catch (final Exception e) {
			throw getAlKhwarizmixBlazeDSException(e, "updateUser");
		}
	}

	/**
	 */
	@Override
	public User connect(final User user) throws AlKhwarizmixBlazeDSException {
		getLogger().debug("connect({})", user);
		try {
			return getUserService().connect(user, true);
		} catch (final Exception e) {
			throw getAlKhwarizmixBlazeDSException(e, "connect");
		}
	}

	/**
	 */
	@Override
	public User subscribe(final User user) throws AlKhwarizmixBlazeDSException {
		getLogger().debug("subscribe({})", user);
		try {
			return getUserService().subscribe(user, true);
		} catch (final Exception e) {
			throw getAlKhwarizmixBlazeDSException(e, "subscribe");
		}
	}

	/**
	 */
	@Override
	public User login(final User user, final String password)
			throws AlKhwarizmixBlazeDSException {
		getLogger().debug("login({})", user);
		try {
			return getUserService().login(user, password, true);
		} catch (final Exception e) {
			throw getAlKhwarizmixBlazeDSException(e, "login");
		}
	}

	/**
	 */
	@Override
	public void logout(final User user) throws AlKhwarizmixBlazeDSException {
		getLogger().debug("logout({})", user);
		try {
			getUserService().logout(user);
		} catch (final Exception e) {
			throw getAlKhwarizmixBlazeDSException(e, "logout");
		}
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// userService
	// ----------------------------------

	protected IUserService getUserService() {
		return userService;
	}

	protected void setUserService(final IUserService value) {
		userService = value;
	}

	// ----------------------------------
	// service
	// ----------------------------------

	// ----------------------------------
	// service
	// ----------------------------------

	@Override
	protected IAlKhwarizmixService getService() {
		return userService;
	}

} // Class
