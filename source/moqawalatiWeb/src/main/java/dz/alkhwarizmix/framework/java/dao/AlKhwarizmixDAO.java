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

package dz.alkhwarizmix.framework.java.dao;

import java.util.List;

import org.hibernate.classic.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.domain.AlKhwarizmixDomainObjectAbstract;
import dz.alkhwarizmix.framework.java.interfaces.IAlKhwarizmixDAO;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٥ ذو القعدة ١٤٣٤ (October 01, 2013)
 * 
 * @see http://community.jboss.org/wiki/GenericDataAccessObjects
 */
public abstract class AlKhwarizmixDAO implements IAlKhwarizmixDAO {

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	protected abstract Logger getLogger();

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	public void saveOrUpdate(AlKhwarizmixDomainObjectAbstract object)
			throws AlKhwarizmixDAOException {
		getLogger().trace("saveOrUpdate({})", object);

		try {
			getHibernateCurrentSession().saveOrUpdate(object);
		} catch (ConcurrencyFailureException e) {
			throw getDAOExceptionForConcurrencyFailure(e);
		} catch (DataAccessException e) {
			// FBEL: TODO: CHECK THIS
			getHibernateCurrentSession().clear();
			throw new AlKhwarizmixDAOException(e);
		}
	}

	/**
	 */
	protected final AlKhwarizmixDAOException getDAOExceptionForConcurrencyFailure(
			ConcurrencyFailureException concurrencyFailureException) {
		getLogger().trace("getDAOExceptionForConcurrencyFailure({})",
				concurrencyFailureException);

		getHibernateCurrentSession().clear();
		AlKhwarizmixDAOException ex = new AlKhwarizmixDAOException(
				"Database error, " + "Concurrency problem",
				concurrencyFailureException);
		ex.setRecoverable(true);
		return ex;
	}

	/**
	 */
	protected final AlKhwarizmixDAOException getDAOExceptionForDataAccess(
			DataAccessException dataAccessException) {
		getLogger().trace("getDAOExceptionForDataAccess({})",
				dataAccessException);

		getHibernateCurrentSession().clear();
		return new AlKhwarizmixDAOException(dataAccessException);
	}

	/**
	 */
	protected final AlKhwarizmixDAOException getDAOException(Exception exception) {
		getLogger().trace("getDAOException({})", exception);

		getHibernateCurrentSession().clear();
		return new AlKhwarizmixDAOException(exception);
	}

	/**
	 */
	@Override
	public AlKhwarizmixDomainObjectAbstract get(
			Class<? extends AlKhwarizmixDomainObjectAbstract> clazz, Long id)
			throws AlKhwarizmixDAOException {
		getLogger().trace("get({}, {})", clazz.getSimpleName(), id);

		try {
			return getHibernateTemplate().get(clazz, id);
		} catch (DataAccessException e) {
			throw new AlKhwarizmixDAOException(e);
		}
	}

	/**
	 */
	@Override
	public List getList(DetachedCriteria criteria, int firstResult,
			int maxResult) throws AlKhwarizmixDAOException {
		getLogger().trace("getList({})", criteria);

		try {
			return getHibernateTemplate().findByCriteria(criteria, firstResult,
					maxResult);
		} catch (DataAccessException e) {
			throw new AlKhwarizmixDAOException(e);
		}
	}

	/**
	 */
	@Override
	public AlKhwarizmixDomainObjectAbstract load(
			Class<? extends AlKhwarizmixDomainObjectAbstract> clazz, Long id)
			throws AlKhwarizmixDAOException {
		getLogger().trace("load({}, {})", clazz.getSimpleName(), id);

		try {
			AlKhwarizmixDomainObjectAbstract result = get(clazz, id);

			if (result == null) {
				AlKhwarizmixDAOException ex = new AlKhwarizmixDAOException(
						"Unable to find the " + clazz.getSimpleName()
								+ " with id " + id);
				ex.setErrorCode(AlKhwarizmixErrorCode.ERROR_DATABASE);
				throw ex;
			}

			return result;
		} catch (DataAccessException e) {
			throw new AlKhwarizmixDAOException(e);
		}
	}

	/**
	 */
	@Override
	public void merge(AlKhwarizmixDomainObjectAbstract object)
			throws AlKhwarizmixDAOException {
		getLogger().trace("merge({})", object);

		try {
			getHibernateCurrentSession().merge(object);
		} catch (ConcurrencyFailureException e) {
			AlKhwarizmixDAOException ex = new AlKhwarizmixDAOException(
					"Database error, Concurrency problem", e);
			ex.setErrorCode(AlKhwarizmixErrorCode.ERROR_DATABASE);
			ex.setRecoverable(true);
			throw ex;
		} catch (DataAccessException e) {
			AlKhwarizmixDAOException ex = new AlKhwarizmixDAOException(e);
			throw ex;
		}
	}

	/**
	 */
	@Override
	public void delete(AlKhwarizmixDomainObjectAbstract object)
			throws AlKhwarizmixDAOException {
		getLogger().trace("delete({})", object);

		try {
			getHibernateCurrentSession().delete(object);
		} catch (DataAccessException e) {
			throw new AlKhwarizmixDAOException(e);
		}
	}

	/**
	 */
	protected Session getHibernateCurrentSession() {
		return getHibernateTemplate().getSessionFactory().getCurrentSession();
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// hibernateTemplate
	// ----------------------------------

	protected abstract HibernateTemplate getHibernateTemplate();

	protected abstract void setHibernateTemplate(HibernateTemplate value);

} // Class