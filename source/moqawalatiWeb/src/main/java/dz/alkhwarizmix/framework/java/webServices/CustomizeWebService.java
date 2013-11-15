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

package dz.alkhwarizmix.framework.java.webServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dz.alkhwarizmix.framework.java.dtos.customize.model.vo.CustomData;
import dz.alkhwarizmix.framework.java.interfaces.IAlKhwarizmixService;
import dz.alkhwarizmix.framework.java.interfaces.ICustomizeService;
import dz.alkhwarizmix.moqawalati.java.MoqawalatiException;

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
public class CustomizeWebService extends AlKhwarizmixWebService {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public CustomizeWebService() {
		super();
	}

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	private static final Logger LOG = LoggerFactory
			.getLogger(CustomizeWebService.class);

	protected Logger getLogger() {
		return LOG;
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@Autowired
	private ICustomizeService customizeService;

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
	 * @throws MoqawalatiException
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> addCustomData(
			@RequestParam("customData") String xmlValue)
			throws MoqawalatiException {
		LOG.debug("addCustomData({})", xmlValue);

		try {
			String result = customizeService.addCustomData(xmlValue);
			StringBuilder sBuilder = new StringBuilder(result);
			return successResponse(sBuilder);
		} catch (MoqawalatiException e) {
			return errorResponse(e);
		}
	}

	/**
	 * get the customData from database
	 * 
	 * @param customDataId
	 *            {@link Long} customDataId
	 * @return {@link ResponseEntity}
	 * @throws MoqawalatiException
	 */
	@RequestMapping(value = "/{customDataId}", method = RequestMethod.GET)
	public ResponseEntity<String> getCustomDataById(
			@PathVariable("customDataId") String customDataId)
			throws MoqawalatiException {
		LOG.debug("getCustomDataById({})", customDataId);

		try {
			CustomData customDataToGet = new CustomData();
			customDataToGet.setCustomDataId(customDataId);
			StringBuilder sBuilder = new StringBuilder(
					customizeService.getCustomDataAsXML(customDataToGet));
			return successResponse(sBuilder);
		} catch (MoqawalatiException e) {
			return errorResponse(e);
		}
	}

	/**
	 * update the customData in database
	 * 
	 * @param xmlValue
	 *            {@link String} the customData as xml
	 * @return {@link ResponseEntity}
	 * @throws MoqawalatiException
	 */
	@RequestMapping(value = "/{customDataId}", method = RequestMethod.POST)
	public ResponseEntity<String> updateCustomData(
			@PathVariable("customDataId") String customDataId,
			@RequestParam("customData") String xmlValue)
			throws MoqawalatiException {
		LOG.debug("updateCustomData({})", xmlValue);

		try {
			StringBuilder sBuilder = new StringBuilder(
					customizeService.updateCustomData(xmlValue));
			return successResponse(sBuilder);
		} catch (MoqawalatiException e) {
			return errorResponse(e);
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

	protected ICustomizeService getCustomizeService() {
		return customizeService;
	}

	protected void setCustomizeService(ICustomizeService value) {
		customizeService = value;
	}

	// ----------------------------------
	// service
	// ----------------------------------

	protected IAlKhwarizmixService getService() {
		return customizeService;
	}

} // Class