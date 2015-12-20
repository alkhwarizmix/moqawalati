////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٧ هجري، فارس بلحواس (Copyright 2015 Fares Belhaouas)
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
import dz.alkhwarizmix.framework.java.services.IAlKhwarizmixService;
import dz.alkhwarizmix.framework.java.services.IPrototypeService;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٨ ربيع الاول ١٤٣٧ (December 19, 2015)
 */
@Controller
@RequestMapping("alkhwarizmix/json/prototype")
public class PrototypeWebServiceForJSON extends
		AbstractAlKhwarizmixWebServiceForJSON {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public PrototypeWebServiceForJSON() {
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
			logger = LoggerFactory.getLogger(PrototypeWebServiceForJSON.class);
		return logger;
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@Autowired
	private IPrototypeService prototypeService;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> protoPost(
			@RequestParam("param1") final String param1)
			throws AlKhwarizmixException {
		getLogger().trace("protoPost({})", param1);

		try {
			final String result = getPrototypeService().protoPost(param1);
			final StringBuilder sBuilder = new StringBuilder(result);
			return successResponseForJSON(sBuilder);
		} catch (final AlKhwarizmixException e) {
			return errorResponseForJSON(e);
		}
	}

	/**
	 */
	@RequestMapping(value = "/{param1}", method = RequestMethod.GET)
	public ResponseEntity<String> protoGet(
			@PathVariable("param1") final String param1)
			throws AlKhwarizmixException {
		getLogger().debug("protoGet({})", param1);

		try {
			final StringBuilder sBuilder = new StringBuilder(
					getPrototypeService().protoGet(param1));
			return successResponseForJSON(sBuilder);
		} catch (final AlKhwarizmixException e) {
			return errorResponseForJSON(e);
		}
	}

	/**
	 */
	@RequestMapping(value = "/p/{latitude}/{longitude}", method = RequestMethod.GET)
	public ResponseEntity<String> position(
			@PathVariable("latitude") final Double latitude,
			@PathVariable("longitude") final Double longitude)
			throws AlKhwarizmixException {
		getLogger().debug("position({}, {})", latitude, longitude);

		try {
			final StringBuilder sBuilder = new StringBuilder(
					getPrototypeService().position(latitude, longitude));
			return successResponseForJSON(sBuilder);
		} catch (final AlKhwarizmixException e) {
			return errorResponseForJSON(e);
		}
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// prototypeService
	// ----------------------------------

	protected IPrototypeService getPrototypeService() {
		return prototypeService;
	}

	protected void setPrototypeService(final IPrototypeService value) {
		prototypeService = value;
	}

	// ----------------------------------
	// service
	// ----------------------------------

	@Override
	protected IAlKhwarizmixService getService() {
		return prototypeService;
	}

} // Class
