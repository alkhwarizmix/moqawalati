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

package dz.alkhwarizmix.framework.java.services.impl;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dao.IAlKhwarizmixDAO;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.extend.model.vo.AbstractAlKhwarizmixDomainObjectExtendable;
import dz.alkhwarizmix.framework.java.model.IAlKhwarizmixSessionData;
import dz.alkhwarizmix.framework.java.services.IAlKhwarizmixService;
import dz.alkhwarizmix.framework.java.services.IAlKhwarizmixServiceValidator;
import dz.alkhwarizmix.framework.java.utils.IXMLUtil;
import dz.alkhwarizmix.framework.java.utils.impl.XMLUtil;

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
	private IAlKhwarizmixSessionData sessionData;

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
		getLogger().trace("addObject: {}, {}", object, validateForPublishing);

		getServiceValidator().validateObjectToAdd(object, getSessionOwner());
		getServiceDAO().saveOrUpdate(object);
		AbstractAlKhwarizmixDomainObject result = object;
		if (validateForPublishing) {
			result = (AbstractAlKhwarizmixDomainObject) result.clone();
			getServiceValidator().validateObjectToPublish(result,
					getSessionOwner());
		}

		getLogger().trace("addObject: return {}", result);
		return result;
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public String addObject(final String objectXml)
			throws AlKhwarizmixException {
		getLogger().trace("addObject: {}", objectXml);

		final AbstractAlKhwarizmixDomainObject newObject = unmarshalObjectFromXML(objectXml);
		final AbstractAlKhwarizmixDomainObject addedObject = addObject(
				newObject, true);
		final String result = marshalObjectToXML(addedObject);

		getLogger().trace("addObject: return {}", result);
		return result;
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
		getLogger().trace("getObjectAsXML: {}", object);

		String result = "";
		final AbstractAlKhwarizmixDomainObject foundObject = getObject(object,
				true);
		if (foundObject != null)
			result = marshalObjectToXML(foundObject);

		getLogger().trace("getObjectAsXML: return {}", result);
		return result;
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public String getObjectAsXML(final String objectXml)
			throws AlKhwarizmixException {
		getLogger().trace("getObjectAsXML: {}", objectXml);

		final AbstractAlKhwarizmixDomainObject newObject = unmarshalObjectFromXML(objectXml);
		final String result = getObjectAsXML(newObject);

		getLogger().trace("getObjectAsXML: return {}", result);
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
		final String methodName = "getObjectList";
		getLogger().trace("{}({}, {}, {}, {})", methodName, criteria,
				firstResult, maxResult, validateForPublishing);

		List<AbstractAlKhwarizmixDomainObject> result = new ArrayList<AbstractAlKhwarizmixDomainObject>();
		final List<AbstractAlKhwarizmixDomainObject> selectedList = getServiceDAO()
				.getList(criteria, firstResult, maxResult);
		if (validateForPublishing)
			validateObjectListToPublish(result, selectedList);
		else
			result = selectedList;

		getLogger().trace("{} return {}", methodName, result);
		return result;
	}

	/**
	 */
	private void validateObjectListToPublish(
			final List<AbstractAlKhwarizmixDomainObject> result,
			final List<AbstractAlKhwarizmixDomainObject> selectedList) {
		for (final AbstractAlKhwarizmixDomainObject obj : selectedList)
			try {
				getServiceValidator().validateObjectToPublish(obj,
						getSessionOwner());
				result.add(obj);
			} catch (final AlKhwarizmixException e) {
				final String methodName = "validateObjectListToPublish";
				getLogger().trace("{}: Validation failure for {}", methodName,
						obj);
			}
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public AbstractAlKhwarizmixDomainObject updateObject(
			final AbstractAlKhwarizmixDomainObject object,
			AlKhwarizmixDomainObject objectOwner,
			final boolean validateForPublishing) throws AlKhwarizmixException {
		getLogger().trace("updateObject({})", object);

		if (objectOwner == null)
			objectOwner = getSessionOwner();
		getServiceValidator().validateObjectToUpdate(object, objectOwner);
		final AbstractAlKhwarizmixDomainObject foundObject = (object
				.getVersion() == null)
				? getObject(object, false)
				: object;
		if (foundObject != null) {
			foundObject.updateFrom(object);
			getServiceDAO().saveOrUpdate(foundObject);
		} else
			throw new AlKhwarizmixException("update1.",
					AlKhwarizmixErrorCode.INVALID_DATA);
		AbstractAlKhwarizmixDomainObject result = foundObject;
		if (validateForPublishing) {
			result = (AbstractAlKhwarizmixDomainObject) result.clone();
			getServiceValidator().validateObjectToPublish(result, objectOwner);
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
				getSessionOwner(), true);
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

		return getXMLUtil().objectListToXML(objectList);
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public final String marshalObjectToXML(
			final AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixException {
		getLogger().trace("marshalObjectToXML({})", object);

		final String result = getXMLUtil().marshalObjectToXML(object);
		return result;
	}

	protected IXMLUtil getXMLUtil() {
		return new XMLUtil(getJaxb2Marshaller());
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public final AbstractAlKhwarizmixDomainObject unmarshalObjectFromXML(
			final String xmlValue) throws AlKhwarizmixException {
		getLogger().trace("unmarshalObjectFromXML({})", xmlValue);

		return getXMLUtil().unmarshalObjectFromXML(xmlValue);
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public String marshalObjectToJSON(
			final AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixException {
		return "";
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
		if (object != null)
			object.setExtendedDataValue(marshalObjectToXML(object));
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

	protected final IAlKhwarizmixSessionData getSessionData() {
		return sessionData;
	}

	protected final void setSessionData(final IAlKhwarizmixSessionData value) {
		sessionData = value;
	}

	/**
	 */
	protected AlKhwarizmixDomainObject getSessionOwner() {
		return getSessionData().getSessionOwner();
	}

} // Class
