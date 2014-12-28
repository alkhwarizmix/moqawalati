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

package dz.alkhwarizmix.framework.java.interfaces;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٥ ذو القعدة ١٤٣٤ (October 01, 2013)
 */
public interface IAlKhwarizmixService {
	/**
	 */
	public void addObject(AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixException;

	/**
	 */
	public String addObject(String objectXml) throws AlKhwarizmixException;

	/**
	 * get the object
	 */
	public AbstractAlKhwarizmixDomainObject getObject(
			AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixException;

	/**
	 */
	public String getObjectAsXML(AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixException;

	/**
	 */
	public String getObjectAsXML(String objectXml) throws AlKhwarizmixException;

	/**
	 */
	public String getObjectAsJSON(AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixException;

	/**
	 */
	public List<AbstractAlKhwarizmixDomainObject> getObjectList(
			DetachedCriteria criteria, int firstResult, int maxResult)
			throws AlKhwarizmixException;

	/**
	 */
	public AbstractAlKhwarizmixDomainObject updateObject(
			AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixException;

	/**
	 */
	public String updateObject(String objectXml) throws AlKhwarizmixException;

	/**
	 */
	public String objectListToJSON(
			List<AbstractAlKhwarizmixDomainObject> objectList);

	/**
	 */
	public String objectListToXML(
			List<AbstractAlKhwarizmixDomainObject> objectList);

	/**
	 */
	public String marshalObjectToXML(AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixException;

	/**
	 */
	public AbstractAlKhwarizmixDomainObject unmarshalObjectFromXML(
			String xmlValue) throws AlKhwarizmixException;

	/**
	 */
	public String marshalObjectToJSON(AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixException;

} // Class
