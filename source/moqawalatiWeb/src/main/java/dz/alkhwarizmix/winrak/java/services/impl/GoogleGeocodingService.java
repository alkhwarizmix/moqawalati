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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.google.maps.GeoApiContext;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٦ ربيع الاول ١٤٣٧ (January 05, 2016)
 */
@Service
public class GoogleGeocodingService {

	// --------------------------------------------------------------------------
	//
	// Constructors
	//
	// --------------------------------------------------------------------------

	// EMPTY

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	private static Logger logger = null;

	private Logger getLogger() {
		if (logger == null)
			logger = LoggerFactory.getLogger(GoogleGeocodingService.class);
		return logger;
	}

	// --------------------------------------------------------------------------
	//
	// properties
	//
	// --------------------------------------------------------------------------

	private String googleAPIKey;

	private GoogleGeocodingApiWrapper googleGeocodingApiWrapper;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	@Cacheable("google_addresses")
	public String convertPositionToAddress(final Double latitude,
			final Double longitude, final long timeout_ms)
			throws AlKhwarizmixException {
		getLogger().debug("convertPositionToAddress: {}, {}, {}", latitude,
				longitude, timeout_ms);
		final GeoApiContext context = getGoogleGeocodingApiWrapper()
				.getGeoApiContext(googleAPIKey, timeout_ms);
		GeocodingResult[] results;
		results = getGoogleGeocodingApiWrapper().reverseGeocode(context,
				new LatLng(latitude, longitude));
		getLogger().debug("convertPositionToAddress: returns {}",
				results[0].formattedAddress);
		return (results[0].formattedAddress);
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// googleAPIKey
	// ----------------------------------

	public void setGoogleGeocodingApiWrapper(
			final GoogleGeocodingApiWrapper value) {
		googleGeocodingApiWrapper = value;
	}

	public GoogleGeocodingApiWrapper getGoogleGeocodingApiWrapper() {
		if (googleGeocodingApiWrapper == null)
			googleGeocodingApiWrapper = new GoogleGeocodingApiWrapper();
		return googleGeocodingApiWrapper;
	}

	// ----------------------------------
	// googleAPIKey
	// ----------------------------------

	public void setGoogleAPIKey(final String value) {
		googleAPIKey = value;
	}

} // Class
