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

package dz.alkhwarizmix.framework.java.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.classic.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.dao.IAlKhwarizmixDAO;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;

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
public abstract class AbstractAlKhwarizmixDAO implements IAlKhwarizmixDAO {

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
	@Override
	public void saveOrUpdate(final AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixDAOException {
		getLogger().trace("saveOrUpdate({})", object);

		try {
			getHibernateCurrentSession().saveOrUpdate(object);
		} catch (final ConcurrencyFailureException e) {
			throw getDAOExceptionForConcurrencyFailure(e);
		} catch (final DataAccessException e) {
			clearHibernateCurrentSession();
			throw new AlKhwarizmixDAOException(e);
		}
	}

	/**
	 */
	protected final AlKhwarizmixDAOException getDAOExceptionForConcurrencyFailure(
			final ConcurrencyFailureException concurrencyFailureException) {
		getLogger().error("getDAOExceptionForConcurrencyFailure({})",
				concurrencyFailureException);

		clearHibernateCurrentSession();
		final AlKhwarizmixDAOException ex = new AlKhwarizmixDAOException(
				"Database error, " + "Concurrency problem",
				concurrencyFailureException);
		ex.setRecoverable(true);
		return ex;
	}

	/**
	 */
	protected final AlKhwarizmixDAOException getDAOExceptionForDataAccess(
			final DataAccessException dataAccessException) {
		getLogger().error("getDAOExceptionForDataAccess({})",
				dataAccessException);

		clearHibernateCurrentSession();
		return new AlKhwarizmixDAOException(dataAccessException);
	}

	/**
	 */
	protected final AlKhwarizmixDAOException getDAOException(
			final Exception exception) {
		getLogger().error("getDAOException({})", exception);

		clearHibernateCurrentSession();
		return new AlKhwarizmixDAOException(exception);
	}

	/**
	 */
	@Override
	public final AbstractAlKhwarizmixDomainObject get(
			final Class<? extends AbstractAlKhwarizmixDomainObject> clazz,
			final Long id) throws AlKhwarizmixDAOException {
		getLogger().trace("get({}, {})", clazz.getSimpleName(), id);

		try {
			return getHibernateTemplate().get(clazz, id);
		} catch (final DataAccessException e) {
			throw new AlKhwarizmixDAOException(e);
		}
	}

	/**
	 */
	@Override
	public final List getList(final DetachedCriteria criteria,
			final int firstResult, final int maxResult)
			throws AlKhwarizmixDAOException {
		getLogger().trace("getList({})", criteria);

		try {
			return getHibernateTemplate().findByCriteria(criteria, firstResult,
					maxResult);
		} catch (final DataAccessException e) {
			throw new AlKhwarizmixDAOException(e);
		}
	}

	/**
	 */
	@Override
	public final AbstractAlKhwarizmixDomainObject load(
			final Class<? extends AbstractAlKhwarizmixDomainObject> clazz,
			final Long id) throws AlKhwarizmixDAOException {
		getLogger().trace("load({}, {})", clazz.getSimpleName(), id);

		try {
			final AbstractAlKhwarizmixDomainObject result = get(clazz, id);

			if (result == null) {
				final AlKhwarizmixDAOException ex = new AlKhwarizmixDAOException(
						"Unable to find the " + clazz.getSimpleName()
								+ " with id " + id);
				ex.setErrorCode(AlKhwarizmixErrorCode.ERROR_DATABASE);
				throw ex;
			}

			return result;
		} catch (final DataAccessException e) {
			throw new AlKhwarizmixDAOException(e);
		}
	}

	/**
	 */
	@Override
	public final void merge(final AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixDAOException {
		getLogger().trace("merge({})", object);

		try {
			getHibernateCurrentSession().merge(object);
		} catch (final ConcurrencyFailureException e) {
			final AlKhwarizmixDAOException ex = new AlKhwarizmixDAOException(
					"Database error, Concurrency problem", e);
			ex.setErrorCode(AlKhwarizmixErrorCode.ERROR_DATABASE);
			ex.setRecoverable(true);
			throw ex;
		} catch (final DataAccessException e) {
			final AlKhwarizmixDAOException ex = new AlKhwarizmixDAOException(e);
			throw ex;
		}
	}

	/**
	 */
	@Override
	public final void delete(final AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixDAOException {
		getLogger().trace("delete({})", object);

		try {
			getHibernateCurrentSession().delete(object);
		} catch (final DataAccessException e) {
			throw new AlKhwarizmixDAOException(e);
		}
	}

	/**
	 */
	@Override
	public final void clear() throws AlKhwarizmixDAOException {
		getLogger().trace("clear()");
		getHibernateTemplate().clear();
	}

	/**
	 */
	@Override
	public final void flush() throws AlKhwarizmixDAOException {
		getLogger().trace("flush()");
		getHibernateTemplate().flush();
	}

	/**
	 */
	protected final Session getHibernateCurrentSession() {
		try {
			return getHibernateTemplate().getSessionFactory()
					.getCurrentSession();
		} catch (final HibernateException e) {
			getLogger().warn("getHibernateCurrentSession: No current session");
		}
		return null;
	}

	private void clearHibernateCurrentSession() {
		final Session s = getHibernateCurrentSession();
		if (s != null)
			s.clear();
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
