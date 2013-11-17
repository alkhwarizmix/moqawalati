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
import dz.alkhwarizmix.framework.java.dao.AlKhwarizmixDAO;
import dz.alkhwarizmix.framework.java.domain.AlKhwarizmixDomainObjectAbstract;
import dz.alkhwarizmix.framework.java.dtos.customize.model.vo.CustomData;
import dz.alkhwarizmix.framework.java.interfaces.ICustomizerService;
import dz.alkhwarizmix.moqawalati.java.MoqawalatiException;
import dz.alkhwarizmix.moqawalati.java.dao.MoqawalatiDAO;

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
		// NOOP
	}

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	private static final Logger LOG = LoggerFactory
			.getLogger(CustomizerService.class);

	protected Logger getLogger() {
		return LOG;
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@Autowired
	private MoqawalatiDAO moqawalatiDAO;

	@Autowired
	private Jaxb2Marshaller jaxb2Marshaller;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	@Transactional(readOnly = false)
	public void setCustomData(CustomData customData) throws MoqawalatiException {
		try {
			CustomData customDataToSave = getCustomData(customData);
			if (customDataToSave != null) {
				customDataToSave.updateFrom(customData);
			} else {
				customDataToSave = customData;
			}
			addObject(customDataToSave);
		} catch (AlKhwarizmixException e) {
			MoqawalatiException ex = new MoqawalatiException(e);
			throw ex;
		}
	}

	/**
	 */
	@Transactional(readOnly = false)
	public String setCustomData(String customDataXml)
			throws MoqawalatiException {
		try {
			CustomData newCustomData = (CustomData) unmarshalObject(customDataXml);
			setCustomData(newCustomData);
			String result = marshalObject(newCustomData);
			return result;
		} catch (AlKhwarizmixException e) {
			getLogger().error("addCustomData: {}", e.getStackTrace());
			MoqawalatiException ex = new MoqawalatiException(e);
			throw ex;
		}
	}

	/**
	 */
	public AlKhwarizmixDomainObjectAbstract getObject(
			AlKhwarizmixDomainObjectAbstract object)
			throws AlKhwarizmixException {
		try {
			CustomData result = getMoqawalatiDAO().getCustomData(
					(CustomData) object);
			return result;
		} catch (AlKhwarizmixException e) {
			throw e;
		}
	}

	/**
	 */
	public CustomData getCustomData(CustomData customData)
			throws MoqawalatiException {
		try {
			return (CustomData) getObject(customData);
		} catch (AlKhwarizmixException e) {
			MoqawalatiException ex = new MoqawalatiException(e);
			throw ex;
		}
	}

	/**
	 */
	public String getCustomDataAsXML(CustomData customData)
			throws MoqawalatiException {
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
	public String getCustomDataAsXML(String customDataXml)
			throws MoqawalatiException {
		try {
			String result = getObjectAsXML(customDataXml);
			return result;
		} catch (AlKhwarizmixException e) {
			MoqawalatiException ex = new MoqawalatiException(e);
			throw ex;
		}
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// moqawalatiDAO
	// ----------------------------------

	protected MoqawalatiDAO getMoqawalatiDAO() {
		return moqawalatiDAO;
	}

	protected void setMoqawalatiDAO(MoqawalatiDAO value) {
		moqawalatiDAO = value;
	}

	protected AlKhwarizmixDAO getServiceDAO() {
		return moqawalatiDAO;
	}

	// ----------------------------------
	// jaxb2Marshaller
	// ----------------------------------

	protected Jaxb2Marshaller getJaxb2Marshaller() {
		return jaxb2Marshaller;
	}

	protected void setJaxb2Marshaller(Jaxb2Marshaller value) {
		jaxb2Marshaller = value;
	}

} // Class