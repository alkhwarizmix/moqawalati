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

package dz.alkhwarizmix.framework.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import dz.alkhwarizmix.framework.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.domain.AlKhwarizmixDomainObject;

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
public abstract class AlKhwarizmixDAO {

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
	public void saveOrUpdate(AlKhwarizmixDomainObject object)
			throws AlKhwarizmixDAOException {
		getLogger().trace("saveOrUpdate({})", object);

		try {
			getHibernateTemplate().saveOrUpdate(object);
		} catch (ConcurrencyFailureException e) {
			throw getDAOExceptionForConcurrencyFailure(e);
		} catch (DataAccessException e) {
			// FBEL: TODO: CHECK THIS
			getHibernateTemplate().clear();
			throw new AlKhwarizmixDAOException(e);
		}
	}

	/**
	 */
	protected final AlKhwarizmixDAOException getDAOExceptionForConcurrencyFailure(
			ConcurrencyFailureException concurrencyFailureException) {
		getLogger().trace("getDAOExceptionForConcurrencyFailure({})",
				concurrencyFailureException);

		getHibernateTemplate().clear();
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

		getHibernateTemplate().clear();
		return new AlKhwarizmixDAOException(dataAccessException);
	}

	/**
	 */
	protected final AlKhwarizmixDAOException getDAOException(Exception exception) {
		getLogger().trace("getDAOException({})", exception);

		getHibernateTemplate().clear();
		return new AlKhwarizmixDAOException(exception);
	}

	/**
	 */
	public AlKhwarizmixDomainObject get(
			Class<? extends AlKhwarizmixDomainObject> clazz, Long id)
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
	public List getList(DetachedCriteria criteria)
			throws AlKhwarizmixDAOException {
		getLogger().trace("getList({})", criteria);

		try {
			return getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			throw new AlKhwarizmixDAOException(e);
		}
	}

	/**
	 */
	public AlKhwarizmixDomainObject load(
			Class<? extends AlKhwarizmixDomainObject> clazz, Long id)
			throws AlKhwarizmixDAOException {
		getLogger().trace("load({}, {})", clazz.getSimpleName(), id);

		try {
			AlKhwarizmixDomainObject result = get(clazz, id);

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
	public void merge(AlKhwarizmixDomainObject object)
			throws AlKhwarizmixDAOException {
		getLogger().trace("merge({})", object);

		try {
			getHibernateTemplate().merge(object);
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
	public Object uniqueValue(final DetachedCriteria criteria)
			throws AlKhwarizmixDAOException {
		getLogger().trace("uniqueValue({})", criteria);

		try {
			return getHibernateTemplate().execute(
					new HibernateCallback<Object>() {
						@Override
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							return criteria.getExecutableCriteria(session)
									.uniqueResult();
						}
					});
		} catch (Exception e) {
			throw new AlKhwarizmixDAOException(e);
		}
	}

	/**
	 */
	public void delete(AlKhwarizmixDomainObject object)
			throws AlKhwarizmixDAOException {
		getLogger().trace("delete({})", object);

		try {
			getHibernateTemplate().delete(object);
		} catch (DataAccessException e) {
			throw new AlKhwarizmixDAOException(e);
		}
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