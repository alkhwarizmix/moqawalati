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

package dz.alkhwarizmix.moqawalati.java.services;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AlKhwarizmixDomainObjectAbstract;
import dz.alkhwarizmix.framework.java.interfaces.IAlKhwarizmixDAO;
import dz.alkhwarizmix.framework.java.services.AlKhwarizmixService;
import dz.alkhwarizmix.moqawalati.java.MoqawalatiException;
import dz.alkhwarizmix.moqawalati.java.dtos.modules.userModule.model.vo.User;
import dz.alkhwarizmix.moqawalati.java.interfaces.IMoqawalatiDAO;
import dz.alkhwarizmix.moqawalati.java.interfaces.IUserService;

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

	protected Logger getLogger() {
		return LOG;
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@Autowired
	private IMoqawalatiDAO moqawalatiDAO;

	@Autowired
	private Jaxb2Marshaller jaxb2Marshaller;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	@Transactional(readOnly = false)
	public void addUser(User user) throws MoqawalatiException {
		getLogger().trace("addUser");

		try {
			addObject(user);
		} catch (AlKhwarizmixException e) {
			MoqawalatiException ex = new MoqawalatiException(e);
			throw ex;
		}
	}

	/**
	 */
	@Transactional(readOnly = false)
	public String addUserFromXML(String userXml, String creatorId)
			throws MoqawalatiException {
		getLogger().trace("addUserFromXML");

		try {
			User newUser = (User) unmarshalObjectFromXML(userXml);
			newUser.setCreatorId(creatorId);
			addUser(newUser);
			String result = marshalObjectToXML(newUser);
			return result;
		} catch (AlKhwarizmixException e) {
			MoqawalatiException ex = new MoqawalatiException(e);
			throw ex;
		}
	}

	/**
	 */
	@Override
	public AlKhwarizmixDomainObjectAbstract getObject(
			AlKhwarizmixDomainObjectAbstract object)
			throws AlKhwarizmixException {
		getLogger().trace("getObject");

		try {
			User result = getMoqawalatiDAO().getUser((User) object);
			return result;
		} catch (AlKhwarizmixException e) {
			throw e;
		}
	}

	/**
	 */
	@Override
	public User getUser(User user) throws MoqawalatiException {
		getLogger().trace("getUser");

		try {
			return (User) getObject(user);
		} catch (AlKhwarizmixException e) {
			MoqawalatiException ex = new MoqawalatiException(e);
			throw ex;
		}
	}

	/**
	 */
	@Override
	public String getUserAsXML(User user) throws MoqawalatiException {
		getLogger().trace("getUserAsXML 1");

		try {
			String result = getObjectAsXML(user);
			return result;
		} catch (AlKhwarizmixException e) {
			MoqawalatiException ex = new MoqawalatiException(e);
			throw ex;
		}
	}

	/**
	 */
	@Override
	public String getUserAsXML(String userXml) throws MoqawalatiException {
		getLogger().trace("getUserAsXML 2");

		try {
			String result = getObjectAsXML(userXml);
			return result;
		} catch (AlKhwarizmixException e) {
			MoqawalatiException ex = new MoqawalatiException(e);
			throw ex;
		}
	}

	/**
	 */
	@Transactional(readOnly = false)
	public User updateUser(User user) throws MoqawalatiException {
		getLogger().trace("updateUser");

		try {
			User result = (User) updateObject(user);
			return result;
		} catch (AlKhwarizmixException e) {
			MoqawalatiException ex = new MoqawalatiException(e);
			throw ex;
		}
	}

	/**
	 */
	@Transactional(readOnly = false)
	public String updateUserFromXML(String userXml, String updaterId)
			throws MoqawalatiException {
		getLogger().trace("updateUserFromXML");

		try {
			User newUser = (User) unmarshalObjectFromXML(userXml);
			newUser.setCreatorId(updaterId);
			User updatedUser = updateUser(newUser);
			String result = marshalObjectToXML(updatedUser);
			return result;
		} catch (AlKhwarizmixException e) {
			MoqawalatiException ex = new MoqawalatiException(e);
			throw ex;
		}
	}

	/**
	 */
	@SuppressWarnings("unchecked")
	public List<User> getUserList(DetachedCriteria criteria, int firstResult,
			int maxResult) throws MoqawalatiException {
		getLogger().trace("getUserList");

		if (criteria == null) {
			criteria = DetachedCriteria.forClass(User.class);
			criteria.addOrder(Order.asc(User.USERID));
		}

		try {
			List<User> result = (List<User>) (List<?>) getObjectList(criteria,
					firstResult, maxResult);
			return result;
		} catch (AlKhwarizmixException e) {
			MoqawalatiException ex = new MoqawalatiException(e);
			throw ex;
		}
	}

	/**
	 */
	public String getUserListAsXML(DetachedCriteria criteria, int firstResult,
			int maxResult) throws MoqawalatiException {
		getLogger().trace("getUserListAsXML");

		String result = userListToXML(getUserList(criteria, firstResult,
				maxResult));
		return result;
	}

	/**
	 */
	@SuppressWarnings("unchecked")
	public String userListToXML(List<User> userList) {

		String result = "<Users>";
		result += objectListToXML((List<AlKhwarizmixDomainObjectAbstract>) (List<?>) userList);
		result += "</Users>";

		getLogger().trace("userListToXML(): returns {}", result);
		return result;
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// moqawalatiDAO
	// ----------------------------------

	protected IMoqawalatiDAO getMoqawalatiDAO() {
		return moqawalatiDAO;
	}

	protected void setMoqawalatiDAO(IMoqawalatiDAO value) {
		moqawalatiDAO = value;
	}

	@Override
	protected IAlKhwarizmixDAO getServiceDAO() {
		return moqawalatiDAO;
	}

	// ----------------------------------
	// jaxb2Marshaller
	// ----------------------------------

	protected Jaxb2Marshaller getJaxb2Marshaller() {
		return jaxb2Marshaller;
	}

	protected void setJaxb2Marshaller(Jaxb2Marshaller value) {
		jaxb2Marshaller = value;
	}

} // Class