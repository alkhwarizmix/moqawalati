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
import dz.alkhwarizmix.framework.java.domain.AlKhwarizmixDomainObjectAbstract;
import dz.alkhwarizmix.framework.java.dtos.user.model.vo.User;
import dz.alkhwarizmix.framework.java.interfaces.IAlKhwarizmixDAO;
import dz.alkhwarizmix.framework.java.interfaces.IUserDAO;
import dz.alkhwarizmix.framework.java.interfaces.IUserService;
import dz.alkhwarizmix.framework.java.model.AlKhwarizmixSessionData;

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
public class UserService extends AlKhwarizmixService implements IUserService {

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

	private static final Logger LOG = LoggerFactory
			.getLogger(UserService.class);

	@Override
	protected Logger getLogger() {
		return LOG;
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@Autowired
	private IUserDAO userDAO;

	@Autowired
	private Jaxb2Marshaller jaxb2Marshaller;

	@Autowired
	private AlKhwarizmixSessionData sessionData;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	@Transactional(readOnly = false)
	@Override
	public void addUser(User user) throws AlKhwarizmixException {
		getLogger().debug("addUser");

		setupObjectExtendedDataXMLValue(user);
		addObject(user);
	}

	/**
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
	 */
	@Override
	public AlKhwarizmixDomainObjectAbstract getObject(
			AlKhwarizmixDomainObjectAbstract object)
			throws AlKhwarizmixException {
		getLogger().trace("getObject");

		User result = getMoqawalatiDAO().getUser((User) object);
		updateObjectFromExtendedDataXML(result);
		return result;
	}

	/**
	 */
	@Override
	public User getUser(User user) throws AlKhwarizmixException {
		getLogger().debug("getUser");

		User result = internal_getUser(user);
		nullifyProtectedProperties(result);
		return result;
	}

	/**
	 */
	User internal_getUser(User user) throws AlKhwarizmixException {
		getLogger().trace("internal_getUser");

		User result = (User) getObject(user);
		return result;
	}

	/**
	 */
	@Override
	public String getUserAsXML(User user) throws AlKhwarizmixException {
		getLogger().trace("getUserAsXML 1");

		String result = getObjectAsXML(user);
		return result;
	}

	/**
	 */
	@Override
	public String getUserAsXML(String userXml) throws AlKhwarizmixException {
		getLogger().trace("getUserAsXML 2");

		String result = getObjectAsXML(userXml);
		return result;
	}

	/**
	 */
	@Transactional(readOnly = false)
	@Override
	public User updateUser(User user) throws AlKhwarizmixException {
		getLogger().debug("updateUser");

		setupObjectExtendedDataXMLValue(user);
		User result = (User) updateObject(user);
		return result;
	}

	/**
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
	 */
	@SuppressWarnings("unchecked")
	public List<User> getUserList(DetachedCriteria criteria, int firstResult,
			int maxResult) throws AlKhwarizmixException {
		getLogger().debug("getUserList");

		if (criteria == null) {
			criteria = DetachedCriteria.forClass(User.class);
			criteria.addOrder(Order.asc(User.USERID));
		}

		List<User> result = (List<User>) (List<?>) getObjectList(criteria,
				firstResult, maxResult);
		return result;
	}

	/**
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
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String userListToXML(List<User> userList) {

		String result = "<Users>";
		result += objectListToXML((List<AlKhwarizmixDomainObjectAbstract>) (List<?>) userList);
		result += "</Users>";

		getLogger().trace("userListToXML(): returns {}", result);
		return result;
	}

	@Override
	public User login(User user) throws AlKhwarizmixException {
		getLogger().debug("login");

		User loggedUser = internal_getUser(user);
		if (loggedUser == null)
			throw new AlKhwarizmixException(AlKhwarizmixErrorCode.ERROR_LOGIN);

		getSessionData().setCustomizer(loggedUser.getDomainObject());

		return loggedUser;
	}

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

	@Override
	public void logout(User user) throws AlKhwarizmixException {
		getLogger().debug("logout");

		User loggedUser = internal_getUser(user);
		if (loggedUser == null)
			throw new AlKhwarizmixException(AlKhwarizmixErrorCode.ERROR_LOGIN);

		getSessionData().resetCustomizer();
	}

	@Override
	protected void nullifyProtectedProperties(
			AlKhwarizmixDomainObjectAbstract object) {
		super.nullifyProtectedProperties(object);
		User user = (User) object;
		if (user != null) {
			user.setDomainObject(null);
		}
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// userDAO
	// ----------------------------------

	final IUserDAO getMoqawalatiDAO() {
		return userDAO;
	}

	final void setUserDAO(IUserDAO value) {
		userDAO = value;
	}

	@Override
	protected IAlKhwarizmixDAO getServiceDAO() {
		return userDAO;
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

	// ----------------------------------
	// sessionData
	// ----------------------------------

	final AlKhwarizmixSessionData getSessionData() {
		return sessionData;
	}

	final void setSessionData(AlKhwarizmixSessionData value) {
		sessionData = value;
	}

} // Class