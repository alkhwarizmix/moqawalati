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

package dz.alkhwarizmix.framework.java.dao;

import javax.annotation.PostConstruct;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dtos.user.model.vo.User;
import dz.alkhwarizmix.framework.java.interfaces.IUserDAO;

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

	/*@PostConstruct
	private void createDefaultUsers() {
		User defaultUser = new User("fares.belhaouas", "فارس بلحواس");

		try {
			saveOrUpdate(defaultUser);
			getLogger().info("createDefaultUser: Created default user <{0}>",
					defaultUser.getName());
		} catch (AlKhwarizmixDAOException e) {
			getLogger().warn(
					"createDefaultUser: default user <{0}> already existing",
					defaultUser.getName());
		}
	}*/

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
			String userId = userToGet.getUserId();
			Criteria criteria = getHibernateTemplate().getSessionFactory()
					.getCurrentSession().createCriteria(User.class);
			criteria.add(Restrictions.eq(User.USERID, userId));
			userToGet = (User) criteria.uniqueResult();

			if (userToGet != null)
				userToGet.setExtendedData(getExtendedData(userToGet
						.getExtendedData()));

			return userToGet;
		} catch (DataAccessException e) {
			AlKhwarizmixException ex = new AlKhwarizmixException(
					AlKhwarizmixErrorCode.ERROR_DATABASE, e);
			throw ex;
		}
	}

} // Class