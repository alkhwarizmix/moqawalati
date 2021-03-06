////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٦ هجري، فارس بلحواس (Copyright 2015 Fares Belhaouas)
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

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dtos.customize.model.vo.CustomData;
import dz.alkhwarizmix.framework.java.services.IAlKhwarizmixService;
import dz.alkhwarizmix.framework.java.services.ICustomizerService;
import dz.alkhwarizmix.framework.java.webservices.ICustomizerWebServiceForBlazeDS;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٤ ربيع الثاني ١٤٣٦ (February 03, 2015)
 */
public class CustomizerWebServiceForBlazeDS implements
		ICustomizerWebServiceForBlazeDS {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public CustomizerWebServiceForBlazeDS() {
		super();
	}

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	private static final Logger LOG = LoggerFactory
			.getLogger(CustomizerWebServiceForBlazeDS.class);

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
	 */
	@Override
	public void setCustomData(final CustomData customData)
			throws AlKhwarizmixException {
		getLogger().debug("setCustomData({})", customData);
		getCustomizerService().setCustomData(customData, true);
	}

	/**
	 */
	@Override
	public CustomData getCustomData(final CustomData customData)
			throws AlKhwarizmixException {
		getLogger().debug("getCustomData({})", customData);
		return getCustomizerService().getCustomData(customData, true);
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// customizerService
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

	protected IAlKhwarizmixService getService() {
		return customizerService;
	}

} // Class
