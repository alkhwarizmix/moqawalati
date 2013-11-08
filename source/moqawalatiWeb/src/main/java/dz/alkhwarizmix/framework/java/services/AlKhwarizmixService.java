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

package dz.alkhwarizmix.framework.java.services;

import java.io.StringWriter;
import java.util.List;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.IOUtils;
import org.dom4j.io.XMLResult;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dao.AlKhwarizmixDAO;
import dz.alkhwarizmix.framework.java.domain.AlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.interfaces.IAlKhwarizmixService;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٥ ذو القعدة ١٤٣٤ (October 01, 2013)
 */
public abstract class AlKhwarizmixService implements IAlKhwarizmixService {

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
	 * add the object
	 */
	public void addObject(AlKhwarizmixDomainObject object)
			throws AlKhwarizmixException {
		try {
			getServiceDAO().saveOrUpdate(object);
		} catch (AlKhwarizmixException e) {
			throw e;
		}
	}

	/**
	 * TODO: Javadoc
	 */
	public String addObject(String objectXml) throws AlKhwarizmixException {
		try {
			AlKhwarizmixDomainObject newObject = unmarshalObject(objectXml);
			addObject(newObject);
			return marshalObject(newObject);
		} catch (AlKhwarizmixException e) {
			throw e;
		}
	}

	/**
	 * get the object
	 */
	public abstract AlKhwarizmixDomainObject getObject(
			AlKhwarizmixDomainObject object) throws AlKhwarizmixException;

	/**
	 * TODO: Javadoc
	 */
	public String getObjectAsXML(AlKhwarizmixDomainObject object)
			throws AlKhwarizmixException {
		AlKhwarizmixDomainObject foundObject = getObject(object);
		String result = (foundObject != null) ? marshalObject(foundObject) : "";
		return result;
	}

	/**
	 * TODO: Javadoc
	 */
	public String getObjectAsXML(String objectXml) throws AlKhwarizmixException {
		AlKhwarizmixDomainObject newObject = (AlKhwarizmixDomainObject) unmarshalObject(objectXml);
		String result = getObjectAsXML(newObject);
		return result;
	}

	/**
	 * TODO: Javadoc
	 */
	@SuppressWarnings("unchecked")
	public List<AlKhwarizmixDomainObject> getObjectList(
			DetachedCriteria criteria, int firstResult, int maxResult)
			throws AlKhwarizmixException {
		getLogger().debug("getObjectList({})", criteria);

		return getServiceDAO().getList(criteria, firstResult, maxResult);
	}

	/**
	 * TODO: Javadoc
	 */
	public AlKhwarizmixDomainObject updateObject(AlKhwarizmixDomainObject object)
			throws AlKhwarizmixException {
		try {
			AlKhwarizmixDomainObject foundObject = getObject(object);
			if (foundObject != null) {
				foundObject.updateFrom(object);
				getServiceDAO().saveOrUpdate(foundObject);
			} else {
				throw new AlKhwarizmixException(
						AlKhwarizmixErrorCode.INVALID_DATA);
			}
			return foundObject;
		} catch (AlKhwarizmixException e) {
			throw e;
		}
	}

	/**
	 * TODO: Javadoc
	 */
	public String updateObject(String objectXml) throws AlKhwarizmixException {
		AlKhwarizmixDomainObject newObject = unmarshalObject(objectXml);
		AlKhwarizmixDomainObject result = updateObject(newObject);
		return marshalObject(result);
	}

	/**
	 * TODO: Javadoc
	 */
	public String objectListToXML(List<AlKhwarizmixDomainObject> objectList) {
		getLogger().debug("objectListToXML()");

		StringWriter stringWriter = new StringWriter();
		XMLResult xmlResult = new XMLResult(stringWriter);
		for (AlKhwarizmixDomainObject object : objectList) {
			getJaxb2Marshaller().marshal(object, xmlResult);
		}
		return stringWriter.toString();
	}

	/**
	 * TODO: Javadoc
	 */
	public String marshalObject(AlKhwarizmixDomainObject object)
			throws AlKhwarizmixException {
		try {
			StringWriter stringWriter = new StringWriter();
			StreamResult streamResult = new StreamResult(stringWriter);
			getJaxb2Marshaller().marshal(object, streamResult);
			return stringWriter.toString();
		} catch (XmlMappingException e) {
			AlKhwarizmixException ex = new AlKhwarizmixException(
					AlKhwarizmixErrorCode.ERROR_XML_PARSING, e);
			throw ex;
		}
	}

	/**
	 * TODO: Javadoc
	 */
	public AlKhwarizmixDomainObject unmarshalObject(String xmlValue)
			throws AlKhwarizmixException {
		try {
			return (AlKhwarizmixDomainObject) getJaxb2Marshaller().unmarshal(
					new StreamSource(IOUtils.toInputStream(xmlValue)));
		} catch (XmlMappingException e) {
			AlKhwarizmixException ex = new AlKhwarizmixException(
					AlKhwarizmixErrorCode.ERROR_XML_PARSING, e);
			throw ex;
		}
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	/**
	 * get the serviceDAO
	 */
	protected abstract AlKhwarizmixDAO getServiceDAO();

	/**
	 * get the jaxb2Marshaller
	 */
	protected abstract Jaxb2Marshaller getJaxb2Marshaller();

	/**
	 * set the jaxb2Marshaller
	 */
	protected abstract void setJaxb2Marshaller(Jaxb2Marshaller value);

} // Class