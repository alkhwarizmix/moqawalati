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

package dz.alkhwarizmix.framework.java.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dao.IAlKhwarizmixDAO;
import dz.alkhwarizmix.framework.java.dao.ICustomDataDAO;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.customize.model.vo.CustomData;
import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.services.IAlKhwarizmixServiceValidator;
import dz.alkhwarizmix.framework.java.services.ICustomizerService;
import dz.alkhwarizmix.framework.java.services.ICustomizerServiceValidator;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٩ محرم ١٤٣٥ (November 13, 2013)
 */
@Service
@Transactional(readOnly = true)
public class CustomizerService extends AbstractAlKhwarizmixService implements
		ICustomizerService {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public CustomizerService() {
		super();
	}

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	private static Logger logger = null;

	@Override
	protected Logger getLogger() {
		if (logger == null)
			logger = LoggerFactory.getLogger(CustomizerService.class);
		return logger;
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@Autowired
	private ICustomDataDAO customDataDAO;

	@Autowired
	private ICustomizerServiceValidator customDataValidator;

	@Autowired
	private Jaxb2Marshaller jaxb2Marshaller;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = false)
	@Override
	public CustomData setCustomData(final CustomData customData,
			final boolean validateObjectToPublish) throws AlKhwarizmixException {
		getLogger().trace("setCustomData");

		CustomData customDataToSave = getCustomData(customData, false);

		if (customDataToSave != null)
			customDataToSave.updateFrom(customData);
		else
			customDataToSave = customData;

		final CustomData result = (CustomData) addObject(customDataToSave,
				validateObjectToPublish);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = false)
	@Override
	public String setCustomDataFromXML(final String customDataXml)
			throws AlKhwarizmixException {
		getLogger().trace("setCustomDataFromXML");

		final CustomData newCustomData = (CustomData) unmarshalObjectFromXML(customDataXml);
		final CustomData addedCustomData = setCustomData(newCustomData, true);
		final String result = marshalObjectToXML(addedCustomData);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AbstractAlKhwarizmixDomainObject getObject(
			final AbstractAlKhwarizmixDomainObject object,
			final boolean validateObjectToPublish) throws AlKhwarizmixException {
		getLogger().trace("getObject");

		CustomData result = null;
		final CustomData customData = (CustomData) object;
		customData.setCustomizer(getSessionCustomizer());
		if (getSessionCustomizer().getId() != null)
			result = getCustomDataDAO().getCustomData(customData);

		if (validateObjectToPublish && (result != null)) {
			result = (CustomData) result.clone();
			getServiceValidator().validateObjectToPublish(result,
					getSessionOwner());
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CustomData getCustomData(final CustomData customData,
			final boolean validateObjectToPublish) throws AlKhwarizmixException {
		getLogger().trace("getCustomData");

		final CustomData result = (CustomData) getObject(customData,
				validateObjectToPublish);
		/*
		 * if (result == null) { result = new CustomData();
		 * result.setCustomDataId(customData.getCustomDataId());
		 * result.updateFrom(customData); setDefaultCustomDataValue(result); }
		 */
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCustomDataAsXML(final CustomData customData)
			throws AlKhwarizmixException {
		getLogger().trace("getCustomDataAsXML 1");

		final String result = getObjectAsXML(customData);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCustomDataAsXML(final String customDataXml)
			throws AlKhwarizmixException {
		getLogger().trace("getCustomDataAsXML 2");

		final String result = getObjectAsXML(customDataXml);
		return result;
	}

	/**
	 * @private
	 */
	private AlKhwarizmixDomainObject getSessionCustomizer() {
		return getSessionData().getSessionOwner();
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// customDataDAO
	// ----------------------------------

	final ICustomDataDAO getCustomDataDAO() {
		return customDataDAO;
	}

	final void setCustomDataDAO(final ICustomDataDAO value) {
		customDataDAO = value;
	}

	@Override
	protected IAlKhwarizmixDAO getServiceDAO() {
		return customDataDAO;
	}

	// ----------------------------------
	// clientValidator
	// ----------------------------------

	protected void setServiceValidator(final ICustomizerServiceValidator value) {
		customDataValidator = value;
	}

	@Override
	protected IAlKhwarizmixServiceValidator getServiceValidator() {
		return customDataValidator;
	}

	// ----------------------------------
	// jaxb2Marshaller
	// ----------------------------------

	@Override
	protected Jaxb2Marshaller getJaxb2Marshaller() {
		return jaxb2Marshaller;
	}

	@Override
	protected void setJaxb2Marshaller(final Jaxb2Marshaller value) {
		jaxb2Marshaller = value;
	}

} // Class
