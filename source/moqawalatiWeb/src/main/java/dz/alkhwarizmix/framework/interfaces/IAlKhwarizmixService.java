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

package dz.alkhwarizmix.framework.interfaces;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import dz.alkhwarizmix.framework.AlKhwarizmixException;
import dz.alkhwarizmix.framework.domain.AlKhwarizmixDomainObject;

/**
 *  <p>
 *  TODO: Javadoc
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٢٥ ذو القعدة ١٤٣٤ (October 01, 2013)
 */
public interface IAlKhwarizmixService
{
	/**
	 */
	public void addObject(AlKhwarizmixDomainObject object) throws AlKhwarizmixException;

	/**
	 */
	public String addObject(String objectXml) throws AlKhwarizmixException;

	/**
	 * get the object
	 */
	public AlKhwarizmixDomainObject getObject(AlKhwarizmixDomainObject object) throws AlKhwarizmixException;

	/**
	 */
	public String getObjectAsXML(AlKhwarizmixDomainObject object) throws AlKhwarizmixException;

	/**
	 */
	public String getObjectAsXML(String objectXml) throws AlKhwarizmixException;

	/**
	 */
	public List<AlKhwarizmixDomainObject> getObjectList(DetachedCriteria criteria) throws AlKhwarizmixException;

	/**
	 */
	public AlKhwarizmixDomainObject updateObject(AlKhwarizmixDomainObject object) throws AlKhwarizmixException;

	/**
	 */
	public String updateObject(String objectXml) throws AlKhwarizmixException;

	/**
	 */
	public String objectListToXML(List<AlKhwarizmixDomainObject> objectList);

	/**
	 */
	public String marshalObject(AlKhwarizmixDomainObject object) throws AlKhwarizmixException;

	/**
	 */
	public AlKhwarizmixDomainObject unmarshalObject(String xmlValue) throws AlKhwarizmixException;

} // Class