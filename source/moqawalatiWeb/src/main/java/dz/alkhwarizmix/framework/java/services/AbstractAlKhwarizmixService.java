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
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.extend.model.vo.AbstractAlKhwarizmixDomainObjectExtendable;
import dz.alkhwarizmix.framework.java.interfaces.IAlKhwarizmixDAO;
import dz.alkhwarizmix.framework.java.interfaces.IAlKhwarizmixService;
import dz.alkhwarizmix.framework.java.interfaces.IAlKhwarizmixServiceValidator;
import dz.alkhwarizmix.framework.java.model.AlKhwarizmixSessionData;
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
	// Properties
	//
	// --------------------------------------------------------------------------

	@Autowired
	private AlKhwarizmixSessionData sessionData;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 * add the object
	 */
	@Override
	public AbstractAlKhwarizmixDomainObject addObject(
			final AbstractAlKhwarizmixDomainObject object,
			final boolean validateForPublishing) throws AlKhwarizmixException {
		getServiceValidator().validateObjectToAdd(object, getSessionOwner());
		getServiceDAO().saveOrUpdate(object);
		AbstractAlKhwarizmixDomainObject result = object;
		if (validateForPublishing) {
			result = (AbstractAlKhwarizmixDomainObject) result.clone();
			getServiceValidator().validateObjectToPublish(result,
					getSessionOwner());
		}

		getLogger().trace("addObject: result={}", result);
		return result;
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public String addObject(final String objectXml)
			throws AlKhwarizmixException {
		final AbstractAlKhwarizmixDomainObject newObject = unmarshalObjectFromXML(objectXml);
		final AbstractAlKhwarizmixDomainObject result = addObject(newObject,
				true);
		return marshalObjectToXML(result);
	}

	/**
	 * get the object
	 */
	@Override
	public abstract AbstractAlKhwarizmixDomainObject getObject(
			AbstractAlKhwarizmixDomainObject object,
			boolean validateForPublishing) throws AlKhwarizmixException;

	/**
	 * TODO: Javadoc
	 */
	@Override
	public String getObjectAsXML(final AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixException {
		String result = "";
		final AbstractAlKhwarizmixDomainObject foundObject = getObject(object,
				true);
		if (foundObject != null)
			result = marshalObjectToXML(foundObject);
		return result;
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public String getObjectAsXML(final String objectXml)
			throws AlKhwarizmixException {
		final AbstractAlKhwarizmixDomainObject newObject = unmarshalObjectFromXML(objectXml);
		final String result = getObjectAsXML(newObject);
		return result;
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public String getObjectAsJSON(final AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixException {
		String result = "";
		final AbstractAlKhwarizmixDomainObject foundObject = getObject(object,
				true);
		if (foundObject != null)
			result = marshalObjectToJSON(foundObject);
		return result;
	}

	/**
	 * TODO: Javadoc
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AbstractAlKhwarizmixDomainObject> getObjectList(
			final DetachedCriteria criteria, final int firstResult,
			final int maxResult, final boolean validateForPublishing)
			throws AlKhwarizmixException {
		getLogger().trace("getObjectList({}, {}, {})", criteria, firstResult,
				maxResult);

		List<AbstractAlKhwarizmixDomainObject> result = new ArrayList<AbstractAlKhwarizmixDomainObject>();
		final List<AbstractAlKhwarizmixDomainObject> selectedList = getServiceDAO()
				.getList(criteria, firstResult, maxResult);
		if (validateForPublishing)
			validateObjectListToPublish(result, selectedList);
		else
			result = selectedList;

		return result;
	}

	/**
	 */
	private void validateObjectListToPublish(
			final List<AbstractAlKhwarizmixDomainObject> result,
			final List<AbstractAlKhwarizmixDomainObject> selectedList) {
		for (final AbstractAlKhwarizmixDomainObject obj : selectedList) {
			try {
				getServiceValidator().validateObjectToPublish(obj,
						getSessionOwner());
				result.add(obj);
			} catch (final AlKhwarizmixException e) {
				getLogger()
						.warn("validateObjectListToPublish: Validation failure for {}",
								obj);
			}
		}
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public AbstractAlKhwarizmixDomainObject updateObject(
			final AbstractAlKhwarizmixDomainObject object,
			final boolean validateForPublishing) throws AlKhwarizmixException {
		getLogger().trace("updateObject({})", object);

		getServiceValidator().validateObjectToUpdate(object, getSessionOwner());
		final AbstractAlKhwarizmixDomainObject foundObject = getObject(object,
				false);
		if (foundObject != null) {
			foundObject.updateFrom(object);
			getServiceDAO().saveOrUpdate(foundObject);
		} else {
			throw new AlKhwarizmixException(AlKhwarizmixErrorCode.INVALID_DATA);
		}
		AbstractAlKhwarizmixDomainObject result = foundObject;
		if (validateForPublishing) {
			result = (AbstractAlKhwarizmixDomainObject) result.clone();
			getServiceValidator().validateObjectToPublish(result,
					getSessionOwner());
		}
		getLogger().trace("updateObject: result={}", result);
		return result;
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public String updateObject(final String objectXml)
			throws AlKhwarizmixException {
		getLogger().trace("updateObject({})", objectXml);

		final AbstractAlKhwarizmixDomainObject newObject = unmarshalObjectFromXML(objectXml);
		final AbstractAlKhwarizmixDomainObject result = updateObject(newObject,
				true);
		return marshalObjectToXML(result);
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public String objectListToJSON(
			final List<AbstractAlKhwarizmixDomainObject> objectList) {
		getLogger().trace("objectListToJSON(objectList)");

		final StringWriter stringWriter = new StringWriter();
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
			final List<AbstractAlKhwarizmixDomainObject> objectList) {
		getLogger().trace("objectListToXML(objectList)");

		return new XMLUtil(getJaxb2Marshaller()).objectListToXML(objectList);
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public final String marshalObjectToXML(
			final AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixException {
		getLogger().trace("marshalObjectToXML({})", object);

		final String result = new XMLUtil(getJaxb2Marshaller())
				.marshalObjectToXML(object);
		return result;
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public final AbstractAlKhwarizmixDomainObject unmarshalObjectFromXML(
			final String xmlValue) throws AlKhwarizmixException {
		getLogger().trace("unmarshalObjectFromXML({})", xmlValue);

		return new XMLUtil(getJaxb2Marshaller())
				.unmarshalObjectFromXML(xmlValue);
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public String marshalObjectToJSON(
			final AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixException {

		final ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(object);
		} catch (final JsonGenerationException exception) {
			throw new AlKhwarizmixException(
					AlKhwarizmixErrorCode.ERROR_JSON_PARSING, exception);
		} catch (final JsonMappingException exception) {
			throw new AlKhwarizmixException(
					AlKhwarizmixErrorCode.ERROR_JSON_PARSING, exception);
		} catch (final IOException exception) {
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
			final AbstractAlKhwarizmixDomainObjectExtendable object)
			throws AlKhwarizmixException {

		if (object != null) {
			final String extendedData = object.getExtendedDataValue();
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
			final AbstractAlKhwarizmixDomainObjectExtendable object)
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

	// ----------------------------------
	// sessionData
	// ----------------------------------

	protected final AlKhwarizmixSessionData getSessionData() {
		return sessionData;
	}

	protected final void setSessionData(final AlKhwarizmixSessionData value) {
		sessionData = value;
	}

	/**
	 */
	protected AlKhwarizmixDomainObject getSessionOwner() {
		return getSessionData().getSessionOwner();
	}

} // Class
