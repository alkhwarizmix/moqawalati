////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٥ هجري، فارس بلحواس (Copyright 2013 Fares Belhaouas)
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.framework.java.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;

import dz.alkhwarizmix.framework.java.dao.impl.AlKhwarizmixDAOException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٤ محرم ١٤٣٥ (November 28, 2013)
 */
public interface IAlKhwarizmixDAO {

	/**
	 */
	public void saveOrUpdate(AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixDAOException;

	/**
	 */
	public void saveOrUpdate(AbstractAlKhwarizmixDomainObject object,
			Session session) throws AlKhwarizmixDAOException;

	/**
	 */
	public AbstractAlKhwarizmixDomainObject get(
			Class<? extends AbstractAlKhwarizmixDomainObject> clazz, Long id)
			throws AlKhwarizmixDAOException;

	/**
	 */
	public List getList(DetachedCriteria criteria, int firstResult,
			int maxResult) throws AlKhwarizmixDAOException;

	public AbstractAlKhwarizmixDomainObject load(
			Class<? extends AbstractAlKhwarizmixDomainObject> clazz, Long id)
			throws AlKhwarizmixDAOException;

	/**
	 */
	public void merge(AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixDAOException;

	/**
	 */
	public void delete(AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixDAOException;

	/**
	 */
	public void clear() throws AlKhwarizmixDAOException;

	/**
	 */
	public void flush() throws AlKhwarizmixDAOException;

	/**
	 */
	public Transaction beginTransaction() throws AlKhwarizmixDAOException;

	/**
	 */
	public void commitTransaction(Transaction trans)
			throws AlKhwarizmixDAOException;

	/**
	 */
	public void rollbackTransaction(Transaction trans)
			throws AlKhwarizmixDAOException;

} // Interface
