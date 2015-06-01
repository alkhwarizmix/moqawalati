////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٥ هجري، فارس بلحواس (Copyright 2014 Fares Belhaouas)
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.framework.java.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dao.IUserDAO;
import dz.alkhwarizmix.framework.java.dtos.security.model.vo.Password;
import dz.alkhwarizmix.framework.java.dtos.security.model.vo.User;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٢ شعبان ١٤٣٥ (June 10, 2014)
 */
public class UserDAO extends AbstractAlKhwarizmixDAOForXMLMarshalling implements
		IUserDAO {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public UserDAO(final List<User> defaultUserList) {
		super();
		this.defaultUserList = defaultUserList;
	}

	@PostConstruct
	private void createDefaultUsers() {
		Session session = null;
		Transaction trans = null;
		boolean isOpenSession = false;
		try {
			session = getHibernateCurrentSession();
			if (session == null) {
				session = getSessionFactory().openSession();
				isOpenSession = true;
			}
			trans = session.beginTransaction();
			for (final User user : defaultUserList)
				createDefaultUser(user, session);
			trans.commit();
		} catch (final RuntimeException e) {
			trans.rollback();
		} finally {
			if (isOpenSession)
				session.close();
		}
	}

	private void createDefaultUser(final User user, final Session session) {
		try {
			saveOrUpdate(user, session);
			getLogger().info("createDefaultUser: Created default user <{}>",
					user.getName());
		} catch (final AlKhwarizmixDAOException e) {
			getLogger().warn(
					"createDefaultUser: default user <{}> already existing",
					user.getName());
		}
	}

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	private static final Logger LOG = LoggerFactory.getLogger(UserDAO.class);

	@Override
	protected Logger getLogger() {
		return LOG;
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	private final List<User> defaultUserList;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	@Override
	public User getUser(User userToGet) throws AlKhwarizmixException {
		getLogger().trace("getUser()");

		try {
			final String userId = userToGet.getUserId();
			final Criteria criteria = getHibernateCurrentSession()
					.createCriteria(User.class);
			criteria.add(Restrictions.eq(User.USERID, userId));
			userToGet = (User) criteria.uniqueResult();

			return userToGet;
		} catch (final DataAccessException e) {
			final AlKhwarizmixException ex = new AlKhwarizmixException(
					AlKhwarizmixErrorCode.ERROR_DATABASE, e);
			throw ex;
		}
	}

	/**
	 */
	@Override
	public List<Password> getUserPasswords(User user)
			throws AlKhwarizmixException {
		getLogger().trace("getUserPasswords()");

		try {
			if (user.getId() == null)
				user = getUser(user);
			final List<Password> result = new ArrayList<Password>();
			if (user != null) {
				// TODO: TO ENHANCE
				final Criteria criteria = getHibernateCurrentSession()
						.createCriteria(Password.class);
				criteria.add(Restrictions.eq(Password.USERID, user.getId()));
				criteria.addOrder(Order.desc(Password.CREATED));
				// criteria.add(Restrictions.and(criter1, criter2));
				fillLastAddedPasswords(result, criteria.list());
			}
			return result;
		} catch (final DataAccessException e) {
			final AlKhwarizmixException ex = new AlKhwarizmixException(
					AlKhwarizmixErrorCode.ERROR_DATABASE, e);
			throw ex;
		}
	}

	private void fillLastAddedPasswords(final List<Password> result,
			final List<Password> foundPasswords) {
		for (final Password password : foundPasswords) {
			result.add(password);
			// Continue to add unused passwords until we found one used
			if (password.getLastUse() != null)
				break; // stop for
		}
	}

} // Class
