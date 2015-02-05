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

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dtos.security.model.vo.User;
import dz.alkhwarizmix.framework.java.interfaces.IAlKhwarizmixService;
import dz.alkhwarizmix.framework.java.interfaces.IUserService;
import dz.alkhwarizmix.framework.java.interfaces.IUserWebServiceForBlazeDS;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٤ ربيع الثاني ١٤٣٦ (February 03, 2015)
 */
public class UserWebServiceForBlazeDS implements IUserWebServiceForBlazeDS {

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

	private static final Logger LOG = LoggerFactory
			.getLogger(UserWebServiceForBlazeDS.class);

	protected Logger getLogger() {
		return LOG;
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
	public void addUser(User user) throws AlKhwarizmixException {
		getLogger().debug("addUser({})", user);
		getUserService().addUser(user);
	}

	/**
	 */
	@Override
	public User getUser(User user) throws AlKhwarizmixException {
		getLogger().debug("getUser({})", user);
		return getUserService().getUser(user);
	}

	/**
	 */
	@Override
	public User updateUser(User user) throws AlKhwarizmixException {
		getLogger().debug("updateUser({})", user);
		return getUserService().updateUser(user);
	}

	/**
	 */
	@Override
	public User connect(User user) throws AlKhwarizmixException {
		getLogger().debug("connect({})", user);
		return getUserService().connect(user);
	}

	/**
	 */
	@Override
	public User login(User user, String password) throws AlKhwarizmixException {
		getLogger().debug("login({})", user);
		return getUserService().login(user, password);
	}

	/**
	 */
	@Override
	public void logout(User user) throws AlKhwarizmixException {
		getLogger().debug("logout({})", user);
		getUserService().logout(user);
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

	protected void setUserService(IUserService value) {
		userService = value;
	}

	// ----------------------------------
	// service
	// ----------------------------------

	protected IAlKhwarizmixService getService() {
		return userService;
	}

} // Class
