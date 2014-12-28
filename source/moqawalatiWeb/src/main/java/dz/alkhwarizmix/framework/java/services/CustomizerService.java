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

package dz.alkhwarizmix.framework.java.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.customize.model.vo.CustomData;
import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.interfaces.IAlKhwarizmixDAO;
import dz.alkhwarizmix.framework.java.interfaces.IAlKhwarizmixServiceValidator;
import dz.alkhwarizmix.framework.java.interfaces.ICustomDataDAO;
import dz.alkhwarizmix.framework.java.interfaces.ICustomizerService;
import dz.alkhwarizmix.framework.java.interfaces.ICustomizerServiceValidator;

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
	public void setCustomData(CustomData customData)
			throws AlKhwarizmixException {
		getLogger().trace("setCustomData");

		CustomData customDataToSave = getCustomData(customData);

		if (customDataToSave != null)
			customDataToSave.updateFrom(customData);
		else
			customDataToSave = customData;

		addObject(customDataToSave);
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = false)
	@Override
	public String setCustomDataFromXML(String customDataXml)
			throws AlKhwarizmixException {
		getLogger().trace("setCustomDataFromXML");

		CustomData newCustomData = (CustomData) unmarshalObjectFromXML(customDataXml);
		setCustomData(newCustomData);
		String result = marshalObjectToXML(newCustomData);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AbstractAlKhwarizmixDomainObject getObject(
			AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixException {
		getLogger().trace("getObject");

		CustomData result = null;
		CustomData customData = (CustomData) object;
		customData.setCustomizer(getSessionCustomizer());
		if (getSessionCustomizer().getId() != null) {
			result = getCustomDataDAO().getCustomData(customData);
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CustomData getCustomData(CustomData customData)
			throws AlKhwarizmixException {
		getLogger().trace("getCustomData");

		CustomData result = (CustomData) getObject(customData);
		/*
		 * if (result == null) { result = new CustomData();
		 * result.setCustomDataId(customData.getCustomDataId());
		 * result.updateFrom(customData); setDefaultCustomDataValue(result); }
		 */
		return result;
	}

	/**
	 * @private
	 */
	private void setDefaultCustomDataValue(CustomData customData) {
		customData.setCustomDataValue("Default");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCustomDataAsXML(CustomData customData)
			throws AlKhwarizmixException {
		getLogger().trace("getCustomDataAsXML 1");

		String result = getObjectAsXML(customData);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCustomDataAsXML(String customDataXml)
			throws AlKhwarizmixException {
		getLogger().trace("getCustomDataAsXML 2");

		String result = getObjectAsXML(customDataXml);
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

	final void setCustomDataDAO(ICustomDataDAO value) {
		customDataDAO = value;
	}

	@Override
	protected IAlKhwarizmixDAO getServiceDAO() {
		return customDataDAO;
	}

	// ----------------------------------
	// clientValidator
	// ----------------------------------

	protected void setServiceValidator(ICustomizerServiceValidator value) {
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
	protected void setJaxb2Marshaller(Jaxb2Marshaller value) {
		jaxb2Marshaller = value;
	}

} // Class
