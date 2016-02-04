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

package dz.alkhwarizmix.framework.java.webservices.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dtos.customize.model.vo.CustomData;
import dz.alkhwarizmix.framework.java.services.IAlKhwarizmixService;
import dz.alkhwarizmix.framework.java.services.ICustomizerService;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٩ محرم ١٤٣٥ (November 13, 2013)
 */
@Controller
@RequestMapping("alkhwarizmix/xml/customize")
public class CustomizerWebServiceForXML extends
		AbstractAlKhwarizmixWebServiceForXML {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public CustomizerWebServiceForXML() {
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
			logger = LoggerFactory.getLogger(CustomizerWebServiceForXML.class);
		return logger;
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@Autowired
	private ICustomizerService customizerService;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 * add the customData to database
	 *
	 * @param xmlValue
	 *            {@link String} the customData as xml
	 * @return {@link ResponseEntity}
	 * @throws AlKhwarizmixException
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> setCustomData(
			@RequestParam("customData") final String xmlValue) {
		getLogger().trace("addCustomData({})", xmlValue);

		try {
			final String result = getCustomizerService().setCustomDataFromXML(
					xmlValue);
			final StringBuilder sBuilder = new StringBuilder(result);
			return successResponseForXML(sBuilder);
		} catch (final Exception e) {
			return errorResponseForXML(e);
		}
	}

	/**
	 * get the customData from database
	 *
	 * @param customDataId
	 *            {@link String} customDataId
	 * @return {@link ResponseEntity}
	 * @throws AlKhwarizmixException
	 */
	@RequestMapping(value = "/{customDataId}", method = RequestMethod.GET)
	public ResponseEntity<String> getCustomDataById(
			@PathVariable("customDataId") final String customDataId) {
		getLogger().debug("getCustomDataById({})", customDataId);

		try {
			final CustomData customDataToGet = new CustomData();
			customDataToGet.setCustomDataId(customDataId);
			final StringBuilder sBuilder = new StringBuilder(
					getCustomizerService().getCustomDataAsXML(customDataToGet));
			return successResponseForXML(sBuilder);
		} catch (final Exception e) {
			return errorResponseForXML(e);
		}
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// customizeService
	// ----------------------------------

	protected ICustomizerService getCustomizerService() {
		return customizerService;
	}

	protected void setCustomizerService(final ICustomizerService value) {
		customizerService = value;
	}

	// ----------------------------------
	// service
	// ----------------------------------

	@Override
	protected IAlKhwarizmixService getService() {
		return customizerService;
	}

} // Class
