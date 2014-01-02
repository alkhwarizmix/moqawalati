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

package dz.alkhwarizmix.framework.java.interfaces;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import dz.alkhwarizmix.framework.java.dao.AlKhwarizmixDAOException;
import dz.alkhwarizmix.framework.java.domain.AlKhwarizmixDomainObjectAbstract;

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
	public void saveOrUpdate(AlKhwarizmixDomainObjectAbstract object)
			throws AlKhwarizmixDAOException;

	/**
	 */
	public AlKhwarizmixDomainObjectAbstract get(
			Class<? extends AlKhwarizmixDomainObjectAbstract> clazz, Long id)
			throws AlKhwarizmixDAOException;

	/**
	 */
	public List getList(DetachedCriteria criteria, int firstResult,
			int maxResult) throws AlKhwarizmixDAOException;

	public AlKhwarizmixDomainObjectAbstract load(
			Class<? extends AlKhwarizmixDomainObjectAbstract> clazz, Long id)
			throws AlKhwarizmixDAOException;

	/**
	 */
	public void merge(AlKhwarizmixDomainObjectAbstract object)
			throws AlKhwarizmixDAOException;

	/**
	 */
	public void delete(AlKhwarizmixDomainObjectAbstract object)
			throws AlKhwarizmixDAOException;

} // Class