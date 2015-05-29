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

package dz.alkhwarizmix.framework.java.webservices.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dz.alkhwarizmix.framework.java.dtos.security.model.vo.User;
import dz.alkhwarizmix.framework.java.services.IAlKhwarizmixService;
import dz.alkhwarizmix.framework.java.services.IUserService;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٨ ذو الحجة ١٤٣٤ (November 01, 2013)
 */
@Controller
@RequestMapping("alkhwarizmix/xml/user")
public class UserWebServiceForXML extends AbstractAlKhwarizmixWebServiceForXML {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public UserWebServiceForXML() {
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
			logger = LoggerFactory.getLogger(UserWebServiceForXML.class);
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
	 * add the user to database
	 *
	 * @param xmlValue
	 *            {@link String} the user as xml
	 * @return {@link ResponseEntity}
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> addUser(@RequestParam("user") final String xmlValue) {
		getLogger().debug("addUser({})", xmlValue);

		try {
			final String result = userService.addUserFromXML(xmlValue);
			final StringBuilder sBuilder = new StringBuilder(result);
			return successResponseForXML(sBuilder);
		} catch (final Exception e) {
			return errorResponseForXML(e);
		}
	}

	/**
	 * get the user from database
	 *
	 * @param userId
	 *            {@link Long} userId
	 * @return {@link ResponseEntity}
	 */
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public ResponseEntity<String> getUserById(
			@PathVariable("userId") final String userId) {
		getLogger().debug("getUserById({})", userId);

		try {
			final User userToGet = new User();
			userToGet.setUserId(userId);
			final StringBuilder sBuilder = new StringBuilder(
					userService.getUserAsXML(userToGet));
			return successResponseForXML(sBuilder);
		} catch (final Exception e) {
			return errorResponseForXML(e);
		}
	}

	/**
	 * update the user in database
	 *
	 * @param xmlValue
	 *            {@link String} the user as xml
	 * @return {@link ResponseEntity}
	 */
	@RequestMapping(value = "/{userId}", method = RequestMethod.POST)
	public ResponseEntity<String> updateUser(
			@PathVariable("userId") final String userId,
			@RequestParam("user") final String xmlValue) {
		getLogger().debug("updateUser({})", xmlValue);

		try {
			final StringBuilder sBuilder = new StringBuilder(
					userService.updateUserFromXML(xmlValue));
			return successResponseForXML(sBuilder);
		} catch (final Exception e) {
			return errorResponseForXML(e);
		}
	}

	/**
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> getUserList(
			@RequestParam("firstResult") final int firstResult,
			@RequestParam("maxResult") final int maxResult) {
		final StringBuilder result = new StringBuilder();

		try {
			result.append(userService.getUserListAsXML(null, firstResult,
					maxResult));

			return successResponseForXML(result);
		} catch (final Exception e) {
			return errorResponseForXML(e);
		}
	}

	/**
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> login(@RequestParam("user") final String userAsXML,
			@RequestParam("password") final String password) {
		getLogger().debug("login({})", userAsXML);
		try {
			final StringBuilder sBuilder = new StringBuilder(
					userService.loginFromXML(userAsXML, password));
			return successResponseForXML(sBuilder);
		} catch (final Exception e) {
			return errorResponseForXML(e);
		}
	}

	/**
	 */
	@RequestMapping(value = "/connect", method = RequestMethod.POST)
	public ResponseEntity<String> connect(@RequestParam("user") final String userAsXML) {
		getLogger().debug("connect({})", userAsXML);
		try {
			final StringBuilder sBuilder = new StringBuilder(
					userService.connectFromXML(userAsXML));
			return successResponseForXML(sBuilder);
		} catch (final Exception e) {
			return errorResponseForXML(e);
		}
	}

	/**
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ResponseEntity<String> logout(@RequestParam("user") final String userAsXML) {
		getLogger().debug("logout({})", userAsXML);
		try {
			userService.logoutFromXML(userAsXML);
			return successResponseForXML(new StringBuilder());
		} catch (final Exception e) {
			return errorResponseForXML(e);
		}
	}

	/**
	 */
	@RequestMapping(value = "/subscribe", method = RequestMethod.POST)
	public ResponseEntity<String> subscribe(
			@RequestParam("user") final String userAsXML) {
		getLogger().debug("subscribe({})", userAsXML);
		try {
			final StringBuilder sBuilder = new StringBuilder(
					userService.subscribeFromXML(userAsXML));
			return successResponseForXML(sBuilder);
		} catch (final Exception e) {
			return errorResponseForXML(e);
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

	@Override
	protected IAlKhwarizmixService getService() {
		return userService;
	}

} // Class
