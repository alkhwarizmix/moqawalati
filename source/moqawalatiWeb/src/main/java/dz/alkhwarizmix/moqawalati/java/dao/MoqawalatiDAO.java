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
import dz.alkhwarizmix.framework.java.dtos.extend.model.vo.ExtendedData;
import dz.alkhwarizmix.framework.java.dtos.extend.model.vo.ExtendedDataPart;
import dz.alkhwarizmix.moqawalati.java.MoqawalatiException;
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
	private ExtendedData getExtendedData(ExtendedData extendedData)
			throws MoqawalatiException {
		getLogger().trace("getExtendedData()");

		if (extendedData == null)
			return null;

		try {
			Criteria criteria = getHibernateTemplate().getSessionFactory()
					.getCurrentSession().createCriteria(ExtendedData.class);
			Criterion criter1 = Restrictions.eq(ExtendedData.ID,
					extendedData.getId());
			criteria.add(criter1);
			extendedData = (ExtendedData) criteria.uniqueResult();

			if (extendedData != null) {
				extendedData
						.setExtendedDataParts(getExtendedDataParts(extendedData));
			}

			return extendedData;
		} catch (DataAccessException e) {
			MoqawalatiException ex = new MoqawalatiException(
					AlKhwarizmixErrorCode.ERROR_DATABASE, e);
			throw ex;
		}
	}

	/**
	 */
	private List<ExtendedDataPart> getExtendedDataParts(
			ExtendedData extendedData) {

		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createCriteria(ExtendedDataPart.class);
		criteria.createCriteria(ExtendedDataPart.EXTENDEDDATA).add(
				Restrictions.eq(ExtendedData.ID, extendedData.getId()));
		List<ExtendedDataPart> extendedDataParts = criteria.list();

		getLogger().debug("getExtendedDataParts: extendedDataParts.size {}",
				(extendedDataParts == null)
						? null
						: extendedDataParts.size());
		return extendedDataParts;
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// hibernateTemplate
	// ----------------------------------

	@Override
	protected HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Override
	protected void setHibernateTemplate(HibernateTemplate value) {
		hibernateTemplate = value;
	}

} // Class