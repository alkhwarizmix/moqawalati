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

package dz.alkhwarizmix.framework.java.services.impl;

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
import dz.alkhwarizmix.framework.java.dao.IAlKhwarizmixDAO;
import dz.alkhwarizmix.framework.java.dao.IUserDAO;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.email.model.vo.EMail;
import dz.alkhwarizmix.framework.java.dtos.security.model.vo.Password;
import dz.alkhwarizmix.framework.java.dtos.security.model.vo.User;
import dz.alkhwarizmix.framework.java.security.ISecurityManager;
import dz.alkhwarizmix.framework.java.services.IAlKhwarizmixServiceValidator;
import dz.alkhwarizmix.framework.java.services.IEMailService;
import dz.alkhwarizmix.framework.java.services.IUserService;
import dz.alkhwarizmix.framework.java.services.IUserServiceValidator;
import dz.alkhwarizmix.framework.java.utils.DateUtil;

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
	// Constructors
	//
	// --------------------------------------------------------------------------

	public UserService() {
		super();
	}

	protected UserService(final Logger theLogger) {
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

	@Autowired
	private ISecurityManager securityManager;

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
	public User addUser(final User user, final boolean validateObjectToPublish)
			throws AlKhwarizmixException {
		getLogger().trace("addUser");
		final User result = (User) addObject(user, validateObjectToPublish);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = false)
	@Override
	public String addUserFromXML(final String userXml)
			throws AlKhwarizmixException {
		getLogger().trace("addUserFromXML");
		final User newUser = (User) unmarshalObjectFromXML(userXml);
		final User addedUser = addUser(newUser, true);
		final String result = marshalObjectToXML(addedUser);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AbstractAlKhwarizmixDomainObject getObject(
			final AbstractAlKhwarizmixDomainObject object,
			final boolean validateObjectToPublish) throws AlKhwarizmixException {
		getLogger().trace("getObject");
		User result = getUserDAO().getUser((User) object);
		if (validateObjectToPublish && (result != null)) {
			result = (User) result.clone();
			getServiceValidator().validateObjectToPublish(result,
					getSessionOwner());
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User getUser(final User user, final boolean validateObjectToPublish)
			throws AlKhwarizmixException {
		getLogger().trace("getUser");
		final User result = (User) getObject(user, validateObjectToPublish);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getUserAsXML(final User user) throws AlKhwarizmixException {
		getLogger().trace("getUserAsXML 1");
		final String result = getObjectAsXML(user);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getUserAsXML(final String userXml)
			throws AlKhwarizmixException {
		getLogger().trace("getUserAsXML 2");
		final String result = getObjectAsXML(userXml);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = false)
	@Override
	public User updateUser(final User user,
			final boolean validateObjectToPublish) throws AlKhwarizmixException {
		getLogger().trace("updateUser");
		final User result = (User) updateObject(user, getSessionOwner(),
				validateObjectToPublish);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = false)
	@Override
	public String updateUserFromXML(final String userXml)
			throws AlKhwarizmixException {
		getLogger().trace("updateUserFromXML");
		final User newUser = (User) unmarshalObjectFromXML(userXml);
		final User updatedUser = updateUser(newUser, true);
		final String result = marshalObjectToXML(updatedUser);
		return result;
	}

	/**
	 * TODO: JAVADOC
	 */
	@SuppressWarnings("unchecked")
	public List<User> getUserList(DetachedCriteria criteriaToUse,
			final int firstResult, final int maxResult)
			throws AlKhwarizmixException {
		getLogger().trace("getUserList");
		if (criteriaToUse == null) {
			criteriaToUse = DetachedCriteria.forClass(User.class);
			criteriaToUse.addOrder(Order.asc(User.USERID));
		}

		final List<User> result = (List<User>) (List<?>) getObjectList(
				criteriaToUse, firstResult, maxResult, true);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getUserListAsXML(final DetachedCriteria criteria,
			final int firstResult, final int maxResult)
			throws AlKhwarizmixException {
		getLogger().trace("getUserListAsXML");
		final String result = userListToXML(getUserList(criteria, firstResult,
				maxResult));
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String userListToXML(final List<User> userList)
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
	public User connect(final User user, final boolean validateObjectToPublish)
			throws AlKhwarizmixException {
		getLogger().trace("connect");

		if (getSessionData().getConnectedUser() != null)
			throw getErrorLoginException("connect1.");
		validateUserAndUserId(user, getErrorLoginException("connect2."));
		User result = new User(user.getUserId());
		final User existingUser = getUser(user, false);
		if (existingUser != null)
			result.setName(existingUser.getName());
		getSessionData().setConnectedUser(existingUser != null
				? existingUser
				: result);

		if (validateObjectToPublish && (result != null)) {
			result = (User) result.clone();
			getUserServiceValidator().validateObjectToPublish(result,
					getSessionOwner());
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String connectFromXML(final String userXml)
			throws AlKhwarizmixException {
		getLogger().trace("connectFromXML");
		final User userToConnect = (User) unmarshalObjectFromXML(userXml);
		final User connectedUser = connect(userToConnect, true);
		final String result = marshalObjectToXML(connectedUser);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User login(final User user, final String password,
			final boolean validateObjectToPublish) throws AlKhwarizmixException {
		getLogger().trace("login");
		try {
			validateUserAndUserId(user, getErrorLoginException("login1."));
			validateUserIsNotLogged(user, getErrorLoginException("login2."));
			validateUserIsConnected(user, getErrorLoginException("login3."));

			final User userToLogin = getUser(user, false);
			if (userToLogin != null)
				validateUserPassword(user, password,
						getErrorLoginException("login4."));
			else
				throw getErrorLoginException("login5.");

			getSessionData().setLoggedUser(userToLogin);
			getSessionData().setSessionOwner(userToLogin.getDomainObject());

			User result = userToLogin;
			if (validateObjectToPublish && (result != null)) {
				result = (User) result.clone();
				getUserServiceValidator().validateObjectToPublish(result,
						getSessionOwner());
			}
			return result;
		} catch (final AlKhwarizmixException e) {
			getSecurityManager().validateRemoteAddrRestrictionForWrongLogin();
			throw e;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String loginFromXML(final String userXml, final String password)
			throws AlKhwarizmixException {
		getLogger().trace("loginFromXML");
		final User userToLogin = (User) unmarshalObjectFromXML(userXml);
		final User loggedUser = login(userToLogin, password, true);
		final String result = marshalObjectToXML(loggedUser);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = false)
	@Override
	public User subscribe(final User user, final boolean validateObjectToPublish)
			throws AlKhwarizmixException {
		getLogger().trace("subscribe");
		validateUserAndUserId(user, getErrorLoginException("subscribe1."));
		validateUserIsNotLogged(user, getErrorLoginException("subscribe2."));
		validateUserIsConnected(user, getErrorLoginException("subscribe3."));
		validateRemoteAddrRestriction(getErrorLoginException("subscribe4-1."));

		User subscribedUser = getUser(user, false);
		if (subscribedUser != null)
			throw getErrorLoginException("subscribe5-1.");

		subscribedUser = addUser(user, false);
		Password generatedPassword = new Password(subscribedUser);
		generatedPassword.setPassword("Mohamed"
				+ new DateUtil().newDate().getTime());
		generatedPassword = (Password) addObject(generatedPassword, false);

		getSessionData().setLoggedUser(subscribedUser);
		getSessionData().setSessionOwner(subscribedUser.getDomainObject());
		sendEmailToAddedUser(subscribedUser, generatedPassword);

		if (validateObjectToPublish && (subscribedUser != null)) {
			subscribedUser = (User) subscribedUser.clone();
			getUserServiceValidator().validateObjectToPublish(subscribedUser,
					getSessionOwner());
		}
		return subscribedUser;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = false)
	@Override
	public String subscribeFromXML(final String userXml)
			throws AlKhwarizmixException {
		getLogger().trace("subscribeFromXML");
		final User userToSubscribe = (User) unmarshalObjectFromXML(userXml);
		final User loggedUser = subscribe(userToSubscribe, true);
		final String result = marshalObjectToXML(loggedUser);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void logout(final User user) throws AlKhwarizmixException {
		getLogger().trace("logout");
		// final User loggedUser = getUser(user, false);
		// if (loggedUser == null)
		// throw new AlKhwarizmixException(AlKhwarizmixErrorCode.ERROR_LOGIN);

		getSessionData().resetLoggedUser();
		getSessionData().resetConnectedUser();
		getSessionData().resetSessionOwner();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void logoutFromXML(final String userXml)
			throws AlKhwarizmixException {
		getLogger().trace("logoutFromXML");
		final User userToLogout = (User) unmarshalObjectFromXML(userXml);
		logout(userToLogout);
	}

	/**
	 */
	private AlKhwarizmixException getErrorLoginException(final String message) {
		return new AlKhwarizmixException(message,
				AlKhwarizmixErrorCode.ERROR_LOGIN);
	}

	/**
	 */
	private void validateUserAndUserId(final User user,
			final AlKhwarizmixException exception) throws AlKhwarizmixException {
		if (user == null)
			throw exception;
		if (!getUserServiceValidator().isValidUserId(user))
			throw exception;
	}

	/**
	 */
	private void validateRemoteAddrRestriction(
			final AlKhwarizmixException exception) throws AlKhwarizmixException {
		if (!getSecurityManager()
				.validateRemoteAddrRestrictionForSubscription())
			throw exception;
	}

	/**
	 */
	private void validateUserIsNotLogged(final User user,
			final AlKhwarizmixException exception) throws AlKhwarizmixException {
		if (getSessionData().getLoggedUser() != null)
			throw exception;
	}

	/**
	 */
	private void validateUserIsConnected(final User user,
			final AlKhwarizmixException exception) throws AlKhwarizmixException {
		if (getSessionData().getConnectedUser() == null)
			throw exception;
		if (!user.getUserId().equals(
				getSessionData().getConnectedUser().getUserId()))
			throw exception;
	}

	/**
	 */
	private Password validateUserPassword(final User user,
			final String password, final AlKhwarizmixException exception)
			throws AlKhwarizmixException {
		if (validateUserPasswordForJMeter(user, password))
			return null;

		for (final Password result : getUserDAO().getUserPasswords(user))
			if (result.getPassword().equals(password))
				return result;
		throw exception;
	}

	/**
	 */
	protected boolean validateUserPasswordForJMeter(final User user,
			final String password) throws AlKhwarizmixException {
		boolean result = false;
		if (user.isJMeterTestUser() && ("Mohamed".equals(password)))
			result = true;
		return result;
	}

	/**
	 */
	private void sendEmailToAddedUser(final User user, final Password password)
			throws AlKhwarizmixException {
		final User infoUser = getUser(new User("fares@dz.moqawalati.com"),
				false);
		final EMail email = new EMail();
		email.setSender(infoUser);
		email.setReceiver(user);
		email.setBody("Dear " + user.getName()
				+ ", thank you for your subscription. Your password is "
				+ password.getPassword());
		getEmailService().addEMail(email, false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final AlKhwarizmixDomainObject getSessionOwner() {
		final AlKhwarizmixDomainObject result = new AlKhwarizmixDomainObject();
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

	protected final void setEmailService(final IEMailService value) {
		emailService = value;
	}

	protected final IEMailService getEmailService() {
		return emailService;
	}

	// ----------------------------------
	// userDAO
	// ----------------------------------

	private final IUserDAO getUserDAO() {
		return userDAO;
	}

	protected final void setUserDAO(final IUserDAO value) {
		userDAO = value;
	}

	@Override
	protected final IAlKhwarizmixDAO getServiceDAO() {
		return userDAO;
	}

	// ----------------------------------
	// userServiceValidator
	// ----------------------------------

	protected final void setUserServiceValidator(
			final IUserServiceValidator value) {
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
	protected void setJaxb2Marshaller(final Jaxb2Marshaller value) {
		jaxb2Marshaller = value;
	}

	// ----------------------------------
	// securityManager
	// ----------------------------------

	private ISecurityManager getSecurityManager() {
		return securityManager;
	}

	protected final void setSecurityManager(final ISecurityManager value) {
		securityManager = value;
	}

} // Class
