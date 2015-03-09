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

package dz.alkhwarizmix.framework.java.services;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.email.model.vo.EMail;
import dz.alkhwarizmix.framework.java.dtos.security.model.vo.User;
import dz.alkhwarizmix.framework.java.interfaces.IAlKhwarizmixDAO;
import dz.alkhwarizmix.framework.java.interfaces.IAlKhwarizmixServiceValidator;
import dz.alkhwarizmix.framework.java.interfaces.IEMailService;
import dz.alkhwarizmix.framework.java.interfaces.IUserDAO;
import dz.alkhwarizmix.framework.java.interfaces.IUserService;
import dz.alkhwarizmix.framework.java.interfaces.IUserServiceValidator;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٨ ذو الحجة ١٤٣٤ (November 01, 2013)
 */
@Service
@Transactional(readOnly = true)
public class UserService extends AbstractAlKhwarizmixService implements
		IUserService {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public UserService() {
		super();
	}

	/**
	 * constructor
	 */
	protected UserService(Logger theLogger) {
		this();
		logger = theLogger;
	}

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	private static Logger logger = null;

	@Override
	protected final Logger getLogger() {
		if (logger == null)
			logger = LoggerFactory.getLogger(UserService.class);
		return logger;
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@Autowired
	private IUserDAO userDAO;

	@Autowired
	private IUserServiceValidator userServiceValidator;

	@Autowired
	private IEMailService emailService;

	@Autowired
	private Jaxb2Marshaller jaxb2Marshaller;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = false)
	@Override
	public void addUser(User user) throws AlKhwarizmixException {
		getLogger().debug("addUser");
		addObject(user);
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = false)
	@Override
	public String addUserFromXML(String userXml) throws AlKhwarizmixException {
		getLogger().trace("addUserFromXML");
		User newUser = (User) unmarshalObjectFromXML(userXml);
		addUser(newUser);
		String result = marshalObjectToXML(newUser);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AbstractAlKhwarizmixDomainObject getObject(
			AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixException {
		getLogger().trace("getObject");
		User result = getMoqawalatiDAO().getUser((User) object);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User getUser(User user) throws AlKhwarizmixException {
		getLogger().debug("getUser");
		User result = internal_getUser(user);
		getUserServiceValidator().validateObjectToPublish(result,
				getSessionOwner());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getUserAsXML(User user) throws AlKhwarizmixException {
		getLogger().trace("getUserAsXML 1");
		String result = getObjectAsXML(user);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getUserAsXML(String userXml) throws AlKhwarizmixException {
		getLogger().trace("getUserAsXML 2");
		String result = getObjectAsXML(userXml);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = false)
	@Override
	public User updateUser(User user) throws AlKhwarizmixException {
		getLogger().debug("updateUser");
		User result = (User) updateObject(user);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = false)
	@Override
	public String updateUserFromXML(String userXml)
			throws AlKhwarizmixException {
		getLogger().trace("updateUserFromXML");
		User newUser = (User) unmarshalObjectFromXML(userXml);
		User updatedUser = updateUser(newUser);
		String result = marshalObjectToXML(updatedUser);
		return result;
	}

	/**
	 * TODO: JAVADOC
	 */
	@SuppressWarnings("unchecked")
	public List<User> getUserList(DetachedCriteria criteriaToUse,
			int firstResult, int maxResult) throws AlKhwarizmixException {
		getLogger().debug("getUserList");
		if (criteriaToUse == null) {
			criteriaToUse = DetachedCriteria.forClass(User.class);
			criteriaToUse.addOrder(Order.asc(User.USERID));
		}

		List<User> result = (List<User>) (List<?>) getObjectList(criteriaToUse,
				firstResult, maxResult);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getUserListAsXML(DetachedCriteria criteria, int firstResult,
			int maxResult) throws AlKhwarizmixException {
		getLogger().trace("getUserListAsXML");
		String result = userListToXML(getUserList(criteria, firstResult,
				maxResult));
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String userListToXML(List<User> userList)
			throws AlKhwarizmixException {
		getLogger().trace("userListToXML");
		String result = "<Users>";
		result += objectListToXML((List<AbstractAlKhwarizmixDomainObject>) (List<?>) userList);
		result += "</Users>";

		getLogger().trace("userListToXML(): returns {}", result);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User connect(User user) throws AlKhwarizmixException {
		getLogger().debug("connect");
		if (getSessionData().getConnectedUser() != null)
			throw getErrorLoginException("connect1.");
		validateUserAndUserId(user, getErrorLoginException("connect2."));
		User result = new User(user.getUserId());
		User existingUser = internal_getUser(user);
		if (existingUser != null)
			result.setName(existingUser.getName());
		getSessionData().setConnectedUser(existingUser != null
				? existingUser
				: result);

		getUserServiceValidator().validateObjectToPublish(result,
				getSessionOwner());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String connectFromXML(String userXml) throws AlKhwarizmixException {
		getLogger().trace("connectFromXML");
		User userToConnect = (User) unmarshalObjectFromXML(userXml);
		User connectedUser = connect(userToConnect);
		String result = marshalObjectToXML(connectedUser);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User login(User user, String password) throws AlKhwarizmixException {
		getLogger().debug("login");
		validateUserAndUserId(user, getErrorLoginException("login1."));
		validateUserIsNotLogged(user, getErrorLoginException("login2."));
		validateUserIsConnected(user, getErrorLoginException("login3."));

		User userToLogin = internal_getUser(user);
		if (userToLogin != null) {
			validateUserPassword(user, password,
					getErrorLoginException("login4."));
		} else {
			throw getErrorLoginException("login5.");
		}

		getSessionData().setLoggedUser(userToLogin);
		getSessionData().setSessionOwner(userToLogin.getDomainObject());

		getUserServiceValidator().validateObjectToPublish(userToLogin,
				getSessionOwner());
		return userToLogin;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String loginFromXML(String userXml, String password)
			throws AlKhwarizmixException {
		getLogger().trace("loginFromXML");
		User userToLogin = (User) unmarshalObjectFromXML(userXml);
		User loggedUser = login(userToLogin, password);
		String result = marshalObjectToXML(loggedUser);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = false)
	@Override
	public User subscribe(User user) throws AlKhwarizmixException {
		getLogger().debug("subscribe");
		validateUserAndUserId(user, getErrorLoginException("subscribe1."));
		validateUserIsNotLogged(user, getErrorLoginException("subscribe2."));
		validateUserIsConnected(user, getErrorLoginException("subscribe3."));

		User subscribedUser = internal_getUser(user);
		if (subscribedUser != null)
			throw getErrorLoginException("subscribe4.");

		addUser(user);
		getSessionData().setLoggedUser(user);
		getSessionData().setSessionOwner(user.getDomainObject());
		sendEmailToAddedUser(user);

		subscribedUser = (User) user.clone();
		getUserServiceValidator().validateObjectToPublish(subscribedUser,
				getSessionOwner());
		return subscribedUser;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = false)
	@Override
	public String subscribeFromXML(String userXml) throws AlKhwarizmixException {
		getLogger().trace("subscribeFromXML");
		User userToSubscribe = (User) unmarshalObjectFromXML(userXml);
		User loggedUser = subscribe(userToSubscribe);
		String result = marshalObjectToXML(loggedUser);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void logout(User user) throws AlKhwarizmixException {
		getLogger().debug("logout");
		User loggedUser = internal_getUser(user);
		if (loggedUser == null)
			throw new AlKhwarizmixException(AlKhwarizmixErrorCode.ERROR_LOGIN);

		getSessionData().resetLoggedUser();
		getSessionData().resetConnectedUser();
		getSessionData().resetSessionOwner();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void logoutFromXML(String userXml) throws AlKhwarizmixException {
		getLogger().trace("logoutFromXML");
		User userToLogout = (User) unmarshalObjectFromXML(userXml);
		logout(userToLogout);
	}

	/**
	 */
	private AlKhwarizmixException getErrorLoginException(String message) {
		return new AlKhwarizmixException(message,
				AlKhwarizmixErrorCode.ERROR_LOGIN);
	}

	/**
	 */
	private void validateUserAndUserId(User user,
			AlKhwarizmixException exception) throws AlKhwarizmixException {
		if (user == null)
			throw exception;
		if (!getUserServiceValidator().isValidUserId(user))
			throw exception;
	}

	/**
	 */
	private void validateUserIsNotLogged(User user,
			AlKhwarizmixException exception) throws AlKhwarizmixException {
		if (getSessionData().getLoggedUser() != null)
			throw exception;
	}

	/**
	 */
	private void validateUserIsConnected(User user,
			AlKhwarizmixException exception) throws AlKhwarizmixException {
		if (getSessionData().getConnectedUser() == null)
			throw exception;
		if (!user.getUserId().equals(
				getSessionData().getConnectedUser().getUserId()))
			throw exception;
	}

	/**
	 */
	private void validateUserPassword(User user, String password,
			AlKhwarizmixException exception) throws AlKhwarizmixException {
		if (!("Mohamed").equals(password))
			throw exception;
	}

	/**
	 */
	private void sendEmailToAddedUser(User user) throws AlKhwarizmixException {
		User infoUser = internal_getUser(new User("fares@dz.moqawalati.com"));
		EMail email = new EMail();
		email.setSender(infoUser);
		email.setReceiver(user);
		email.setBody("Dear " + user.getName()
				+ ", thank you for your subscription. Your password is "
				+ "Mohamed");
		getEmailService().addEMail(email);
	}

	/**
	 * TODO: JAVADOC
	 */
	protected User internal_getUser(User user) throws AlKhwarizmixException {
		getLogger().trace("internal_getUser");
		User result = (User) getObject(user);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final AlKhwarizmixDomainObject getSessionOwner() {
		AlKhwarizmixDomainObject result = new AlKhwarizmixDomainObject();
		result.setId(-1L);
		return result;
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// emailService
	// ----------------------------------

	protected final void setEmailService(IEMailService value) {
		emailService = value;
	}

	protected final IEMailService getEmailService() {
		return emailService;
	}

	// ----------------------------------
	// userDAO
	// ----------------------------------

	private final IUserDAO getMoqawalatiDAO() {
		return userDAO;
	}

	protected final void setUserDAO(IUserDAO value) {
		userDAO = value;
	}

	@Override
	protected final IAlKhwarizmixDAO getServiceDAO() {
		return userDAO;
	}

	// ----------------------------------
	// userServiceValidator
	// ----------------------------------

	protected final void setUserServiceValidator(IUserServiceValidator value) {
		userServiceValidator = value;
	}

	protected final IUserServiceValidator getUserServiceValidator() {
		return userServiceValidator;
	}

	@Override
	protected final IAlKhwarizmixServiceValidator getServiceValidator() {
		return userServiceValidator;
	}

	// ----------------------------------
	// jaxb2Marshaller
	// ----------------------------------

	@Override
	protected Jaxb2Marshaller getJaxb2Marshaller() {
		return jaxb2Marshaller;
	}

	@Override
	protected void setJaxb2Marshaller(Jaxb2Marshaller value) {
		jaxb2Marshaller = value;
	}

} // Class
