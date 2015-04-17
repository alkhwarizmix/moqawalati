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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

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
@Repository
public class UserDAO extends AlKhwarizmixDAOForXMLMarshalling implements
		IUserDAO {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public UserDAO() {
		super();
	}

	@PostConstruct
	private void createDefaultUsers() {
		createDefaultUser("fbelhaouas@icloud.com", "فارس بلحواس");
		createDefaultUser("fares@dz.moqawalati.com", "Fares @ Moqawalati");
		createDefaultUser("jmeter@dz.alkhwarizmix.com", "JMeter Test User");
	}

	private void createDefaultUser(final String userId, final String userName) {
		final User defaultUser = new User(userId, userName);
		try {
			saveOrUpdate(defaultUser);
			getLogger().info("createDefaultUser: Created default user <{}>",
					defaultUser.getName());
		} catch (final AlKhwarizmixDAOException e) {
			getLogger().warn(
					"createDefaultUser: default user <{}> already existing",
					defaultUser.getName());
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
			final Criteria criteria = getHibernateTemplate()
					.getSessionFactory().getCurrentSession()
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
				final Criteria criteria = getHibernateTemplate()
						.getSessionFactory().getCurrentSession()
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
