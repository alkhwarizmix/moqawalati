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

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.dom4j.io.XMLResult;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AlKhwarizmixDomainObjectAbstract;
import dz.alkhwarizmix.framework.java.dtos.extend.model.vo.AlKhwarizmixDomainObjectExtendable;
import dz.alkhwarizmix.framework.java.interfaces.IAlKhwarizmixDAO;
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
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public AlKhwarizmixService() {
		getLogger().trace("Constructor");
	}

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
	@Override
	public void addObject(AlKhwarizmixDomainObjectAbstract object)
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
	@Override
	public String addObject(String objectXml) throws AlKhwarizmixException {
		try {
			AlKhwarizmixDomainObjectAbstract newObject = unmarshalObjectFromXML(objectXml);
			addObject(newObject);
			nullifyProtectedProperties(newObject);
			return marshalObjectToXML(newObject);
		} catch (AlKhwarizmixException e) {
			throw e;
		}
	}

	/**
	 * get the object
	 */
	@Override
	public abstract AlKhwarizmixDomainObjectAbstract getObject(
			AlKhwarizmixDomainObjectAbstract object)
			throws AlKhwarizmixException;

	/**
	 * TODO: Javadoc
	 */
	@Override
	public String getObjectAsXML(AlKhwarizmixDomainObjectAbstract object)
			throws AlKhwarizmixException {
		String result = "";
		AlKhwarizmixDomainObjectAbstract foundObject = getObject(object);
		if (foundObject != null) {
			nullifyProtectedProperties(foundObject);
			result = marshalObjectToXML(foundObject);
		}
		return result;
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public String getObjectAsXML(String objectXml) throws AlKhwarizmixException {
		AlKhwarizmixDomainObjectAbstract newObject = (AlKhwarizmixDomainObjectAbstract) unmarshalObjectFromXML(objectXml);
		String result = getObjectAsXML(newObject);
		return result;
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public String getObjectAsJSON(AlKhwarizmixDomainObjectAbstract object)
			throws AlKhwarizmixException {
		AlKhwarizmixDomainObjectAbstract foundObject = getObject(object);
		String result = (foundObject != null)
				? marshalObjectToJSON(foundObject)
				: "";
		return result;
	}

	/**
	 * TODO: Javadoc
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AlKhwarizmixDomainObjectAbstract> getObjectList(
			DetachedCriteria criteria, int firstResult, int maxResult)
			throws AlKhwarizmixException {
		getLogger().trace("getObjectList({})", criteria);

		return getServiceDAO().getList(criteria, firstResult, maxResult);
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public AlKhwarizmixDomainObjectAbstract updateObject(
			AlKhwarizmixDomainObjectAbstract object)
			throws AlKhwarizmixException {

		try {
			AlKhwarizmixDomainObjectAbstract foundObject = getObject(object);
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
	@Override
	public String updateObject(String objectXml) throws AlKhwarizmixException {
		AlKhwarizmixDomainObjectAbstract newObject = unmarshalObjectFromXML(objectXml);
		AlKhwarizmixDomainObjectAbstract result = updateObject(newObject);
		return marshalObjectToXML(result);
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public String objectListToJSON(
			List<AlKhwarizmixDomainObjectAbstract> objectList) {
		getLogger().trace("objectListToXML()");

		StringWriter stringWriter = new StringWriter();
		XMLResult xmlResult = new XMLResult(stringWriter);
		for (AlKhwarizmixDomainObjectAbstract object : objectList) {
			getJaxb2Marshaller().marshal(object, xmlResult);
		}
		return stringWriter.toString();
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public String objectListToXML(
			List<AlKhwarizmixDomainObjectAbstract> objectList) {
		getLogger().trace("objectListToXML()");

		StringWriter stringWriter = new StringWriter();
		XMLResult xmlResult = new XMLResult(stringWriter);
		for (AlKhwarizmixDomainObjectAbstract object : objectList) {
			getJaxb2Marshaller().marshal(object, xmlResult);
		}
		return stringWriter.toString();
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public final String marshalObjectToXML(
			AlKhwarizmixDomainObjectAbstract object)
			throws AlKhwarizmixException {
		try {
			return internal_marshalObjectToXML(object);
		} catch (XmlMappingException e) {
			throw new AlKhwarizmixException(
					AlKhwarizmixErrorCode.ERROR_XML_PARSING, e);
		}
	}

	protected String internal_marshalObjectToXML(
			AlKhwarizmixDomainObjectAbstract object) {

		StringWriter stringWriter = new StringWriter();
		StreamResult streamResult = new StreamResult(stringWriter);
		getJaxb2Marshaller().marshal(object, streamResult);
		return stringWriter.toString();
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public final AlKhwarizmixDomainObjectAbstract unmarshalObjectFromXML(
			String xmlValue) throws AlKhwarizmixException {

		try {
			return internal_unmarshalObjectFromXML(xmlValue);
		} catch (XmlMappingException e) {
			throw new AlKhwarizmixException(
					AlKhwarizmixErrorCode.ERROR_XML_PARSING, e);
		}
	}

	protected AlKhwarizmixDomainObjectAbstract internal_unmarshalObjectFromXML(
			String xmlValue) {
		return (AlKhwarizmixDomainObjectAbstract) getJaxb2Marshaller()
				.unmarshal(new StreamSource(IOUtils.toInputStream(xmlValue)));
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public String marshalObjectToJSON(AlKhwarizmixDomainObjectAbstract object)
			throws AlKhwarizmixException {

		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonGenerationException exception) {
			throw new AlKhwarizmixException(
					AlKhwarizmixErrorCode.ERROR_JSON_PARSING, exception);
		} catch (JsonMappingException exception) {
			throw new AlKhwarizmixException(
					AlKhwarizmixErrorCode.ERROR_JSON_PARSING, exception);
		} catch (IOException exception) {
			throw new AlKhwarizmixException(
					AlKhwarizmixErrorCode.ERROR_JSON_PARSING, exception);
		}
	}

	/**
	 * TODO: Javadoc
	 */
	protected void nullifyProtectedProperties(
			AlKhwarizmixDomainObjectAbstract object) {
		if (object != null)
			object.setId(null);
	}

	/**
	 * TODO: Javadoc
	 * 
	 * @throws AlKhwarizmixException
	 */
	protected final void updateObjectFromExtendedDataXML(
			AlKhwarizmixDomainObjectExtendable object)
			throws AlKhwarizmixException {

		if (object != null) {
			String extendedData = object.getExtendedDataValue();
			if (extendedData.length() > 0)
				object.updateFrom(unmarshalObjectFromXML(extendedData));
		}
	}

	/**
	 * TODO: Javadoc
	 * 
	 * @throws AlKhwarizmixException
	 */
	protected final void setupObjectExtendedDataXMLValue(
			AlKhwarizmixDomainObjectExtendable object)
			throws AlKhwarizmixException {

		if (object != null) {
			object.setExtendedDataValue(marshalObjectToXML(object));
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
	protected abstract IAlKhwarizmixDAO getServiceDAO();

	/**
	 * get the jaxb2Marshaller
	 */
	protected abstract Jaxb2Marshaller getJaxb2Marshaller();

	/**
	 * set the jaxb2Marshaller
	 */
	protected abstract void setJaxb2Marshaller(Jaxb2Marshaller value);

} // Class