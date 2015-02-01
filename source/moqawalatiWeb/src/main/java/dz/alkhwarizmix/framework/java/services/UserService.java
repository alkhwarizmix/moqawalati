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

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	private static Logger logger = null;

	@Override
	protected Logger getLogger() {
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
	private IUserServiceValidator userValidator;

	@Autowired
	private EMailService emailService;

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
	public String addUserFromXML(String userXml, String creatorId)
			throws AlKhwarizmixException {
		getLogger().trace("addUserFromXML");

		User newUser = (User) unmarshalObjectFromXML(userXml);
		// newUser.setCreatorId(creatorId);
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
		getServiceValidator()
				.validateObjectToPublish(result, getSessionOwner());
		return result;
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
	public String updateUserFromXML(String userXml, String updaterId)
			throws AlKhwarizmixException {
		getLogger().trace("updateUserFromXML");
		User newUser = (User) unmarshalObjectFromXML(userXml);
		// newUser.setCreatorId(updaterId);
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
	public String userListToXML(List<User> userList) {
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
	public User login(User user) throws AlKhwarizmixException {
		getLogger().debug("login");

		User loggedUser = internal_getUser(user);
		if (loggedUser == null)
			throw new AlKhwarizmixException(AlKhwarizmixErrorCode.ERROR_LOGIN);

		getSessionData().setSessionOwner(loggedUser.getDomainObject());

		if ("fbelhaouas@icloud.com".equals(loggedUser.getUserId())) {
			User infoUser = internal_getUser(new User("fares@dz.moqawalati.com"));
			EMail email = new EMail();
			email.setSender(infoUser);
			email.setReceiver(loggedUser);
			email.setBody("Dear " + loggedUser.getName()
					+ ", thank you for placing order. Your order number is "
					+ "12345");
			emailService.sendEMail(email);
		}

		return loggedUser;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String loginFromXML(String userXml, String loggerId)
			throws AlKhwarizmixException {
		getLogger().trace("loginFromXML");

		User userToLogin = (User) unmarshalObjectFromXML(userXml);
		// newUser.setCreatorId(updaterId);
		User loggedUser = login(userToLogin);
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

		getSessionData().resetSessionOwner();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AlKhwarizmixDomainObject getSessionOwner() {
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
	// userDAO
	// ----------------------------------

	private final IUserDAO getMoqawalatiDAO() {
		return userDAO;
	}

	protected final void setUserDAO(IUserDAO value) {
		userDAO = value;
	}

	@Override
	protected IAlKhwarizmixDAO getServiceDAO() {
		return userDAO;
	}

	// ----------------------------------
	// userValidator
	// ----------------------------------

	protected final void setUserValidator(IUserServiceValidator value) {
		userValidator = value;
	}

	@Override
	protected IAlKhwarizmixServiceValidator getServiceValidator() {
		return userValidator;
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
