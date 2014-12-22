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

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.extend.model.vo.AbstractAlKhwarizmixDomainObjectExtendable;
import dz.alkhwarizmix.framework.java.interfaces.IAlKhwarizmixDAO;
import dz.alkhwarizmix.framework.java.interfaces.IAlKhwarizmixService;
import dz.alkhwarizmix.framework.java.interfaces.IAlKhwarizmixServiceValidator;
import dz.alkhwarizmix.framework.java.utils.XMLUtil;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٥ ذو القعدة ١٤٣٤ (October 01, 2013)
 */
public abstract class AbstractAlKhwarizmixService implements
		IAlKhwarizmixService {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public AbstractAlKhwarizmixService() {
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
	public void addObject(AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixException {
		getServiceValidator().validateObjectToAdd(object);
		getServiceDAO().saveOrUpdate(object);
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public String addObject(String objectXml) throws AlKhwarizmixException {
		AbstractAlKhwarizmixDomainObject newObject = unmarshalObjectFromXML(objectXml);
		addObject(newObject);
		getServiceValidator().validateObjectToPublish(newObject);
		return marshalObjectToXML(newObject);
	}

	/**
	 * get the object
	 */
	@Override
	public abstract AbstractAlKhwarizmixDomainObject getObject(
			AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixException;

	/**
	 * TODO: Javadoc
	 */
	@Override
	public String getObjectAsXML(AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixException {
		String result = "";
		AbstractAlKhwarizmixDomainObject foundObject = getObject(object);
		if (foundObject != null) {
			getServiceValidator().validateObjectToPublish(foundObject);
			result = marshalObjectToXML(foundObject);
		}
		return result;
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public String getObjectAsXML(String objectXml) throws AlKhwarizmixException {
		AbstractAlKhwarizmixDomainObject newObject = (AbstractAlKhwarizmixDomainObject) unmarshalObjectFromXML(objectXml);
		String result = getObjectAsXML(newObject);
		return result;
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public String getObjectAsJSON(AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixException {
		AbstractAlKhwarizmixDomainObject foundObject = getObject(object);
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
	public List<AbstractAlKhwarizmixDomainObject> getObjectList(
			DetachedCriteria criteria, int firstResult, int maxResult)
			throws AlKhwarizmixException {
		getLogger().trace("getObjectList({}, {}, {})", criteria, firstResult,
				maxResult);
		
		return getServiceDAO().getList(criteria, firstResult, maxResult);
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public AbstractAlKhwarizmixDomainObject updateObject(
			AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixException {
		getLogger().trace("updateObject({})", object);
		
		getServiceValidator().validateObjectToUpdate(object);
		AbstractAlKhwarizmixDomainObject foundObject = getObject(object);
		if (foundObject != null) {
			foundObject.updateFrom(object);
			getServiceDAO().saveOrUpdate(foundObject);
		} else {
			throw new AlKhwarizmixException(AlKhwarizmixErrorCode.INVALID_DATA);
		}
		return foundObject;
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public String updateObject(String objectXml) throws AlKhwarizmixException {
		getLogger().trace("updateObject({})", objectXml);
		
		AbstractAlKhwarizmixDomainObject newObject = unmarshalObjectFromXML(objectXml);
		AbstractAlKhwarizmixDomainObject result = updateObject(newObject);
		return marshalObjectToXML(result);
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public String objectListToJSON(
			List<AbstractAlKhwarizmixDomainObject> objectList) {
		getLogger().trace("objectListToJSON(objectList)");
		
		StringWriter stringWriter = new StringWriter();
		// XMLResult xmlResult = new XMLResult(stringWriter);
		// for (AlKhwarizmixDomainObjectAbstract object : objectList) {
		// getJaxb2Marshaller().marshal(object, xmlResult);
		// }
		return stringWriter.toString();
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public String objectListToXML(
			List<AbstractAlKhwarizmixDomainObject> objectList) {
		getLogger().trace("objectListToXML(objectList)");
		
		return new XMLUtil(getJaxb2Marshaller()).objectListToXML(objectList);
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public final String marshalObjectToXML(
			AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixException {
		getLogger().trace("marshalObjectToXML({})", object);
		
		String result = new XMLUtil(getJaxb2Marshaller())
				.marshalObjectToXML(object);
		return result;
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public final AbstractAlKhwarizmixDomainObject unmarshalObjectFromXML(
			String xmlValue) throws AlKhwarizmixException {
		getLogger().trace("unmarshalObjectFromXML({})", xmlValue);
		
		return new XMLUtil(getJaxb2Marshaller())
				.unmarshalObjectFromXML(xmlValue);
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public String marshalObjectToJSON(AbstractAlKhwarizmixDomainObject object)
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
	 * 
	 * @throws AlKhwarizmixException
	 */
	protected final void updateObjectFromExtendedDataXML(
			AbstractAlKhwarizmixDomainObjectExtendable object)
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
			AbstractAlKhwarizmixDomainObjectExtendable object)
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
	 * get the serviceDAO
	 */
	protected abstract IAlKhwarizmixServiceValidator getServiceValidator();

	// ----------------------------------
	// jaxb2Marshaller
	// ----------------------------------

	/**
	 * get the jaxb2Marshaller
	 */
	protected abstract Jaxb2Marshaller getJaxb2Marshaller();

	/**
	 * set the jaxb2Marshaller
	 */
	protected abstract void setJaxb2Marshaller(Jaxb2Marshaller value);

} // Class
