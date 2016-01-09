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

package dz.alkhwarizmix.winrak.java.services.impl;

import java.util.concurrent.TimeUnit;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٦ ربيع الاول ١٤٣٧ (January 05, 2016)
 */
public class GoogleGeocodingApiWrapper {

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

	// EMPTY

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	public GeoApiContext getGeoApiContext(final String apiKey,
			final long timeout_ms) {
		final GeoApiContext context = new GeoApiContext().setApiKey(apiKey);
		context.setConnectTimeout(timeout_ms, TimeUnit.MILLISECONDS);
		return context;
	}

	public GeocodingResult[] reverseGeocode(final GeoApiContext context,
			final LatLng location) throws AlKhwarizmixException {
		try {
			return GeocodingApi.reverseGeocode(context, location).await();
		} catch (final Exception e) {
			throw new AlKhwarizmixException(AlKhwarizmixErrorCode.ERROR_WINRAK);
		}
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// EMPTY

} // Class
