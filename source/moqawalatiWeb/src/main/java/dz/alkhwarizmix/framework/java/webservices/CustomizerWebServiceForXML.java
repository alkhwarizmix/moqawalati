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

package dz.alkhwarizmix.framework.java.webservices;

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
import dz.alkhwarizmix.framework.java.interfaces.IAlKhwarizmixService;
import dz.alkhwarizmix.framework.java.interfaces.ICustomizerService;

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
public class CustomizerWebServiceForXML extends AlKhwarizmixWebServiceForXML {

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

	private static final Logger LOG = LoggerFactory
			.getLogger(CustomizerWebServiceForXML.class);

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
			@RequestParam("customData") String xmlValue)
			throws AlKhwarizmixException {
		getLogger().trace("addCustomData({})", xmlValue);

		try {
			String result = getCustomizerService().setCustomDataFromXML(
					xmlValue);
			StringBuilder sBuilder = new StringBuilder(result);
			return successResponseForXML(sBuilder);
		} catch (AlKhwarizmixException e) {
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
			@PathVariable("customDataId") String customDataId)
			throws AlKhwarizmixException {
		getLogger().debug("getCustomDataById({})", customDataId);

		try {
			CustomData customDataToGet = new CustomData();
			customDataToGet.setCustomDataId(customDataId);
			StringBuilder sBuilder = new StringBuilder(getCustomizerService()
					.getCustomDataAsXML(customDataToGet));
			return successResponseForXML(sBuilder);
		} catch (AlKhwarizmixException e) {
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

	protected void setCustomizerService(ICustomizerService value) {
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