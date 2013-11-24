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

package dz.alkhwarizmix.moqawalati.java.webServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dz.alkhwarizmix.framework.java.interfaces.IAlKhwarizmixService;
import dz.alkhwarizmix.framework.java.webServices.AlKhwarizmixWebService;
import dz.alkhwarizmix.moqawalati.java.MoqawalatiException;
import dz.alkhwarizmix.moqawalati.java.dtos.modules.userModule.model.vo.User;
import dz.alkhwarizmix.moqawalati.java.interfaces.IUserService;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٨ ذو الحجة ١٤٣٤ (November 01, 2013)
 */
@Controller
@RequestMapping("moqawalati/xml/user")
public class UserWebService extends AlKhwarizmixWebService {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public UserWebService() {
		super();
	}

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	private static final Logger LOG = LoggerFactory
			.getLogger(UserWebService.class);

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
	 * add the user to database
	 * 
	 * @param xmlValue
	 *            {@link String} the user as xml
	 * @return {@link ResponseEntity}
	 * @throws MoqawalatiException
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> addUser(@RequestParam("user") String xmlValue)
			throws MoqawalatiException {
		LOG.debug("addUser({})", xmlValue);

		try {
			String result = userService.addUserFromXML(xmlValue,
					getCurrentRequestRemoteAddress());
			StringBuilder sBuilder = new StringBuilder(result);
			return successResponse(sBuilder);
		} catch (MoqawalatiException e) {
			return errorResponse(e);
		}
	}

	/**
	 * get the user from database
	 * 
	 * @param userId
	 *            {@link Long} userId
	 * @return {@link ResponseEntity}
	 * @throws MoqawalatiException
	 */
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public ResponseEntity<String> getUserById(
			@PathVariable("userId") String userId) throws MoqawalatiException {
		LOG.debug("getUserById({})", userId);

		try {
			User userToGet = new User();
			userToGet.setUserId(userId);
			StringBuilder sBuilder = new StringBuilder(
					userService.getUserAsXML(userToGet));
			return successResponse(sBuilder);
		} catch (MoqawalatiException e) {
			return errorResponse(e);
		}
	}

	/**
	 * update the user in database
	 * 
	 * @param xmlValue
	 *            {@link String} the user as xml
	 * @return {@link ResponseEntity}
	 * @throws MoqawalatiException
	 */
	@RequestMapping(value = "/{userId}", method = RequestMethod.POST)
	public ResponseEntity<String> updateUser(
			@PathVariable("userId") String userId,
			@RequestParam("user") String xmlValue) throws MoqawalatiException {
		LOG.debug("updateUser({})", xmlValue);

		try {
			StringBuilder sBuilder = new StringBuilder(
					userService.updateUserFromXML(xmlValue,
							getCurrentRequestRemoteAddress()));
			return successResponse(sBuilder);
		} catch (MoqawalatiException e) {
			return errorResponse(e);
		}
	}

	/**
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> getUserList(
			@RequestParam("firstResult") int firstResult,
			@RequestParam("maxResult") int maxResult) {
		StringBuilder result = new StringBuilder();

		try {
			result.append(userService.getUserListAsXML(null, firstResult,
					maxResult));

			return successResponse(result);
		} catch (MoqawalatiException e) {
			return errorResponse(e);
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