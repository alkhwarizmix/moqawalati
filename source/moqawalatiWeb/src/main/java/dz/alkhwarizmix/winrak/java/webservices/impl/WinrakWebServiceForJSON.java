////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٧ هجري، فارس بلحواس (Copyright 2016 Fares Belhaouas)
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.winrak.java.webservices.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.services.IAlKhwarizmixService;
import dz.alkhwarizmix.framework.java.webservices.impl.AbstractAlKhwarizmixWebServiceForJSON;
import dz.alkhwarizmix.trouvauto.java.model.vo.ReservautoPosition;
import dz.alkhwarizmix.winrak.java.services.IWinrakService;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٣٠ ربيع الاول ١٤٣٧ (January 10, 2016)
 */
@Controller
@RequestMapping("alkhwarizmix/json/winrak")
public class WinrakWebServiceForJSON extends
		AbstractAlKhwarizmixWebServiceForJSON {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public WinrakWebServiceForJSON() {
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
			logger = LoggerFactory.getLogger(WinrakWebServiceForJSON.class);
		return logger;
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@Autowired
	private IWinrakService winrakService;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	@RequestMapping(value = "/trouvauto/{latitude}/{longitude}/{count}", method = RequestMethod.GET)
	public ResponseEntity<String> trouvauto(
			@PathVariable("latitude") final Double latitude,
			@PathVariable("longitude") final Double longitude,
			@PathVariable("count") final int count)
			throws AlKhwarizmixException {
		getLogger().debug("position({}, {}, {})", latitude, longitude, count);
		try {
			final StringBuilder sBuilder = new StringBuilder(getWinrakService()
					.trouvauto(new ReservautoPosition(latitude, longitude),
							count));
			return successResponseForJSON(sBuilder);
		} catch (final Exception e) {
			return errorResponseForJSON(e);
		}
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// winrakService
	// ----------------------------------

	protected IWinrakService getWinrakService() {
		return winrakService;
	}

	protected void setWinrakService(final IWinrakService value) {
		winrakService = value;
	}

	// ----------------------------------
	// service
	// ----------------------------------

	@Override
	protected IAlKhwarizmixService getService() {
		return winrakService;
	}

} // Class
