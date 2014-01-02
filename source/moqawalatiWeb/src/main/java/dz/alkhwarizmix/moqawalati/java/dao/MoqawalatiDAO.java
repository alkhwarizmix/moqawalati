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

package dz.alkhwarizmix.moqawalati.java.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.dao.AlKhwarizmixDAO;
import dz.alkhwarizmix.framework.java.dao.AlKhwarizmixDAOException;
import dz.alkhwarizmix.framework.java.domain.AlKhwarizmixDomainObjectAbstract;
import dz.alkhwarizmix.framework.java.dtos.customize.model.vo.CustomData;
import dz.alkhwarizmix.framework.java.dtos.customize.model.vo.CustomDataPart;
import dz.alkhwarizmix.moqawalati.java.MoqawalatiException;
import dz.alkhwarizmix.moqawalati.java.dtos.modules.clientModule.model.vo.Client;
import dz.alkhwarizmix.moqawalati.java.dtos.modules.userModule.model.vo.User;
import dz.alkhwarizmix.moqawalati.java.interfaces.IMoqawalatiDAO;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٥ ذو القعدة ١٤٣٤ (October 01, 2013)
 */
@Repository
public class MoqawalatiDAO extends AlKhwarizmixDAO implements IMoqawalatiDAO {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public MoqawalatiDAO() {
		// NOOP
	}

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	private static final Logger LOG = LoggerFactory
			.getLogger(MoqawalatiDAO.class);

	protected Logger getLogger() {
		return LOG;
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@Autowired
	private HibernateTemplate hibernateTemplate;

	// --------------------------------------------------------------------------
	//
	// Overridden Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	@Override
	public void saveOrUpdate(AlKhwarizmixDomainObjectAbstract object)
			throws AlKhwarizmixDAOException {
		getLogger().trace("saveOrUpdate({})", object);

		try {
			for (AlKhwarizmixDomainObjectAbstract cursor : object
					.getDaoObjectList()) {
				object.beforeDaoSaveOrUpdate(cursor);
				getHibernateTemplate().saveOrUpdate(cursor);
			}
			String s = "Success";
			s = s + "";
		} catch (ConcurrencyFailureException e) {
			throw getDAOExceptionForConcurrencyFailure(e);
		} catch (DataAccessException e) {
			throw getDAOExceptionForDataAccess(e);
		} catch (Exception e) {
			throw getDAOException(e);
		}
	}

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	public Client getClient(Client client) throws MoqawalatiException {
		getLogger().trace("getClient()");

		try {
			String clientId = client.getClientId();
			Criteria criteria = getHibernateTemplate().getSessionFactory()
					.getCurrentSession().createCriteria(Client.class);
			criteria.add(Restrictions.eq(Client.CLIENTID, clientId));
			return (Client) criteria.uniqueResult();
		} catch (DataAccessException e) {
			MoqawalatiException ex = new MoqawalatiException(
					AlKhwarizmixErrorCode.ERROR_DATABASE, e);
			throw ex;
		}
	}

	/**
	 */
	public User getUser(User user) throws MoqawalatiException {
		getLogger().trace("getUser()");

		try {
			String userId = user.getUserId();
			Criteria criteria = getHibernateTemplate().getSessionFactory()
					.getCurrentSession().createCriteria(User.class);
			criteria.add(Restrictions.eq(User.USERID, userId));
			return (User) criteria.uniqueResult();
		} catch (DataAccessException e) {
			MoqawalatiException ex = new MoqawalatiException(
					AlKhwarizmixErrorCode.ERROR_DATABASE, e);
			throw ex;
		}
	}

	/**
	 */
	public CustomData getCustomData(CustomData customData)
			throws MoqawalatiException {
		getLogger().trace("getCustomData()");

		try {
			Criteria criteria = getHibernateTemplate().getSessionFactory()
					.getCurrentSession().createCriteria(CustomData.class);
			Criterion criter1 = Restrictions.eq(CustomData.CUSTOMDATAID,
					customData.getCustomDataId());
			Criterion criter2 = Restrictions.eq(CustomData.CUSTOMIZER,
					customData.getCustomizer().getId());
			criteria.add(Restrictions.and(criter1, criter2));
			customData = (CustomData) criteria.uniqueResult();

			if (customData != null)
				customData.setCustomDataParts(getCustomDataParts(customData));

			return customData;
		} catch (DataAccessException e) {
			MoqawalatiException ex = new MoqawalatiException(
					AlKhwarizmixErrorCode.ERROR_DATABASE, e);
			throw ex;
		}
	}

	/**
	 */
	private List<CustomDataPart> getCustomDataParts(CustomData customData) {

		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createCriteria(CustomDataPart.class);
		criteria.createCriteria(CustomDataPart.CUSTOMDATA).add(
				Restrictions.eq(CustomData.ID, customData.getId()));
		List<CustomDataPart> customDataParts = criteria.list();

		getLogger().debug("getCustomDataParts: customDataParts.size {}",
				(customDataParts == null) ? null : customDataParts.size());
		return customDataParts;
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// hibernateTemplate
	// ----------------------------------

	protected HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	protected void setHibernateTemplate(HibernateTemplate value) {
		hibernateTemplate = value;
	}

} // Class