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
import dz.alkhwarizmix.framework.java.domain.AlKhwarizmixDomainObjectAbstract;

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
	public void addObject(AlKhwarizmixDomainObjectAbstract object)
			throws AlKhwarizmixException;

	/**
	 */
	public String addObject(String objectXml) throws AlKhwarizmixException;

	/**
	 * get the object
	 */
	public AlKhwarizmixDomainObjectAbstract getObject(
			AlKhwarizmixDomainObjectAbstract object)
			throws AlKhwarizmixException;

	/**
	 */
	public String getObjectAsXML(AlKhwarizmixDomainObjectAbstract object)
			throws AlKhwarizmixException;

	/**
	 */
	public String getObjectAsXML(String objectXml) throws AlKhwarizmixException;

	/**
	 */
	public String getObjectAsJSON(AlKhwarizmixDomainObjectAbstract object)
			throws AlKhwarizmixException;

	/**
	 */
	public List<AlKhwarizmixDomainObjectAbstract> getObjectList(
			DetachedCriteria criteria, int firstResult, int maxResult)
			throws AlKhwarizmixException;

	/**
	 */
	public AlKhwarizmixDomainObjectAbstract updateObject(
			AlKhwarizmixDomainObjectAbstract object)
			throws AlKhwarizmixException;

	/**
	 */
	public String updateObject(String objectXml) throws AlKhwarizmixException;

	/**
	 */
	public String objectListToJSON(
			List<AlKhwarizmixDomainObjectAbstract> objectList);

	/**
	 */
	public String objectListToXML(
			List<AlKhwarizmixDomainObjectAbstract> objectList);

	/**
	 */
	public String marshalObjectToXML(AlKhwarizmixDomainObjectAbstract object)
			throws AlKhwarizmixException;

	/**
	 */
	public AlKhwarizmixDomainObjectAbstract unmarshalObjectFromXML(
			String xmlValue) throws AlKhwarizmixException;

	/**
	 */
	public String marshalObjectToJSON(AlKhwarizmixDomainObjectAbstract object)
			throws AlKhwarizmixException;

} // Class