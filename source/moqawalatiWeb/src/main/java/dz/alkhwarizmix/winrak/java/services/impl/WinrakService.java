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

package dz.alkhwarizmix.winrak.java.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.winrak.java.services.IWinrakService;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٧ ربيع الاول ١٤٣٧ (December 28, 2015)
 */
public class WinrakService implements IWinrakService {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	public WinrakService() {
	}

	// --------------------------------------------------------------------------
	//
	// properties
	//
	// --------------------------------------------------------------------------

	@Autowired
	private GoogleGeoApiWrapper googleGeoApiWrapper;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	@Override
	public String convertPositionToAddress(final Double latitude,
			final Double longitude, final long timeout_ms)
			throws AlKhwarizmixException {
		return googleGeoApiWrapper.convertPositionToAddress(latitude,
				longitude, timeout_ms);
	}

	public String findBook(final int isbn) {
		return String.valueOf(isbn);
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	/**
	 * @param value
	 *            the googleGeoApiWrapper to set
	 */
	public void setGoogleGeoApiWrapper(final GoogleGeoApiWrapper value) {
		googleGeoApiWrapper = value;
	}

} // Class
