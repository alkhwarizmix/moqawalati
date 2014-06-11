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
import dz.alkhwarizmix.framework.java.domain.AlKhwarizmixDomainObjectAbstract;
import dz.alkhwarizmix.framework.java.dtos.customize.model.vo.CustomData;
import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.interfaces.IAlKhwarizmixDAO;
import dz.alkhwarizmix.framework.java.interfaces.ICustomizerService;
import dz.alkhwarizmix.framework.java.model.AlKhwarizmixSessionData;
import dz.alkhwarizmix.moqawalati.java.MoqawalatiException;
import dz.alkhwarizmix.moqawalati.java.interfaces.ICustomDataDAO;

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
public class CustomizerService extends AlKhwarizmixService implements
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

	private static final Logger LOG = LoggerFactory
			.getLogger(CustomizerService.class);

	@Override
	protected Logger getLogger() {
		return LOG;
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@Autowired
	private ICustomDataDAO customDataDAO;

	@Autowired
	private Jaxb2Marshaller jaxb2Marshaller;

	@Autowired
	private AlKhwarizmixSessionData sessionData;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	@Transactional(readOnly = false)
	@Override
	public void setCustomData(CustomData customData) throws MoqawalatiException {
		getLogger().trace("setCustomData");

		try {
			CustomData customDataToSave = getCustomData(customData);

			if (customDataToSave != null)
				customDataToSave.updateFrom(customData);
			else
				customDataToSave = customData;

			addObject(customDataToSave);
		} catch (AlKhwarizmixException e) {
			MoqawalatiException ex = new MoqawalatiException(e);
			throw ex;
		}
	}

	/**
	 */
	@Transactional(readOnly = false)
	@Override
	public String setCustomDataFromXML(String customDataXml)
			throws MoqawalatiException {
		getLogger().trace("setCustomDataFromXML");

		try {
			CustomData newCustomData = (CustomData) unmarshalObjectFromXML(customDataXml);
			setCustomData(newCustomData);
			String result = marshalObjectToXML(newCustomData);
			return result;
		} catch (AlKhwarizmixException e) {
			getLogger().error("addCustomData: {}", e.getStackTrace());
			MoqawalatiException ex = new MoqawalatiException(e);
			throw ex;
		}
	}

	/**
	 */
	@Override
	public AlKhwarizmixDomainObjectAbstract getObject(
			AlKhwarizmixDomainObjectAbstract object)
			throws AlKhwarizmixException {
		getLogger().trace("getObject");

		try {
			CustomData result = null;
			CustomData customData = (CustomData) object;
			customData.setCustomizer(getSessionCustomizer());
			if (getSessionCustomizer().getId() != null) {
				result = getCustomDataDAO().getCustomData(customData);
			}
			return result;
		} catch (AlKhwarizmixException e) {
			throw new MoqawalatiException(e);
		}
	}

	/**
	 */
	@Override
	public CustomData getCustomData(CustomData customData)
			throws MoqawalatiException {
		getLogger().trace("getCustomData");

		try {
			CustomData result = (CustomData) getObject(customData);
			/*
			 * if (result == null) { result = new CustomData();
			 * result.setCustomDataId(customData.getCustomDataId());
			 * result.updateFrom(customData); setDefaultCustomDataValue(result);
			 * }
			 */
			return result;
		} catch (AlKhwarizmixException e) {
			throw new MoqawalatiException(e);
		}
	}

	/**
	 * @private
	 */
	private void setDefaultCustomDataValue(CustomData customData) {
		customData.setCustomDataValue("Default");
	}

	/**
	 */
	@Override
	public String getCustomDataAsXML(CustomData customData)
			throws MoqawalatiException {
		getLogger().trace("getCustomDataAsXML 1");

		try {
			String result = getObjectAsXML(customData);
			return result;
		} catch (AlKhwarizmixException e) {
			MoqawalatiException ex = new MoqawalatiException(e);
			throw ex;
		}
	}

	/**
	 */
	@Override
	public String getCustomDataAsXML(String customDataXml)
			throws MoqawalatiException {
		getLogger().trace("getCustomDataAsXML 2");

		try {
			String result = getObjectAsXML(customDataXml);
			return result;
		} catch (AlKhwarizmixException e) {
			MoqawalatiException ex = new MoqawalatiException(e);
			throw ex;
		}
	}

	/**
	 */
	private AlKhwarizmixDomainObject getSessionCustomizer() {
		return getSessionData().getCustomizer();
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

	// ----------------------------------
	// sessionData
	// ----------------------------------

	final AlKhwarizmixSessionData getSessionData() {
		return sessionData;
	}

	final void setSessionData(AlKhwarizmixSessionData value) {
		sessionData = value;
	}

} // Class