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
import org.springframework.stereotype.Service;

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
@Service
public class WinrakService implements IWinrakService {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	// EMPTY

	// --------------------------------------------------------------------------
	//
	// properties
	//
	// --------------------------------------------------------------------------

	@Autowired
	private GoogleGeocodingService googleGeocodingService;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	@Override
	public String convertPositionToAddress(final Double latitude,
			final Double longitude, final long timeout_ms)
			throws AlKhwarizmixException {
		return googleGeocodingService.convertPositionToAddress(
				roundTo5Decimals(latitude), roundTo5Decimals(longitude),
				timeout_ms);
	}

	private Double roundTo5Decimals(final Double value) {
		final Double factor = Math.pow(10, 5);
		return Math.round(value * factor) / factor;
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	/**
	 * @param value
	 *            the googleGeocodingService to set
	 */
	public void setGoogleGeocodingService(final GoogleGeocodingService value) {
		googleGeocodingService = value;
	}

} // Class
